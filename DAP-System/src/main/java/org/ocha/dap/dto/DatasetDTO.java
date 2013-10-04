package org.ocha.dap.dto;

public class DatasetDTO {
	
	private boolean success;
	private Result result;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}

}

class Result{
	private String license_title;

	public String getLicense_title() {
		return license_title;
	}

	public void setLicense_title(String license_title) {
		this.license_title = license_title;
	} 
}
