package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.IntegrationTestSetUpAndTearDown;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorTypeCount;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml", "classpath:/ctx-integration-test.xml" })
public class IndicatorTypeDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(IndicatorTypeDAOImplTest.class);

	@Autowired
	private IntegrationTestSetUpAndTearDown integrationTestSetUpAndTearDown;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private UnitDAO unitDAO;

	@Test
	public void testIndicatorTypeCounts() {
		final List<IndicatorTypeCount> listIndicatorTypeCounts = indicatorTypeDAO.listIndicatorTypeCounts();
		Assert.assertEquals(0, listIndicatorTypeCounts.size());

		// No more test related to a view...
		
		/*
		integrationTestSetUpAndTearDown.setUp();
		listIndicatorTypeCounts = indicatorTypeDAO.listIndicatorTypeCounts();
		Assert.assertEquals(5, listIndicatorTypeCounts.size());
		integrationTestSetUpAndTearDown.tearDown();
		*/
}

	@Test
	public void testIndicatorTypesCRUD() {

		try {
			indicatorTypeDAO.getIndicatorTypeByCode("CodeFromUnitTest");
			Assert.fail("Should have raised a NoResultException");
		} catch (final NoResultException e) {
			// expected
		}

		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());
		final Text textForTest = textDAO.createText("Per capita gdp");

		final Text dollarText = textDAO.createText("dollar");
		final Unit dollar = unitDAO.createUnit("dollar", dollarText);

		logger.info("Testing create indicator type...");
		indicatorTypeDAO.createIndicatorType("per-capita-gdp", textForTest, dollar, ValueType.NUMBER);

		logger.info("Testing get indicator type by code...");
		final IndicatorType indicatorTypeForCode = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		Assert.assertEquals("Per capita gdp", indicatorTypeForCode.getName().getDefaultValue());

		logger.info("Testing get indicator type by id...");
		final IndicatorType indicatorTypeForId = indicatorTypeDAO.getIndicatorTypeById(indicatorTypeForCode.getId());
		Assert.assertEquals("Per capita gdp", indicatorTypeForId.getName().getDefaultValue());

		final Text newUnitText = textDAO.createText("newUnit");
		final Unit newUnit = unitDAO.createUnit("newUnit", newUnitText);

		logger.info("Testing update indicator type...");
		indicatorTypeDAO.updateIndicatorType(indicatorTypeForId.getId(), "newCode", "newName", newUnit, ValueType.DATE);
		final IndicatorType indicatorTypeUpdated = indicatorTypeDAO.getIndicatorTypeById(indicatorTypeForId.getId());
		Assert.assertEquals("newCode", indicatorTypeUpdated.getCode());
		Assert.assertEquals("newName", indicatorTypeUpdated.getName().getDefaultValue());
		Assert.assertEquals("newUnit", indicatorTypeUpdated.getUnit().getName().getDefaultValue());
		Assert.assertEquals(ValueType.DATE, indicatorTypeUpdated.getValueType());

		logger.info("Testing list indicator types...");
		Assert.assertEquals(1, indicatorTypeDAO.listIndicatorTypes().size());

		logger.info("Testing delete indicator type by id...");
		indicatorTypeDAO.deleteIndicatorType(indicatorTypeForId.getId());
		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());

		logger.info("Testing delete indicator type by code...");
		indicatorTypeDAO.createIndicatorType("per-capita-gdp", textForTest, dollar, ValueType.NUMBER);
		indicatorTypeDAO.deleteIndicatorTypeByCode("per-capita-gdp");
		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());

		final Text textToDelete = indicatorTypeForCode.getName();
		textDAO.deleteText(textToDelete.getId());

		textDAO.deleteText(dollarText.getId());
		textDAO.deleteText(newUnitText.getId());

		unitDAO.deleteUnit(newUnit.getId());
		unitDAO.deleteUnit(dollar.getId());
	}
}
