package org.ocha.hdx.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.IntegrationTestSetUpAndTearDown;
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.report.ImportReport;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorImportConfig;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-integration-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml",
		"classpath:/ctx-persistence-test.xml" })
public class FileEvaluatorAndExtractorImplTest {

	@Autowired
	private IntegrationTestSetUpAndTearDown integrationTestSetUpAndTearDown;

	@Autowired
	private FileEvaluatorAndExtractor fileEvaluatorAndExtractorImpl;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private DataSerieToCuratedDatasetDAO dataSerieToCuratedDatasetDAO;

	@Before
	public void setUp() {
		this.integrationTestSetUpAndTearDown.setUp();
	}

	@After
	public void tearDown() {
		this.integrationTestSetUpAndTearDown.tearDown();
	}

	@Test
	public void testEvaluateDummyCSVFile() throws IOException {

	}

	@Test
	public void testSaveReadIndicatorsToDatabase() {

		{
			final List<Indicator> indicators = this.indicatorDAO.listLastIndicators(10);
			assertEquals(2, indicators.size());
			final List<DataSerieToCuratedDataset> datasetsWithUnsyncedMetadata = dataSerieToCuratedDatasetDAO.getDatasetsWithUnsyncedMetadata(10);
			assertEquals(0, datasetsWithUnsyncedMetadata.size());
		}

		final PreparedIndicator preparedIndicator = new PreparedIndicator();
		preparedIndicator.setIndicatorTypeCode("per-capita-gdp");
		preparedIndicator.setSourceCode("acled");
		preparedIndicator.setEntityTypeCode("country");
		preparedIndicator.setEntityCode("LUX");
		preparedIndicator.setStart(LocalDateTime.now().toDate());
		preparedIndicator.setEnd(LocalDateTime.now().toDate());
		preparedIndicator.setPeriodicity(Periodicity.YEAR);
		preparedIndicator.setIndicatorImportConfig(new IndicatorImportConfig("10", ValidationStatus.SUCCESS));
		preparedIndicator.setValue(new IndicatorValue(10.0));

		final ImportReport report = this.fileEvaluatorAndExtractorImpl.saveReadIndicatorsToDatabase(Lists.newArrayList(preparedIndicator), "test-resource-id", "test-revision-id");
		assertEquals(1, report.getNbOfNewRecords());

		{
			final List<Indicator> indicators = this.indicatorDAO.listLastIndicators(10);
			assertEquals(3, indicators.size());

			final List<DataSerieToCuratedDataset> datasetsWithUnsyncedMetadata = dataSerieToCuratedDatasetDAO.getDatasetsWithUnsyncedMetadata(10);
			assertEquals(1, datasetsWithUnsyncedMetadata.size());
			assertEquals("per-capita-gdp", datasetsWithUnsyncedMetadata.get(0).getIndicatorType().getCode());

			final Indicator indicator = indicators.get(0);
			final Long indicatorId = indicator.getId();
			this.checkPreparedIndicatorEqualsIndicator(preparedIndicator, indicator);

			preparedIndicator.setIndicatorImportConfig(new IndicatorImportConfig("20", ValidationStatus.SUCCESS));
			preparedIndicator.setValue(new IndicatorValue(20.0));

			final ImportReport report2 = this.fileEvaluatorAndExtractorImpl.saveReadIndicatorsToDatabase(Lists.newArrayList(preparedIndicator), "test-resource-id", "test-revision-id");
			assertEquals(1, report2.getNbOfUpdatedRecords());

			final Indicator indicator2 = this.indicatorDAO.getIndicatorById(indicatorId);

			assertNotNull(indicator2);

			this.checkPreparedIndicatorEqualsIndicator(preparedIndicator, indicator2);
		}

	}

	private void checkPreparedIndicatorEqualsIndicator(final PreparedIndicator preparedIndicator, final Indicator indicator) {
		assertEquals(preparedIndicator.getValue().getNumberValue(), indicator.getValue().getNumberValue());
		assertEquals(preparedIndicator.getIndicatorTypeCode(), indicator.getType().getCode());
		assertEquals(preparedIndicator.getSourceCode(), indicator.getSource().getCode());
		assertEquals(preparedIndicator.getEntityCode(), indicator.getEntity().getCode());
		assertEquals(preparedIndicator.getPeriodicity(), indicator.getPeriodicity());
		assertEquals(preparedIndicator.getStart(), indicator.getStart());
	}

}
