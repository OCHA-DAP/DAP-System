package org.ocha.hdx.persistence.dao.ckan;

import java.util.Date;
import java.util.List;

import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;

public interface DataSerieToCuratedDatasetDAO {

	/**
	 * 
	 * @return the datasets where lastMetadataUpdate > lastMetadataPush
	 */
	public List<DataSerieToCuratedDataset> getDatasetsWithUnsyncedMetadata(final int limit);

	public DataSerieToCuratedDataset getDataSerieToCuratedDataset(final DataSerie dataSerie);

	public DataSerieToCuratedDataset getDataSerieToCuratedDataset(final long id);

	/**
	 * updates the lastMetadataUpdate field for the DataSerieToCuratedDataset found with DataSerie
	 * 
	 * @param dataSerie
	 *            to find the unique corresponding DataSerieToCuratedDataset
	 * @param newTimestamp
	 *            the new lastMetadataUpdate ts
	 * @return true if the DataSerieToCuratedDataset existed and could be updated
	 */
	public boolean updateLastMetadataTimestamp(final DataSerie dataSerie, final Date newTimestamp);

	public void updateLastMetadataPushTimestamp(final long id, final Date timestamp);

	/**
	 * updates the lastDataUpdate field for the DataSerieToCuratedDataset found with DataSerie
	 * 
	 * @param dataSerie
	 *            to find the unique corresponding DataSerieToCuratedDataset
	 * @param newTimestamp
	 *            the new lastDataUpdate ts
	 * @return true if the DataSerieToCuratedDataset existed and could be updated
	 */
	public boolean updateLastDataTimestamp(final DataSerie dataSerie, final Date newTimestamp);

	public void createDataSerieToCuratedDataset(final Source source, final IndicatorType indicatorType);

}
