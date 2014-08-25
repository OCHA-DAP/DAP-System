/**
 *
 */
package org.ocha.hdx.service;

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
	 */
	ApiResultWrapper<ApiIndicatorValue>  listIndicatorsByCriteriaWithPagination(RequestParamsWrapper paramsWrapper);

	/**
	 *
	 * @param paramsWrapper
	 * @return
	 */
	ApiResultWrapper<ApiIndicatorValue>  listIndicatorsByCriteria(RequestParamsWrapper paramsWrapper);
}
