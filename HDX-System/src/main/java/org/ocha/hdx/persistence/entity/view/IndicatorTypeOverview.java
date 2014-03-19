package org.ocha.hdx.persistence.entity.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * A view to give an overview over an indicator type.
	it.id as "indicator_type_id", 
	it.code as "indicator_type_code",
	s.code as "source_code",
	st.default_value as "source_default_value",
	u.code as "unit_code",
	ut.default_value as "unit_default_value",
	dads.id as "data_summary_id",
	dads.default_value as "data_summary_default_value",
	dami.id as "more_info_id",
	dami.default_value as "more_info_default_value",
	datu.id as "terms_of_use_id",
	datu.default_value as "terms_of_use_default_value",
	damy.id as "methodology_id",
	damy.default_value as "methodology_default_value"

 * @author Benoît Michiels
 */
@Entity
@Table(name = "hdx_view_report_indicator_type_overview")
public class IndicatorTypeOverview {

	
	@Embeddable
	public static class Id implements Serializable {
		
		private static final long serialVersionUID = -7419743564690787464L;

		@Column(name = "indicator_type_code")
		private String indicatorTypeCode;

		@Column(name = "source_code")
		private String sourceCode;

		public String getSourceCode() {
			return sourceCode;
		}

		public void setSourceCode(final String sourceCode) {
			this.sourceCode = sourceCode;
		}

		public String getIndicatorTypeCode() {
			return indicatorTypeCode;
		}

		public void setIndicatorTypeCode(final String indicatorTypeCode) {
			this.indicatorTypeCode = indicatorTypeCode;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = (prime * result) + ((getIndicatorTypeCode() == null) ? 0 : getIndicatorTypeCode().hashCode());
			result = (prime * result) + ((sourceCode == null) ? 0 : sourceCode.hashCode());
			return result;
		}

		@Override
		public boolean equals(final Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final Id other = (Id) obj;
			if (getIndicatorTypeCode() == null) {
				if (other.getIndicatorTypeCode() != null) {
					return false;
				}
			} else if (!getIndicatorTypeCode().equals(other.getIndicatorTypeCode())) {
				return false;
			}
			if (sourceCode == null) {
				if (other.sourceCode != null) {
					return false;
				}
			} else if (!sourceCode.equals(other.sourceCode)) {
				return false;
			}
			return true;
		}
	}	

	@EmbeddedId
	private Id id = new Id();
	
	@Column(name = "indicator_type_id")
	private Long indicatorTypeId;

	@Column(name = "indicator_type_default_value")
	private String indicatorTypeDefaultValue;

	@Column(name = "source_default_value")
	private String sourceDefaultValue;

	@Column(name = "unit_code")
	private String unitCode;

	@Column(name = "unit_default_value")
	private String unitDefaultValue;

	@Column(name = "data_summary_id")
	private Long dataSummaryId;

	@Column(name = "data_summary_default_value")
	private String dataSummaryDefaultValue;

	@Column(name = "more_info_id")
	private Long moreInfoId;

	@Column(name = "more_info_default_value")
	private String moreInfoDefaultValue;

	@Column(name = "terms_of_use_id")
	private Long termsOfUseId;

	@Column(name = "terms_of_use_default_value")
	private String termsOfUseDefaultValue;

	@Column(name = "methodology_id")
	private Long methodologyId;

	@Column(name = "methodology_default_value")
	private String methodologyDefaultValue;

	public String getSourceDefaultValue() {
		return sourceDefaultValue;
	}

	public void setSourceDefaultValue(final String sourceDefaultValue) {
		this.sourceDefaultValue = sourceDefaultValue;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(final String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitDefaultValue() {
		return unitDefaultValue;
	}

	public void setUnitDefaultValue(final String unitDefaultValue) {
		this.unitDefaultValue = unitDefaultValue;
	}

	public Long getDataSummaryId() {
		return dataSummaryId;
	}

	public void setDataSummaryId(final Long dataSummaryId) {
		this.dataSummaryId = dataSummaryId;
	}

	public String getDataSummaryDefaultValue() {
		return dataSummaryDefaultValue;
	}

	public void setDataSummaryDefaultValue(final String dataSummaryDefaultValue) {
		this.dataSummaryDefaultValue = dataSummaryDefaultValue;
	}

	public Long getMoreInfoId() {
		return moreInfoId;
	}

	public void setMoreInfoId(final Long moreInfoId) {
		this.moreInfoId = moreInfoId;
	}

	public String getMoreInfoDefaultValue() {
		return moreInfoDefaultValue;
	}

	public void setMoreInfoDefaultValue(final String moreInfoDefaultValue) {
		this.moreInfoDefaultValue = moreInfoDefaultValue;
	}

	public Long getTermsOfUseId() {
		return termsOfUseId;
	}

	public void setTermsOfUseId(final Long termsOfUseId) {
		this.termsOfUseId = termsOfUseId;
	}

	public String getTermsOfUseDefaultValue() {
		return termsOfUseDefaultValue;
	}

	public void setTermsOfUseDefaultValue(final String termsOfUseDefaultValue) {
		this.termsOfUseDefaultValue = termsOfUseDefaultValue;
	}

	public Long getMethodologyId() {
		return methodologyId;
	}

	public void setMethodologyId(final Long methodologyId) {
		this.methodologyId = methodologyId;
	}

	public String getMethodologyDefaultValue() {
		return methodologyDefaultValue;
	}

	public void setMethodologyDefaultValue(final String methodologyDefaultValue) {
		this.methodologyDefaultValue = methodologyDefaultValue;
	}

	public Id getId() {
		return id;
	}

	public void setId(final Id id) {
		this.id = id;
	}

	public Long getIndicatorTypeId() {
		return indicatorTypeId;
	}

	public void setIndicatorTypeId(final Long indicatorTypeId) {
		this.indicatorTypeId = indicatorTypeId;
	}

	public String getSourceCode() {
		return id.sourceCode;
	}

	public String getIndicatorTypeCode() {
		return id.indicatorTypeCode;
	}

	public String getIndicatorTypeDefaultValue() {
		return indicatorTypeDefaultValue;
	}

	public void setIndicatorTypeDefaultValue(final String indicatorTypeDefaultValue) {
		this.indicatorTypeDefaultValue = indicatorTypeDefaultValue;
	}

}
