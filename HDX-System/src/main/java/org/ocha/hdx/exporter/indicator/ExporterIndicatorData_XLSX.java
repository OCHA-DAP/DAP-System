package org.ocha.hdx.exporter.indicator;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for an indicator - data.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorData_XLSX extends AbstractExporterIndicator_XLSX {

	public ExporterIndicatorData_XLSX(final ExporterService exporterService) {
		super(exporterService);
	}

	public ExporterIndicatorData_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter) {
		super(exporter);
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {
		final Map<Long, Map<String, IndicatorData>> data = exporterService.getIndicatorDataData(queryData);
		return export(workbook, queryData, data, "Data");
	}
}
