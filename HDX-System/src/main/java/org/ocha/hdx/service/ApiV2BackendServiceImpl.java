/**
 *
 */
package org.ocha.hdx.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.ocha.hdx.exceptions.apiv2.ApiV2ProcessingException;
import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.PeriodType;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.RequestType;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.SortingOption;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.cache.LoadingCache;

/**
 * @author alexandru-m-g
 *
 */
public class ApiV2BackendServiceImpl implements ApiV2BackendService {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ApiV2BackendServiceImpl.class);

	@Autowired
	IntermediaryBackendService intermediaryBackendService;

	@Resource
	LoadingCache<RequestParamsWrapper, ApiResultWrapper<?>> indicatorResultCache;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ocha.hdx.service.ApiV2BackendService#listIndicatorsByCriteriaWithPagination(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer,
	 * java.lang.Integer, java.lang.String)
	 */
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination(final List<String> indicatorTypeCodes, final List<String> sourceCodes, final List<String> entityCodes,
			final Integer startYear, final Integer endYear, final PeriodType periodType, final SortingOption sortingOption, final Integer pageNum, final Integer pageSize, final String lang) {

		final Long time = System.currentTimeMillis();
		final ApiResultWrapper<ApiIndicatorValue> result = this.innerListIndicators(RequestType.INDICATOR_LIST, indicatorTypeCodes, sourceCodes, entityCodes, startYear, endYear,
				periodType, sortingOption, pageNum, pageSize, lang);
		logger.info( String.format("Query answered in %s ms", System.currentTimeMillis()-time ) );
		return result;
	}


	/**
	 * @param indicatorTypeCodes
	 * @param sourceCodes
	 * @param entityCodes
	 * @param startYear
	 * @param endYear
	 * @param periodType
	 * @param pageNum
	 * @param pageSize
	 * @param lang
	 * @return
	 */
	private ApiResultWrapper<ApiIndicatorValue> innerListIndicators(final RequestType requestType, final List<String> indicatorTypeCodes, final List<String> sourceCodes,
			final List<String> entityCodes, final Integer startYear,
			final Integer endYear, final PeriodType periodType, final SortingOption sortingOption,
			final Integer pageNum, final Integer pageSize, final String lang) {
		ApiResultWrapper<ApiIndicatorValue> result;
		try {
			final RequestParamsWrapper paramsWrapper = new RequestParamsWrapper(requestType, indicatorTypeCodes, sourceCodes, entityCodes, startYear, endYear,
					periodType, sortingOption, pageNum, pageSize, lang);
			result = (ApiResultWrapper<ApiIndicatorValue>) this.searchViaCache(paramsWrapper);
		} catch (final ApiV2ProcessingException e) {
			result = new ApiResultWrapper<ApiIndicatorValue>(e.getMessage());
		}
		return result;
	}

	/**
	 * @param paramsWrapper
	 * @throws ApiV2ProcessingException
	 */
	private ApiResultWrapper<?> searchViaCache(final RequestParamsWrapper paramsWrapper) throws ApiV2ProcessingException {
		ApiResultWrapper<?> result;
		try {
			result = this.indicatorResultCache.get(paramsWrapper);
			logger.info(this.indicatorResultCache.stats().toString());
			return result;
		} catch (final ExecutionException e) {
			if ( e.getCause() instanceof ApiV2ProcessingException ) {
				throw (ApiV2ProcessingException)e.getCause();
			} else {
				logger.error(e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
	}


	@Override
	public ApiResultWrapper<Integer> listAvailablePeriods(final List<String> indicatorTypeCodes, final List<String> sourceCodes, final List<String> entityCodes,
			final Integer startYear, final Integer endYear) {

		ApiResultWrapper<Integer> result;
		final Long time = System.currentTimeMillis();
		try {
			final RequestParamsWrapper paramsWrapper = new RequestParamsWrapper(RequestType.PERIOD_LIST, indicatorTypeCodes, sourceCodes, entityCodes, startYear, endYear,
					null, null, null, null, null);
			result = (ApiResultWrapper<Integer>) this.searchViaCache(paramsWrapper);
		} catch (final ApiV2ProcessingException e) {
			e.printStackTrace();
			result = new ApiResultWrapper<Integer>(e.getMessage());
		}
		logger.info( String.format("Query answered in %s ms", System.currentTimeMillis()-time ) );
		return result;
	}

}
