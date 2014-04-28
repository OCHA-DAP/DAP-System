package org.ocha.hdx.persistence.entity.dictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.Source;

import com.google.gson.JsonObject;

@Entity
@Table(name = "source_dictionary")
@SequenceGenerator(name = "source_dictionary_seq", sequenceName = "source_dictionary_seq")
public class SourceDictionary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "source_dictionary_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@ForeignKey(name = "fk_source_dictionary_to_source")
	@JoinColumn(name = "source_id", nullable = false)
	private final Source source;

	public Source getSource() {
		return source;
	}

	public SourceDictionary(final String unnormalizedName, final String importer, final Source source, final ResourceConfiguration configuration) {
		super(unnormalizedName, importer, configuration);
		this.source = source;
	}

	public SourceDictionary() {
		super();
		this.source = null;

	}

	@Override
	public JsonObject toJSON() {
		final JsonObject element = super.toJSON();
		element.addProperty("sourceName", getSource().getName().getDefaultValue());
		return element;
	}

}
