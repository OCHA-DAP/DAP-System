package org.ocha.hdx.persistence.dao.ckan;

import java.util.List;

import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;

public interface DataSerieToCuratedDatasetDAO {

	/**
	 * 
	 * @return the datasets where lastMetadataUpdate > lastMetadataPush
	 */
	public List<DataSerieToCuratedDataset> getDatasetsWithUnsyncedMetadata();

}
