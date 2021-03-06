package org.ocha.hdx;

import java.util.Date;

import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName;
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
	private DataSerieMetadataDAO dataSerieMetadataDAO;

	@Autowired
	private DataSerieToCuratedDatasetDAO dataSerieToCuratedDatasetDAO;

	@Autowired
	TextDAO textDAO;

	@Autowired
	private UnitDAO unitDAO;

	public void setUp() {
		final Text text = textDAO.createText("Country");
		final EntityType countryEntityType = entityTypeDAO.createEntityType("country", text);

		final Text Luxembourgtext = textDAO.createText("Luxembourg");
		final Text Russiatext = textDAO.createText("Russia");
		final Text Rwandatext = textDAO.createText("Rwanda");
		entityDAO.createEntity("LUX", Luxembourgtext, countryEntityType);
		entityDAO.createEntity("RUS", Russiatext, countryEntityType);
		entityDAO.createEntity("RWA", Rwandatext, countryEntityType);

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
		sourceDAO.createSource("WB", wb, "www.test.com", null);
		sourceDAO.createSource("acled", acled, "www.test.com", null);

		final Source sourceWB = sourceDAO.getSourceByCode("WB");
		final Entity russia = entityDAO.getEntityByCodeAndType("RUS", "country");
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");

		final DateTime dateTime2013 = new DateTime(2013, 1, 1, 0, 0);
		final Date date2013 = dateTime2013.toDate();
		final Date date2014 = dateTime2013.plusYears(1).toDate();

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		indicatorDAO.createIndicator(sourceWB, russia, indicatorType, date2013, date2014, Periodicity.YEAR, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS, "http:www.example.com",
				importFromCKAN);

		/*
		 * Reports
		 */

		// Country overview

		// Source
		final Text m49 = textDAO.createText("m49");
		sourceDAO.createSource("m49", m49, "www.m49.com", null);
		final Source sourceM49 = sourceDAO.getSourceByCode("m49");

		// Entity
		final Text Colombiatext = textDAO.createText("Colombia");
		entityDAO.createEntity("COL", Colombiatext, countryEntityType);
		final Entity colombia = entityDAO.getEntityByCodeAndType("COL", "country");

		// Indicator type
		final Text m49_num = textDAO.createText("m49-num");
		indicatorTypeDAO.createIndicatorType("CG060", m49_num, dollar, ValueType.NUMBER);
		final IndicatorType cg060 = indicatorTypeDAO.getIndicatorTypeByCode("CG060");

		// Indicator
		final IndicatorValue indicatorValue = new IndicatorValue(170d);
		indicatorDAO.createIndicator(sourceM49, colombia, cg060, new Date(), null, Indicator.Periodicity.YEAR, indicatorValue, "0", ValidationStatus.SUCCESS, null, importFromCKAN);

	}

	public void tearDown() {
		indicatorDAO.deleteAllIndicators();

		entityDAO.deleteEntityByCodeAndType("LUX", "country");
		entityDAO.deleteEntityByCodeAndType("RUS", "country");
		entityDAO.deleteEntityByCodeAndType("RWA", "country");
		entityDAO.deleteEntityByCodeAndType("COL", "country");

		entityTypeDAO.deleteEntityTypeByCode("country");

		dataSerieToCuratedDatasetDAO.deleteAllDataSerieToCuratedDataset();

		indicatorTypeDAO.deleteIndicatorTypeByCode("per-capita-gdp");
		indicatorTypeDAO.deleteIndicatorTypeByCode("PVX040");
		indicatorTypeDAO.deleteIndicatorTypeByCode("CG060");

		sourceDAO.deleteSourceByCode("WB");
		sourceDAO.deleteSourceByCode("acled");

		unitDAO.deleteUnit(unitDAO.getUnitByCode("dollar").getId());
		unitDAO.deleteUnit(unitDAO.getUnitByCode("count").getId());

		sourceDAO.deleteSourceByCode("m49");

	}

	public void setUpDataForCountryOverview() {
		EntityType countryEntityType = null;
		try {
			final Text text = textDAO.createText("Country");
			countryEntityType = entityTypeDAO.createEntityType("country", text);
		} catch (final ConstraintViolationException | PersistenceException e) {
			// Might have been created by another setup
			countryEntityType = entityTypeDAO.getEntityTypeByCode("country");
		}

		try {
			final Text USAtext = textDAO.createText("USA");
			entityDAO.createEntity("USA", USAtext, countryEntityType);
		} catch (final ConstraintViolationException | PersistenceException e) {
			// Might have been created by another setup
		}

		final Text geo = textDAO.createText("Wikipedia: geography");
		final Text urlText = textDAO.createText("Url");
		final Unit url = unitDAO.createUnit("url", urlText);
		indicatorTypeDAO.createIndicatorType("CD010", geo, url, ValueType.STRING);

		final Entity usa = entityDAO.getEntityByCodeAndType("USA", "country");
		final Source sourceWB = sourceDAO.getSourceByCode("WB");
		final IndicatorType indicatorTypeCD010 = indicatorTypeDAO.getIndicatorTypeByCode("CD010");

		final DateTime dateTime2013 = new DateTime(2013, 1, 1, 0, 0);
		final Date date2013 = dateTime2013.toDate();
		final Date date2014 = dateTime2013.plusYears(1).toDate();

		indicatorDAO.createIndicator(sourceWB, usa, indicatorTypeCD010, date2013, date2014, Periodicity.YEAR, new IndicatorValue("Url for Usa"), "Url for Usa", ValidationStatus.SUCCESS,
				"http:www.example.com", importFromCKANDAO.listImportsFromCKAN().get(0));

	}

	public void tearDownDataForCountryOverview() {
		indicatorDAO.deleteAllIndicators();

		try {
			entityDAO.deleteEntityByCodeAndType("USA", "country");
		} catch (final Exception e) {
			// Might have been deleted by another setup
		}

		indicatorTypeDAO.deleteIndicatorTypeByCode("CD010");

		unitDAO.deleteUnit(unitDAO.getUnitByCode("url").getId());

	}

	public void setUpDataForCountryCrisisHistory() {
		EntityType countryEntityType = null;
		try {
			final Text text = textDAO.createText("Country");
			countryEntityType = entityTypeDAO.createEntityType("country", text);
		} catch (final ConstraintViolationException | PersistenceException e) {
			// Might have been created by another setup
			countryEntityType = entityTypeDAO.getEntityTypeByCode("country");
		}

		try {
			final Text USAtext = textDAO.createText("USA");
			entityDAO.createEntity("USA", USAtext, countryEntityType);
		} catch (final ConstraintViolationException | PersistenceException e) {
			// Might have been created by another setup
		}

		final Text unoText = textDAO.createText("uno");
		final Unit uno = unitDAO.createUnit("uno", unoText);

		final Text nod = textDAO.createText("Number of disasters");
		final Text pkid = textDAO.createText("People killed in disasters");

		indicatorTypeDAO.createIndicatorType("CH070", nod, uno, ValueType.NUMBER);
		indicatorTypeDAO.createIndicatorType("CH080", pkid, uno, ValueType.NUMBER);

		final Text emdat = textDAO.createText("emdat");
		sourceDAO.createSource("emdat", emdat, "www.test.com", null);

		final Entity usa = entityDAO.getEntityByCodeAndType("USA", "country");
		final Source sourceEmdat = sourceDAO.getSourceByCode("emdat");
		final IndicatorType indicatorTypeCH070 = indicatorTypeDAO.getIndicatorTypeByCode("CH070");
		final IndicatorType indicatorTypeCH080 = indicatorTypeDAO.getIndicatorTypeByCode("CH080");

		final DateTime dateTime2008 = new DateTime(2008, 1, 1, 0, 0);
		final Date date2008 = dateTime2008.toDate();
		final Date date2009 = dateTime2008.plusYears(1).toDate();
		final Date date2010 = dateTime2008.plusYears(2).toDate();

		indicatorDAO.createIndicator(sourceEmdat, usa, indicatorTypeCH070, date2008, date2009, Periodicity.YEAR, new IndicatorValue(5.0), "5", ValidationStatus.SUCCESS, "www.disasters.com",
				importFromCKANDAO.listImportsFromCKAN().get(0));

		indicatorDAO.createIndicator(sourceEmdat, usa, indicatorTypeCH080, date2009, date2010, Periodicity.YEAR, new IndicatorValue(1000.0), "1000", ValidationStatus.SUCCESS, "www.disasters.com",
				importFromCKANDAO.listImportsFromCKAN().get(0));

		final Text extracted = textDAO.createText("Extracted from 1st hand sources");
		dataSerieMetadataDAO.createDataSerieMetadata(indicatorTypeCH080, sourceEmdat, MetadataName.DATASET_SUMMARY, extracted);

		final Text moreInfo = textDAO.createText("http://mdgs.un.org/unsd/mdg/Metadata.aspx?IndicatorId=0&SeriesId=589");
		dataSerieMetadataDAO.createDataSerieMetadata(indicatorTypeCH080, sourceEmdat, MetadataName.MORE_INFO, moreInfo);

	}

	public void tearDownDataForCountryCrisisHistory() {
		final DataSerieMetadata datasetSummary = dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey("CH080", "emdat", MetadataName.DATASET_SUMMARY);
		dataSerieMetadataDAO.deleteDataSerieMetadata(datasetSummary.getId());

		final DataSerieMetadata moreInfo = dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey("CH080", "emdat", MetadataName.MORE_INFO);
		dataSerieMetadataDAO.deleteDataSerieMetadata(moreInfo.getId());

		indicatorDAO.deleteAllIndicators();

		try {
			entityDAO.deleteEntityByCodeAndType("USA", "country");
		} catch (final Exception e) {
			// Might have been deleted by another setup
		}

		indicatorTypeDAO.deleteIndicatorTypeByCode("CH070");
		indicatorTypeDAO.deleteIndicatorTypeByCode("CH080");

		sourceDAO.deleteSourceByCode("emdat");

		try {
			unitDAO.deleteUnit(unitDAO.getUnitByCode("uno").getId());
		} catch (final Exception e) {
			// Might have been deleted by another setup
		}

	}

	public void setUpDataForCountry5Years() {
		EntityType countryEntityType = null;
		try {
			final Text text = textDAO.createText("Country");
			countryEntityType = entityTypeDAO.createEntityType("country", text);
		} catch (final ConstraintViolationException | PersistenceException e) {
			// Might have been created by another setup
			countryEntityType = entityTypeDAO.getEntityTypeByCode("country");
		}

		try {
			final Text USAtext = textDAO.createText("USA");
			entityDAO.createEntity("USA", USAtext, countryEntityType);
		} catch (final ConstraintViolationException | PersistenceException e) {
			// Might have been created by another setup
		}

		final Text unoText = textDAO.createText("uno");
		final Unit uno = unitDAO.createUnit("uno", unoText);

		final Text nod = textDAO.createText("Number of disasters");

		indicatorTypeDAO.createIndicatorType("PVH200", nod, uno, ValueType.NUMBER);

		final Text esaunpdWPP2012 = textDAO.createText("esa-unpd-wpp2012");
		sourceDAO.createSource("esa-unpd-wpp2012", esaunpdWPP2012, "www.test.com", null);

		final Entity usa = entityDAO.getEntityByCodeAndType("USA", "country");
		final Source sourceesaunpdWPP2012 = sourceDAO.getSourceByCode("esa-unpd-wpp2012");
		final IndicatorType indicatorTypeF02 = indicatorTypeDAO.getIndicatorTypeByCode("PVH200");

		final DateTime dateTime2008 = new DateTime(2008, 1, 1, 0, 0);
		final Date date2008 = dateTime2008.toDate();
		final Date date2013 = dateTime2008.plusYears(5).toDate();

		indicatorDAO.createIndicator(sourceesaunpdWPP2012, usa, indicatorTypeF02, date2008, date2013, Periodicity.FIVE_YEARS, new IndicatorValue(5.0), "5", ValidationStatus.SUCCESS,
				"www.disasters.com", importFromCKANDAO.listImportsFromCKAN().get(0));

		final Text extracted = textDAO.createText("Average for 5 years");
		dataSerieMetadataDAO.createDataSerieMetadata(indicatorTypeF02, sourceesaunpdWPP2012, MetadataName.DATASET_SUMMARY, extracted);
	}

	public void tearDownDataForCountry5Years() {
		final DataSerieMetadata dataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey = dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey("PVH200",
				"esa-unpd-wpp2012", MetadataName.DATASET_SUMMARY);
		dataSerieMetadataDAO.deleteDataSerieMetadata(dataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey.getId());
		indicatorDAO.deleteAllIndicators();

		try {
			entityDAO.deleteEntityByCodeAndType("USA", "country");
		} catch (final Exception e) {
			// Might have been deleted by another setup
		}

		indicatorTypeDAO.deleteIndicatorTypeByCode("PVH200");

		sourceDAO.deleteSourceByCode("esa-unpd-wpp2012");

		try {
			unitDAO.deleteUnit(unitDAO.getUnitByCode("uno").getId());
		} catch (final Exception e) {
			// Might have been deleted by another setup
		}

	}
}
