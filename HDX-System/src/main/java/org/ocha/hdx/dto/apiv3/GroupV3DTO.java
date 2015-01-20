package org.ocha.hdx.dto.apiv3;

import java.util.List;

public class GroupV3DTO {

	private String display_name;
	private String title;
	private String id;
	private String name;

	private String package_count;

	private List<DatasetV3DTO> packages;

	public GroupV3DTO(final String name) {
		super();
		this.name = name;
	}

	public GroupV3DTO() {
		super();
	}

	public List<DatasetV3DTO> getPackages() {
		return packages;
	}

	public void setPackages(final List<DatasetV3DTO> packages) {
		this.packages = packages;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(final String display_name) {
		this.display_name = display_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPackage_count() {
		return package_count;
	}

	public void setPackage_count(final String package_count) {
		this.package_count = package_count;
	}

}
