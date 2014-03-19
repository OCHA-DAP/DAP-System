package org.ocha.hdx.persistence.dao.view;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.dao.metadata.AdditionalDataDAO;
import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test-pglocal.xml" })
@Ignore
public class IndicatorTypeOverviewDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(IndicatorTypeOverviewDAOImplTest.class);

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
	private AdditionalDataDAO additionalDataDAO ;

	@Autowired
	private IndicatorTypeOverviewDAO indicatorTypeOverviewDAO;

	@Autowired
	private TextDAO textDAO;

	@Before
	public void setUp() {
		/*
		// Create the country entity type
		final Text cetText = textDAO.createText("Country");
		entityTypeDAO.createEntityType("country", cetText);

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
		
		// Create the metadata : dataset summary
		final Text mdsText = textDAO.createText("Dataset summary text");
		additionalDataDAO.createAdditionalData(indicatorType, source, AdditionalData.EntryKey.DATASET_SUMMARY, mdsText);

		// Create the metadata : more info
		final Text mmiText = textDAO.createText("More info text");
		additionalDataDAO.createAdditionalData(indicatorType, source, AdditionalData.EntryKey.MORE_INFO, mmiText);

		// Create the metadata : terms of use
		final Text mtuText = textDAO.createText("Terms of use text");
		additionalDataDAO.createAdditionalData(indicatorType, source, AdditionalData.EntryKey.TERMS_OF_USE, mtuText);

		// Create the metadata : methodology
		final Text mmyText = textDAO.createText("Methodology text");
		additionalDataDAO.createAdditionalData(indicatorType, source, AdditionalData.EntryKey.METHODOLOGY, mmyText);
		*/
	}

	@After
	public void tearDown() {
		/*
		try {
			additionalDataDAO.deleteAdditionalData(additionalDataDAO.getAdditionalDataByIndicatorTypeCodeAndSourceCodeAndEntryKey("PVF020", "faostat3", AdditionalData.EntryKey.METHODOLOGY).getId());
			additionalDataDAO.deleteAdditionalData(additionalDataDAO.getAdditionalDataByIndicatorTypeCodeAndSourceCodeAndEntryKey("PVF020", "faostat3", AdditionalData.EntryKey.TERMS_OF_USE).getId());
			additionalDataDAO.deleteAdditionalData(additionalDataDAO.getAdditionalDataByIndicatorTypeCodeAndSourceCodeAndEntryKey("PVF020", "faostat3", AdditionalData.EntryKey.MORE_INFO).getId());
			additionalDataDAO.deleteAdditionalData(additionalDataDAO.getAdditionalDataByIndicatorTypeCodeAndSourceCodeAndEntryKey("PVF020", "faostat3", AdditionalData.EntryKey.DATASET_SUMMARY).getId());
			
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
	public void testListIndicatorTypeOverview() {
		logger.info("Testing list indicator type overview...");
		final IndicatorTypeOverview indicatorTypeOverview = indicatorTypeOverviewDAO.getIndicatorTypeOverview("PVF020", "faostat3");
		Assert.assertNotNull(indicatorTypeOverview);
		
	}
}
