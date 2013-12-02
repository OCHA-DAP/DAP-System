package org.ocha.dap.persistence.dao.currateddata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class IndicatorDAOImplTest {

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Before
	public void setUp() {
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
	public void testListLastIndicators() {
		Assert.assertEquals(0, indicatorDAO.listLastIndicators(100).size());

		final Entity russia = entityDAO.getEntityByCodeAndType("RUS", "country");
		final Entity luxembourg = entityDAO.getEntityByCodeAndType("LUX", "country");

		final IndicatorType perCapitaGdp = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		final IndicatorType pvx040 = indicatorTypeDAO.getIndicatorTypeByCode("PVX040");
		
		List<String> indicatorTypesOnlyPerCapita = new ArrayList<>();
		indicatorTypesOnlyPerCapita.add("per-capita-gdp");
		
		final Source sourceWB = sourceDAO.getSourceByCode("WB");
		final Source sourceAcled = sourceDAO.getSourceByCode("acled");
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final ImportFromCKAN importFromCKAN2 = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());

		final DateTime dateTime2012 = new DateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();
		final Date date2014 = dateTime2012.plusYears(2).toDate();

		indicatorDAO.addIndicator(sourceWB, russia, perCapitaGdp, date2013, date2014, Periodicity.YEAR, true, "10000", "10000$", importFromCKAN);
		Assert.assertEquals(1, indicatorDAO.listLastIndicators(100).size());

		try {
			indicatorDAO.addIndicator(sourceWB, russia, perCapitaGdp, date2013, date2014, Periodicity.YEAR, true, "10000", "10000$", importFromCKAN);
			Assert.fail("Should not be possible to add the same value twice, multiple column constraint not enforced");
		} catch (final PersistenceException e) {
			// Expected behavior () caused by a ConstraintViolationException
		}

		indicatorDAO.addIndicator(sourceAcled, russia, perCapitaGdp, date2013, date2014, Periodicity.YEAR, true, "9000", "9000$", importFromCKAN);
		Assert.assertEquals(2, indicatorDAO.listLastIndicators(100).size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, "country", "RUS", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorTypes(2013, "WB", indicatorTypesOnlyPerCapita).size());

		indicatorDAO.addIndicator(sourceAcled, luxembourg, perCapitaGdp, date2013, date2014, Periodicity.YEAR, true, "100000", "100000$", importFromCKAN);
		Assert.assertEquals(3, indicatorDAO.listLastIndicators(100).size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, "country", "RUS", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "acled", "per-capita-gdp").size());

		indicatorDAO.addIndicator(sourceAcled, luxembourg, perCapitaGdp, dateTime2012.plusDays(1).toDate(), dateTime2012.plusDays(2).toDate(), Periodicity.DAY, true, "273.97", "237.97$ per day",
				importFromCKAN);

		Assert.assertEquals(4, indicatorDAO.listLastIndicators(100).size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, "country", "RUS", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "acled", "per-capita-gdp").size());

		indicatorDAO.addIndicator(sourceAcled, luxembourg, perCapitaGdp, dateTime2012.plusDays(2).toDate(), dateTime2012.plusDays(3).toDate(), Periodicity.DAY, true, "273.97", "237.97$ per day",
				importFromCKAN2);

		Assert.assertEquals(5, indicatorDAO.listLastIndicators(100).size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, "country", "RUS", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "per-capita-gdp").size());

		indicatorDAO.addIndicator(sourceWB, luxembourg, perCapitaGdp, date2012, date2013, Periodicity.YEAR, true, "273.97", "237.97$ per day", importFromCKAN2);

		Assert.assertEquals(6, indicatorDAO.listLastIndicators(100).size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, "country", "RUS", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2012, "WB", "per-capita-gdp").size());

		indicatorDAO.addIndicator(sourceWB, russia, perCapitaGdp, date2012, date2013, Periodicity.YEAR, true, "273.97", "237.97$ per day", importFromCKAN2);

		Assert.assertEquals(7, indicatorDAO.listLastIndicators(100).size());
		Assert.assertEquals(3, indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, "WB", "per-capita-gdp").size());
		Assert.assertEquals(3, indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, "country", "RUS", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "per-capita-gdp").size());
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2012, "WB", "per-capita-gdp").size());
		
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorTypes(2013, "WB", indicatorTypesOnlyPerCapita).size());
		
		indicatorDAO.addIndicator(sourceWB, russia, pvx040, date2013, date2014, Periodicity.YEAR, true, "273.97", "237.97$ per day", importFromCKAN2);
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorTypes(2013, "WB", indicatorTypesOnlyPerCapita).size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "per-capita-gdp").size());
		Assert.assertEquals(1, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(2013, "WB", "PVX040").size());

		List<String> indicatorTypes = new ArrayList<>();
		indicatorTypes.add("per-capita-gdp");
		indicatorTypes.add("PVX040");
		
		Assert.assertEquals(2, indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorTypes(2013, "WB", indicatorTypes).size());
		
		indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());

		Assert.assertEquals(4, indicatorDAO.listLastIndicators(100).size());

	}
}
