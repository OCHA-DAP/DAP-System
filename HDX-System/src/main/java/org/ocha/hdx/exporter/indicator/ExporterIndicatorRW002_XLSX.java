package org.ocha.hdx.exporter.indicator;

import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for the RW002 indicator type.
 * 
 * @author bmichiels
 * 
 */
public class ExporterIndicatorRW002_XLSX extends AbstractExporterIndicatorRW_XLSX {

	public ExporterIndicatorRW002_XLSX(final ExporterService exporterService) {
		super(exporterService);
		setRWIndicatorTypeCode("RW002");
	}

	public ExporterIndicatorRW002_XLSX(final Exporter<XSSFWorkbook, ExporterIndicatorQueryData> exporter) {
		super(exporter);
		setRWIndicatorTypeCode("RW002");
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterIndicatorQueryData queryData) throws Exception {
		queryData.setIndicatorTypeCode(getRWIndicatorTypeCode());
		queryData.setSourceCode(getRWSourceCode());
		final Map<Long, Map<String, IndicatorData>> data = exporterService.getIndicatorDataData(queryData);
		return export(workbook, queryData, data, "Number of Reports");
	}
}
