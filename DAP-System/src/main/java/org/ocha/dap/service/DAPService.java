package org.ocha.dap.service;

import java.util.List;

import org.ocha.dap.dto.apiv2.DatasetV2DTO;
import org.ocha.dap.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;

public interface DAPService {
	
	/**
	 * return the list of the Datasets
	 * Performing the query on behalf of the user
	 * 
	 * @param userId id of the user performing the query
	 */
	public List<String> getDatasetsListFromCKAN(final String userId) throws InsufficientCredentialsException;
	
	/**
	 * uses the CKAN api V3
	 * fetchs the content of the given dataset
	 * Performing the query on behalf of the user
	 * 
	 * @param userId id of the user performing the query
	 * @param datasetName name of the dataset 
	 */
	public DatasetV3WrapperDTO getDatasetContentFromCKANV3(final String userId, final String datasetName) throws InsufficientCredentialsException;
	
	/**
	 * uses the CKAN api V2
	 * fetchs the content of the given dataset
	 * Performing the query on behalf of the user
	 * 
	 * @param userId id of the user performing the query
	 * @param datasetName name of the dataset 
	 */
	public DatasetV2DTO getDatasetContentFromCKANV2(final String userId, final String datasetName) throws InsufficientCredentialsException;
	
	public void updateDatasetContent(final String userId, final String datasetName, final DatasetV2DTO datasetV2DTO) throws InsufficientCredentialsException;
	
	public boolean authenticate(final String id, final String password) throws AuthenticationException;

}
