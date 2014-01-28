package org.ocha.dap.persistence.entity.dictionary;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;

@Entity
@Table(name = "indicator_type_dictionary")
public class IndicatorTypeDictionary extends AbstractDictionary {

	@ManyToOne
	@ForeignKey(name = "fk_indicator_type_dictionary_to_indicator_type")
	@JoinColumn(name = "indicator_type_id", nullable = false)
	private IndicatorType indicatorType;

	public IndicatorType getIndicatorType() {
		return indicatorType;
	}

	public IndicatorTypeDictionary(final String unnormalizedName, final String importer, final IndicatorType indicatorType) {
		super(unnormalizedName, importer);
		this.indicatorType = indicatorType;
	}

	public IndicatorTypeDictionary(final String unnormalizedName, final String importer) {
		super(unnormalizedName, importer);
	}

	public IndicatorTypeDictionary() {
		super();
		this.indicatorType = null;

	}

}
