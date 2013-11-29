package org.ocha.dap.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.service.CuratedDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.render.CsvRenderer;
import com.google.visualization.datasource.render.JsonRenderer;
import com.ibm.icu.util.ULocale;
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
	@Produces({ "text/csv" })
	@Path("/yearly/source/{sourceCode}/indicatortype/{indicatorTypeCode}/csv")
	public String getYearlyDataForSourceAndIndicatorTypeAsCSV(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode)
			throws TypeMismatchException {
		final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, sourceCode, indicatorTypeCode);
		final String result = CsvRenderer.renderDataTable(dataTable, ULocale.ENGLISH, ",").toString();
		logger.debug("about to return from getYearlyDataForSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/yearly/source/{sourceCode}/indicatortype/{indicatorTypeCode}/json")
	public String getYearlyDataForSourceAndIndicatorType(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode) throws TypeMismatchException {
		final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndSourceAndIndicatorType(Periodicity.YEAR, sourceCode, indicatorTypeCode);
		final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();
		logger.debug("about to return from getYearlyDataForSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	/**
	 * The actual data is fetched in a separate call
	 * @see #getYearlyDataForSourceAndIndicatorType
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/yearly/source/{sourceCode}/indicatortype/{indicatorTypeCode}/{chartType}")
	public Response getChartWithYearlyDataForSourceAndIndicatorType(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode,
			@PathParam("chartType") final String chartType) {
		final Map<String, String> model = new HashMap<String, String>();
		model.put("chartType", chartType);
		final IndicatorType indicatorType = curatedDataService.getIndicatorTypeByCode(indicatorTypeCode);
		model.put("title", indicatorType.getDisplayableTitle());
		if ("BarChart".equals(chartType)) {
			model.put("vAxisTitle", "year");
			model.put("hAxisTitle", indicatorType.getName());
		} else if ("ColumnChart".equals(chartType) || "AreaChart".equals(chartType)) {
			model.put("vAxisTitle", indicatorType.getName());
			model.put("hAxisTitle", "year");
		}
		return Response.ok(new Viewable("/charts", model)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype/{indicatorTypeCode}/json")
	public String getDataForYearAndSourceAndIndicatorType(@PathParam("year") final int year, @PathParam("sourceCode") final String sourceCode,
			@PathParam("indicatorTypeCode") final String indicatorTypeCode) throws TypeMismatchException {
		final DataTable dataTable = curatedDataService.listIndicatorsByYearAndSourceAndIndicatorType(year, sourceCode, indicatorTypeCode);
		final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();

		logger.debug("about to return from getDataForYearAndSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype/{indicatorTypeCode}/{chartType}")
	public Response getChartForYearAndSourceAndIndicatorType(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode,
			@PathParam("chartType") final String chartType) throws TypeMismatchException {

		final Map<String, String> model = new HashMap<String, String>();
		model.put("chartType", chartType);
		final IndicatorType indicatorType = curatedDataService.getIndicatorTypeByCode(indicatorTypeCode);
		model.put("title", indicatorType.getDisplayableTitle());
		return Response.ok(new Viewable("/charts", model)).build();
	}

}
