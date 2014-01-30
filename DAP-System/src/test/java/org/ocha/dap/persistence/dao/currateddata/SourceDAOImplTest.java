package org.ocha.dap.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.dao.i18n.TextDAO;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class SourceDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(SourceDAOImplTest.class);

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
		sourceDAO.createSource("WB", text);
		final Source source = sourceDAO.getSourceByCode("WB");
		Assert.assertEquals("World Bank", source.getName().getDefaultValue());
		Assert.assertEquals(1, sourceDAO.listSources().size());

		sourceDAO.deleteSourceByCode("WB");
		Assert.assertEquals(0, sourceDAO.listSources().size());

		final Text textToDelete = source.getName();
		textDAO.deleteText(textToDelete.getId());
	}

	@Test
	public void testCreateSource() {
		logger.info("Testing create source...");

		final Text s1 = textDAO.createText("Source 1");
		sourceDAO.createSource("S1", s1);

		final Source sourceForCode = sourceDAO.getSourceByCode("S1");
		Assert.assertEquals("Source 1", sourceForCode.getName().getDefaultValue());
		Assert.assertEquals(1, sourceDAO.listSources().size());

		final Source sourceById = sourceDAO.getSourceById(sourceForCode.getId());
		Assert.assertEquals("Source 1", sourceById.getName().getDefaultValue());

		logger.info("Testing delete source...");
		sourceDAO.deleteSourceByCode("S1");

		Assert.assertEquals(0, sourceDAO.listSources().size());
	}

	@Test
	public void testUpdateSource() {
		logger.info("Testing update source...");

		final Text s1 = textDAO.createText("S1");
		sourceDAO.createSource("S1", s1);
		final Source source = sourceDAO.getSourceByCode("S1");

		sourceDAO.updateSource(source.getId(), "NewName");
		final Source updatedSource = sourceDAO.getSourceById(source.getId());

		Assert.assertEquals("NewName", updatedSource.getName().getDefaultValue());
		Assert.assertEquals(source.getCode(), updatedSource.getCode());
		Assert.assertEquals(source.getId(), updatedSource.getId());

		sourceDAO.deleteSourceByCode("S1");
	}

	@Test
	public void testDeleteSource() {
		logger.info("Testing delete source...");

		final Text s1 = textDAO.createText("S1");
		sourceDAO.createSource("S1", s1);

		final Source sourceForCode = sourceDAO.getSourceByCode("S1");
		final long id = sourceForCode.getId();

		sourceDAO.deleteSource(id);

		Assert.assertNull(sourceDAO.getSourceById(id));
	}
}
