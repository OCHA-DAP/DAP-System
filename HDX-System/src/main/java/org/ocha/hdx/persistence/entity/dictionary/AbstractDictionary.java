package org.ocha.hdx.persistence.entity.dictionary;

import com.google.gson.JsonObject;
import org.glassfish.jersey.server.ResourceConfig;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

import java.io.Serializable;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractDictionary {

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "unnormalized_name", nullable = false, updatable = false)
		private String unnormalizedName;

		@Column(name = "importer", nullable = false, updatable = false)
		private String importer;

		public Id(final String unnormalizedName, final String importer) {
			super();
			this.unnormalizedName = unnormalizedName;
			this.importer = importer;
		}

		public Id() {
			super();
		}

		public String getUnnormalizedName() {
			return unnormalizedName;
		}

		public String getImporter() {
			return importer;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((importer == null) ? 0 : importer.hashCode());
			result = prime * result + ((unnormalizedName == null) ? 0 : unnormalizedName.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Id other = (Id) obj;
			if (importer == null) {
				if (other.importer != null)
					return false;
			} else if (!importer.equals(other.importer))
				return false;
			if (unnormalizedName == null) {
				if (other.unnormalizedName != null)
					return false;
			} else if (!unnormalizedName.equals(other.unnormalizedName))
				return false;
			return true;
		}
	}

	@EmbeddedId
	private final Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "configuration_id", nullable = false)
    private ResourceConfiguration configuration;

    public Id getId() {
		return id;
	}

    public ResourceConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ResourceConfiguration configuration) {
        this.configuration = configuration;
    }

    public AbstractDictionary(final String unnormalizedName, final String importer, final ResourceConfiguration configuration) {
		super();
		this.id.unnormalizedName = unnormalizedName;
		this.id.importer = importer;
        this.configuration = configuration;
	}

	public AbstractDictionary() {
		super();
		this.id.unnormalizedName = null;
		this.id.importer = null;
	}

    public JsonObject toJSON(){
        final JsonObject element = new JsonObject();
        element.addProperty("importer", getId().getImporter());
        element.addProperty("unnormalizedName", getId().getUnnormalizedName());
        return element;
    }

}
