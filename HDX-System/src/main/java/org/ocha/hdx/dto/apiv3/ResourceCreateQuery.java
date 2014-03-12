package org.ocha.hdx.dto.apiv3;

public class ResourceCreateQuery {

	private String package_id;
	private String url;
	private String name;

	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(final String package_id) {
		this.package_id = package_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
