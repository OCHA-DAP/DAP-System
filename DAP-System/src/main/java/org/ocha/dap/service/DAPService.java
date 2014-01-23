package org.ocha.dap.service;

import java.io.IOException;
import java.util.List;

import org.ocha.dap.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.dap.persistence.entity.ckan.CKANResource;
import org.ocha.dap.persistence.entity.ckan.CKANResource.WorkflowState;
import org.ocha.dap.persistence.entity.i18n.Language;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;

public interface DAPService {

	public void checkForNewCKANDatasets();

	/**
	 * will try to get all the resources from CKAN instance first datasets and then resources
	 * 
	 * Il some resources are new, will register a CKANResource in DAP db with {@link WorkflowState#Detected}
	 */
	public void checkForNewCKANResources();

	public List<CKANResource> listCKANResources();

	public List<CKANDataset> listCKANDatasets();

	public CKANResource getCKANResource(final String id, final String revision_id);

	public void flagDatasetAsToBeCurated(final String datasetName, final Type type);
	
	public void flagDatasetAsIgnored(final String datasetName);

	/**
	 * 
	 * downloads the file associated to the given id / revision and flags the record as {@link WorkflowState#DOWNLOADED}
	 * 
	 * The record must be in a Workflow State allowing download
	 */
	public void downloadFileForCKANResource(final String id, final String revision_id) throws IOException;

	/**
	 * 
	 * evaluate the file associated to the given id / revision and flags the record as {@link WorkflowState#TECH_EVALUTATION_SUCCESS} or
	 * {@link WorkflowState#TECH_EVALUTATION_FAIL}
	 * 
	 * The record must be in a Workflow State allowing evaluation
	 */
	public void evaluateFileForCKANResource(final String id, final String revision_id) throws IOException;

	/**
	 * 
	 * Performs the required transformation and import data from the file associated to the given id / revision and flags the record as
	 * {@link WorkflowState#IMPORT_SUCCESS} or {@link WorkflowState#IMPORT_FAIL}
	 * 
	 * The record must be in a Workflow State allowing transformationAndImport
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
	public DatasetV3WrapperDTO getDatasetContentFromCKANV3(final String userId, final String datasetName) throws InsufficientCredentialsException;

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
	 * Languages management.
	 */
	public List<Language> listLanguages();

	public void createLanguage(String code, String nativeName) throws Exception;

	public void updateLanguage(String code, String nativeName) throws Exception;

	public void deleteLanguage(String code) throws Exception;



}
