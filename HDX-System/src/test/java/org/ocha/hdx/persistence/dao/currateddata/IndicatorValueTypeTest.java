package org.ocha.hdx.persistence.dao.currateddata;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.IntegrationTestSetUpAndTearDown;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-integration-test.xml",
		"classpath:/ctx-persistence-test.xml" })
public class IndicatorValueTypeTest {

	@Autowired
	private IntegrationTestSetUpAndTearDown integrationTestSetUpAndTearDown;

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

	@Autowired
	TextDAO textDAO;

	@Before
	public void setUp() {
		this.integrationTestSetUpAndTearDown.setUp();
	}

	@After
	public void tearDown() {
		this.integrationTestSetUpAndTearDown.tearDown();
	}

	@Test
	public void testStrongValueTypeIndicators() {
		Assert.assertEquals(1, this.indicatorDAO.listLastIndicators(100).size());

		final Entity russia = this.entityDAO.getEntityByCodeAndType("RUS", "country");
		final Entity luxembourg = this.entityDAO.getEntityByCodeAndType("LUX", "country");
		final List<String> countriesOnlyLuxembourg = new ArrayList<>();
		countriesOnlyLuxembourg.add("LUX");

		final IndicatorType perCapitaGdp = this.indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		final IndicatorType pvx040 = this.indicatorTypeDAO.getIndicatorTypeByCode("PVX040");

		final List<String> indicatorTypesOnlyPerCapita = new ArrayList<>();
		indicatorTypesOnlyPerCapita.add("per-capita-gdp");

		final Source sourceWB = this.sourceDAO.getSourceByCode("WB");
		final Source sourceAcled = this.sourceDAO.getSourceByCode("acled");
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final ImportFromCKAN importFromCKAN2 = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());

		final DateTime dateTime2012 = new DateTime(2012, 1, 1, 0, 0);
		final Date date2013 = dateTime2012.plusYears(1).toDate();
		final Date date2013Feb = dateTime2012.plusYears(1).plusMonths(1).toDate();
		final Date date2014 = dateTime2012.plusYears(2).toDate();

		this.indicatorDAO.createIndicator(sourceWB, russia, perCapitaGdp, date2013, date2013Feb, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", "http://www.example.com", importFromCKAN);

		this.indicatorDAO.createIndicator(sourceAcled, russia, perCapitaGdp, date2013, date2014, Periodicity.YEAR, new IndicatorValue(9000.0), "9000$", "http://www.example.com", importFromCKAN);

		this.indicatorDAO.createIndicator(sourceAcled, luxembourg, perCapitaGdp, date2013, date2014, Periodicity.YEAR, new IndicatorValue(100000.0), "100000$", "http://www.example.com",
				importFromCKAN);
		this.indicatorDAO.createIndicator(sourceAcled, luxembourg, perCapitaGdp, dateTime2012.plusDays(1).toDate(), dateTime2012.plusDays(2).toDate(), Periodicity.DAY, new IndicatorValue(273.97),
				"237.97$ per day", "http://www.example.com", importFromCKAN);

		this.indicatorDAO.createIndicator(sourceWB, russia, pvx040, date2013, date2014, Periodicity.YEAR, new IndicatorValue(273.97), "237.97$ per day", "http://www.example.com", importFromCKAN2);

		final Text strType = this.textDAO.createText("strType");
		final Text txtType = this.textDAO.createText("txtType");
		final Text dateType = this.textDAO.createText("dateType");
		final Text dateTimeType = this.textDAO.createText("dateTimeType");
		this.indicatorTypeDAO.createIndicatorType("txtType", txtType, "text", ValueType.TEXT);
		this.indicatorTypeDAO.createIndicatorType("strType", strType, "string", ValueType.STRING);
		this.indicatorTypeDAO.createIndicatorType("dateType", dateType, "dateType", ValueType.DATE);
		this.indicatorTypeDAO.createIndicatorType("dateTimeType", dateTimeType, "string", ValueType.DATETIME);

		final IndicatorType strIndType = this.indicatorTypeDAO.getIndicatorTypeByCode("strType");
		final IndicatorType txtIndType = this.indicatorTypeDAO.getIndicatorTypeByCode("txtType");
		final IndicatorType dateIndType = this.indicatorTypeDAO.getIndicatorTypeByCode("dateType");
		final IndicatorType dateTimeIndType = this.indicatorTypeDAO.getIndicatorTypeByCode("dateTimeType");

		final Text txtInd = this.textDAO.createText("A TXT value for Indicator");
		final String strInd = new String("A value string for indicator");
		final Date dateInd = new Date();
		final Date dateTimeInd = new Date();

		this.indicatorDAO.createIndicator(sourceAcled, russia, txtIndType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(txtInd), "TextInitialValuelorepipsulumTextInitialValue",
				"http://www.example.com", importFromCKAN);

		this.indicatorDAO.createIndicator(sourceAcled, russia, strIndType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(strInd), "StringInitialValuelorepipsulumStringInitialValue",
				"http://www.example.com", importFromCKAN);

		this.indicatorDAO.createIndicator(sourceAcled, russia, dateIndType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(dateInd, ValueType.DATE), "DATElorepipsulum",
				"http://www.example.com", importFromCKAN);

		this.indicatorDAO.createIndicator(sourceAcled, russia, dateTimeIndType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(dateTimeInd, ValueType.DATETIME), "DATETIMElorepipsulum",
				"http://www.example.com", importFromCKAN);
		List<Indicator> listLastIndicators = this.indicatorDAO.listLastIndicators(10);
		final List<Long> ids = new ArrayList<Long>();
		for (final Indicator item : listLastIndicators) {
			Assert.assertTrue(isValidIndicatorValueType(item));
			ids.add(item.getId());
		}
		this.indicatorDAO.deleteIndicators(ids);
		listLastIndicators = this.indicatorDAO.listLastIndicators(10);
		Assert.assertEquals(0, listLastIndicators.size());
	}

	private boolean isValidIndicatorValueType(final Indicator ind) {
		Boolean isOK = false;
		final IndicatorType it = ind.getType();
		final IndicatorValue iv = ind.getValue();
		switch (it.getValueType()) {
		case STRING:
			if (iv.getStringValue() != null && "".compareTo(iv.getStringValue()) != 0 && iv.getDatetimeValue() == null && iv.getDateValue() == null && iv.getNumberValue() == null
					&& iv.getTextValue() == null)
				isOK = true;
			break;
		case DATE:
			if (iv.getDateValue() != null && iv.getDatetimeValue() == null && iv.getStringValue() == null && iv.getNumberValue() == null && iv.getTextValue() == null)
				isOK = true;
			break;
		case DATETIME:
			if (iv.getDatetimeValue() != null && iv.getStringValue() == null && iv.getDateValue() == null && iv.getNumberValue() == null && iv.getTextValue() == null)
				isOK = true;
			break;
		case NUMBER:
			if (iv.getNumberValue() != null && iv.getStringValue() == null && iv.getDateValue() == null && iv.getDatetimeValue() == null && iv.getTextValue() == null)
				isOK = true;
			break;
		case TEXT:
			if (iv.getTextValue() != null && iv.getStringValue() == null && iv.getDateValue() == null && iv.getNumberValue() == null && iv.getDatetimeValue() == null)
				isOK = true;
			break;
		default:
			isOK = false;
			break;
		}
		return isOK;
	}

}
