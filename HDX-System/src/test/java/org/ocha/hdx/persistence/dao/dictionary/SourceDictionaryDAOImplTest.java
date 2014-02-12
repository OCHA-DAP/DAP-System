package org.ocha.hdx.persistence.dao.dictionary;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class SourceDictionaryDAOImplTest {

	@Autowired
	private SourceDictionaryDAO sourceDictionaryDAO;

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Before
	public void setUp() {
		final Text wb = textDAO.createText("World Bank");
		final Text acled = textDAO.createText("Armed Conflict Location and Event Dataset");
		sourceDAO.createSource("WB", wb, "www.test.com");
		sourceDAO.createSource("acled", acled, "www.test.com");
	}

	@After
	public void tearDown() {
		sourceDictionaryDAO.deleteAllSourceDictionaries();

		sourceDAO.deleteSourceByCode("WB");
		sourceDAO.deleteSourceByCode("acled");
	}

	@Test
	public void testListSourceDictionaries() {
		Assert.assertEquals(0, sourceDictionaryDAO.listSourceDictionaries().size());

		final Source sourceWB = sourceDAO.getSourceByCode("WB");

		sourceDictionaryDAO.createSourceDictionary("World Bank", "scraper", sourceWB);
		sourceDictionaryDAO.createSourceDictionary("World B.", "another", sourceWB);

		Assert.assertEquals(2, sourceDictionaryDAO.listSourceDictionaries().size());
		Assert.assertEquals(1, sourceDictionaryDAO.getSourceDictionariesByImporter("scraper").size());
	}

}