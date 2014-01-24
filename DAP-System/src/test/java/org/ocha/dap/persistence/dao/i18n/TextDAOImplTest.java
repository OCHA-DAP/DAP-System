package org.ocha.dap.persistence.dao.i18n;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.i18n.Language;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.ocha.dap.persistence.entity.i18n.Translation;
import org.ocha.dap.persistence.entity.i18n.Translation.Id;
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
	}

	@After
	public void tearDown() {
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
		textDAO.deleteText(text);
		textById = textDAO.getTextById(text.getId());

		Assert.assertNull(textById);
	}
	
	@Test
	public void testTranslationCRUD() {
		logger.info("Testing translation CRUD...");

		final Text text = textDAO.createText("Fake text");
		final Language language = languageDAO.createLanguage("FL", "Fake language");
		
		// Create
		textDAO.createTranslationForText(text.getId(), language.getCode(), "Fake translation value");

		// Read
		final List<Translation> translations = textDAO.getTranslationsForText(text.getId());
		
		Assert.assertEquals(1, translations.size());
		final Translation translation = translations.get(0);
		final Id id = translation.getId();
		
		Assert.assertEquals("FL", id.getLanguage().getCode());
		Assert.assertEquals(text.getId(), id.getText().getId());
		Assert.assertEquals("Fake translation value", translation.getValue());
		
		
	}	
}
