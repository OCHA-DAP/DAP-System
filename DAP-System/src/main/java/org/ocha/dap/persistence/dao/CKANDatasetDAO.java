package org.ocha.dap.persistence.dao;

import java.util.List;

import org.ocha.dap.persistence.entity.CKANDataset;

public interface CKANDatasetDAO {

	/**
	 * Will add in the DAP db the detected dataset that are not already present
	 * in {@link CKANDataset.Status#PENDING} with no {@link CKANDataset.Type}
	 * 
	 * @param datasets
	 */
	public void importDetectedDatasetsIfNotPresent(List<String> datasets);

	public void flagDatasetAsToBeCurated(String datasetName, CKANDataset.Type type);

	public void flagDatasetAsIgnored(String datasetId);

	public List<CKANDataset> listCKANDatasets();
	public List<String> listToBeCuratedCKANDatasets();

	public void deleteAllCKANDatasetsRecords();

}
