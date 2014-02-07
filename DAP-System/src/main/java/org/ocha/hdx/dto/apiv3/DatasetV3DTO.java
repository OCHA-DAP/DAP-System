package org.ocha.hdx.dto.apiv3;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DatasetV3DTO {

	private String license_title;
	private Date revision_timestamp;

	private String name;
	private String title;
	private String id;
	private String revision_id;

	private List<Tag> tags;
	private List<Extra> extras;
	private List<Resource> resources;

	private String maintainer;
	private String maintainer_email;
	private String author;
	private String author_email;

	public String getMaintainer() {
		return maintainer;
	}

	public void setMaintainer(final String maintainer) {
		this.maintainer = maintainer;
	}

	public String getMaintainer_email() {
		return maintainer_email;
	}

	public void setMaintainer_email(final String maintainer_email) {
		this.maintainer_email = maintainer_email;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getAuthor_email() {
		return author_email;
	}

	public void setAuthor_email(final String author_email) {
		this.author_email = author_email;
	}

	public String getLicense_title() {
		return license_title;
	}

	public void setLicense_title(final String license_title) {
		this.license_title = license_title;
	}

	public Date getRevision_timestamp() {
		return revision_timestamp;
	}

	public void setRevision_timestamp(final Date revision_timestamp) {
		this.revision_timestamp = revision_timestamp;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
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

	public String getRevision_id() {
		return revision_id;
	}

	public void setRevision_id(final String revision_id) {
		this.revision_id = revision_id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(final List<Tag> tags) {
		this.tags = tags;
	}

	public List<Extra> getExtras() {
		return extras;
	}

	public void setExtras(final List<Extra> extras) {
		this.extras = extras;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(final List<Resource> resources) {
		this.resources = resources;
	}

	public class Tag {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(final String name) {
			this.name = name;
		}
	}

	public class Extra {

		private String key;
		private String value;

		private Map<String, String> __extras;

		public String getKey() {
			return key;
		}

		public void setKey(final String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(final String value) {
			this.value = value;
		}

		public Map<String, String> get__extras() {
			return __extras;
		}

		public void set__extras(final Map<String, String> __extras) {
			this.__extras = __extras;
		}

	}

	public class Resource {
		private String id;
		private String revision_id;

		private String name;
		private String description;
		private Date revision_timestamp;
		private Date created;
		private String state;
		private String url;

		public String getId() {
			return id;
		}

		public void setId(final String id) {
			this.id = id;
		}

		public String getRevision_id() {
			return revision_id;
		}

		public void setRevision_id(final String revision_id) {
			this.revision_id = revision_id;
		}

		public String getName() {
			return name;
		}

		public void setName(final String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(final String description) {
			this.description = description;
		}

		public Date getRevision_timestamp() {
			return revision_timestamp;
		}

		public void setRevision_timestamp(final Date revision_timestamp) {
			this.revision_timestamp = revision_timestamp;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(final Date created) {
			this.created = created;
		}

		public String getState() {
			return state;
		}

		public void setState(final String state) {
			this.state = state;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(final String url) {
			this.url = url;
		}

	}

}
