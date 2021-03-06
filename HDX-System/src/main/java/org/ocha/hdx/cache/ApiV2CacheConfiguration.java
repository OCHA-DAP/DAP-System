/**
 *
 */
package org.ocha.hdx.cache;

import java.util.concurrent.TimeUnit;

import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.RequestType;
import org.ocha.hdx.service.IntermediaryBackendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author alexandru-m-g
 *
 */
@Configuration
public class ApiV2CacheConfiguration {

	@Autowired
	private IntermediaryBackendService intermediaryBackendService;

	@Bean
	public Cache<RequestParamsWrapper, ApiResultWrapper<?>> indicatorResultCache() {
		final LoadingCache<RequestParamsWrapper, ApiResultWrapper<?>> indicatorResultCache =
			CacheBuilder.newBuilder().maximumSize(5000)
				.expireAfterWrite(1, TimeUnit.HOURS)
				.recordStats()
				.build(new CacheLoader<RequestParamsWrapper, ApiResultWrapper<?>>() {
					@Override
					public ApiResultWrapper<?> load(final RequestParamsWrapper paramsWrapper) throws Exception {
						if ( RequestType.PERIOD_LIST.equals(paramsWrapper.getRequestType()) ) {
							return ApiV2CacheConfiguration.this.intermediaryBackendService.listAvailablePeriods(paramsWrapper);
						} else if ( paramsWrapper.getPageSize() == null && paramsWrapper.getPageNum() == null) {
							return ApiV2CacheConfiguration.this.intermediaryBackendService.listIndicatorsByCriteria(paramsWrapper);
						} else {
							return ApiV2CacheConfiguration.this.intermediaryBackendService.listIndicatorsByCriteriaWithPagination(paramsWrapper);
						}
					}
				});
		return indicatorResultCache;
	}
}
