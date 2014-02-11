package org.ocha.dap;

import java.util.Date;

import org.joda.time.DateTime;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.SourceDAO;
import org.ocha.dap.persistence.dao.i18n.TextDAO;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorValue;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.ocha.dap.service.CuratedDataService;
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

	public void setUp() {
		final Text text = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", text);

		curatedDataService.createEntity("LUX", "Luxembourg", "country");
		curatedDataService.createEntity("RUS", "Russia", "country");
		curatedDataService.createEntity("RWA", "Rwanda", "country");

		final Text gdp = textDAO.createText("Per capita gdp");
		final Text conflict = textDAO.createText("Incidence of conflict");
		indicatorTypeDAO.createIndicatorType("per-capita-gdp", gdp, "dollar", ValueType.NUMBER);
		indicatorTypeDAO.createIndicatorType("PVX040", conflict, "Count", ValueType.NUMBER);

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
	}

}
