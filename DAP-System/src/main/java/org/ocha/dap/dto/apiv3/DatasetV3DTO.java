package org.ocha.dap.dto.apiv3;

public class DatasetV3DTO {
	
	private boolean success;
	private DatasetResultV3DTO result;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(final boolean success) {
		this.success = success;
	}
	public DatasetResultV3DTO getResult() {
		return result;
	}
	public void setResult(final DatasetResultV3DTO result) {
		this.result = result;
	}

}
