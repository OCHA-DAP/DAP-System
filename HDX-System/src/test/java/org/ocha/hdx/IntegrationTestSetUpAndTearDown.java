package org.ocha.hdx;

import java.util.Date;

import org.joda.time.DateTime;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.*;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.*;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.service.CuratedDataService;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegrationTestSetUpAndTearDown {

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

	@Autowired
	private CuratedDataService curatedDataService;

	@Autowired
	TextDAO textDAO;

    @Autowired
    private UnitDAO unitDAO;


	public void setUp() {
		final Text text = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", text);

		curatedDataService.createEntity("LUX", "Luxembourg", "country");
		curatedDataService.createEntity("RUS", "Russia", "country");
		curatedDataService.createEntity("RWA", "Rwanda", "country");

        final Text dollarText = textDAO.createText("dollar");
        final Unit dollar = unitDAO.createUnit("dollar", dollarText);
        final Text countText = textDAO.createText("Count");
        final Unit count = unitDAO.createUnit("count", countText);


		final Text gdp = textDAO.createText("Per capita gdp");
		final Text conflict = textDAO.createText("Incidence of conflict");
		indicatorTypeDAO.createIndicatorType("per-capita-gdp", gdp, dollar, ValueType.NUMBER);
		indicatorTypeDAO.createIndicatorType("PVX040", conflict, count, ValueType.NUMBER);

		final Text wb = textDAO.createText("World Bank");
		final Text acled = textDAO.createText("Armed Conflict Location and Event Dataset");
		sourceDAO.createSource("WB", wb, "www.test.com");
		sourceDAO.createSource("acled", acled, "www.test.com");

		final Source sourceWB = sourceDAO.getSourceByCode("WB");
		final Entity russia = entityDAO.getEntityByCodeAndType("RUS", "country");
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");

		final DateTime dateTime2013 = new DateTime(2013, 1, 1, 0, 0);
		final Date date2013 = dateTime2013.toDate();
		final Date date2014 = dateTime2013.plusYears(1).toDate();

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		indicatorDAO.createIndicator(sourceWB, russia, indicatorType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(10000.0), "10000$", "http:www.example.com", importFromCKAN);
	}

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

        unitDAO.deleteUnit(unitDAO.getUnitByCode("dollar").getId());
        unitDAO.deleteUnit(unitDAO.getUnitByCode("count").getId());
	}

}
