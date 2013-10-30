package org.ocha.dap.model;

import java.io.Serializable;

public class ValidationReportEntry implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 192590799226450518L;
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
