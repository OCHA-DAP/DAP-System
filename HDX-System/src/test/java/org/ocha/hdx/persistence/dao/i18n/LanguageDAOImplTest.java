package org.ocha.hdx.persistence.dao.i18n;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.entity.i18n.Language;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class LanguageDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(LanguageDAOImplTest.class);

	@Autowired
	private LanguageDAO languageDAO;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testListLanguages() {
		logger.info("Testing language list...");

		List<Language> languages = languageDAO.listLanguages();
		Assert.assertEquals(0, languages.size());

		final Language l1 = languageDAO.createLanguage("fakeCode1", "fakeNativeName");
		final Language l2 = languageDAO.createLanguage("fakeCode2", "fakeNativeName");
		final Language l3 = languageDAO.createLanguage("fakeCode3", "fakeNativeName");
		final Language l4 = languageDAO.createLanguage("fakeCode4", "fakeNativeName");
		final Language l5 = languageDAO.createLanguage("fakeCode5", "fakeNativeName");

		languages = languageDAO.listLanguages();
		Assert.assertEquals(5, languages.size());

		languageDAO.deleteLanguage(l3.getCode());
		languages = languageDAO.listLanguages();
		Assert.assertEquals(4, languages.size());

		languageDAO.deleteLanguage(l1.getCode());
		languageDAO.deleteLanguage(l2.getCode());
		languageDAO.deleteLanguage(l4.getCode());
		languageDAO.deleteLanguage(l5.getCode());
		languages = languageDAO.listLanguages();
		Assert.assertEquals(0, languages.size());
	}

	@Test
	public void testLanguageCRUD() {
		logger.info("Testing language CRUD...");

		// Create
		final Language language1 = languageDAO.createLanguage("fakeCode1", "fakeNativeName");
		final Language language2 = languageDAO.createLanguage("fakeCode2", "fakeNativeName");

		// Read
		Language languageByCode = languageDAO.getLanguageByCode(language1.getCode());
		Assert.assertEquals(language1.getCode(), languageByCode.getCode());
		Assert.assertEquals(language1.getNativeName(), languageByCode.getNativeName());

		// Update
		languageDAO.updateLanguage(languageByCode.getCode(), "fakeNativeName2");
		languageByCode = languageDAO.getLanguageByCode(languageByCode.getCode());
		Assert.assertEquals("fakeNativeName2", languageByCode.getNativeName());

		// Delete 1
		languageDAO.deleteLanguage(language1.getCode());
		languageByCode = languageDAO.getLanguageByCode(language1.getCode());
		Assert.assertNull(languageByCode);

		// Delete 2
		languageDAO.deleteLanguage(language2.getCode());
		languageByCode = languageDAO.getLanguageByCode(language2.getCode());
		Assert.assertNull(languageByCode);
	}
}
