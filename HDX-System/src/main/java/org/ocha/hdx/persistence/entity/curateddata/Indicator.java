package org.ocha.hdx.persistence.entity.curateddata;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;

/**
 * 
 * @author Samuel Eustachi
 * @author David Megginson
 * 
 *         a table listing specific values for indicators, with references to the {@link org.ocha.hdx.persistence.entity.curateddata.Entity} table for the associated entity, to the {@link Source}
 *         table for the data source, and to the {@link IndicatorType} table for the specific indicator in question. This table will hold most of the data in the repository, and is expected to grow
 *         eventually into the millions of rows (or more).
 * 
 * 
 *         The main prupose of this table is to contain the curated data. However, some columns represent some technical metadata used by the application
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
		NONE("N"), DAY("D"), WEEK("W"), MONTH("M"), QUARTER("Q"), YEAR("Y"), TWO_YEARS("2Y"), THREE_YEARS("3Y"), FIVE_YEARS("5Y"), TEN_YEARS("10Y");

		private static final Map<String, Periodicity> map;
		static {
			final HashMap<String, Periodicity> tempMap = new HashMap<String, Indicator.Periodicity>();
			for (final Periodicity periodicity : Periodicity.values()) {
				tempMap.put(periodicity.getCode(), periodicity);
			}
			map = Collections.unmodifiableMap(tempMap);
		}

		public static Periodicity findPeriodicityByCode(final String code) {
			String tempCode = code;
			if (tempCode.startsWith("1")) {
				tempCode = tempCode.substring(1);
			}
			return map.get(tempCode);
		}

		private final String code;

		private Periodicity(final String code) {
			this.code = code;
		}

		public String getCode() {
			return this.code;
		}

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
	 * Must not be confused with {@link Indicator#source} This is a metadata, a link to a resource from which the data originated. Initially used for source links from ScraperWiki data.
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

	@Embedded
	private IndicatorImportConfig indicatorImportConfig = new IndicatorImportConfig();

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

	public IndicatorImportConfig getIndicatorImportConfig() {
		return indicatorImportConfig;
	}

	public void setIndicatorImportConfig(final IndicatorImportConfig indicatorImportConfig) {
		this.indicatorImportConfig = indicatorImportConfig;
	}

	public void setInitialValue(final String initialValue) {
		indicatorImportConfig.setInitialValue(initialValue);
	}

	public void setLowerBoundary(final double lowerBoundary) {
		indicatorImportConfig.setLowerBoundary(lowerBoundary);
	}

	public void setUpperBoundary(final double upperBoundary) {
		indicatorImportConfig.setUpperBoundary(upperBoundary);
	}

	public void setMultiplier(final double multiplier) {
		indicatorImportConfig.setMultiplier(multiplier);
	}

	public void setExpectedTimeFormat(final String expectedTimeFormat) {
		indicatorImportConfig.setExpectedTimeFormat(expectedTimeFormat);
	}

	public void setInterpretedTimeFormat(final String interpretedTimeFormat) {
		indicatorImportConfig.setInterpretedTimeFormat(interpretedTimeFormat);
	}

	public void setValidationStatus(final ValidationStatus validationStatus) {
		indicatorImportConfig.setValidationStatus(validationStatus);
	}

	public void setValidationMessage(final String validationMessage) {
		indicatorImportConfig.setValidationMessage(validationMessage);
	}

	@Override
	public String toString() {
		return "Indicator [id=" + this.id + ", source=" + this.source.getCode() + ", entity=" + this.entity.getCode() + ", type=" + this.type.getCode() + ", start=" + this.start + ", end=" + this.end
				+ ", periodicity=" + this.periodicity + ", sourceLink=" + this.sourceLink + ", value=" + this.value + "]";
	}

}
