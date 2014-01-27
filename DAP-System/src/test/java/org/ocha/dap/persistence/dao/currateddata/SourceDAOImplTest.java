package org.ocha.dap.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.i18n.TextDAO;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class SourceDAOImplTest {

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private TextDAO textDAO;

	@Test
	public void testListSources() {
		try {
			sourceDAO.getSourceByCode("CodeFromUnitTest");
			Assert.fail("Should have raised a NoResultException");
		} catch (final NoResultException e) {
			// expected
		}

		Assert.assertEquals(0, sourceDAO.listSources().size());

		final Text text = textDAO.createText("World Bank");
		sourceDAO.addSource("WB", text);
		final Source source = sourceDAO.getSourceByCode("WB");
		Assert.assertEquals("World Bank", source.getName().getDefaultValue());
		Assert.assertEquals(1, sourceDAO.listSources().size());

		sourceDAO.deleteSourceByCode("WB");
		Assert.assertEquals(0, sourceDAO.listSources().size());

		final Text textToDelete = source.getName();
		textDAO.deleteText(textToDelete);
	}
}
