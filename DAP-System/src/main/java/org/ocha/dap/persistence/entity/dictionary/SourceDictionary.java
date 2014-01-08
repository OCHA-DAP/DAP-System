package org.ocha.dap.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.dap.persistence.entity.curateddata.Source;

@Entity
@Table(name = "source_dictionary")
public class SourceDictionary extends AbstractDictionary {

	@ManyToOne
	@ForeignKey(name = "fk_source_dictionary_to_source")
	@JoinColumn(name = "source_id", nullable = false)
	private final Source source;

	public Source getSource() {
		return source;
	}

	public SourceDictionary(final String unnormalizedName, final String importer, final Source source) {
		super(unnormalizedName, importer);
		this.source = source;
	}

	public SourceDictionary() {
		super();
		this.source = null;

	}

}
