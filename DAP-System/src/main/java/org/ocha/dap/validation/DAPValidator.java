package org.ocha.dap.validation;

import java.io.File;

import org.ocha.dap.model.ValidationReport;

public interface DAPValidator {
	
	public ValidationReport evaluateFile(final File file);

}
