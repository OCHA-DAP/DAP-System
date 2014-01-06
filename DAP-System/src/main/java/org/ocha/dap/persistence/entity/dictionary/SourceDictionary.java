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
import org.ocha.dap.persistence.entity.curateddata.Source;

@Entity
@Table(name = "source_dictionary")
public class SourceDictionary {

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

	public Id getId() {
		return id;
	}

	@ManyToOne
	@ForeignKey(name = "fk_source_dictionary_to_source")
	@JoinColumn(name = "source_id", nullable = false)
	private final Source source;

	public Source getSource() {
		return source;
	}

	public SourceDictionary(final String unnormalizedName, final String importer, final Source source) {
		super();
		this.id.unnormalizedName = unnormalizedName;
		this.id.importer = importer;
		this.source = source;
	}

	public SourceDictionary() {
		super();
		this.source = null;

	}

}
