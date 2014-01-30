package org.ocha.dap.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.i18n.TextDAO;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class IndicatorTypeDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(IndicatorTypeDAOImplTest.class);

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private TextDAO textDAO;

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

		logger.info("Testing create indicator type...");
		indicatorTypeDAO.createIndicatorType("per-capita-gdp", textForTest, "dollar", ValueType.NUMBER);

		logger.info("Testing get indicator type by code...");
		final IndicatorType indicatorTypeForCode = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		Assert.assertEquals("Per capita gdp", indicatorTypeForCode.getName().getDefaultValue());

		logger.info("Testing get indicator type by id...");
		final IndicatorType indicatorTypeForId = indicatorTypeDAO.getIndicatorTypeById(indicatorTypeForCode.getId());
		Assert.assertEquals("Per capita gdp", indicatorTypeForId.getName().getDefaultValue());

		logger.info("Testing update indicator type...");
		indicatorTypeDAO.updateIndicatorType(indicatorTypeForId.getId(), "newName", "newUnit", ValueType.DATE);
		final IndicatorType indicatorTypeUpdated = indicatorTypeDAO.getIndicatorTypeById(indicatorTypeForId.getId());
		Assert.assertEquals("newName", indicatorTypeUpdated.getName().getDefaultValue());
		Assert.assertEquals("newUnit", indicatorTypeUpdated.getUnit());
		Assert.assertEquals(ValueType.DATE, indicatorTypeUpdated.getValueType());

		logger.info("Testing list indicator types...");
		Assert.assertEquals(1, indicatorTypeDAO.listIndicatorTypes().size());

		logger.info("Testing delete indicator type by id...");
		indicatorTypeDAO.deleteIndicatorType(indicatorTypeForId.getId());
		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());

		logger.info("Testing delete indicator type by code...");
		indicatorTypeDAO.createIndicatorType("per-capita-gdp", textForTest, "dollar", ValueType.NUMBER);
		indicatorTypeDAO.deleteIndicatorTypeByCode("per-capita-gdp");
		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());

		final Text textToDelete = indicatorTypeForCode.getName();
		textDAO.deleteText(textToDelete.getId());
	}
}
