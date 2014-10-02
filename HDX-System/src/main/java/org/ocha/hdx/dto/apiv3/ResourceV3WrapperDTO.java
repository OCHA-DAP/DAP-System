package org.ocha.hdx.dto.apiv3;

import org.ocha.hdx.dto.apiv3.DatasetV3DTO.Resource;


public class ResourceV3WrapperDTO {

	private boolean success;
	private Resource result;
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return this.success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(final boolean success) {
		this.success = success;
	}
	/**
	 * @return the result
	 */
	public Resource getResult() {
		return this.result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(final Resource result) {
		this.result = result;
	}

}
