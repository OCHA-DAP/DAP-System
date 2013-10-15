package org.ocha.dap.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.dto.apiv2.DatasetV2DTO;
import org.ocha.dap.dto.apiv3.DatasetV3DTO;
import org.ocha.dap.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.dap.persistence.dao.CKANResourceDAO;
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
		userDAO.createUser("seustachi", "dummyPwd", "079f6194-45e1-4534-8ca7-1bd4130ef897");
	}

	@After
	public void tearDown() throws Exception {
		userDAO.deleteUser("seustachi");
	}

	@Autowired
	private DAPService dapService;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CKANResourceDAO ckanResourceDAO;

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
	public void testGetDatasetContentFromCKANV3() throws Exception {
		{
			final DatasetV3WrapperDTO dto = dapService.getDatasetContentFromCKANV3("seustachi", "testforauth");
			Assert.assertTrue(dto.isSuccess());
		}

		try {
			dapService.getDatasetContentFromCKANV3("otherUser", "testforauth");
			Assert.fail("Should have raised an InsufficientCredentialsException");
		} catch (final InsufficientCredentialsException e) {
		}
	}

	@Test
	public void testGetDatasetContentFromCKANV2() throws Exception {
		{
			final DatasetV2DTO dto = dapService.getDatasetContentFromCKANV2("seustachi", "testforauth");
			Assert.assertEquals("testforauth", dto.getName());
			Assert.assertEquals("1da0dd94-33c2-4934-a541-c04871c3dc52", dto.getRevision_id());
			Assert.assertEquals(0, dto.getTags().size());
			Assert.assertEquals(0, dto.getExtras().size());
		}

		try {
			dapService.getDatasetContentFromCKANV2("otherUser", "testforauth");
			Assert.fail("Should have raised an InsufficientCredentialsException");
		} catch (final InsufficientCredentialsException e) {
		}
	}

	@Test
	public void testDatasetContentCRUDFromCKANV2() throws Exception {
		{
			final DatasetV2DTO dto = dapService.getDatasetContentFromCKANV2("seustachi", "testforauth");
			Assert.assertEquals("testforauth", dto.getName());
			Assert.assertEquals("1da0dd94-33c2-4934-a541-c04871c3dc52", dto.getRevision_id());
			Assert.assertEquals(0, dto.getTags().size());
			Assert.assertEquals(0, dto.getExtras().size());

			dto.getTags().add("FromUnitTests");
			dto.getExtras().put("UnitTestsKey", "UnitTestsValue");

			dapService.updateDatasetContent("seustachi", "testforauth", dto);

		}

		{
			final DatasetV2DTO dto = dapService.getDatasetContentFromCKANV2("seustachi", "testforauth");
			Assert.assertEquals("testforauth", dto.getName());
			Assert.assertEquals("1da0dd94-33c2-4934-a541-c04871c3dc52", dto.getRevision_id());
			Assert.assertEquals(1, dto.getTags().size());
			Assert.assertEquals(1, dto.getExtras().size());

			dto.getTags().clear();
			dto.getExtras().put("UnitTestsKey", null);
			dapService.updateDatasetContent("seustachi", "testforauth", dto);
		}

		{
			final DatasetV2DTO dto = dapService.getDatasetContentFromCKANV2("seustachi", "testforauth");
			Assert.assertEquals("testforauth", dto.getName());
			Assert.assertEquals("1da0dd94-33c2-4934-a541-c04871c3dc52", dto.getRevision_id());
			Assert.assertEquals(0, dto.getTags().size());
			Assert.assertEquals(0, dto.getExtras().size());
		}

	}

	@Test
	public void testGetDatasetDTOFromQueryV3() {
		{
			final DAPServiceImpl dapServiceImpl = new DAPServiceImpl("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
			final DatasetV3WrapperDTO dto = dapServiceImpl.getDatasetDTOFromQueryV3("mali-hp-data-test", null);
			Assert.assertTrue(dto.isSuccess());
		}
		
		{
			final DAPServiceImpl dapServiceImpl = new DAPServiceImpl("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
			final DatasetV3WrapperDTO wrapper = dapServiceImpl.getDatasetDTOFromQueryV3("test1", null);
			final DatasetV3DTO dto = wrapper.getResult();
			Assert.assertEquals("test1", dto.getName());
			Assert.assertEquals(1380794534042L, dto.getRevision_timestamp().getTime());
			Assert.assertEquals("77d87b78-4773-4c02-b613-756bdbd421f2", dto.getRevision_id());
			Assert.assertEquals(2, dto.getTags().size());
			Assert.assertEquals("Junk", dto.getTags().get(0).getName());
			Assert.assertEquals("toBeCurated", dto.getTags().get(1).getName());
			Assert.assertEquals(1, dto.getExtras().size());
			Assert.assertEquals("dap_status", dto.getExtras().get(0).getKey());
			Assert.assertEquals(1, dto.getResources().size());
			
			Assert.assertEquals("active", dto.getResources().get(0).getState());
		}
	}

	@Test
	public void testGetDatasetDTOFromQueryV2() {
		final DAPServiceImpl dapServiceImpl = new DAPServiceImpl("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		final DatasetV2DTO dto = dapServiceImpl.getDatasetDTOFromQueryV2("test1", null);
		Assert.assertEquals("test1", dto.getName());
		Assert.assertEquals("77d87b78-4773-4c02-b613-756bdbd421f2", dto.getRevision_id());
		Assert.assertEquals(2, dto.getTags().size());
		Assert.assertEquals("Junk", dto.getTags().get(0));
		Assert.assertEquals("toBeCurated", dto.getTags().get(1));
		Assert.assertEquals(1, dto.getExtras().size());
		Assert.assertTrue(dto.getExtras().containsKey("dap_status"));
		Assert.assertTrue(dto.getExtras().containsValue("initial_upload"));
	}

	@Test
	public void testGetPrivateDatasetDTOFromQueryV3() {
		final DAPServiceImpl dapServiceImpl = new DAPServiceImpl("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		{
			final DatasetV3WrapperDTO dto = dapServiceImpl.getDatasetDTOFromQueryV3("testforauth", null);
			// Cannot access private dataset without API key
			Assert.assertNull(dto);
		}

		{
			final DatasetV3WrapperDTO dto = dapServiceImpl.getDatasetDTOFromQueryV3("testforauth", "079f6194-45e1-4534-8ca7-1bd4130ef897");
			Assert.assertTrue(dto.isSuccess());
		}
	}
	
	@Test
	public void testCheckForNewCKANResources(){
		Assert.assertEquals(0, ckanResourceDAO.listCKANResources().size());
		dapService.checkForNewCKANResources();
		Assert.assertEquals(4, ckanResourceDAO.listCKANResources().size());
	}

}
