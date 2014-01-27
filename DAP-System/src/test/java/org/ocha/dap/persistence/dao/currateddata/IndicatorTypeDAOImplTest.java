package org.ocha.dap.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.i18n.TextDAO;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class IndicatorTypeDAOImplTest {

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private TextDAO textDAO;

	@Test
	public void testListIndicatorTypes() {
		try {
			indicatorTypeDAO.getIndicatorTypeByCode("CodeFromUnitTest");
			Assert.fail("Should have raised a NoResultException");
		} catch (final NoResultException e) {
			// expected
		}

		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());
		final Text textForTest = textDAO.createText("Per capita gdp");
		indicatorTypeDAO.addIndicatorType("per-capita-gdp", textForTest, "dollar");
		final IndicatorType indicatorTypeForCode = indicatorTypeDAO.getIndicatorTypeByCode("per-capita-gdp");
		Assert.assertEquals("Per capita gdp", indicatorTypeForCode.getName().getDefaultValue());
		Assert.assertEquals(1, indicatorTypeDAO.listIndicatorTypes().size());

		indicatorTypeDAO.deleteIndicatorTypeByCode("per-capita-gdp");
		Assert.assertEquals(0, indicatorTypeDAO.listIndicatorTypes().size());

		final Text textToDelete = indicatorTypeForCode.getName();
		textDAO.deleteText(textToDelete);

	}

}
