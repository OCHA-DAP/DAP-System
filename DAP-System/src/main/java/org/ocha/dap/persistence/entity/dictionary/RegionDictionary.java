package org.ocha.dap.persistence.entity.dictionary;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "region_dictionary")
public class RegionDictionary {

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "unnormalized_name", nullable = false, updatable = false)
		private String unnormalizedName;

		@Column(name = "source", nullable = false, updatable = false)
		private String source;

		public Id(final String unnormalizedName, final String source) {
			super();
			this.unnormalizedName = unnormalizedName;
			this.source = source;
		}

		public Id() {
			super();
		}

		public String getUnnormalizedName() {
			return unnormalizedName;
		}

		public String getSource() {
			return source;
		}

		public void setUnnormalizedName(final String unnormalizedName) {
			this.unnormalizedName = unnormalizedName;
		}

		public void setSource(final String source) {
			this.source = source;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((source == null) ? 0 : source.hashCode());
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
			if (source == null) {
				if (other.source != null)
					return false;
			} else if (!source.equals(other.source))
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

	public Id getId() {
		return id;
	}

	@ManyToOne
	@ForeignKey(name = "fk_region_dictionary_to_entity")
	@JoinColumn(name = "entity_id", nullable = false)
	private org.ocha.dap.persistence.entity.curateddata.Entity entity;

	public void setEntity(final org.ocha.dap.persistence.entity.curateddata.Entity entity) {
		this.entity = entity;
	}

	public RegionDictionary(final String unnormalizedName, final String source, final org.ocha.dap.persistence.entity.curateddata.Entity entity) {
		super();
		this.id.unnormalizedName = unnormalizedName;
		this.id.source = source;
		this.entity = entity;
	}

	public RegionDictionary() {
		super();
		this.entity = null;

	}

	public org.ocha.dap.persistence.entity.curateddata.Entity getEntity() {
		return entity;
	}

}
