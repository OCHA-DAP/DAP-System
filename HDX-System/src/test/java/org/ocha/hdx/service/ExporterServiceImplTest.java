package org.ocha.hdx.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.IntegrationTestSetUpAndTearDown;
import org.ocha.hdx.exporter.country.ExporterCountryQueryData;
import org.ocha.hdx.exporter.helper.ReportRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-integration-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml",
		"classpath:/ctx-persistence-test.xml" })
public class ExporterServiceImplTest {

	@Autowired
	private IntegrationTestSetUpAndTearDown integrationTestSetUpAndTearDown;

	@Autowired
	private ExporterService exporterService;

	@Before
	public void setUp() {
		integrationTestSetUpAndTearDown.setUp();
		integrationTestSetUpAndTearDown.setUpDataForCountryOverview();
		integrationTestSetUpAndTearDown.setUpDataForCountryCrisisHistory();
	}

	@After
	public void tearDown() {
		integrationTestSetUpAndTearDown.tearDownDataForCountryCrisisHistory();
		integrationTestSetUpAndTearDown.tearDownDataForCountryOverview();
		integrationTestSetUpAndTearDown.tearDown();
	}

	@Test
	public void testGetCountryCrisisHistoryData() throws FileNotFoundException, IOException {
		final ExporterCountryQueryData exporterCountryQueryData = new ExporterCountryQueryData();
		exporterCountryQueryData.setCountryCode("USA");
		exporterCountryQueryData.setFromYear(2005);
		exporterCountryQueryData.setToYear(2010);
		exporterCountryQueryData.setLanguage("En");
		final Map<String, ReportRow> countryCrisisHistoryData = exporterService.getCountryCrisisHistoryData(exporterCountryQueryData);

		{
			Assert.assertEquals(2, countryCrisisHistoryData.size());
			final ReportRow reportRowCH070 = countryCrisisHistoryData.get("CH070");
			Assert.assertEquals("CH070", reportRowCH070.getIndicatorCode());
			Assert.assertEquals("Number of disasters", reportRowCH070.getIndicatorName());
			Assert.assertEquals("", reportRowCH070.getDatasetSummary());
			Assert.assertNull(reportRowCH070.getValue(2005));
			Assert.assertNull(reportRowCH070.getValue(2006));
			Assert.assertNull(reportRowCH070.getValue(2007));
			Assert.assertEquals("5.0", reportRowCH070.getValue(2008));
			Assert.assertNull(reportRowCH070.getValue(2009));
			Assert.assertNull(reportRowCH070.getValue(2010));
		}

		{
			final ReportRow reportRowCH080 = countryCrisisHistoryData.get("CH080");
			Assert.assertEquals("CH080", reportRowCH080.getIndicatorCode());
			Assert.assertEquals("People killed in disasters", reportRowCH080.getIndicatorName());
			Assert.assertEquals("Extracted from 1st hand sources", reportRowCH080.getDatasetSummary());
			Assert.assertNull(reportRowCH080.getValue(2005));
			Assert.assertNull(reportRowCH080.getValue(2006));
			Assert.assertNull(reportRowCH080.getValue(2007));
			Assert.assertNull(reportRowCH080.getValue(2008));
			Assert.assertEquals("1000.0", reportRowCH080.getValue(2009));
			Assert.assertNull(reportRowCH080.getValue(2010));
		}
	}

	@Test
	public void testExportCountry_XLSX() throws FileNotFoundException, IOException {
		final XSSFWorkbook exportCountry_XLSX = exporterService.exportCountry_XLSX("USA", 2005, 2010, "En");
		exportCountry_XLSX.write(new FileOutputStream(new File("C:\\Users\\seustachi\\Desktop\\USA.xlsx")));
	}
}
