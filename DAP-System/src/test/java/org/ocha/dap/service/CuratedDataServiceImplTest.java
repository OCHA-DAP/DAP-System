package org.ocha.dap.service;

import java.util.Date;

import javax.persistence.NoResultException;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.SourceDAO;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.render.JsonRenderer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class CuratedDataServiceImplTest {

	@Autowired
	CuratedDataService curatedDataService;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Before
	public void setUp() {
		// FIXME should probably be factorized, the same setup can be used for a lot of tests across test classes
		entityTypeDAO.addEntityType("country", "Country");

		final EntityType entityTypeForCode = entityTypeDAO.getEntityTypeByCode("country");
		entityDAO.addEntity("LUX", "Luxembourg", entityTypeForCode);
		entityDAO.addEntity("RUS", "Russia", entityTypeForCode);
		entityDAO.addEntity("RWA", "Rwanda", entityTypeForCode);

		indicatorTypeDAO.addIndicatorType("per-capita-gdp", "Per capita gdp", "dollar");
		indicatorTypeDAO.addIndicatorType("PVX040", "Incidence of conflict", "Count");

		sourceDAO.addSource("WB", "World Bank");
		sourceDAO.addSource("acled", "Armed Conflict Location and Event Dataset");
	}

	@After
	public void tearDown() {
		indicatorDAO.deleteAllIndicators();
		entityDAO.deleteEntityByCodeAndType("LUX", "country");
		entityDAO.deleteEntityByCodeAndType("RUS", "country");
		entityDAO.deleteEntityByCodeAndType("RWA", "country");

		entityTypeDAO.deleteEntityTypeByCode("country");

		indicatorTypeDAO.deleteIndicatorTypeByCode("per-capita-gdp");
		indicatorTypeDAO.deleteIndicatorTypeByCode("PVX040");

		sourceDAO.deleteSourceByCode("WB");
		sourceDAO.deleteSourceByCode("acled");
	}

	@Test
	public void testListEntities() {
		Assert.assertEquals(3, curatedDataService.listEntities().size());

		try {
			curatedDataService.addEntity("RUS", "Russia", "crisis");
			Assert.fail("entity type crisis does not exist");
		} catch (final NoResultException e) {
			// expected
		}

		curatedDataService.addEntity("SWE", "Sweden", "country");

		Assert.assertEquals(4, curatedDataService.listEntities().size());

		entityDAO.deleteEntityByCodeAndType("SWE", "country");

		Assert.assertEquals(3, curatedDataService.listEntities().size());
	}

	@Test
	public void testListIndicatorsByPeriodicityAndSourceAndIndicatorType() throws TypeMismatchException {
		final Entity russia = entityDAO.getEntityByCodeAndType("RUS", "country");
		final Entity luxembourg = entityDAO.getEntityByCodeAndType("LUX", "country");

		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		final Source sourceWB = sourceDAO.getSourceByCode("WB");
		final Source sourceAcled = sourceDAO.getSourceByCode("acled");
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());

		final DateTime dateTime2013 = new DateTime(2013, 1, 1, 0, 0);
		final Date date2013 = dateTime2013.toDate();
		final Date date2014 = dateTime2013.plusYears(1).toDate();

		indicatorDAO.addIndicator(sourceWB, russia, indicatorType, date2013, date2014, Periodicity.YEAR, true, "10000", "10000$", importFromCKAN);
		indicatorDAO.addIndicator(sourceAcled, russia, indicatorType, date2013, date2014, Periodicity.YEAR, true, "9000", "9000$", importFromCKAN);

		{
			final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp");
			Assert.assertEquals(2, dataTable.getNumberOfColumns());
			Assert.assertEquals("RUS", dataTable.getColumnDescription(1).getId());
			Assert.assertEquals(1, dataTable.getNumberOfRows());
		}

		{
			final DataTable dataTable = curatedDataService.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "per-capita-gdp");
			Assert.assertEquals(2, dataTable.getNumberOfColumns());
			Assert.assertEquals("per-capita-gdp", dataTable.getColumnDescription(1).getId());
			Assert.assertEquals(1, dataTable.getNumberOfRows());
		}

		indicatorDAO.addIndicator(sourceAcled, luxembourg, indicatorType, date2013, date2014, Periodicity.YEAR, true, "100000", "100000$", importFromCKAN);

		{
			final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp");
			Assert.assertEquals(2, dataTable.getNumberOfColumns());
			Assert.assertEquals("RUS", dataTable.getColumnDescription(1).getId());
			Assert.assertEquals(1, dataTable.getNumberOfRows());
		}

		{
			final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "acled", "per-capita-gdp");
			Assert.assertEquals(3, dataTable.getNumberOfColumns());
			Assert.assertEquals("LUX", dataTable.getColumnDescription(1).getId());
			Assert.assertEquals("RUS", dataTable.getColumnDescription(2).getId());
			Assert.assertEquals(1, dataTable.getNumberOfRows());
			Assert.assertEquals(3, dataTable.getRow(0).getCells().size());
			Assert.assertEquals("2013", dataTable.getRow(0).getCell(0).getValue().toString());
			Assert.assertEquals("100000.0", dataTable.getRow(0).getCell(1).getValue().toString());
			Assert.assertEquals("9000.0", dataTable.getRow(0).getCell(2).getValue().toString());

			final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();
			// final String result = GSONBuilderWrapper.getGSON().toJson(dataTable);
			result.toString();
		}

	}
}
