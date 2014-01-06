package org.ocha.dap.service;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.dto.apiv3.DatasetV3DTO;
import org.ocha.dap.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.dap.persistence.dao.UserDAO;
import org.ocha.dap.persistence.dao.ckan.CKANDatasetDAO;
import org.ocha.dap.persistence.dao.ckan.CKANResourceDAO;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.ckan.CKANResource;
import org.ocha.dap.persistence.entity.ckan.CKANResource.WorkflowState;
import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//FIXME reactivate these integration tests when the dedicated ckan instance is ready
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class DAPServiceImplTest {

	@Before
	public void setUp() throws Exception {
		userDAO.createUser("seustachi", "dummyPwd", "admin", "079f6194-45e1-4534-8ca7-1bd4130ef897");
	}

	@After
	public void tearDown() throws Exception {
		userDAO.deleteUser("seustachi");
		ckanResourceDAO.deleteAllCKANResourcesRecords();
		ckanDatasetDAO.deleteAllCKANDatasetsRecords();
	}

	@Autowired
	private DAPService dapService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private CKANResourceDAO ckanResourceDAO;

	@Autowired
	private CKANDatasetDAO ckanDatasetDAO;

	@Test
	public void testGetDatasetsListFromCKAN() throws InsufficientCredentialsException {
		Assert.assertTrue(dapService.getDatasetsListFromCKAN("seustachi").size() > 0);

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

	@Ignore
	@Test
	public void testGetDatasetDTOFromQueryV3() {
		{
			final DatasetV3WrapperDTO dto = dapService.getDatasetDTOFromQueryV3("mali-hp-data-test", null);
			Assert.assertTrue(dto.isSuccess());
		}

		{
			final DatasetV3WrapperDTO wrapper = dapService.getDatasetDTOFromQueryV3("test1", null);
			final DatasetV3DTO dto = wrapper.getResult();
			Assert.assertEquals("test1", dto.getName());
			Assert.assertEquals(1381841484380L, dto.getRevision_timestamp().getTime());
			Assert.assertEquals("16d36b88-ce78-4a69-94a1-634dd77133fc", dto.getRevision_id());
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
	public void testGetPrivateDatasetDTOFromQueryV3() {
		{
			final DatasetV3WrapperDTO dto = dapService.getDatasetDTOFromQueryV3("testforauth", null);
			// Cannot access private dataset without API key
			Assert.assertNull(dto);
		}

		{
			final DatasetV3WrapperDTO dto = dapService.getDatasetDTOFromQueryV3("testforauth", "079f6194-45e1-4534-8ca7-1bd4130ef897");
			Assert.assertTrue(dto.isSuccess());
		}
	}

	@Test
	public void testCheckForNewCKANDatasets() {
		Assert.assertEquals(0, ckanDatasetDAO.listCKANDatasets().size());
		dapService.checkForNewCKANDatasets();
		Assert.assertTrue(ckanDatasetDAO.listCKANDatasets().size() > 0);
		for (final CKANDataset ckandDataset : ckanDatasetDAO.listCKANDatasets()) {
			Assert.assertEquals(CKANDataset.Status.PENDING, ckandDataset.getStatus());
			dapService.flagDatasetAsToBeCurated(ckandDataset.getName(), CKANDataset.Type.SCRAPER);
		}
		for (final CKANDataset ckandDataset : ckanDatasetDAO.listCKANDatasets()) {
			Assert.assertEquals(CKANDataset.Status.TO_BE_CURATED, ckandDataset.getStatus());
		}
	}

	@Test
	public void testCheckForNewCKANResources() {
		// we need to initialize datasets as to be curated to get some data to
		// work on
		dapService.checkForNewCKANDatasets();
		for (final CKANDataset ckandDataset : ckanDatasetDAO.listCKANDatasets()) {
			Assert.assertEquals(CKANDataset.Status.PENDING, ckandDataset.getStatus());
			dapService.flagDatasetAsToBeCurated(ckandDataset.getName(), CKANDataset.Type.SCRAPER);
		}

		Assert.assertEquals(0, ckanResourceDAO.listCKANResources().size());
		dapService.checkForNewCKANResources();
		Assert.assertTrue(ckanResourceDAO.listCKANResources().size() > 0);
	}

	@Ignore
	@Test
	public void testStandardWorkflow() throws IOException {
		// we need to initialize datasets as to be curated to get some data to
		// work on
		dapService.checkForNewCKANDatasets();
		for (final CKANDataset ckandDataset : ckanDatasetDAO.listCKANDatasets()) {
			Assert.assertEquals(CKANDataset.Status.PENDING, ckandDataset.getStatus());
			if (!ckandDataset.getName().equals("nope"))
				dapService.flagDatasetAsToBeCurated(ckandDataset.getName(), CKANDataset.Type.DUMMY);
		}

		Assert.assertEquals(0, ckanResourceDAO.listCKANResources().size());
		dapService.checkForNewCKANResources();

		final List<CKANResource> resources = ckanResourceDAO.listCKANResources();
		Assert.assertTrue(resources.size() > 0);

		{
			final CKANResource firstResource = resources.get(0);
			Assert.assertEquals(WorkflowState.DETECTED_NEW, firstResource.getWorkflowState());

			dapService.downloadFileForCKANResource(firstResource.getId().getId(), firstResource.getId().getRevision_id());

			final CKANResource firstResourceAfterDownload = ckanResourceDAO.getCKANResource(firstResource.getId().getId(), firstResource.getId().getRevision_id());
			Assert.assertEquals(WorkflowState.DOWNLOADED, firstResourceAfterDownload.getWorkflowState());

			dapService.evaluateFileForCKANResource(firstResource.getId().getId(), firstResource.getId().getRevision_id());

			final CKANResource firstResourceAfterEvaluation = ckanResourceDAO.getCKANResource(firstResource.getId().getId(), firstResource.getId().getRevision_id());
			Assert.assertEquals(WorkflowState.TECH_EVALUATION_SUCCESS, firstResourceAfterEvaluation.getWorkflowState());

			dapService.transformAndImportDataFromFileForCKANResource(firstResource.getId().getId(), firstResource.getId().getRevision_id());

			final CKANResource firstResourceAfterImport = ckanResourceDAO.getCKANResource(firstResource.getId().getId(), firstResource.getId().getRevision_id());
			Assert.assertEquals(WorkflowState.IMPORT_FAIL, firstResourceAfterImport.getWorkflowState());
		}

		{
			final CKANResource secondResource = resources.get(1);
			Assert.assertEquals(WorkflowState.DETECTED_NEW, secondResource.getWorkflowState());

			dapService.downloadFileForCKANResource(secondResource.getId().getId(), secondResource.getId().getRevision_id());

			final CKANResource secondResourceAfterDownload = ckanResourceDAO.getCKANResource(secondResource.getId().getId(), secondResource.getId().getRevision_id());
			Assert.assertEquals(WorkflowState.DOWNLOADED, secondResourceAfterDownload.getWorkflowState());

			dapService.evaluateFileForCKANResource(secondResource.getId().getId(), secondResource.getId().getRevision_id());

			final CKANResource secondResourceAfterEvaluation = ckanResourceDAO.getCKANResource(secondResource.getId().getId(), secondResource.getId().getRevision_id());
			Assert.assertEquals(WorkflowState.TECH_EVALUATION_FAIL, secondResourceAfterEvaluation.getWorkflowState());

			dapService.transformAndImportDataFromFileForCKANResource(secondResource.getId().getId(), secondResource.getId().getRevision_id());

			// we should still be in TECH_EVALUATION_FAIL, as the workflow
			// cannot go from TECH_EVALUATION_FAIL to IMPORT_XXX
			final CKANResource secondResourceAfterImport = ckanResourceDAO.getCKANResource(secondResource.getId().getId(), secondResource.getId().getRevision_id());
			Assert.assertEquals(WorkflowState.TECH_EVALUATION_FAIL, secondResourceAfterImport.getWorkflowState());
		}

	}
}
