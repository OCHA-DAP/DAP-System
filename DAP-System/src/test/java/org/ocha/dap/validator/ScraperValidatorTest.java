package org.ocha.dap.validator;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.ocha.dap.model.ValidationStatus;
import org.ocha.dap.validation.ScraperValidator;
import org.springframework.core.io.ClassPathResource;

public class ScraperValidatorTest {

	@Test
	public void testEvaluateFile() throws IOException {
		final ScraperValidator scraperValidator = new ScraperValidator();
		{
			final File csvFile = new ClassPathResource("samples/scraper/csv-from-dragon.zip").getFile();
			Assert.assertEquals(ValidationStatus.SUCCESS, scraperValidator.evaluateFile(csvFile).getStatus());
			Assert.assertEquals(3, scraperValidator.evaluateFile(csvFile).getEntries().size());
		}
	}

}
