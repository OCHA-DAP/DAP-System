package org.ocha.hdx.persistence.entity.curateddata;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing specific values for indicators, with references to the {@link org.ocha.hdx.persistence.entity.curateddata.Entity}
 *         table for the associated entity, to the {@link Source} table for the data source, and to the {@link IndicatorType} table for the
 *         specific indicator in question. This table will hold most of the data in the repository, and is expected to grow eventually into
 *         the millions of rows (or more).
 * 
 * 
 *         The main prupose of this table is to contain the curated data. However, some columns represent some technical metadata used by
 *         the application
 * 
 *         {@link Indicator#importFromCKAN}
 * 
 *         {@link Indicator#initialValue}
 * 
 * 
 */
@Entity
@Table(name = "hdx_indicator", uniqueConstraints = @UniqueConstraint(columnNames = { "source_id", "entity_id", "type_id", "start_time", "periodicity" }))
@SequenceGenerator(name = "indicator_seq", sequenceName = "indicator_seq")
public class Indicator {

	public enum Periodicity {
		NONE, DAY, WEEK, MONTH, QUARTER, YEAR, TWO_YEARS, THREE_YEARS, FIVE_YEARS, TEN_YEARS;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "indicator_seq")
	@Column(name = "id", nullable = false)
	private long id;

	@ManyToOne
	@ForeignKey(name = "fk_indicator_to_source")
	@JoinColumn(name = "source_id", nullable = false)
	private Source source;

	@ManyToOne
	@ForeignKey(name = "fk_indicator_to_entity")
	@JoinColumn(name = "entity_id", nullable = false)
	private org.ocha.hdx.persistence.entity.curateddata.Entity entity;

	@ManyToOne
	@ForeignKey(name = "fk_indicator_to_type")
	@JoinColumn(name = "type_id", nullable = false)
	private IndicatorType type;

	@Column(name = "start_time", columnDefinition = "timestamp", nullable = false, updatable = false)
	private Date start;

	@Column(name = "end_time", columnDefinition = "timestamp", nullable = true, updatable = false)
	private Date end;

	@Column(name = "periodicity", nullable = false, updatable = true)
	@Enumerated(EnumType.STRING)
	private Periodicity periodicity;

	/**
	 * Storing the initial value as represented in the resource before import. For troubleshooting
	 */
	@Column(name = "initial_value", nullable = false, updatable = false)
	private String initialValue;

	/**
	 * Must not be confused with {@link Indicator#source} This is a metadata, a link to a resource from which the data originated. Initially
	 * used for source links from ScraperWiki data.
	 */
	@Column(name = "source_link", nullable = true, updatable = true)
	private String sourceLink;

	/**
	 * stores a reference to the technical import. Might be useful to trace and rollback an import if something went wrong
	 */
	@ManyToOne
	@ForeignKey(name = "fk_import_from_ckan")
	@JoinColumn(name = "import_from_ckan_id")
	private ImportFromCKAN importFromCKAN;

	// will be used for soft delete
	// private int version;

	@Embedded
	private IndicatorValue value;

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Source getSource() {
		return this.source;
	}

	public void setSource(final Source source) {
		this.source = source;
	}

	public org.ocha.hdx.persistence.entity.curateddata.Entity getEntity() {
		return this.entity;
	}

	public void setEntity(final org.ocha.hdx.persistence.entity.curateddata.Entity entity) {
		this.entity = entity;
	}

	public IndicatorType getType() {
		return this.type;
	}

	public void setType(final IndicatorType type) {
		this.type = type;
	}

	public Date getStart() {
		return this.start;
	}

	public void setStart(final Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return this.end;
	}

	public void setEnd(final Date end) {
		this.end = end;
	}

	public Periodicity getPeriodicity() {
		return this.periodicity;
	}

	public void setPeriodicity(final Periodicity periodicity) {
		this.periodicity = periodicity;
	}

	public String getInitialValue() {
		return this.initialValue;
	}

	public void setInitialValue(final String initialValue) {
		this.initialValue = initialValue;
	}

	public ImportFromCKAN getImportFromCKAN() {
		return this.importFromCKAN;
	}

	public void setImportFromCKAN(final ImportFromCKAN importFromCKAN) {
		this.importFromCKAN = importFromCKAN;
	}

	public IndicatorValue getValue() {
		return this.value;
	}

	public void setValue(final IndicatorValue value) {
		this.value = value;
	}

	public String getSourceLink() {
		return this.sourceLink;
	}

	public void setSourceLink(final String sourceLink) {
		this.sourceLink = sourceLink;
	}

	@Override
	public String toString() {
		return "Indicator [id=" + this.id + ", source=" + this.source.getCode() + ", entity=" + this.entity.getCode() + ", type=" + this.type.getCode() + ", start=" + this.start + ", end=" + this.end
				+ ", periodicity=" + this.periodicity + ", initialValue=" + this.initialValue + ", sourceLink=" + this.sourceLink + ", value=" + this.value + "]";
	}

}
