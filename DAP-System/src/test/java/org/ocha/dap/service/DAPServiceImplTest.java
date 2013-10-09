package org.ocha.dap.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.dto.DatasetDTO;
import org.ocha.dap.persistence.dao.UserDAO;
import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml",
		"classpath:/ctx-persistence-test.xml" })
public class DAPServiceImplTest {

	@Before
	public void setUp() throws Exception {
		userDao.createUser("seustachi", "dummyPwd", "079f6194-45e1-4534-8ca7-1bd4130ef897");
	}

	@After
	public void tearDown() throws Exception {
		userDao.deleteUser("seustachi");
	}

	@Autowired
	private DAPService dapService;

	@Autowired
	private UserDAO userDao;

	@Test
	public void testGetDatasetsListFromCKAN() throws InsufficientCredentialsException {
		Assert.assertEquals(5, dapService.getDatasetsListFromCKAN("seustachi").size());

		try {
			dapService.getDatasetsListFromCKAN("otherUser");
			Assert.fail("Should have raised an InsufficientCredentialsException");
		} catch (final InsufficientCredentialsException e) {
		}
	}

	@Test
	public void testGetDatasetContentFromCKAN() throws Exception {
		{
			final DatasetDTO dto = dapService.getDatasetContentFromCKAN("seustachi", "testforauth");
			Assert.assertTrue(dto.isSuccess());
		}

		try {
			dapService.getDatasetContentFromCKAN("otherUser", "testforauth");
			Assert.fail("Should have raised an InsufficientCredentialsException");
		} catch (final InsufficientCredentialsException e) {
		}
	}

	@Test
	public void testGetDatasetDTOFromQuery() {
		final DAPServiceImpl dapServiceImpl = new DAPServiceImpl("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		final DatasetDTO dto = dapServiceImpl.getDatasetDTOFromQuery("mali-hp-data-test", null, null);
		Assert.assertTrue(dto.isSuccess());
	}

	@Test
	public void testGetPrivateDatasetDTOFromQuery() {
		final DAPServiceImpl dapServiceImpl = new DAPServiceImpl("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		{
			final DatasetDTO dto = dapServiceImpl.getDatasetDTOFromQuery("testforauth", null, null);
			// Cannot access private dataset without API key
			Assert.assertNull(dto);
		}

		{
			final DatasetDTO dto = dapServiceImpl.getDatasetDTOFromQuery("testforauth", "079f6194-45e1-4534-8ca7-1bd4130ef897", null);
			Assert.assertTrue(dto.isSuccess());
		}
	}

}
