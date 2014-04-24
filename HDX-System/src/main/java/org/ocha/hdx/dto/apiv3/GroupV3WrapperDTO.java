package org.ocha.hdx.dto.apiv3;

public class GroupV3WrapperDTO {

	private boolean success;
	private GroupV3DTO result;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

	public GroupV3DTO getResult() {
		return result;
	}

	public void setResult(final GroupV3DTO result) {
		this.result = result;
	}

}
