package org.ocha.hdx.service;

import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.dto.apiv3.HdxPackageUpdateMetadataDTO;
import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAO;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName;
import org.ocha.hdx.tools.GSONBuilderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CkanSynchronizerServiceImpl extends CkanClient implements CkanSynchronizerService {

	private static String HDX_PACKAGE_UPDATE_API_PATTERN = "http://%s/api/3/action/hdx_package_update_metadata";
	private static String DATASET_UPDATE_V3_API_PATTERN = "http://%s/api/3/action/package_update";

	private final String urlBaseForHdxPackageUpdate;

	public CkanSynchronizerServiceImpl(final String host, final String technicalAPIKey) {
		super(technicalAPIKey);
		this.urlBaseForHdxPackageUpdate = String.format(HDX_PACKAGE_UPDATE_API_PATTERN, host);
	}

	private static final Logger log = LoggerFactory.getLogger("ckan-updater-logger");

	@Autowired
	private DataSerieToCuratedDatasetDAO dataSerieToCuratedDatasetDAO;

	@Autowired
	DataSerieMetadataDAO dataSerieMetadataDAO;

	@Override
	public void updateMetadataToCkan() {
		final List<DataSerieToCuratedDataset> datasetsWithUnsyncedMetadata = dataSerieToCuratedDatasetDAO.getDatasetsWithUnsyncedMetadata();

		for (final DataSerieToCuratedDataset dataSerieToCuratedDataset : datasetsWithUnsyncedMetadata) {
			final HdxPackageUpdateMetadataDTO dto = convertDataSerieToCuratedDataset(dataSerieToCuratedDataset);

			final String query = GSONBuilderWrapper.getGSON().toJson(dto);
			performHttpPOST(urlBaseForHdxPackageUpdate, technicalAPIKey, query);

		}

	}

	@Override
	public List<HdxPackageUpdateMetadataDTO> getDatasetsWithUnsyncedMetadata() {
		final List<HdxPackageUpdateMetadataDTO> result = new ArrayList<HdxPackageUpdateMetadataDTO>();
		for (final DataSerieToCuratedDataset dataSerieToCuratedDataset : dataSerieToCuratedDatasetDAO.getDatasetsWithUnsyncedMetadata()) {
			final HdxPackageUpdateMetadataDTO dto = convertDataSerieToCuratedDataset(dataSerieToCuratedDataset);
			result.add(dto);
		}
		return result;
	}

	private HdxPackageUpdateMetadataDTO convertDataSerieToCuratedDataset(final DataSerieToCuratedDataset dataSerieToCuratedDataset) {
		final IndicatorType indType = dataSerieToCuratedDataset.getIndicatorType();
		final Source source = dataSerieToCuratedDataset.getSource();

		final HdxPackageUpdateMetadataDTO dto = new HdxPackageUpdateMetadataDTO();
		dto.setId(String.format("%s_%s", indType.getCode(), source.getCode()));
		// FIXME
		dto.setDataset_date("11/02/2014-11/20/2014");
		dto.setDataset_source(source.getName().getDefaultValue());
		dto.setDataset_source_code(source.getCode());
		dto.setDataset_summary(dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey(indType.getCode(), source.getCode(), MetadataName.DATASET_SUMMARY).getEntryValue()
				.getDefaultValue());
		dto.setIndicator_type(indType.getName().getDefaultValue());
		dto.setIndicator_type_code(indType.getCode());

		dto.setLast_data_update_date(dataSerieToCuratedDataset.getLastDataUpdate());
		dto.setLast_metadata_update_date(dataSerieToCuratedDataset.getLastMetadataUpdate());

		dto.setMethodology("the methodology");
		dto.setMore_info("more info");
		dto.setTerms_of_use("terms of use");
		dto.setValidation_notes_and_comments("Notes and comments");

		return dto;
	}
}
