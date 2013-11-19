package org.ocha.dap.persistence.dao.currateddata;

import java.util.Date;

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
		entityDAO.addEntity("RU", "Russia", entityTypeForCode);

		indicatorTypeDAO.addIndicatorType("per-capita-gdp", "Per capita gdp", "dollar");

		sourceDAO.addSource("WB", "World Bank");
	}

	@After
	public void tearDown() {
		indicatorDAO.deleteAllIndicators();
		entityDAO.deleteEntityByCodeAndType("RU", "country");
		entityTypeDAO.deleteEntityTypeByCode("country");
		indicatorTypeDAO.deleteIndicatorTypeByCode("per-capita-gdp");
		sourceDAO.deleteSourceByCode("WB");
	}

	@Test
	public void testListLastIndicators() {
		Assert.assertEquals(0, indicatorDAO.listLastIndicators(100).size());

		final Entity entity = entityDAO.getEntityByCodeAndType("RU", "country");
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		final Source source = sourceDAO.getSourceByCode("WB");
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());

		final DateTime dateTime = new DateTime(2013, 1, 1, 0, 0);
		final Date start = dateTime.toDate();
		final Date end = dateTime.plusYears(1).toDate();

		indicatorDAO.addIndicator(source, entity, indicatorType, start, end, Periodicity.YEAR, true, "10000", "10000$", importFromCKAN);
		Assert.assertEquals(1, indicatorDAO.listLastIndicators(100).size());

		try {
			indicatorDAO.addIndicator(source, entity, indicatorType, start, end, Periodicity.YEAR, true, "10000", "10000$", importFromCKAN);
			Assert.fail("Should not be possible to add the same value twice, multiple column constraint not enforced");
		} catch (final PersistenceException e) {
			// Expected behavior () caused by a ConstraintViolationException
		}

	}
}
