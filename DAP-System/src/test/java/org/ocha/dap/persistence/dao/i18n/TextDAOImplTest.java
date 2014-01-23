package org.ocha.dap.persistence.dao.i18n;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.i18n.Text;
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
}
