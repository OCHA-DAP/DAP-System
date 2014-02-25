package org.ocha.hdx.importer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.config.DummyConfigurationCreator;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.validation.itemvalidator.IValidator;
import org.ocha.hdx.validation.prevalidator.IPreValidator;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper.DummyEntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alexandru-m-g
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml",
		"classpath:/ctx-persistence-test.xml" })
@Transactional
public class ScraperValidatingImporterTest {


	@Resource
	private List<IValidator> validators;

	@Resource
	private List<IPreValidator> preValidators;

	@Autowired
	private DummyConfigurationCreator dummyConfigurationCreator;

	@Autowired
	private DummyEntityCreatorWrapper dummyEntityCreatorWrapper;

	@Test
	public void testPrepareDataForImport() throws IOException {

		final DummyEntityCreator entityCreator	= this.dummyEntityCreatorWrapper.generateNewEntityCreator();
		entityCreator.createNeededIndicatorTypeAndSource();

		final ScraperValidatingImporter scraperImporter = new ScraperValidatingImporter(null, this.dummyConfigurationCreator.createConfiguration(), this.validators,
				this.preValidators, new ValidationReport(CKANDataset.Type.SCRAPER_VALIDATING));

		final File csvValueFile = new ClassPathResource("samples/scraper/csv.zip").getFile();

		final PreparedData preparedData = scraperImporter.prepareDataForImport(csvValueFile);
		Assert.assertTrue(preparedData.isSuccess());
		Assert.assertEquals(50081, preparedData.getIndicatorsToImport().size());

		for (final PreparedIndicator preparedIndicator : preparedData.getIndicatorsToImport()) {
			if (preparedIndicator.getSourceCode().equals("WB")) {
				Assert.fail("Source WB was not in the file");
			}
		}

		entityCreator.deleteNeededIndicatorTypeAndSource();


	}
}