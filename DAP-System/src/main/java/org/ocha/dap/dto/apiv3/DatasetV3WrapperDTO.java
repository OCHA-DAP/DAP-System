package org.ocha.dap.dto.apiv3;

public class DatasetV3WrapperDTO {
	
	private boolean success;
	private DatasetV3DTO result;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(final boolean success) {
		this.success = success;
	}
	public DatasetV3DTO getResult() {
		return result;
	}
	public void setResult(final DatasetV3DTO result) {
		this.result = result;
	}

}
