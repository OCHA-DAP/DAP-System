package org.ocha.dap.rest.helper;

import java.util.List;

public class Index {
	private String userId;
	private List<String> datasets;

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public List<String> getDatasets() {
		return datasets;
	}

	public void setDatasets(final List<String> datasets) {
		this.datasets = datasets;
	}

}
