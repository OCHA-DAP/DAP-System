package org.ocha.dap.dto.apiv2;

import java.util.List;
import java.util.Map;

public class DatasetV2DTO {

	private String name;
	private String revision_id;

	private List<String> tags;
	private Map<String,String> extras;
	
	private List<ResourceV2DTO> resources;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getRevision_id() {
		return revision_id;
	}

	public void setRevision_id(final String revision_id) {
		this.revision_id = revision_id;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(final List<String> tags) {
		this.tags = tags;
	}

	public Map<String,String> getExtras() {
		return extras;
	}

	public void setExtras(final Map<String,String> extras) {
		this.extras = extras;
	}

	public List<ResourceV2DTO> getResources() {
		return resources;
	}

	public void setResources(final List<ResourceV2DTO> resources) {
		this.resources = resources;
	}

}
