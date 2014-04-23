/**
 *
 */
package org.ocha.hdx.persistence.entity.metadata;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 * @author alexandru-m-g
 * 
 */
@Entity
@Table(name = "hdx_dataserie_metadata")
@SequenceGenerator(name = "hdx_dataserie_metadata_seq", sequenceName = "hdx_dataserie_metadata_seq")
public class DataSerieMetadata {

	public enum MetadataType {
		METADATA, NOTE, VALIDATOR
	}

	public enum MetadataName {
		METHODOLOGY("Methodology", MetadataType.METADATA), MORE_INFO("More info", MetadataType.METADATA), DATASET_SUMMARY("Dataset summary", MetadataType.METADATA), TERMS_OF_USE("Terms of use", MetadataType.METADATA), VALIDATION_NOTES(
				"Validation notes", MetadataType.NOTE), MIN_BOUNDARY("Min Boundary", MetadataType.VALIDATOR), MAX_BOUNDARY("Max Boundary", MetadataType.VALIDATOR);
		private final String label;
		private final MetadataType type;

		private MetadataName(final String label, final MetadataType type) {
			this.label = label;
			this.type = type;
		}

		public String getLabel() {
			return label;
		}

		public MetadataType getType() {
			return type;
		}

		public static List<MetadataName> getByType(final MetadataType type_) {
			final List<MetadataName> result = new ArrayList<DataSerieMetadata.MetadataName>();
			for (final MetadataName metadataName : values()) {
				if (type_.equals(metadataName.getType())) {
					result.add(metadataName);
				}
			}
			return result;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hdx_dataserie_metadata_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@ForeignKey(name = "fk__dataserie_metadata_to_source")
	@JoinColumn(name = "source_id", nullable = false)
	private Source source;

	@ManyToOne
	@ForeignKey(name = "fk__dataserie_metadata_to_indicator_type")
	@JoinColumn(name = "indicator_type_id", nullable = false)
	private IndicatorType indicatorType;

	@Column(name = "entry_key", nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private MetadataName entryKey;

	@ManyToOne
	@JoinColumn(name = "entry_value_text_id")
	@ForeignKey(name = "fk__dataserie_metadata_to_name_text")
	private Text entryValue;

	public DataSerieMetadata() {
	}

	public DataSerieMetadata(final IndicatorType type, final Source source, final MetadataName entryKey, final Text entryValue) {
		super();
		this.source = source;
		indicatorType = type;
		this.entryKey = entryKey;
		this.entryValue = entryValue;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(final Source source) {
		this.source = source;
	}

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(final IndicatorType indicatorType) {
		this.indicatorType = indicatorType;
	}

	public Text getEntryValue() {
		return entryValue;
	}

	public void setEntryValue(final Text entryValue) {
		this.entryValue = entryValue;
	}

	public MetadataName getEntryKey() {
		return entryKey;
	}

	public void setEntryKey(final MetadataName entryKey) {
		this.entryKey = entryKey;
	}

	@Override
	public String toString() {
		return "DataSerieMetadata [id=" + id + ", source=" + source.getName().getDefaultValue() + ", indicatorType=" + indicatorType.getName().getDefaultValue() + ", entryKey= label:" + entryKey.getLabel() + ", type:" + entryKey.getType().name() + ", entryValue=" + entryValue + "]";
	}
}
