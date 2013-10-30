package org.ocha.dap.service;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.model.ValidationStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml",
		"classpath:/ctx-persistence-test.xml" })
public class FileEvaluatorAndExtractorImplTest {

	@Test
	public void testEvaluateDummyCSVFile() throws IOException {
		final FileEvaluatorAndExtractorImpl fileEvaluatorAndExtractor = new FileEvaluatorAndExtractorImpl();
		{
			final File csvFile = new ClassPathResource("samples/country_category.csv").getFile();
			Assert.assertEquals(ValidationStatus.SUCCESS, fileEvaluatorAndExtractor.evaluateDummyCSVFile(csvFile).getStatus());
		}
		{
			final File csvFile = new ClassPathResource("samples/country_category_wrong_total.csv").getFile();
			Assert.assertEquals(ValidationStatus.ERROR, fileEvaluatorAndExtractor.evaluateDummyCSVFile(csvFile).getStatus());
		}

		{
			final File csvFile = new ClassPathResource("samples/country_category_malformed_file.csv").getFile();
			Assert.assertEquals(ValidationStatus.ERROR, fileEvaluatorAndExtractor.evaluateDummyCSVFile(csvFile).getStatus());
		}
	}

}
