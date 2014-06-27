package org.ocha.hdx.validation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ocha.hdx.model.validation.ValidationReport;
import org.ocha.hdx.model.validation.ValidationReportEntry;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.springframework.core.io.ClassPathResource;

public class WfpValidatorTest {

	@SuppressWarnings("static-method")
	@Test
	public void testEvaluateFile() throws IOException {
		final WfpValidator validator = new WfpValidator();
		{
			final File file = new ClassPathResource("samples/scraper/FCSforHDX.xlsx").getFile();
			final ValidationReport evaluateFile = validator.evaluateFile(file);
			if(!ValidationStatus.SUCCESS.equals(evaluateFile.getStatus())) {
				final List<ValidationReportEntry> entries = evaluateFile.getEntries();
				for (final ValidationReportEntry validationReportEntry : entries) {
					System.out.println(validationReportEntry.getMessage());
				}
			}
			Assert.assertEquals(ValidationStatus.SUCCESS, evaluateFile.getStatus());
		}
	}

}
