package org.ocha.dap.persistence.dao;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class ImportFromCKANDAOImplTest {

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Test
	public void testCreateNewImportRecord() {
		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());

		Assert.assertEquals("anyResourceId", importFromCKAN.getResourceId());
		Assert.assertEquals("anyRevisionId", importFromCKAN.getRevisionId());
	}

	@Test
	public void testGetDummyImport() {
		final ImportFromCKAN dummy1 = importFromCKANDAO.getDummyImport();
		Assert.assertEquals("dummy", dummy1.getResourceId());

		final ImportFromCKAN dummy2 = importFromCKANDAO.getDummyImport();
		Assert.assertEquals("dummy", dummy2.getResourceId());

		Assert.assertEquals(dummy1.getId(), dummy2.getId());
	}

}
