package org.ocha.hdx.validation;

import java.io.File;

import org.ocha.hdx.model.validation.ValidationReport;

public interface HDXValidator {

	public ValidationReport evaluateFile(final File file);

}
