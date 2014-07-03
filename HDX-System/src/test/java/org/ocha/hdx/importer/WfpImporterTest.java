package org.ocha.hdx.importer;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.service.CuratedDataService;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper.DummyEntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Test class for WFP Importer
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml", "classpath:/ctx-service.xml" })
@Transactional
public class WfpImporterTest {

	@Autowired
	private CuratedDataService curatedDataService;

	@Autowired
	private DummyEntityCreatorWrapper dummyEntityCreatorWrapper;

	@Test
	public void testPrepareDataForImport() {

		final DummyEntityCreator entityCreator = dummyEntityCreatorWrapper.generateNewEntityCreator();
		entityCreator.createNeededIndicatorTypeAndSourceForWfp();

		final WfpImporter wfpImporter = new WfpImporter(curatedDataService);

		File file = null;
		try {
			file = new ClassPathResource("samples/scraper/FCSforHDX.xlsx").getFile();
		} catch (final IOException e) {
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}

		final PreparedData preparedData = wfpImporter.prepareDataForImport(file);
		Assert.assertTrue(preparedData.isSuccess());
		Assert.assertEquals(329 * 2, preparedData.getIndicatorsToImport().size());

		entityCreator.deleteNeededIndicatorTypeAndSourceForWfp();
	}
}
