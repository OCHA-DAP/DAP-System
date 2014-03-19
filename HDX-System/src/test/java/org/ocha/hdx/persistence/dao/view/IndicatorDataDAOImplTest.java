package org.ocha.hdx.persistence.dao.view;

import java.util.Map;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.view.IndicatorData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test-pglocal.xml" })
@Ignore
public class IndicatorDataDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(IndicatorDataDAOImplTest.class);

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private UnitDAO unitDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private IndicatorDataDAO indicatorDataDAO;

	@Autowired
	private TextDAO textDAO;

	@Before
	public void setUp() {
		/*
		// Create the country entity type
		final Text cetText = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", cetText);
		final EntityType cEntityType = entityTypeDAO.getEntityTypeByCode("country");

		// Create the BEL entity 
		final Text bText = textDAO.createText("Belgium");
		entityDAO.createEntity("BEL", bText, cEntityType);
		final Entity entity = entityDAO.getEntityByCodeAndType("BEL", "country");

		// Create the faostat3 source
		final Text sText = textDAO.createText("Faostat 3");
		sourceDAO.createSource("faostat3", sText, "www.faostat3.com");
		final Source source = sourceDAO.getSourceByCode("faostat3");

		// Create the unit
		final Text uText = textDAO.createText("Unit");
		unitDAO.createUnit("unit", uText);
		final Unit unit = unitDAO.getUnitByCode("unit");

		// Create the indicator type
		final Text itText = textDAO.createText("PVF020 name");
		indicatorTypeDAO.createIndicatorType("PVF020", itText, unit, ValueType.NUMBER);
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("PVF020");

		// Create the import from CKAN
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.getDummyImport();

		// Create the indicators
		final Calendar iDate = new GregorianCalendar();
		final double value = 1000.01d;
		for (int i = 1998; i < 2013; i++) {
			iDate.set(Calendar.YEAR, i);
			final IndicatorValue indicatorValue = new IndicatorValue(value + i);
			indicatorDAO.createIndicator(source, entity, indicatorType, iDate.getTime(), null, Periodicity.YEAR, indicatorValue, "initialValue", "www.sourcelink.com", importFromCKAN);
		}
		*/
	}

	@After
	public void tearDown() {
		/*
		try {
			indicatorDAO.deleteAllIndicators();
			importFromCKANDAO.deleteImportFromCKAN(importFromCKANDAO.getDummyImport().getId()); // Useful ?
			indicatorTypeDAO.deleteIndicatorTypeByCode("PVF020");
			unitDAO.deleteUnit(unitDAO.getUnitByCode("unit").getId());
			sourceDAO.deleteSourceByCode("faostat3");
			entityDAO.deleteEntityByCodeAndType("BEL", "country");
			entityTypeDAO.deleteEntityTypeByCode("country");
		} catch (final Exception e) {
		}
		*/
	}

	@Test
	public void testListIndicatorData() {
		logger.info("Testing list indicator data...");
		final Map<Long, Map<String, IndicatorData>> data = indicatorDataDAO.getIndicatorData("PVF020", "faostat3", 1998l, 2014l);
		for (final Long year : new TreeSet<Long>(data.keySet()).descendingSet()) {
			logger.debug("Indicator data year : " + year);
			final Map<String, IndicatorData> countryMap = data.get(year);
			for (final String countryCode : new TreeSet<String>(countryMap.keySet())) {
				logger.debug("\tCountry code : " + countryCode);
			}
			
		}
		Assert.assertNotNull(data);
	}
}
