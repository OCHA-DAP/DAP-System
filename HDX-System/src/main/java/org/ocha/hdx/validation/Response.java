/**
 *
 */
package org.ocha.hdx.validation;

import org.ocha.hdx.model.validation.ValidationStatus;

/**
 * @author alexandru-m-g
 *
 */
public class Response {

	private ValidationStatus status;

	private String description;


	/**
	 * @return the status
	 */
	public ValidationStatus getStatus() {
		return this.status;
	}




	/**
	 * @param status the status to set
	 */
	public void setStatus(final ValidationStatus status) {
		this.status = status;
	}




	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}




	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}




//	public static enum ResponseType {
//		BLOCK, REPORT, OK
//	}
}
