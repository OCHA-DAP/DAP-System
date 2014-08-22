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
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 *
 */
public class IntermediaryBackendServiceImpl implements IntermediaryBackendService {
	@Autowired
	private IndicatorDAO indicatorDAO;

	/* (non-Javadoc)
	 * @see org.ocha.hdx.service.IntermediaryBackendService#listIndicatorsByCriteriaWithPagination(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination(final RequestParamsWrapper paramsWrapper) {

		Integer maxResults = paramsWrapper.getPageSize();
		if ( paramsWrapper.getPageSize() == null || paramsWrapper.getPageSize() > Constants.MAX_RESULTS ) {
			maxResults = Constants.MAX_RESULTS;
		}
		final Integer currentPage = (paramsWrapper.getPageNum()!=null?paramsWrapper.getPageNum():0);
		final Integer startPosition = currentPage * maxResults;

		final String languageCode = paramsWrapper.getLang() != null ? paramsWrapper.getLang() : Constants.DEFAULT_LANGUAGE;
		final List<IntermediaryIndicatorValue> interimValues =
				this.indicatorDAO.listIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(),
						paramsWrapper.getSourceCodes(), paramsWrapper.getEntityCodes(),
						paramsWrapper.getStartYear(), paramsWrapper.getEndYear(),
						startPosition, maxResults, languageCode);

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper;
		if ( interimValues != null ) {
			final List<ApiIndicatorValue> values = this.transformAll(interimValues);
			final Long totalCount = this.indicatorDAO.countIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(),
					paramsWrapper.getSourceCodes(), paramsWrapper.getEntityCodes(),
					paramsWrapper.getStartYear(), paramsWrapper.getEndYear());
			final Long totalPages = totalCount/maxResults + 1;
			resultWrapper = new ApiResultWrapper<ApiIndicatorValue>(values, totalCount.intValue(), currentPage, totalPages.intValue(), true, "None", currentPage < totalPages);
		}
		else {
			resultWrapper = new ApiResultWrapper<>(null, 0, null, null, false, "No items could be found", false);
		}

		return resultWrapper;
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.service.IntermediaryBackendService#listIndicatorsByCriteria(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Transactional(readOnly = true)
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteria(final RequestParamsWrapper paramsWrapper) {

		final Integer maxResults = Constants.MAX_RESULTS;
		final String languageCode = paramsWrapper.getLang() != null ? paramsWrapper.getLang() : Constants.DEFAULT_LANGUAGE;
		final List<IntermediaryIndicatorValue> interimValues =
				this.indicatorDAO.listIndicatorsByCriteria(paramsWrapper.getIndicatorTypeCodes(),
						paramsWrapper.getSourceCodes(), paramsWrapper.getEntityCodes(),
						paramsWrapper.getStartYear(), paramsWrapper.getEndYear(), 0, maxResults+1, languageCode);

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper;
		if ( interimValues != null ) {
			final List<ApiIndicatorValue> values = this.transformAll(interimValues);
			boolean moreResults = false;
			if ( values.size() == maxResults+1 ) {
				values.remove(values.size()-1);
				moreResults = true;
			}

			resultWrapper = new ApiResultWrapper<>(values, values.size(), null, null, true, "None", moreResults);
		}
		else {
			resultWrapper = new ApiResultWrapper<>(null, 0, null, null, false, "No items could be found", false);
		}

		return resultWrapper;

	}

	private List<ApiIndicatorValue> transformAll(final List<IntermediaryIndicatorValue> interimValues) {
		if ( interimValues != null ) {
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

		final ApiIndicatorValue value = new ApiIndicatorValue(interimValue.getValue(),
				interimValue.getIndicatorTypeCode(), interimValue.getIndicatorTypeName(),
				interimValue.getLocationCode(), interimValue.getLocationName(),
				interimValue.getSourceCode(), interimValue.getSourceName(),
				format.format(interimValue.getStartDate()));

		return value;
	}

}
