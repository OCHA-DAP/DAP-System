package org.ocha.hdx.exporter.indicator;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for the RWxxx indicator types.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorRW_XLSX extends AbstractExporterIndicatorRW_XLSX {

	public ExporterIndicatorRW_XLSX(final ExporterService exporterService, final String indicatorTypeCode, final String sheetName) {
		super(exporterService);
		setRWIndicatorTypeCode(indicatorTypeCode);
		setRWSheetName(sheetName);
	}

	public ExporterIndicatorRW_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter, final String indicatorTypeCode, final String sheetName) {
		super(exporter);
		setRWIndicatorTypeCode(indicatorTypeCode);
		setRWSheetName(sheetName);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {
		queryData.setIndicatorTypeCode(getRWIndicatorTypeCode());
		queryData.setSourceCode(getRWSourceCode());
		final Map<Long, Map<String, IndicatorData>> data = exporterService.getIndicatorDataData(queryData);
		return export(workbook, queryData, data, getRWSheetName());
	}
}
