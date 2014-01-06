package org.ocha.dap.validation;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.ocha.dap.model.validation.ValidationStatus;
import org.springframework.core.io.ClassPathResource;

public class DummyValidatorTest {

	@Test
	public void testEvaluateFile() throws IOException {
		final DummyValidator dummyValidator = new DummyValidator();
		{
			final File csvFile = new ClassPathResource("samples/country_category.csv").getFile();
			Assert.assertEquals(ValidationStatus.SUCCESS, dummyValidator.evaluateFile(csvFile).getStatus());
		}
		{
			final File csvFile = new ClassPathResource("samples/country_category_wrong_total.csv").getFile();
			Assert.assertEquals(ValidationStatus.ERROR, dummyValidator.evaluateFile(csvFile).getStatus());
		}

		{
			final File csvFile = new ClassPathResource("samples/country_category_malformed_file.csv").getFile();
			Assert.assertEquals(ValidationStatus.ERROR, dummyValidator.evaluateFile(csvFile).getStatus());
		}
	}

}
