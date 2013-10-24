package org.ocha.dap.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ocha.dap.dto.apiv2.DatasetV2DTO;
import org.ocha.dap.dto.apiv3.DatasetListV3DTO;
import org.ocha.dap.dto.apiv3.DatasetV3DTO;
import org.ocha.dap.dto.apiv3.DatasetV3DTO.Resource;
import org.ocha.dap.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.dap.persistence.dao.CKANDatasetDAO;
import org.ocha.dap.persistence.dao.CKANResourceDAO;
import org.ocha.dap.persistence.dao.UserDAO;
import org.ocha.dap.persistence.entity.CKANDataset;
import org.ocha.dap.persistence.entity.CKANDataset.Type;
import org.ocha.dap.persistence.entity.CKANResource;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.ocha.dap.tools.GSONBuilderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DAPServiceImpl implements DAPService {

	private static final Logger log = LoggerFactory.getLogger(DAPServiceImpl.class);

	private static String DATASET_LIST_V3_API_PATTERN = "http://%s/api/3/action/package_list";
	private static String DATASET_V3_API_PATTERN = "http://%s/api/3/action/package_show?id=";

	// can be useful to update a dataset
	private static String DATASET_V2_API_PATTERN = "http://%s/api/2/rest/dataset/";

	private final String urlBaseForDatasetsList;
	private final String urlBaseForDatasetContentV3;
	private final String urlBaseForDatasetContentV2;
	private final String technicalAPIKey;

	private final File stagingDirectory;

	public DAPServiceImpl(final String host, final String technicalAPIKey, final File stagingDirectory) {
		super();
		if (!stagingDirectory.isDirectory()) {
			throw new IllegalArgumentException("staging  directory doesn't exist: " + stagingDirectory.getAbsolutePath());
		}
		this.stagingDirectory = stagingDirectory;

		this.urlBaseForDatasetsList = String.format(DATASET_LIST_V3_API_PATTERN, host);
		this.urlBaseForDatasetContentV3 = String.format(DATASET_V3_API_PATTERN, host);
		this.urlBaseForDatasetContentV2 = String.format(DATASET_V2_API_PATTERN, host);
		this.technicalAPIKey = technicalAPIKey;
	}

	@Autowired
	private UserDAO userDao;

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

	@Override
	public void checkForNewCKANDatasets() {
		final List<DatasetV3DTO> datasetV3DTOList = getDatasetV3DTOsFromQuery(technicalAPIKey);
		datasetDAO.importDetectedDatasetsIfNotPresent(datasetV3DTOList);
	}

	@Override
	@Transactional
	public void checkForNewCKANResources() {
		final List<String> datasetList = getDatasetNamesFromQuery(technicalAPIKey);
		final List<String> datasetToBeCurated = datasetDAO.listToBeCuratedCKANDatasets();
		for (final String datasetName : datasetList) {
			if (datasetToBeCurated.contains(datasetName)) {
				final DatasetV3WrapperDTO dataset = getDatasetDTOFromQueryV3(datasetName, technicalAPIKey);
				final List<Resource> resources = dataset.getResult().getResources();
				for (final Resource resource : resources) {
					// if the same id/revisionId is already present, do nothing,
					// this has already been processed
					if (resourceDAO.getCKANResource(resource.getId(), resource.getRevision_id()) == null) {
						// If some revisions were detected before, but were not
						// processed yet, (i.e a revision was uploaded in the
						// mean
						// time )we mark them as outdated
						final List<CKANResource> ckanResources = resourceDAO.listCKANResourceRevisions(resource.getId());
						for (final CKANResource ckanResource : ckanResources) {
							workflowService.flagCKANResourceAsOutdated(ckanResource.getId().getId(), ckanResource.getId().getRevision_id());
						}

						resourceDAO.newCKANResourceDetected(resource.getId(), resource.getRevision_id(), resource.getName(), resource.getRevision_timestamp(), datasetName, dataset.getResult()
								.getId(), dataset.getResult().getRevision_id(), dataset.getResult().getRevision_timestamp());
					}
				}
			}
		}
	}

	@Override
	public List<CKANResource> listCKANResources() {
		return resourceDAO.listCKANResources();
	}

	@Override
	public List<CKANDataset> listCKANDatasets() {
		// TODO Auto-generated method stub
		return datasetDAO.listCKANDatasets();
	}
	
	@Override
	public void flagDatasetAsToBeCurated(final String datasetName, final Type type) {
		datasetDAO.flagDatasetAsToBeCurated(datasetName, type);
		
	}

	@Override
	@Transactional
	public void downloadFileForCKANResource(final String id, final String revision_id) throws IOException {
		final File reourceFolder = new File(stagingDirectory, id);
		final File revisionFile = new File(reourceFolder, revision_id);

		final URL url = getResourceURLFromAPI(id, revision_id);

		if (!workflowService.flagCKANResourceAsDownloaded(id, revision_id))
			return;

		// if we can't download the file, the flag will be rolled back
		final boolean success = performDownload(url, revisionFile);
		if (!success)
			throw new RuntimeException("Failed downloading the given resource");
	}

	@Override
	public void evaluateFileForCKANResource(final String id, final String revision_id) throws IOException {
		final CKANDataset.Type type = fileEvaluatorAndExtractor.getTypeForFile(id, revision_id);
		final boolean result = fileEvaluatorAndExtractor.evaluateResource(id, revision_id, type);

		if (result) {
			workflowService.flagCKANResourceAsTechEvaluationSuccess(id, revision_id, type);
		} else {
			workflowService.flagCKANResourceAsTechEvaluationFail(id, revision_id, type);
			mailService.sendMailForResourceEvaluationFailure(id, revision_id, type);
		}

	}

	/**
	 * 
	 * @return true if the file was successfully downloaded
	 */
	private boolean performDownload(final URL url, final File destinationFile) throws IOException {
		// if the resource does not exist anymore in CKAN
		if (url == null)
			return false;

		final URLConnection uCon = url.openConnection();

		final InputStream is = uCon.getInputStream();

		final byte[] buf = new byte[1024];
		int byteRead = 0;
		FileOutputStream fos = null;
		try {
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
			if (fos != null)
				fos.close();
		}
	}

	/**
	 * 
	 * The url might change, while ids cannot, so it is best to get the url from
	 * the api (just in time), and never store it
	 * 
	 * Up to now, this requires a very inefficient browsing of the whole tree of
	 * datasets and resources
	 * 
	 * @throws MalformedURLException
	 */
	private URL getResourceURLFromAPI(final String id, final String revision_id) throws MalformedURLException {
		final List<String> datasetList = getDatasetNamesFromQuery(technicalAPIKey);
		for (final String datasetName : datasetList) {
			final DatasetV3WrapperDTO dataset = getDatasetDTOFromQueryV3(datasetName, technicalAPIKey);
			final List<Resource> resources = dataset.getResult().getResources();
			for (final Resource resource : resources) {
				if (resource.getId().equals(id) && resource.getRevision_id().equals(revision_id))
					return new URL(resource.getUrl());
			}
		}
		return null;
	}

	@Override
	public List<String> getDatasetsListFromCKAN(final String userId) throws InsufficientCredentialsException {
		final String apiKey = userDao.getUserApiKey(userId);
		return getDatasetNamesFromQuery(apiKey);
	}

	@Override
	public DatasetV3WrapperDTO getDatasetContentFromCKANV3(final String userId, final String datasetName) throws InsufficientCredentialsException {
		final String apiKey = userDao.getUserApiKey(userId);

		return getDatasetDTOFromQueryV3(datasetName, apiKey);

	}

	@Override
	public DatasetV2DTO getDatasetContentFromCKANV2(final String userId, final String datasetName) throws InsufficientCredentialsException {
		final String apiKey = userDao.getUserApiKey(userId);

		return getDatasetDTOFromQueryV2(datasetName, apiKey);
	}

	@Override
	public void updateDatasetContent(final String userId, final String datasetName, final DatasetV2DTO datasetV2DTO) throws InsufficientCredentialsException {
		final String apiKey = userDao.getUserApiKey(userId);

		updateDatasetDTO(datasetName, apiKey, datasetV2DTO);
	}

	List<DatasetV3DTO> getDatasetV3DTOsFromQuery(final String apiKey){
		final List<String> names = getDatasetNamesFromQuery(apiKey);
		final List<DatasetV3DTO> result = new ArrayList<>();
		
		for(final String name : names){
			result.add(getDatasetDTOFromQueryV3(name, apiKey).getResult());
		}
		return result;
	}
	List<String> getDatasetNamesFromQuery(final String apiKey) {
		final String jsonResult = performHttpGET(urlBaseForDatasetsList, apiKey);
		if (jsonResult == null) {
			return null;
		} else {
			final DatasetListV3DTO returnedValue = GSONBuilderWrapper.getGSON().fromJson(jsonResult, DatasetListV3DTO.class);
			return returnedValue.getResult();
		}
	}

	@Override
	public boolean authenticate(final String id, final String password) throws AuthenticationException {
		return userDao.authenticate(id, password);
	}

	@Override
	public DatasetV3WrapperDTO getDatasetDTOFromQueryV3(final String datasetName, final String apiKey) {
		final String urlForDataSet = String.format("%s%s", urlBaseForDatasetContentV3, datasetName);
		final String jsonResult = performHttpGET(urlForDataSet, apiKey);
		if (jsonResult == null) {
			return null;
		} else {

			return GSONBuilderWrapper.getGSON().fromJson(jsonResult, DatasetV3WrapperDTO.class);
		}
	}

	@Override
	public DatasetV2DTO getDatasetDTOFromQueryV2(final String datasetName, final String apiKey) {
		final String urlForDataSet = String.format("%s%s", urlBaseForDatasetContentV2, datasetName);
		final String jsonResult = performHttpGET(urlForDataSet, apiKey);
		if (jsonResult == null) {
			return null;
		} else {

			return GSONBuilderWrapper.getGSON().fromJson(jsonResult, DatasetV2DTO.class);
		}
	}

	void updateDatasetDTO(final String datasetName, final String apiKey, final DatasetV2DTO datasetV2DTO) {
		final String urlForDataSet = String.format("%s%s", urlBaseForDatasetContentV2, datasetName);

		final String query = GSONBuilderWrapper.getGSON().toJson(datasetV2DTO);

		final String jsonResult = performHttpPOST(urlForDataSet, apiKey, query);
		jsonResult.toString();
	}

	private String performHttpGET(final String url, final String apiKey) {
		String responseBody = null;
		final DefaultHttpClient httpclient = new DefaultHttpClient();

		final HttpGet httpGet = new HttpGet(url);
		try {
			httpGet.addHeader("Content-Type", "application/json");
			httpGet.addHeader("accept", "application/json");

			if (apiKey != null) {
				httpGet.addHeader("X-CKAN-API-Key", apiKey);
			}

			final ResponseHandler<String> responseHandler = new BasicResponseHandler();
			responseBody = httpclient.execute(httpGet, responseHandler);
		} catch (final Exception e) {
			log.debug(e.toString(), e);
		}

		return responseBody;

	}

	private String performHttpPOST(final String url, final String apiKey, final String query) {
		String responseBody = null;
		final DefaultHttpClient httpclient = new DefaultHttpClient();

		final HttpPost httpPost = new HttpPost(url);
		try {

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
			responseBody = httpclient.execute(httpPost, responseHandler);
		} catch (final Exception e) {
			log.debug(e.toString(), e);
		}
		return responseBody;
	}
}
