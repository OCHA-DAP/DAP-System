package org.ocha.hdx.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.PeriodType;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.SortingOption;
import org.ocha.hdx.service.ApiV2BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PermitAll
@Path("/public/api2")
@Component
public class ApiV2Resource {

	// public enum PeriodType {
	// LATEST_YEAR("latest_year"), LATEST_YEAR_BY_COUNTRY("latest_year_by_country");
	//
	// private final String label;
	//
	// private PeriodType(final String label) {
	// this.label = label;
	// }
	//
	// public String getLabel() {
	// return label;
	// }
	//
	// }

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiV2Resource.class);

	@Autowired
	private ApiV2BackendService apiV2BackendService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/values")
	public ApiResultWrapper<ApiIndicatorValue> getIndicatorValues(@QueryParam("l") final List<String> entityCodes, 
			@QueryParam("it") final List<String> indicatorTypeCodes, @QueryParam("s") final List<String> sourceCodes, 
			@QueryParam("ds") final List<String> dataseriesCodes,
			@QueryParam("minTime") final String minTime, @QueryParam("maxTime") final String maxTime,
			@QueryParam("periodType") final PeriodType periodType, @QueryParam("sorting") final SortingOption sortingOption,
			@QueryParam("pageNum") final Integer pageNum, @QueryParam("pageSize") final Integer pageSize, @QueryParam("lang") final String lang) {

		LOGGER.debug("Entering getIndicatorValues");

		try {
			// We could let Jersey do this for us, but we might want to do something smarter in the future
			final Integer startYear = minTime == null ? null : new Integer(minTime);
			final Integer endYear = maxTime == null ? null : new Integer(maxTime);

			final ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(indicatorTypeCodes, sourceCodes,
					dataseriesCodes, entityCodes, startYear, endYear, periodType, sortingOption, pageNum, pageSize, lang);

			return listIndicatorsByCriteriaWithPagination;
		} catch (final Exception e) {
			LOGGER.error(e.toString(), e);
			return null;
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/available-periods")
	public ApiResultWrapper<Integer> getAvailablePeriods(@QueryParam("l") final List<String> entityCodes,
			@QueryParam("it") final List<String> indicatorTypeCodes,
			@QueryParam("s") final List<String> sourceCodes,
			@QueryParam("ds") final List<String> dataseriesCodes,
			@QueryParam("minTime") final String minTime,
			@QueryParam("maxTime") final String maxTime) {

		LOGGER.debug("Entering getIndicatorValues");

		try {
			// We could let Jersey do this for us, but we might want to do something smarter in the future
			final Integer startYear = minTime == null ? null : new Integer(minTime);
			final Integer endYear = maxTime == null ? null : new Integer(maxTime);

			final ApiResultWrapper<Integer> periodList = this.apiV2BackendService.listAvailablePeriods(indicatorTypeCodes, sourceCodes,
					dataseriesCodes, entityCodes, startYear, endYear);

			return periodList;
		} catch (final Exception e) {
			LOGGER.error(e.toString(), e);
			return null;
		}
	}

}
