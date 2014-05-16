package org.ocha.hdx.exporter.country;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ocha.hdx.exporter.Exporter;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.service.ExporterService;

/**
 * Exporter for a country - any data.
 * 
 * @author bmichiels
 * 
 */
public class ExporterCountryData_XLSX extends AbstractExporterCountry_XLSX {

	// The list of data series for this sheet
	private final List<DataSerie> dataSeries;
	
	// The sheet name
	private final String sheetName;
	
	// The periodicity of the indicators in the data series
	private final Periodicity periodicity;
	
	public ExporterCountryData_XLSX(final ExporterService exporterService, final List<DataSerie> dataSeries, final String sheetName) {
		super(exporterService);
		this.dataSeries = dataSeries;
		this.sheetName = sheetName;
		periodicity = Periodicity.YEAR;
	}

	public ExporterCountryData_XLSX(final ExporterService exporterService, final Periodicity periodicity, final List<DataSerie> dataSeries, final String sheetName) {
		super(exporterService);
		this.dataSeries = dataSeries;
		this.sheetName = sheetName;
		this.periodicity = periodicity;
	}

	public ExporterCountryData_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter, final List<DataSerie> dataSeries, final String sheetName) {
		super(exporter);
		this.dataSeries = dataSeries;
		this.sheetName = sheetName;
		periodicity = Periodicity.YEAR;
	}

	public ExporterCountryData_XLSX(final Exporter<XSSFWorkbook, ExporterCountryQueryData> exporter, final Periodicity periodicity, final List<DataSerie> dataSeries, final String sheetName) {
		super(exporter);
		this.dataSeries = dataSeries;
		this.sheetName = sheetName;
		this.periodicity = periodicity;
	}

	@Override
	public XSSFWorkbook export(final XSSFWorkbook workbook, final ExporterCountryQueryData queryData) throws Exception {
		final Map<String, ReportRow> data = exporterService.getCountryData(queryData, periodicity, dataSeries);
		return export(workbook, queryData, data, sheetName);
	}
}
