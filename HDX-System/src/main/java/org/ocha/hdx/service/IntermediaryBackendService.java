/**
 *
 */
package org.ocha.hdx.service;

import org.ocha.hdx.exceptions.apiv2.ApiV2ProcessingException;
import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper;

/**
 * @author alexandru-m-g
 *
 */
public interface IntermediaryBackendService {
	/**
	 *
	 * @param paramsWrapper
	 * @return
	 * @throws ApiV2ProcessingException
	 */
	ApiResultWrapper<ApiIndicatorValue>  listIndicatorsByCriteriaWithPagination(RequestParamsWrapper paramsWrapper) throws ApiV2ProcessingException;

	/**
	 *
	 * @param paramsWrapper
	 * @return
	 * @throws ApiV2ProcessingException
	 */
	ApiResultWrapper<ApiIndicatorValue>  listIndicatorsByCriteria(RequestParamsWrapper paramsWrapper) throws ApiV2ProcessingException;

	ApiResultWrapper<Integer> listAvailablePeriods(final RequestParamsWrapper paramsWrapper);
}
