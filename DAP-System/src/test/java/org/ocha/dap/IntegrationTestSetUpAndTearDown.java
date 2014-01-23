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
		entityTypeDAO.addEntityType("country", "Country");

		curatedDataService.addEntity("LUX", "Luxembourg", "country");
		curatedDataService.addEntity("RUS", "Russia", "country");
		curatedDataService.addEntity("RWA", "Rwanda", "country");

		final Text gdp = textDAO.addText("Per capita gdp");
		final Text conflict = textDAO.addText("Incidence of conflict");
		indicatorTypeDAO.addIndicatorType("per-capita-gdp", gdp, "dollar");
		indicatorTypeDAO.addIndicatorType("PVX040", conflict, "Count");

		sourceDAO.addSource("WB", "World Bank");
		sourceDAO.addSource("acled", "Armed Conflict Location and Event Dataset");

		final Source sourceWB = sourceDAO.getSourceByCode("WB");
		final Entity russia = entityDAO.getEntityByCodeAndType("RUS", "country");
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");

		final DateTime dateTime2013 = new DateTime(2013, 1, 1, 0, 0);
		final Date date2013 = dateTime2013.toDate();
		final Date date2014 = dateTime2013.plusYears(1).toDate();

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		indicatorDAO.addIndicator(sourceWB, russia, indicatorType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(10000.0), "10000$", importFromCKAN);
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
