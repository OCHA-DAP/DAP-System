package org.ocha.hdx.service;

import java.util.List;

import org.ocha.hdx.dto.apiv3.HdxPackageUpdateMetadataDTO;
import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.ocha.hdx.tools.GSONBuilderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CkanSynchronizerServiceImpl extends CkanClient implements CkanSynchronizerService {

	// When the DATA was updated the last time (i.e last run of Import)
	private static final String LAST_DATA_UPDATE_DATE = "last_data_update_date";

	// sourceName
	private static final String DATASET_SOURCE = "dataset_source";

	// sourceCode
	private static final String DATASET_SOURCE_CODE = "dataset_source_code";

	// indicator type name
	private static final String INDICATOR_TYPE = "indicator_type";

	// indicator type code
	private static final String INDICATOR_TYPE_CODE = "indicator_type_code";

	// range of the data, format : 11/02/2014-11/20/2014
	private static final String DATASET_DATE = "dataset_date";

	private static final String DATASET_SUMMARY = "dataset_summary";
	private static final String METHODOLOGY = "methodology";
	private static final String MORE_INFO = "more_info";
	private static final String TERMS_OF_USE = "terms_of_use";
	private static final String VALIDATION_NOTES_AND_COMMENTS = "validation_notes_and_comments";

	private static String HDX_PACKAGE_UPDATE_API_PATTERN = "http://%s/api/3/action/hdx_package_update_metadata";

	private final String urlBaseForHdxPackageUpdate;

	public CkanSynchronizerServiceImpl(final String host, final String technicalAPIKey) {
		super(technicalAPIKey);
		this.urlBaseForHdxPackageUpdate = String.format(HDX_PACKAGE_UPDATE_API_PATTERN, host);
	}

	private static final Logger log = LoggerFactory.getLogger("ckan-updater-logger");

	@Autowired
	private DataSerieToCuratedDatasetDAO dataSerieToCuratedDatasetDAO;

	@Override
	public void updateMetadataToCkan() {
		final List<DataSerieToCuratedDataset> datasetsWithUnsyncedMetadata = dataSerieToCuratedDatasetDAO.getDatasetsWithUnsyncedMetadata();

		for (final DataSerieToCuratedDataset dataSerieToCuratedDataset : datasetsWithUnsyncedMetadata) {
			// TODO fetch the right data and populate the dto

			final HdxPackageUpdateMetadataDTO dto = new HdxPackageUpdateMetadataDTO();
			dto.setDataset_date("");

			final String query = GSONBuilderWrapper.getGSON().toJson(dto);
			performHttpPOST(urlBaseForHdxPackageUpdate, technicalAPIKey, query);

		}

	}

	private static String DATASET_UPDATE_V3_API_PATTERN = "http://%s/api/3/action/package_update";

}
