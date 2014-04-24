package org.ocha.hdx.dto.apiv3;

import java.util.List;

public class GroupListV3DTO {

	private boolean success;
	private List<String> result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public List<String> getResult() {
		return result;
	}

	public void setResult(final List<String> result) {
		this.result = result;
	}

}
