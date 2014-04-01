package org.ocha.hdx.persistence.dao.dictionary;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
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
		sourceDAO.createSource("WB", wb, "www.test.com", null);
		sourceDAO.createSource("acled", acled, "www.test.com", null);
	}

	@After
	public void tearDown() {
		sourceDictionaryDAO.deleteAllSourceDictionaries();

		sourceDAO.deleteSourceByCode("WB");
		sourceDAO.deleteSourceByCode("acled");
	}

	@Test
	public void testSourceDictionaries() {
		List<SourceDictionary> sourceDictionaryList = sourceDictionaryDAO.listSourceDictionaries();
		Assert.assertEquals(0, sourceDictionaryList.size());

		final Source sourceWB = sourceDAO.getSourceByCode("WB");

		sourceDictionaryDAO.createSourceDictionary("World Bank", "scraper", sourceWB);
		sourceDictionaryDAO.createSourceDictionary("World B.", "another", sourceWB);

		sourceDictionaryList = sourceDictionaryDAO.listSourceDictionaries();
		Assert.assertEquals(2, sourceDictionaryList.size());
		Assert.assertEquals(1, sourceDictionaryDAO.getSourceDictionariesByImporter("scraper").size());

		// delete a SourceDictionary by object
		final SourceDictionary sourceDictionaryToDelete = sourceDictionaryList.get(0);
		sourceDictionaryDAO.deleteSourceDictionary(sourceDictionaryToDelete);
		sourceDictionaryList = sourceDictionaryDAO.listSourceDictionaries();
		Assert.assertEquals("After deletion, there should be 1 SourceDictionaries in the table.", 1, sourceDictionaryList.size());

		// add this back to avoid breaking the next test
		sourceDictionaryDAO.createSourceDictionary(sourceDictionaryToDelete.getId().getUnnormalizedName(), sourceDictionaryToDelete.getId().getImporter(), sourceDictionaryToDelete.getSource());
		sourceDictionaryList = sourceDictionaryDAO.listSourceDictionaries();
		Assert.assertEquals("source_dictionary table should now have 2 entries", 2, sourceDictionaryList.size());

		// delete a specific SourceDictionary by unique fields
		final String unnormalizedName = sourceDictionaryToDelete.getId().getUnnormalizedName();
		final String importer = sourceDictionaryToDelete.getId().getImporter();
		sourceDictionaryDAO.deleteSourceDictionary(unnormalizedName, importer);
		sourceDictionaryList = sourceDictionaryDAO.listSourceDictionaries();
		Assert.assertEquals("After deletion, there should be 1 SourceDictionaries in the table.", 1, sourceDictionaryList.size());
	}
}
