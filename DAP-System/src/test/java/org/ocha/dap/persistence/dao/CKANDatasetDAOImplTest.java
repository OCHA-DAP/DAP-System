package org.ocha.dap.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.CKANDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml",
		"classpath:/ctx-persistence-test.xml" })
public class CKANDatasetDAOImplTest {

	@Autowired
	private CKANDatasetDAO ckanDatasetDAO;

	@After
	public void tearDown() {
		ckanDatasetDAO.deleteAllCKANDatasetsRecords();
	}

	@Test
	public void testImportDetectedDatasetsIfNotPresent() {
		Assert.assertEquals(0, ckanDatasetDAO.listCKANDatasets().size());

		final List<String> datasets = new ArrayList<>();
		datasets.add("TestA");
		datasets.add("TestB");
		datasets.add("TestC");
		ckanDatasetDAO.importDetectedDatasetsIfNotPresent(datasets);

		{
			final List<CKANDataset> storedDatasets = ckanDatasetDAO.listCKANDatasets();
			Assert.assertEquals(3, storedDatasets.size());
			Assert.assertEquals(CKANDataset.Status.PENDING, storedDatasets.get(0).getStatus());
			Assert.assertEquals(CKANDataset.Status.PENDING, storedDatasets.get(1).getStatus());
			Assert.assertEquals(CKANDataset.Status.PENDING, storedDatasets.get(2).getStatus());

			Assert.assertEquals(0, ckanDatasetDAO.listToBeCuratedCKANDatasets().size());
		}

		ckanDatasetDAO.flagDatasetAsToBeCurated("TestA", CKANDataset.Type.DUMMY);
		ckanDatasetDAO.flagDatasetAsToBeCurated("TestB", CKANDataset.Type.SCRAPPER);
		ckanDatasetDAO.flagDatasetAsIgnored("TestC");

		{
			final List<CKANDataset> storedDatasets = ckanDatasetDAO.listCKANDatasets();
			Assert.assertEquals(3, storedDatasets.size());
			Assert.assertEquals(CKANDataset.Type.DUMMY, storedDatasets.get(0).getType());
			Assert.assertEquals(CKANDataset.Status.TO_BE_CURATED, storedDatasets.get(0).getStatus());
			Assert.assertEquals(CKANDataset.Type.SCRAPPER, storedDatasets.get(1).getType());
			Assert.assertEquals(CKANDataset.Status.TO_BE_CURATED, storedDatasets.get(1).getStatus());
			Assert.assertEquals(CKANDataset.Status.IGNORED, storedDatasets.get(2).getStatus());
			
			Assert.assertEquals(2, ckanDatasetDAO.listToBeCuratedCKANDatasets().size());
		}

	}

}
