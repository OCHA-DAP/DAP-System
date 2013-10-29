package org.ocha.dap.model;

public class ValidationReportEntry {
	
	private final ValidationStatus status;
	private final String message;
	
	public ValidationReportEntry(final ValidationStatus status, final String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public ValidationStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

}
