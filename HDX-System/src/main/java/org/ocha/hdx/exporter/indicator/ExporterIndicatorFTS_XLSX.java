package org.ocha.hdx.exporter.indicator;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for the FTSxxx indicator types.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorFTS_XLSX extends AbstractExporterIndicatorFTS_XLSX {

	public ExporterIndicatorFTS_XLSX(final ExporterService exporterService, final String indicatorTypeCode, final String sheetName) {
		super(exporterService);
		setFTSIndicatorTypeCode(indicatorTypeCode);
		setFTSSheetName(sheetName);
	}

	public ExporterIndicatorFTS_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter, final String indicatorTypeCode, final String sheetName) {
		super(exporter);
		setFTSIndicatorTypeCode(indicatorTypeCode);
		setFTSSheetName(sheetName);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {
		queryData.setIndicatorTypeCode(getFTSIndicatorTypeCode());
		queryData.setSourceCode(getFTSSourceCode());
		final Map<Long, Map<String, IndicatorData>> data = exporterService.getIndicatorDataData(queryData);
		return export(workbook, queryData, data, getFTSSheetName());
	}
}
