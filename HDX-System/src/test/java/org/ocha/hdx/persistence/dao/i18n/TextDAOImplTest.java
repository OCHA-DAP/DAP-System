package org.ocha.hdx.persistence.dao.i18n;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.i18n.Translation;
import org.ocha.hdx.persistence.entity.i18n.Translation.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class TextDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(TextDAOImplTest.class);

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private LanguageDAO languageDAO;

	@Before
	public void setUp() {
		languageDAO.createLanguage("FL", "Fake language");
		languageDAO.createLanguage("FL2", "Fake language2");
	}

	@After
	public void tearDown() {
		languageDAO.deleteLanguage("FL");
		languageDAO.deleteLanguage("FL2");
	}

	@Test
	public void testTextCRUD() {
		logger.info("Testing text CRUD...");

		// Create
		final Text text = textDAO.createText("Test text");

		// Read
		Text textById = textDAO.getTextById(text.getId());
		Assert.assertEquals(text.getId(), textById.getId());
		Assert.assertEquals(text.getDefaultValue(), textById.getDefaultValue());

		// TODO Update

		// Delete
		textDAO.deleteText(text.getId());
		textById = textDAO.getTextById(text.getId());

		Assert.assertNull(textById);
	}

	@Test
	public void testTranslationCRUD() {
		logger.info("Testing translation CRUD...");

		final Text text = textDAO.createText("Fake text");

		// Create
		textDAO.createTranslationForText(text.getId(), "FL", "Fake translation value");

		// Read
		List<Translation> translations = textDAO.getTranslationsForText(text.getId());

		Assert.assertEquals(1, translations.size());
		Translation translation = translations.get(0);
		Id id = translation.getId();

		Assert.assertEquals("FL", id.getLanguage().getCode());
		Assert.assertEquals(text.getId(), id.getText().getId());
		Assert.assertEquals("Fake translation value", translation.getValue());

		// TODO Update
		// Update
		textDAO.updateTranslation(text.getId(), translation.getId().getLanguage().getCode(), "Fake translation value 2");
		translations = textDAO.getTranslationsForText(text.getId());

		Assert.assertEquals(1, translations.size());
		translation = translations.get(0);
		id = translation.getId();

		Assert.assertEquals("FL", id.getLanguage().getCode());
		Assert.assertEquals(text.getId(), id.getText().getId());
		Assert.assertEquals("Fake translation value 2", translation.getValue());

		// Delete
		textDAO.deleteTranslation(text.getId(), translation.getId().getLanguage().getCode());
		translations = textDAO.getTranslationsForText(text.getId());
		Assert.assertEquals(0, translations.size());
	}

	@Test
	public void testCascadeDelete() {

		final Text text = textDAO.createText("Fake text");
		final Text text2 = textDAO.createText("Fake text2");

		textDAO.createTranslationForText(text.getId(), "FL", "Fake translation value 1_1");
		textDAO.createTranslationForText(text2.getId(), "FL", "Fake translation value 2_1");
		textDAO.createTranslationForText(text.getId(), "FL2", "Fake translation value 1_2");
		textDAO.createTranslationForText(text2.getId(), "FL2", "Fake translation value 2_2");

		Assert.assertEquals(2, textDAO.getTranslationsForText(text.getId()).size());
		Assert.assertEquals(2, textDAO.getTranslationsForText(text2.getId()).size());

		textDAO.deleteText(text.getId());

		Assert.assertEquals(0, textDAO.getTranslationsForText(text.getId()).size());
		Assert.assertEquals(2, textDAO.getTranslationsForText(text2.getId()).size());
	}
}
