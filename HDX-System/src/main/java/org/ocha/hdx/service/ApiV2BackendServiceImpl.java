/**
 *
 */
package org.ocha.hdx.service;

import java.util.List;

import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;

/**
 * @author alexandru-m-g
 *
 */
public class ApiV2BackendServiceImpl implements ApiV2BackendService {

	/* (non-Javadoc)
	 * @see org.ocha.hdx.service.ApiV2BackendService#listIndicatorsByCriteriaWithPagination(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination(final List<String> indicatorTypeCodes, final List<String> sourceCodes,
			final List<String> entityCodes, final Integer startYear, final Integer endYear, final Integer pageNum, final Integer pageSize, final String lang) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.service.ApiV2BackendService#listIndicatorsByCriteria(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteria(final List<String> indicatorTypeCodes, final List<String> sourceCodes, final List<String> entityCodes,
			final Integer startYear, final Integer endYear, final String lang) {
		// TODO Auto-generated method stub
		return null;
	}

}
