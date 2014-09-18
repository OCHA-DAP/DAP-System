/**
 *
 */
package org.ocha.hdx.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.config.api2.Constants;
import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.ocha.hdx.model.api2util.IntermediaryIndicatorValue;
import org.ocha.hdx.model.api2util.RequestParamsWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.PeriodType;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.dao.view.IndicatorMaxDateDAO;
import org.ocha.hdx.persistence.entity.view.IndicatorMaxDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 * 
 */
public class IntermediaryBackendServiceImpl implements IntermediaryBackendService {
	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private IndicatorMaxDateDAO indicatorMaxDateDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.service.IntermediaryBackendService#listIndicatorsByCriteriaWithPagination(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination(final RequestParamsWrapper paramsWrapper) {
		if (PeriodType.LATEST_YEAR_BY_COUNTRY.equals(paramsWrapper.getPeriodType())) {
			return getLastYearByEntryValues(paramsWrapper);
		}

		final Integer[] yearPair = this.findYearPeriod(paramsWrapper);
		final Integer startYear = yearPair[0];
		final Integer endYear = yearPair[1];

		Integer maxResults = paramsWrapper.getPageSize();
		if (paramsWrapper.getPageSize() == null || paramsWrapper.getPageSize() > Constants.MAX_RESULTS) {
			maxResults = Constants.MAX_RESULTS;
		}
		final Integer currentPage = (paramsWrapper.getPageNum() != null ? paramsWrapper.getPageNum() : 0);
		final Integer startPosition = currentPage * maxResults;

		final String languageCode = paramsWrapper.getLang() != null ? paramsWrapper.getLang() : Constants.DEFAULT_LANGUAGE;
		final List<IntermediaryIndicatorValue> interimValues = this.indicatorDAO.listIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(), paramsWrapper.getSourceCodes(),
				paramsWrapper.getEntityCodes(), startYear, endYear, startPosition, maxResults, languageCode);

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper;
		if (interimValues != null) {
			final List<ApiIndicatorValue> values = this.transformAll(interimValues);
			final Long totalCount = this.indicatorDAO.countIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(), paramsWrapper.getSourceCodes(), paramsWrapper.getEntityCodes(), startYear,
					endYear);
			final Long totalPages = totalCount / maxResults + 1;
			resultWrapper = new ApiResultWrapper<ApiIndicatorValue>(values, totalCount.intValue(), currentPage, totalPages.intValue(), true, "None", currentPage < totalPages);
		} else {
			resultWrapper = new ApiResultWrapper<>(null, 0, null, null, false, "No items could be found", false);
		}

		return resultWrapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.service.IntermediaryBackendService#listIndicatorsByCriteria(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteria(final RequestParamsWrapper paramsWrapper) {
		if (PeriodType.LATEST_YEAR_BY_COUNTRY.equals(paramsWrapper.getPeriodType())) {
			return getLastYearByEntryValues(paramsWrapper);
		}

		final Integer[] yearPair = this.findYearPeriod(paramsWrapper);
		final Integer startYear = yearPair[0];
		final Integer endYear = yearPair[1];

		final Integer maxResults = Constants.MAX_RESULTS;
		final String languageCode = paramsWrapper.getLang() != null ? paramsWrapper.getLang() : Constants.DEFAULT_LANGUAGE;
		final List<IntermediaryIndicatorValue> interimValues = this.indicatorDAO.listIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(), paramsWrapper.getSourceCodes(),
				paramsWrapper.getEntityCodes(), startYear, endYear, 0, maxResults + 1, languageCode);

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper;
		if (interimValues != null) {
			final List<ApiIndicatorValue> values = this.transformAll(interimValues);
			boolean moreResults = false;
			if (values.size() == maxResults + 1) {
				values.remove(values.size() - 1);
				moreResults = true;
			}

			resultWrapper = new ApiResultWrapper<>(values, values.size(), null, null, true, "None", moreResults);
		} else {
			resultWrapper = new ApiResultWrapper<>(null, 0, null, null, false, "No items could be found", false);
		}

		return resultWrapper;

	}

	private Integer[] findYearPeriod(final RequestParamsWrapper paramsWrapper) {
		Integer startYear = paramsWrapper.getStartYear();
		Integer endYear = paramsWrapper.getEndYear();
		if (RequestParamsWrapper.PeriodType.LATEST_YEAR.equals(paramsWrapper.getPeriodType())) {
			startYear = this.indicatorDAO.latestYearForIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(), paramsWrapper.getSourceCodes(), paramsWrapper.getEntityCodes());
			endYear = startYear;
		}
		return new Integer[] { startYear, endYear };
	}

	private List<ApiIndicatorValue> transformAll(final List<IntermediaryIndicatorValue> interimValues) {
		if (interimValues != null) {
			final List<ApiIndicatorValue> values = new ArrayList<>(interimValues.size());
			for (final IntermediaryIndicatorValue interimValue : interimValues) {
				values.add(this.transform(interimValue));
			}
			return values;
		}
		return null;
	}

	private ApiIndicatorValue transform(final IntermediaryIndicatorValue interimValue) {
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final ApiIndicatorValue value = new ApiIndicatorValue(interimValue.getValue(), interimValue.getIndicatorTypeCode(), interimValue.getIndicatorTypeName(), interimValue.getLocationCode(),
				interimValue.getLocationName(), interimValue.getSourceCode(), interimValue.getSourceName(), format.format(interimValue.getStartDate()));

		return value;
	}

	/**
	 * for now, no pagination in any case here
	 * 
	 * @param paramsWrapper
	 * @return
	 */
	private ApiResultWrapper<ApiIndicatorValue> getLastYearByEntryValues(final RequestParamsWrapper paramsWrapper) {

		final List<IndicatorMaxDate> rawValues = indicatorMaxDateDAO.getValues(paramsWrapper.getEntityCodes(), paramsWrapper.getIndicatorTypeCodes(), paramsWrapper.getSourceCodes());

		final List<ApiIndicatorValue> values = new ArrayList<>();
		for (final IndicatorMaxDate rValue : rawValues) {
			final ApiIndicatorValue value = new ApiIndicatorValue(rValue.getValue(), rValue.getIndicatorTypeCode(), rValue.getIndicatorTypeName(), rValue.getLocationCode(), rValue.getLocationName(),
					rValue.getSourceCode(), rValue.getSourceName(), rValue.getTime());
			values.add(value);
		}
		final ApiResultWrapper<ApiIndicatorValue> resultWrapper = new ApiResultWrapper<>(values, values.size(), null, null, true, "None", false);

		return resultWrapper;

	}

}
