package org.ocha.hdx.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.ocha.hdx.dto.apiv3.DatasetListV3DTO;
import org.ocha.hdx.dto.apiv3.DatasetV3DTO;
import org.ocha.hdx.dto.apiv3.DatasetV3DTO.Resource;
import org.ocha.hdx.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.hdx.dto.apiv3.GroupListV3DTO;
import org.ocha.hdx.dto.apiv3.GroupV3DTO;
import org.ocha.hdx.dto.apiv3.GroupV3WrapperDTO;
import org.ocha.hdx.dto.apiv3.ResourceCreateQuery;
import org.ocha.hdx.dto.apiv3.ResourceV3WrapperDTO;
import org.ocha.hdx.importer.report.ImportReport;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.dao.UserDAO;
import org.ocha.hdx.persistence.dao.ckan.CKANDatasetDAO;
import org.ocha.hdx.persistence.dao.ckan.CKANResourceDAO;
import org.ocha.hdx.persistence.dao.config.ResourceConfigurationDAO;
import org.ocha.hdx.persistence.dao.i18n.LanguageDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAO;
import org.ocha.hdx.persistence.entity.User;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.ckan.CKANResource;
import org.ocha.hdx.persistence.entity.ckan.CKANResource.WorkflowState;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.i18n.Language;
import org.ocha.hdx.security.exception.AuthenticationException;
import org.ocha.hdx.security.exception.InsufficientCredentialsException;
import org.ocha.hdx.tools.GSONBuilderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.gson.JsonObject;

public class HDXServiceImpl implements HDXService {

	private static final Logger log = LoggerFactory.getLogger(HDXServiceImpl.class);

	private static String DATASET_LIST_V3_API_PATTERN = "http://%s/api/3/action/package_list";
	private static String GROUP_LIST_V3_API_PATTERN = "http://%s/api/3/action/group_list";
	private static String GROUP_V3_API_PATTERN = "http://%s/api/3/action/group_show?id=";
	private static String DATASET_V3_API_PATTERN = "http://%s/api/3/action/package_show?id=";
	private static String RESOURCE_CREATE_V3_API_PATTERN = "http://%s/api/3/action/resource_create";
	private static String RESOURCE_SHOW_V3_API_PATTERN = "http://%s/api/3/action/resource_show?id=";

	private final String urlBaseForDatasetsList;
	private final String urlBaseForGroupsList;
	private final String urlBaseForDatasetContentV3;
	private final String urlBaseForGroupContentV3;
	private final String urlBaseForResourceCreation;
	private final String urlBaseForResourceShow;
	private final String technicalAPIKey;

	private final File stagingDirectory;

	public HDXServiceImpl(final String host, final String technicalAPIKey, final File stagingDirectory) {
		super();
		if (!stagingDirectory.isDirectory()) {
			throw new IllegalArgumentException("staging  directory doesn't exist: " + stagingDirectory.getAbsolutePath());
		}

		this.stagingDirectory = stagingDirectory;

		this.urlBaseForDatasetsList = String.format(DATASET_LIST_V3_API_PATTERN, host);
		this.urlBaseForGroupsList = String.format(GROUP_LIST_V3_API_PATTERN, host);
		this.urlBaseForDatasetContentV3 = String.format(DATASET_V3_API_PATTERN, host);
		this.urlBaseForGroupContentV3 = String.format(GROUP_V3_API_PATTERN, host);
		this.urlBaseForResourceCreation = String.format(RESOURCE_CREATE_V3_API_PATTERN, host);
		this.urlBaseForResourceShow = String.format(RESOURCE_SHOW_V3_API_PATTERN, host);
		this.technicalAPIKey = technicalAPIKey;
	}

	@Autowired
	private UserDAO userDao;

	@Autowired
	private LanguageDAO languageDao;

	@Autowired
	private TextDAO textDao;

	@Autowired
	private CKANResourceDAO resourceDAO;

	@Autowired
	private CKANDatasetDAO datasetDAO;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private MailService mailService;

	@Autowired
	private FileEvaluatorAndExtractor fileEvaluatorAndExtractor;

	@Autowired
	private ResourceConfigurationDAO resourceConfigurationDAO;

	@Autowired
	private DataSerieMetadataDAO dataSerieMetadataDAO;

	@Override
	public boolean addResourceToCKANDataset(final String packageId, final String resourceUrl, final String name) {
		final ResourceCreateQuery resourceCreateQuery = new ResourceCreateQuery();
		resourceCreateQuery.setPackage_id(packageId);
		resourceCreateQuery.setUrl(resourceUrl);
		resourceCreateQuery.setName(name);
		final String result = this.performHttpPOST(this.urlBaseForResourceCreation, this.technicalAPIKey, GSONBuilderWrapper.getGSON().toJson(resourceCreateQuery));

		final JsonObject res = GSONBuilderWrapper.getGSON().fromJson(result, JsonObject.class);

		log.debug(res.toString());
		return res.get("success").getAsBoolean();

	}

	// @Override
	// public boolean addResourceToCKANDataset(final String packageId, final File file) {
	// final String result = performHttpPOSTMultipart(urlBaseForResourceCreation, technicalAPIKey, packageId, file);
	// final JsonObject res = GSONBuilderWrapper.getGSON().fromJson(result, JsonObject.class);
	//
	// return res.get("success").getAsBoolean();
	// }

	@Override
	public void checkForNewCKANDatasets() {
		final List<DatasetV3DTO> datasetV3DTOList = this.getDatasetV3DTOsFromQuery(this.technicalAPIKey);
		this.datasetDAO.importDetectedDatasetsIfNotPresent(datasetV3DTOList);
	}

	@Override
	@Transactional
	public void addNewCKANResource(final String resourceId, final String resourceName, final long resourceConfigurationId, final InputStream resourceFile) throws IOException {

		final ResourceConfiguration config = this.resourceConfigurationDAO.getResourceConfigurationById(resourceConfigurationId);

		this.resourceDAO.newCKANResourceDetected(resourceId, resourceId, CKANDataset.Type.MANUAL, resourceName, new Date(), CKANDataset.MANUAL_UPLOAD, CKANDataset.MANUAL_UPLOAD, CKANDataset.MANUAL_UPLOAD,
				new Date(), config);

		this.writeResourceFileFromInputStream(resourceName, resourceName, resourceFile);

		this.resourceDAO.flagCKANResourceAsDownloaded(resourceName, resourceName);

	}

	@Override
	public void checkForNewCKANResources() {
		// final List<String> datasetList = getDatasetNamesFromQuery(technicalAPIKey);
		final Map<String, CKANDataset> datasetToBeCuratedMap = this.datasetDAO.listToBeCuratedCKANDatasets();
		// for (final String datasetName : datasetList) {
		for (final Entry<String, CKANDataset> entry : datasetToBeCuratedMap.entrySet()) {
			final String datasetName = entry.getKey();
			final CKANDataset ckanDataset = entry.getValue();
			if (ckanDataset != null) {
				final DatasetV3WrapperDTO dataset = this.getDatasetDTOFromQueryV3(datasetName, this.technicalAPIKey);

				if (dataset != null && dataset.getResult() != null && dataset.getResult().getResources() != null) {
					final List<Resource> resources = dataset.getResult().getResources();
					for (final Resource resource : resources) {
						// if the same id/revisionId is already present, do nothing,
						// this has already been processed
						if (this.resourceDAO.getCKANResource(resource.getId(), resource.getRevision_id()) == null) {
							log.debug(String.format("Could not find resource for id : %s, revisionId : %s", resource.getId(), resource.getRevision_id()));
							// If some revisions were detected before, but were not
							// processed yet, (i.e a revision was uploaded in the
							// mean time) we mark them as outdated
							final List<CKANResource> ckanResources = this.resourceDAO.listCKANResourceRevisions(resource.getId());
							for (final CKANResource ckanResource : ckanResources) {
								this.workflowService.flagCKANResourceAsOutdated(ckanResource.getId().getId(), ckanResource.getId().getRevision_id());
							}

							this.resourceDAO.newCKANResourceDetected(resource.getId(), resource.getRevision_id(), ckanDataset.getType(), resource.getName(), resource.getRevision_timestamp(), datasetName,
									dataset.getResult().getId(), dataset.getResult().getRevision_id(), dataset.getResult().getRevision_timestamp(), ckanDataset.getConfiguration());
						}
					}
				}
			}
		}
	}

	@Override
	public List<String> getCKANGroupNames() {
		List<String> grps = new ArrayList<String>();

		final String jsonResult = this.performHttpGET(this.urlBaseForGroupsList, this.technicalAPIKey);
		if (jsonResult == null) {
			log.warn(String.format("Got null result from %s", this.urlBaseForGroupsList));
		} else {
			final GroupListV3DTO returnedValue = GSONBuilderWrapper.getGSON().fromJson(jsonResult, GroupListV3DTO.class);
			grps = returnedValue.getResult();
		}

		return grps;
	}

	@Override
	public List<GroupV3DTO> getCKANGroups(final List<String> groups) {
		final List<GroupV3DTO> grps = new ArrayList<GroupV3DTO>();

		for (final String grp : groups) {
			final String urlForGroup = String.format("%s%s", this.urlBaseForGroupContentV3, grp);
			final String jsonResult = this.performHttpGET(urlForGroup, this.technicalAPIKey);
			if (jsonResult == null) {
				log.warn(String.format("Got null result from %s", this.urlBaseForGroupContentV3));
			} else {
				final GroupV3WrapperDTO returnedValue = GSONBuilderWrapper.getGSON().fromJson(jsonResult, GroupV3WrapperDTO.class);
				grps.add(returnedValue.getResult());
			}
		}
		return grps;
	}

	@Override
	public DatasetV3DTO getDatasetContent(final String name) {
		DatasetV3DTO result = null;
		final String urlForDataset = String.format("%s%s", this.urlBaseForDatasetContentV3, name);
		final String jsonResult = this.performHttpGET(urlForDataset, this.technicalAPIKey);
		if (jsonResult == null) {
			log.warn(String.format("Got null result from %s", this.urlBaseForDatasetContentV3));
		} else {
			final DatasetV3WrapperDTO returnedValue = GSONBuilderWrapper.getGSON().fromJson(jsonResult, DatasetV3WrapperDTO.class);
			result = returnedValue.getResult();
		}
		return result;
	}

	@Override
	public List<CKANResource> listCKANResources() {
		return this.resourceDAO.listCKANResources();
	}

	@Override
	public List<CKANDataset> listCKANDatasets() {
		return this.datasetDAO.listCKANDatasets();
	}

	@Override
	public void flagDatasetAsToBeCurated(final String datasetName, final Type type, final long configurationId) {
		final ResourceConfiguration configuration = this.resourceConfigurationDAO.getResourceConfigurationById(configurationId);
		this.datasetDAO.flagDatasetAsToBeCurated(datasetName, type, configuration);

	}

	@Override
	public void flagDatasetAsIgnored(final String datasetName) {
		this.datasetDAO.flagDatasetAsIgnored(datasetName);
	}

	@Override
	public void updateDataset(final String datasetName, final String importer, final Long configurationId) {
		this.datasetDAO.updateDataset(datasetName, importer, configurationId);
	}

	private void writeResourceFileFromInputStream(final String id, final String revision_id, final InputStream resourceFile) throws IOException {
		final File destinationFile = this.getLocalFileFromResourceIdAndRevisionId(id, revision_id);
		FileUtils.writeByteArrayToFile(destinationFile, IOUtils.toByteArray(resourceFile));
	}

	@Override
	@Transactional
	public void downloadFileForCKANResource(final String id, final String revision_id) throws IOException {
		final File destinationFile = this.getLocalFileFromResourceIdAndRevisionId(id, revision_id);
		final URL url = this.getResourceUrlFromAPIDirectly(id, revision_id);

		if (!this.workflowService.flagCKANResourceAsDownloaded(id, revision_id)) {
			return;
		}

		// if we can't download the file, the flag will be rolled back
		final boolean success = this.performDownload(url, destinationFile);
		if (!success) {
			throw new RuntimeException("Failed downloading the given resource from url: " + url);
		}

	}

	@Override
	public void evaluateFileForCKANResource(final String id, final String revision_id) throws IOException {
		final File destinationFile = this.getLocalFileFromResourceIdAndRevisionId(id, revision_id);

		final CKANDataset.Type type = this.getTypeForFile(id, revision_id);
		final ValidationReport report = this.fileEvaluatorAndExtractor.evaluateResource(destinationFile, type);

		if (report.isNotInError()) {
			this.workflowService.flagCKANResourceAsFilePreValidationSuccess(id, revision_id, report);
		} else {
			this.workflowService.flagCKANResourceAsFilePreValidationFail(id, revision_id, report);
			this.mailService.sendMailForResourceEvaluationFailure(id, revision_id, report);
		}

	}

	@Override
	public void transformAndImportDataFromFileForCKANResource(final String id, final String revision_id) {
		if (!this.workflowService.nextStateIsPossible(id, revision_id, WorkflowState.IMPORTING)) {
			return;
		}
		this.workflowService.flagCKANResourceAsImporting(id, revision_id);

		final File destinationFile = this.getLocalFileFromResourceIdAndRevisionId(id, revision_id);

		final CKANDataset.Type type = this.getTypeForFile(id, revision_id);

		ValidationReport validationReport = this.workflowService.readValidationReport(id, revision_id);

		if (validationReport == null) {
			// Not very likely to happen, but it is possible to have validation only during the import
			validationReport = new ValidationReport(type);
		}

		final ResourceConfiguration config = this.getResourceConfigFromResourceIdAndRevisionId(id, revision_id);
		final ImportReport importReport = this.fileEvaluatorAndExtractor.transformAndImportDataFromResource(destinationFile, type, id, revision_id, config, validationReport);

		if (importReport.isSuccess()) {
			this.workflowService.flagCKANResourceAsImportSuccess(id, revision_id, type, validationReport, importReport);
		} else {
			this.workflowService.flagCKANResourceAsImportFail(id, revision_id, type, validationReport, importReport);
			this.mailService.sendMailForResourceImportFailure(id, revision_id, importReport);
		}
	}

	/**
	 *
	 * @return true if the file was successfully downloaded
	 */
	private boolean performDownload(final URL url, final File destinationFile) throws IOException {
		// if the resource does not exist anymore in CKAN
		if (url == null) {
			return false;
		}

		HttpURLConnection uCon = (HttpURLConnection) url.openConnection();
		final int httpStatus = uCon.getResponseCode();
		if (httpStatus == HttpURLConnection.HTTP_MOVED_TEMP || httpStatus == HttpURLConnection.HTTP_MOVED_PERM || httpStatus == HttpURLConnection.HTTP_SEE_OTHER) {
			final String newUrlStr = uCon.getHeaderField("Location");
			uCon = (HttpURLConnection) new URL(newUrlStr).openConnection();
		}

		final byte[] buf = new byte[1024];
		int byteRead = 0;
		FileOutputStream fos = null;
		try {
			final InputStream is = uCon.getInputStream();
			destinationFile.getParentFile().mkdirs();
			fos = new FileOutputStream(destinationFile);

			while ((byteRead = is.read(buf)) != -1) {
				fos.write(buf, 0, byteRead);
			}

			return true;
		} catch (final Exception e) {
			log.error(e.toString(), e);
			return false;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}

	private File getLocalFileFromResourceIdAndRevisionId(final String id, final String revision_id) {
		final String fileName = this.resourceDAO.getCKANResource(id, revision_id).getName();

		final File resourceFolder = new File(this.stagingDirectory, id);
		final File revisionFolder = new File(resourceFolder, revision_id);
		return new File(revisionFolder, fileName);
	}

	@Transactional
	private ResourceConfiguration getResourceConfigFromResourceIdAndRevisionId(final String id, final String revision_id) {
		final CKANResource resource = this.resourceDAO.getCKANResource(id, revision_id);
		final ResourceConfiguration config = resource.getResourceConfiguration();
		if ((config != null) && ((config.getGeneralConfigEntries() != null) || (config.getIndicatorConfigEntries() != null))) {
			return config;
		} else {
			return null;
		}
	}

	/**
	 *
	 * The url might change, while ids cannot, so it is best to get the url from the api (just in time), and never store it
	 *
	 * Up to now, this requires a very inefficient browsing of the whole tree of datasets and resources
	 *
	 * @throws MalformedURLException
	 * @Deprecated Use {@link #getResourceUrlFromAPIDirectly(String, String)} instead
	 */
	@Deprecated
	private URL getResourceURLFromAPI(final String id, final String revision_id) throws MalformedURLException {
		final List<String> datasetList = this.getDatasetNamesFromQuery(this.technicalAPIKey);
		for (final String datasetName : datasetList) {
			final DatasetV3WrapperDTO dataset = this.getDatasetDTOFromQueryV3(datasetName, this.technicalAPIKey);
			final List<Resource> resources = dataset.getResult().getResources();
			for (final Resource resource : resources) {
				if (resource.getId().equals(id) && resource.getRevision_id().equals(revision_id)) {
					return new URL(resource.getUrl());
				}
			}
		}
		return null;
	}

	/**
	 * This uses directly the CKAN API call 'resource_show'
	 * @param id
	 * @param revision_id
	 * @return the url of the resource
	 * @throws MalformedURLException
	 */
	private URL getResourceUrlFromAPIDirectly(final String id, final String revision_id) throws MalformedURLException {
		final ResourceV3WrapperDTO resourceWrapper = this.getResourceDTOFromQueryV3(id, this.technicalAPIKey);
		final Resource resource = resourceWrapper.getResult();
		if ( resource != null && resource.getId().equals(id) && resource.getRevision_id().equals(revision_id) ) {
			return new URL(resource.getUrl());
		}
		return null;
	}

	@Override
	public List<String> getDatasetsListFromCKAN(final String userId) throws InsufficientCredentialsException {
		final String apiKey = this.userDao.getUserApiKey(userId);
		return this.getDatasetNamesFromQuery(apiKey);
	}

	// @Override
	// public DatasetV3WrapperDTO getDatasetContentFromCKANV3(final String userId, final String datasetName) throws InsufficientCredentialsException {
	// final String apiKey = userDao.getUserApiKey(userId);
	//
	// return getDatasetDTOFromQueryV3(datasetName, apiKey);
	//
	// }

	List<DatasetV3DTO> getDatasetV3DTOsFromQuery(final String apiKey) {
		final List<String> names = this.getDatasetNamesFromQuery(apiKey);
		final List<DatasetV3DTO> result = new ArrayList<>();

		for (final String name : names) {
			result.add(this.getDatasetDTOFromQueryV3(name, apiKey).getResult());
		}
		return result;
	}

	List<String> getDatasetNamesFromQuery(final String apiKey) {
		final String jsonResult = this.performHttpGET(this.urlBaseForDatasetsList, apiKey);
		if (jsonResult == null) {
			log.warn(String.format("Got null result from %s", this.urlBaseForDatasetsList));
			return new ArrayList<String>();
		} else {
			final DatasetListV3DTO returnedValue = GSONBuilderWrapper.getGSON().fromJson(jsonResult, DatasetListV3DTO.class);
			return returnedValue.getResult();
		}
	}

	@Override
	public boolean authenticate(final String id, final String password) throws AuthenticationException {
		return this.userDao.authenticate(id, password);
	}

	@Override
	public User getUserById(final String userId) {
		return this.userDao.getUserById(userId);
	}

	@Override
	public DatasetV3WrapperDTO getDatasetDTOFromQueryV3(final String datasetName, final String apiKey) {
		final String urlForDataSet = String.format("%s%s", this.urlBaseForDatasetContentV3, datasetName);
		log.debug(String.format("About to call url : %s", urlForDataSet));
		final String jsonResult = this.performHttpGET(urlForDataSet, apiKey);
		if (jsonResult == null) {
			return null;
		} else {

			return GSONBuilderWrapper.getGSON().fromJson(jsonResult, DatasetV3WrapperDTO.class);
		}
	}

	private ResourceV3WrapperDTO getResourceDTOFromQueryV3(final String resourceId, final String apiKey) {
		final String urlForResource = String.format("%s%s", this.urlBaseForResourceShow, resourceId);
		log.debug(String.format("About to call url : %s", urlForResource));
		final String jsonResult = this.performHttpGET(urlForResource, apiKey);
		if (jsonResult == null) {
			return null;
		} else {
			return GSONBuilderWrapper.getGSON().fromJson(jsonResult, ResourceV3WrapperDTO.class);
		}
	}

	private String performHttpGET(final String url, final String apiKey) {
		String responseBody = null;

		final HttpGet httpGet = new HttpGet(url);
		try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build()) {
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("accept", "application/json");

			if (apiKey != null) {
				httpGet.addHeader("X-CKAN-API-Key", apiKey);
			}

			final ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = closeableHttpClient.execute(httpGet, responseHandler);
		} catch (final Exception e) {
			log.debug(e.toString(), e);
		}

		return responseBody;

	}

	private String performHttpPOST(final String url, final String apiKey, final String query) {
		log.debug(String.format("About to post on : %s", url));
		String responseBody = null;

		final HttpPost httpPost = new HttpPost(url);
		try (CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build()) {

			final StringEntity se = new StringEntity(query);
			httpPost.setEntity(se);

			// se.setContentType("text/xml");
			httpPost.addHeader("Content-Type", "application/json");
			httpPost.addHeader("accept", "application/json");

			if (apiKey != null) {
				httpPost.addHeader("X-CKAN-API-Key", apiKey);
			}

			// log.debug("about to send query: " + query);

			final ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = closeableHttpClient.execute(httpPost, responseHandler);
		} catch (final Exception e) {
			log.debug(e.toString(), e);
		}
		return responseBody;
	}

	private String performHttpPOSTMultipart(final String url, final String apiKey, final String packageId, final File file) {
		String responseBody = null;
		final CloseableHttpClient httpclient = HttpClients.createDefault();

		final HttpPost httpPost = new HttpPost(url);
		try {

			// se.setContentType("text/xml");

			// This does not work yet. CKAN complains if boundary is not set
			// but the content-Type should be exactly multipart/form-data !!
			httpPost.addHeader("Content-Type", "multipart/form-data");
			// httpPost.addHeader("Content-Type", "multipart/form-data; boundary=nwxUuePw4tNxnJqfcLQem2PLZJFBQS");
			httpPost.addHeader("accept", "application/json");

			if (apiKey != null) {
				httpPost.addHeader("X-CKAN-API-Key", apiKey);
			}

			final FileBody bin = new FileBody(file);
			final StringBody package_id = new StringBody(packageId, ContentType.TEXT_PLAIN);

			final HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("upload", bin).addPart("package_id", package_id).build();
			// final HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("package_id", package_id).build();

			httpPost.setEntity(reqEntity);

			System.out.println("executing request " + httpPost.getRequestLine());
			final CloseableHttpResponse response = httpclient.execute(httpPost);
			final HttpEntity resEntity = response.getEntity();
			responseBody = EntityUtils.toString(resEntity);
		} catch (final Exception e) {
			e.printStackTrace();
			log.debug(e.toString(), e);
		}
		return responseBody;
	}

	/**
	 * In order to evaluate a file, we must know its type (to use the appropriate evaluator The Type is defined on the Dataset level)
	 *
	 */
	private Type getTypeForFile(final String id, final String revision_id) {
		final CKANResource ckanResource = this.resourceDAO.getCKANResource(id, revision_id);
		return ckanResource.getType();
	}

	@Override
	public CKANResource getCKANResource(final String id, final String revision_id) {
		return this.resourceDAO.getCKANResource(id, revision_id);
	}

	@Override
	public List<User> listUsers() {
		return this.userDao.listUsers();
	}

	@Override
	public List<String> listRoles() {
		return this.userDao.listRoles();
	}

	@Override
	public void createUser(final String id, final String password, final String role, final String apiKey) throws Exception {
		this.userDao.createUser(id, password, role, apiKey);

	}

	@Override
	public void updateUser(final String id, final String password, final String role, final String apiKey) throws Exception {
		this.userDao.updateUser(id, password, role, apiKey);

	}

	@Override
	public void deleteUser(final String id) throws Exception {
		this.userDao.deleteUser(id);

	}

	/*
	 * Languages management
	 */

	@Override
	public List<Language> listLanguages() {
		return this.languageDao.listLanguages();
	}

	@Override
	public void createLanguage(final String code, final String nativeName) throws Exception {
		this.languageDao.createLanguage(code, nativeName);
	}

	@Override
	public void updateLanguage(final String code, final String nativeName) throws Exception {
		this.languageDao.updateLanguage(code, nativeName);
	}

	@Override
	public void deleteLanguage(final String code) throws Exception {
		this.languageDao.deleteLanguage(code);
	}

	/*
	 * Configurations management
	 */

	@Override
	public List<ResourceConfiguration> listConfigurations() {
		return this.resourceConfigurationDAO.listResourceConfigurations();
	}

	@Override
	public ResourceConfiguration createResourceConfiguration(final String name) throws Exception {
		return this.resourceConfigurationDAO.createResourceConfiguration(name, null, null);
	}

	@Override
	public ResourceConfiguration createResourceConfiguration(final String name, final Set<ResourceConfigEntry> generalConfigList, final Set<IndicatorResourceConfigEntry> indicatorConfigList)
			throws Exception {
		return this.resourceConfigurationDAO.createResourceConfiguration(name, generalConfigList, indicatorConfigList);
	}

	@Override
	public void updateResourceConfiguration(final long id, final String name) {
		// public void updateLanguage(final String code, final String nativeName) throws Exception {
		this.resourceConfigurationDAO.updateResourceConfiguration(id, name, null, null);
	}

	@Override
	public void updateResourceConfiguration(final long id, final String name, final Set<ResourceConfigEntry> generalConfigList, final Set<IndicatorResourceConfigEntry> indicatorConfigList) {
		// public void updateLanguage(final String code, final String nativeName) throws Exception {
		this.resourceConfigurationDAO.updateResourceConfiguration(id, name, generalConfigList, indicatorConfigList);
	}

	@Override
	public void deleteResourceConfiguration(final long id) throws Exception {
		this.resourceConfigurationDAO.deleteResourceConfiguration(id);
	}

	@Override
	public ResourceConfiguration getResourceConfiguration(final long id) throws Exception {
		return this.resourceConfigurationDAO.getResourceConfigurationById(id);
	}

	@Override
	public void addGeneralConfiguration(final long id, final String key, final String value) throws Exception {
		this.resourceConfigurationDAO.addGeneralConfiguration(id, key, value);
	}

	@Override
	public void deleteGeneralConfiguration(final long rcID, final long id) throws Exception {
		this.resourceConfigurationDAO.deleteGeneralConfiguration(rcID, id);
	}

	@Override
	public void updateGeneralConfiguration(final long id, final String key, final String value) throws Exception {
		this.resourceConfigurationDAO.updateGeneralConfiguration(id, key, value);
	}

	@Override
	public void addIndicatorConfiguration(final long rcID, final long itID, final long srcID, final String key, final String value) throws Exception {
		this.resourceConfigurationDAO.addIndicatorConfiguration(rcID, itID, srcID, key, value);
	}

	@Override
	public void deleteIndicatorConfiguration(final long rcID, final long id) throws Exception {
		this.resourceConfigurationDAO.deleteIndicatorConfiguration(rcID, id);
	}

	@Override
	public void deleteAllIndicatorConfigurations(final long rcID) throws Exception {
		this.resourceConfigurationDAO.deleteAllIndicatorConfigurations(rcID);
	}

	@Override
	public void updateIndicatorConfiguration(final long id, final long indTypeID, final long srcID, final String key, final String value) throws Exception {
		this.resourceConfigurationDAO.updateIndicatorConfiguration(id, indTypeID, srcID, key, value);
	}

	@Override
	public File exportDataSeriesConfiguration_CSV(final Long id) throws Exception {
		final ResourceConfiguration configuration = this.resourceConfigurationDAO.getResourceConfigurationById(id);
		final Set<IndicatorResourceConfigEntry> indicatorConfigEntries = configuration.getIndicatorConfigEntries();
		final List<String[]> content = new ArrayList<>();
		if (null != indicatorConfigEntries) {
			for (final IndicatorResourceConfigEntry indicatorResourceConfigEntry : indicatorConfigEntries) {
				final String[] line = new String[4];
				line[0] = indicatorResourceConfigEntry.getIndicatorType().getCode();
				line[1] = indicatorResourceConfigEntry.getSource().getCode();
				line[2] = indicatorResourceConfigEntry.getEntryKey();
				line[3] = indicatorResourceConfigEntry.getEntryValue();

				content.add(line);
			}
		}
		// Export the data in a new file
		final File file = File.createTempFile("Config_" + new Date().getTime() + "_", ".csv");
		final CSVWriter csvWriter = new CSVWriter(new FileWriter(file), '#');
		csvWriter.writeAll(content);
		csvWriter.close();

		// Return the workbook
		return file;
	}

	/*
	 * Translations management
	 */

	@Override
	public void createTranslation(final long textId, final String languageCode, final String translationValue) {
		this.textDao.createTranslationForText(textId, languageCode, translationValue);
	}

	@Override
	public void deleteTranslation(final long textId, final String languageCode) throws Exception {
		this.textDao.deleteTranslation(textId, languageCode);
	}

	@Override
	public void updateTranslation(final long textId, final String languageCode, final String translationValue) throws Exception {
		this.textDao.updateTranslation(textId, languageCode, translationValue);
	}

}
