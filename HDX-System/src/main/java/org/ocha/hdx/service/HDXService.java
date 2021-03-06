package org.ocha.hdx.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import org.ocha.hdx.dto.apiv3.DatasetV3DTO;
import org.ocha.hdx.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.hdx.dto.apiv3.GroupV3DTO;
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

public interface HDXService {

	public boolean addResourceToCKANDataset(final String packageId, final String resourceUrl, final String name);

	// not used for now
	// public boolean addResourceToCKANDataset(final String packageId, final File file);

	public void checkForNewCKANDatasets();

	public void addNewCKANResource(final String resourceId, final String resourceName, final long resourceConfigurationId, final InputStream resourceFile) throws IOException;

	/**
	 * will try to get all the resources from CKAN instance first datasets and then resources
	 * 
	 * Il some resources are new, will register a CKANResource in HDX db with {@link WorkflowState#Detected}
	 */
	public void checkForNewCKANResources();

	public List<CKANResource> listCKANResources();

	public List<CKANDataset> listCKANDatasets();

	public List<GroupV3DTO> getCKANGroups(final List<String> groups);

	public DatasetV3DTO getDatasetContent(final String name);

	public List<String> getCKANGroupNames();

	public CKANResource getCKANResource(final String id, final String revision_id);

	public void flagDatasetAsToBeCurated(final String datasetName, final Type type, final long configurationId);

	public void flagDatasetAsIgnored(final String datasetName);

	public void updateDataset(final String datasetName, final String importer, final Long configurationId);

	/**
	 * 
	 * downloads the file associated to the given id / revision and flags the record as {@link WorkflowState#DOWNLOADED}
	 * 
	 * The record must be in a Workflow State allowing download
	 */
	public void downloadFileForCKANResource(final String id, final String revision_id) throws IOException;

	/**
	 * 
	 * evaluate the file associated to the given id / revision and flags the record as {@link WorkflowState#FILE_PRE_VALIDATION_SUCCESS} or {@link WorkflowState#FILE_PRE_VALIDATION_FAIL}
	 * 
	 * The record must be in a Workflow State allowing evaluation
	 */
	public void evaluateFileForCKANResource(final String id, final String revision_id) throws IOException;

	/**
	 * 
	 * Performs the required transformation and import data from the file associated to the given id / revision and flags the record as {@link WorkflowState#IMPORT_SUCCESS} or
	 * {@link WorkflowState#IMPORT_FAIL}
	 * 
	 */
	public void transformAndImportDataFromFileForCKANResource(final String id, final String revision_id);

	/**
	 * return the list of the Datasets Performing the query on behalf of the user
	 * 
	 * @param userId
	 *            id of the user performing the query
	 */
	public List<String> getDatasetsListFromCKAN(final String userId) throws InsufficientCredentialsException;

	/**
	 * uses the CKAN api V3 fetchs the content of the given dataset Performing the query on behalf of the user
	 * 
	 * @param userId
	 *            id of the user performing the query
	 * @param datasetName
	 *            name of the dataset
	 */
	// public DatasetV3WrapperDTO getDatasetContentFromCKANV3(final String userId, final String datasetName) throws InsufficientCredentialsException;

	public DatasetV3WrapperDTO getDatasetDTOFromQueryV3(final String datasetName, final String apiKey);

	/*
	 * Users management.
	 */

	public boolean authenticate(final String id, final String password) throws AuthenticationException;

	public User getUserById(String userId);

	public List<User> listUsers();

	public List<String> listRoles();

	public void createUser(final String id, final String password, final String role, final String apiKey) throws Exception;

	public void updateUser(final String id, final String password, final String role, final String apiKey) throws Exception;

	public void deleteUser(final String id) throws Exception;

	/*
	 * Configurations management.
	 */
	public List<ResourceConfiguration> listConfigurations();

	public ResourceConfiguration createResourceConfiguration(String name, Set<ResourceConfigEntry> generalConfigList, Set<IndicatorResourceConfigEntry> indicatorConfigList) throws Exception;

	public ResourceConfiguration createResourceConfiguration(String name) throws Exception;

	public void updateResourceConfiguration(final long id, String name, Set<ResourceConfigEntry> generalConfigList, Set<IndicatorResourceConfigEntry> indicatorConfigList) throws Exception;

	public void updateResourceConfiguration(final long id, String name) throws Exception;

	public void deleteResourceConfiguration(final long id) throws Exception;

	public ResourceConfiguration getResourceConfiguration(final long id) throws Exception;

	public void addGeneralConfiguration(long id, String key, String value) throws Exception;

	public void deleteGeneralConfiguration(long rcID, long gcID) throws Exception;

	public void updateGeneralConfiguration(long id, String key, String value) throws Exception;

	public void addIndicatorConfiguration(long rcID, long itID, long srcID, String key, String value) throws Exception;

	public void deleteIndicatorConfiguration(long rcID, long id) throws Exception;

	public void deleteAllIndicatorConfigurations(long resConfID) throws Exception;

	public void updateIndicatorConfiguration(long id, long indTypeID, long srcID, String key, String value) throws Exception;

	public File exportDataSeriesConfiguration_CSV(Long id) throws Exception;

	/*
	 * Languages management.
	 */
	public List<Language> listLanguages();

	public void createLanguage(String code, String nativeName) throws Exception;

	public void updateLanguage(String code, String nativeName) throws Exception;

	public void deleteLanguage(String code) throws Exception;

	/*
	 * Translations management
	 */
	public void createTranslation(long textId, String languageCode, String translationValue) throws Exception;

	public void deleteTranslation(long textId, String languageCode) throws Exception;

	public void updateTranslation(long valueOf, String languageCode, String translationValue) throws Exception;

}
