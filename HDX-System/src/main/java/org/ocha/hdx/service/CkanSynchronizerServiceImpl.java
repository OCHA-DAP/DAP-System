package org.ocha.hdx.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.ocha.hdx.dto.apiv3.HdxPackageUpdateMetadataDTO;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAO;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
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
	private DataSerieMetadataDAO dataSerieMetadataDAO;

	@Autowired
	private CuratedDataService curatedDataService;

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

		if (dataSerieToCuratedDataset.getCkanDatasetId() != null && !dataSerieToCuratedDataset.getCkanDatasetId().isEmpty()) {
			dto.setId(dataSerieToCuratedDataset.getCkanDatasetId());
		}

		if (dataSerieToCuratedDataset.getCkanDatasetName() != null && !dataSerieToCuratedDataset.getCkanDatasetName().isEmpty()) {
			dto.setName(dataSerieToCuratedDataset.getCkanDatasetName());
		}

		final Map<String, Timestamp> minMaxDatesForDataSeries = curatedDataService.getMinMaxDatesForDataSeries(new DataSerie(indType.getCode(), source.getCode()));
		final DateTimeFormatter customFormatter = DateTimeFormat.forPattern("MM/dd/YYYY");
		final String minDate = customFormatter.print(minMaxDatesForDataSeries.get("MIN").getTime());
		final String maxDate = customFormatter.print(minMaxDatesForDataSeries.get("MAX").getTime());
		dto.setDataset_date(String.format("%s-%s", minDate, maxDate));

		dto.setDataset_source(source.getName().getDefaultValue());
		dto.setDataset_source_code(source.getCode());
		dto.setDataset_summary(dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey(indType.getCode(), source.getCode(), MetadataName.DATASET_SUMMARY).getEntryValue()
				.getDefaultValue());
		dto.setIndicator_type(indType.getName().getDefaultValue());
		dto.setIndicator_type_code(indType.getCode());

		dto.setLast_data_update_date(getDateIsoFormatted(dataSerieToCuratedDataset.getLastDataUpdate()));
		dto.setLast_metadata_update_date(getDateIsoFormatted(dataSerieToCuratedDataset.getLastMetadataUpdate()));

		dto.setMethodology(getMetadataAsString(indType.getCode(), source.getCode(), MetadataName.METHODOLOGY));
		dto.setMore_info(getMetadataAsString(indType.getCode(), source.getCode(), MetadataName.MORE_INFO));
		dto.setTerms_of_use(getMetadataAsString(indType.getCode(), source.getCode(), MetadataName.TERMS_OF_USE));
		dto.setValidation_notes_and_comments(getMetadataAsString(indType.getCode(), source.getCode(), MetadataName.VALIDATION_NOTES));

		return dto;
	}

	private String getMetadataAsString(final String indicatorTypeCode, final String sourceCode, final MetadataName entryKey) {
		final DataSerieMetadata dataSerieMetadata = dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey(indicatorTypeCode, sourceCode, entryKey);
		if (dataSerieMetadata != null) {
			return dataSerieMetadata.getEntryValue().getDefaultValue();
		} else {
			return null;
		}
	}

	private String getDateIsoFormatted(final Date date) {
		if (date == null)
			return "";

		final DateTimeFormatter isoFmt = ISODateTimeFormat.basicDateTime();

		return isoFmt.print(date.getTime());

	}
}
