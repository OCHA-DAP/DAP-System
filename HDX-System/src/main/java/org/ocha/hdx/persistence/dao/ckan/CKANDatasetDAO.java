package org.ocha.hdx.persistence.dao.ckan;

import java.util.List;
import java.util.Map;

import org.ocha.hdx.dto.apiv3.DatasetV3DTO;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

public interface CKANDatasetDAO {

	/**
	 * Will add in the HDX db the detected dataset that are not already present in {@link CKANDataset.Status#PENDING} with no
	 * {@link CKANDataset.Type}
	 *
	 * @param datasets
	 */
	public void importDetectedDatasetsIfNotPresent(List<DatasetV3DTO> datasets);

	public void flagDatasetAsToBeCurated(String datasetName, CKANDataset.Type type, ResourceConfiguration configuration);

	public void flagDatasetAsIgnored(String datasetName);

	public List<CKANDataset> listCKANDatasets();

	public Map<String,CKANDataset> listToBeCuratedCKANDatasets();

	public void deleteAllCKANDatasetsRecords();

	public CKANDataset.Type getTypeForName(String name);

	public String getMaintainerMailForName(String name);

}
