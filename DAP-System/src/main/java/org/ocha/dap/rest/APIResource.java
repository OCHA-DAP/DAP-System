package org.ocha.dap.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.service.CuratedDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.render.JsonRenderer;
import com.sun.jersey.api.view.Viewable;

@Path("/api")
@Component
public class APIResource {

	private static Logger logger = LoggerFactory.getLogger(APIResource.class);

	@Context
	private HttpServletRequest request;

	@Autowired
	private CuratedDataService curatedDataService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/yearly/source/{sourceCode}/indicatortype/{indicatorTypeCode}/json")
	public String getYearlyDataForSourceAndIndicatorType(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode) throws TypeMismatchException {
		final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, sourceCode, indicatorTypeCode);
		// final String result = GSONBuilderWrapper.getGSON().toJson(dataTable);
		final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();

		logger.debug("about to return from getYearlyDataForSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/yearly/source/{sourceCode}/indicatortype/{indicatorTypeCode}/{chartType}")
	public Response getChartWithYearlyDataForSourceAndIndicatorType(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode,
			@PathParam("chartType") final String chartType) throws TypeMismatchException {

		return Response.ok(new Viewable("/charts", chartType)).build();
	}

}
