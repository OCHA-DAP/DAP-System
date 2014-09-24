/**
 *
 */
package org.ocha.hdx.model.api2util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ocha.hdx.exceptions.apiv2.ApiV2ProcessingException;

/**
 * @author alexandru-m-g
 *
 */
public class RequestParamsWrapper {

	public enum PeriodType {
		LATEST_YEAR, LATEST_YEAR_BY_COUNTRY;
	}

	public enum SortingOption {
		INDICATOR_TYPE_ASC, INDICATOR_TYPE_DESC, SOURCE_TYPE_ASC, SOURCE_TYPE_DESC, COUNTRY_ASC, COUNTRY_DESC,
		VALUE_ASC, VALUE_DESC
	}

	private final List<String> indicatorTypeCodes;
	private final List<String> sourceCodes;
	private final List<String> entityCodes;
	private final Integer startYear;
	private final Integer endYear;
	private PeriodType periodType;
	private final Integer pageNum;
	private final Integer pageSize;
	private final String lang;
	private SortingOption sortingOption;

	public RequestParamsWrapper(final List<String> indicatorTypeCodes, final List<String> sourceCodes, final List<String> entityCodes, final Integer startYear, final Integer endYear,
			final PeriodType periodType, final SortingOption sortingOption, final Integer pageNum, final Integer pageSize, final String lang) throws ApiV2ProcessingException {
		super();
		this.indicatorTypeCodes = new ArrayList<String>();
		this.populateOrderedList(indicatorTypeCodes, this.indicatorTypeCodes);

		this.sourceCodes = new ArrayList<String>();
		this.populateOrderedList(sourceCodes, this.sourceCodes);

		this.entityCodes = new ArrayList<String>();
		this.populateOrderedList(entityCodes, this.entityCodes);

		this.startYear = startYear;
		this.endYear = endYear;

		this.periodType = periodType;

		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.lang = lang;

		if ( sortingOption != null ) {
			this.sortingOption = sortingOption;
		} else {
			this.sortingOption = SortingOption.VALUE_DESC;
		}

	}

	private void populateOrderedList(final List<String> srclist, final List<String> destList) {
		if (srclist != null && srclist.size() > 0) {
			destList.addAll(srclist);
			Collections.sort(destList);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.endYear == null) ? 0 : this.endYear.hashCode());
		result = prime * result + ((this.entityCodes == null) ? 0 : this.entityCodes.hashCode());
		result = prime * result + ((this.indicatorTypeCodes == null) ? 0 : this.indicatorTypeCodes.hashCode());
		result = prime * result + ((this.lang == null) ? 0 : this.lang.hashCode());
		result = prime * result + ((this.pageNum == null) ? 0 : this.pageNum.hashCode());
		result = prime * result + ((this.pageSize == null) ? 0 : this.pageSize.hashCode());
		result = prime * result + ((this.periodType == null) ? 0 : this.periodType.hashCode());
		result = prime * result + ((this.sourceCodes == null) ? 0 : this.sourceCodes.hashCode());
		result = prime * result + ((this.startYear == null) ? 0 : this.startYear.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof RequestParamsWrapper)) {
			return false;
		}
		final RequestParamsWrapper other = (RequestParamsWrapper) obj;
		if (this.endYear == null) {
			if (other.endYear != null) {
				return false;
			}
		} else if (!this.endYear.equals(other.endYear)) {
			return false;
		}
		if (this.entityCodes == null) {
			if (other.entityCodes != null) {
				return false;
			}
		} else if (!this.entityCodes.equals(other.entityCodes)) {
			return false;
		}
		if (this.indicatorTypeCodes == null) {
			if (other.indicatorTypeCodes != null) {
				return false;
			}
		} else if (!this.indicatorTypeCodes.equals(other.indicatorTypeCodes)) {
			return false;
		}
		if (this.lang == null) {
			if (other.lang != null) {
				return false;
			}
		} else if (!this.lang.equals(other.lang)) {
			return false;
		}
		if (this.pageNum == null) {
			if (other.pageNum != null) {
				return false;
			}
		} else if (!this.pageNum.equals(other.pageNum)) {
			return false;
		}
		if (this.pageSize == null) {
			if (other.pageSize != null) {
				return false;
			}
		} else if (!this.pageSize.equals(other.pageSize)) {
			return false;
		}
		if (this.periodType != other.periodType) {
			return false;
		}
		if (this.sourceCodes == null) {
			if (other.sourceCodes != null) {
				return false;
			}
		} else if (!this.sourceCodes.equals(other.sourceCodes)) {
			return false;
		}
		if (this.startYear == null) {
			if (other.startYear != null) {
				return false;
			}
		} else if (!this.startYear.equals(other.startYear)) {
			return false;
		}
		return true;
	}

	public List<String> getIndicatorTypeCodes() {
		return this.indicatorTypeCodes;
	}

	public List<String> getSourceCodes() {
		return this.sourceCodes;
	}

	public List<String> getEntityCodes() {
		return this.entityCodes;
	}

	public Integer getStartYear() {
		return this.startYear;
	}

	public Integer getEndYear() {
		return this.endYear;
	}

	public Integer getPageNum() {
		return this.pageNum;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public String getLang() {
		return this.lang;
	}

	public PeriodType getPeriodType() {
		return this.periodType;
	}

	public void setPeriodType(final PeriodType periodType) {
		this.periodType = periodType;
	}

	public SortingOption getSortingOption() {
		return this.sortingOption;
	}



}
