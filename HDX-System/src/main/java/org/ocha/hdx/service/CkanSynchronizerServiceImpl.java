package org.ocha.hdx.service;

import java.util.List;

import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CkanSynchronizerServiceImpl extends CkanClient implements CkanSynchronizerService {

	private static final Logger log = LoggerFactory.getLogger("ckan-updater-logger");

	@Autowired
	private DataSerieToCuratedDatasetDAO dataSerieToCuratedDatasetDAO;

	@Override
	public void updateMetadataToCkan() {
		final List<DataSerieToCuratedDataset> datasetsWithUnsyncedMetadata = dataSerieToCuratedDatasetDAO.getDatasetsWithUnsyncedMetadata();

		for (final DataSerieToCuratedDataset dataSerieToCuratedDataset : datasetsWithUnsyncedMetadata) {
			// TODO Create the wrapper final and perform the final ckan call
		}

	}

	private static String DATASET_UPDATE_V3_API_PATTERN = "http://%s/api/3/action/package_update";

}
