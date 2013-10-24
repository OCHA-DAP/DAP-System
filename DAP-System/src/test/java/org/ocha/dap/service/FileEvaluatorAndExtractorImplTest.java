package org.ocha.dap.service;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml",
		"classpath:/ctx-persistence-test.xml" })
public class FileEvaluatorAndExtractorImplTest {

	@Autowired
	private FileEvaluatorAndExtractor fileEvaluatorAndExtractor;

	@Test
	public void testEvaluateDummyCSVFile() throws IOException {
		{
			final File csvFile = new ClassPathResource("samples/country_category.csv").getFile();
			Assert.assertTrue(fileEvaluatorAndExtractor.evaluateDummyCSVFile(csvFile));
		}
		{
			final File csvFile = new ClassPathResource("samples/country_category_wrong_total.csv").getFile();
			Assert.assertFalse(fileEvaluatorAndExtractor.evaluateDummyCSVFile(csvFile));
		}
		
		{
			final File csvFile = new ClassPathResource("samples/country_category_malformed_file.csv").getFile();
			Assert.assertFalse(fileEvaluatorAndExtractor.evaluateDummyCSVFile(csvFile));
		}
	}

}
