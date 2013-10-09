package org.ocha.dap.service;

import java.util.List;

import org.ocha.dap.dto.DatasetDTO;
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
	 * fetchs the content of the given dataset
	 * Performing the query on behalf of the user
	 * 
	 * @param userId id of the user performing the query
	 * @param datasetName name of the dataset 
	 */
	public DatasetDTO getDatasetContentFromCKAN(final String userId, final String datasetName) throws InsufficientCredentialsException;
	
	public boolean authenticate(final String id, final String password) throws AuthenticationException;

}
