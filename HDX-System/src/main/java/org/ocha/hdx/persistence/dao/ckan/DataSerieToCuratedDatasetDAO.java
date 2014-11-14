package org.ocha.hdx.persistence.dao.ckan;

import java.util.Date;
import java.util.List;

import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;

public interface DataSerieToCuratedDatasetDAO {

	/**
	 * 
	 * @return the datasets where lastMetadataUpdate > lastMetadataPush
	 */
	public List<DataSerieToCuratedDataset> getDatasetsWithUnsyncedMetadata();

	/**
	 * updates the lastMetadataUpdate field for the DataSerieToCuratedDataset found with DataSerie
	 * 
	 * @param dataSerie
	 *            to find the unique corresponding DataSerieToCuratedDataset
	 * @param newTimestamp
	 *            the new lastMetadataUpdate ts
	 * @return
	 */
	public void updateLastMetadataTimestamp(final DataSerie dataSerie, final Date newTimestamp);

	/**
	 * updates the lastDataUpdate field for the DataSerieToCuratedDataset found with DataSerie
	 * 
	 * @param dataSerie
	 *            to find the unique corresponding DataSerieToCuratedDataset
	 * @param newTimestamp
	 *            the new lastDataUpdate ts
	 * @return
	 */
	public void updateLastDataTimestamp(final DataSerie dataSerie, final Date newTimestamp);

}
