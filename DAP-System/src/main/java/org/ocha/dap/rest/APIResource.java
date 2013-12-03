package org.ocha.dap.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.service.CuratedDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
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
	 * 
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
		model.put("title", indicatorType.getDisplayableTitle() + " according to " + curatedDataService.getSourceByCode(sourceCode).getName());
		if ("BarChart".equals(chartType)) {
			model.put("vAxisTitle", "year");
			model.put("hAxisTitle", indicatorType.getName());
		} else if ("ColumnChart".equals(chartType) || "AreaChart".equals(chartType)) {
			model.put("vAxisTitle", indicatorType.getName());
			model.put("hAxisTitle", "year");
		}
		return Response.ok(new Viewable("/analytical/charts", model)).build();
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
		return Response.ok(new Viewable("/analytical/charts", model)).build();
	}

	@GET
	@Produces({ "text/csv" })
	@Path("/yearly/entity/{entityType}/{entityCode}/indicatortype/{indicatorTypeCode}/csv")
	public String getYearlyDataForEntityAndIndicatorTypeAsCSV(@PathParam("entityType") final String entityType, @PathParam("entityCode") final String entityCode,
			@PathParam("indicatorTypeCode") final String indicatorTypeCode) throws TypeMismatchException {
		final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, entityType, entityCode, indicatorTypeCode);
		final String result = CsvRenderer.renderDataTable(dataTable, ULocale.ENGLISH, ",").toString();
		logger.debug("about to return from getYearlyDataForSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/yearly/entity/{entityType}/{entityCode}/indicatortype/{indicatorTypeCode}/json")
	public String getYearlyDataForEntityAndIndicatorType(@PathParam("entityType") final String entityType, @PathParam("entityCode") final String entityCode,
			@PathParam("indicatorTypeCode") final String indicatorTypeCode) throws TypeMismatchException {
		final DataTable dataTable = curatedDataService.listIndicatorsByPeriodicityAndEntityAndIndicatorType(Periodicity.YEAR, entityType, entityCode, indicatorTypeCode);
		final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();
		logger.debug("about to return from getYearlyDataForSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	/**
	 * The actual data is fetched in a separate call
	 * 
	 * @see #getYearlyDataForSourceAndIndicatorType
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/yearly/entity/{entityType}/{entityCode}/indicatortype/{indicatorTypeCode}/{chartType}")
	public Response getChartWithYearlyDataForEntityAndIndicatorType(@PathParam("entityType") final String entityType, @PathParam("entityCode") final String entityCode,
			@PathParam("indicatorTypeCode") final String indicatorTypeCode, @PathParam("chartType") final String chartType) {
		final Map<String, String> model = new HashMap<String, String>();
		model.put("chartType", chartType);
		final IndicatorType indicatorType = curatedDataService.getIndicatorTypeByCode(indicatorTypeCode);
		final Entity entity = curatedDataService.getEntityByCodeAndType(entityCode, entityType);
		model.put("title", indicatorType.getDisplayableTitle() + " for " + entity.getName());
		if ("BarChart".equals(chartType)) {
			model.put("vAxisTitle", "year");
			model.put("hAxisTitle", indicatorType.getName());
		} else if ("ColumnChart".equals(chartType) || "AreaChart".equals(chartType)) {
			model.put("vAxisTitle", indicatorType.getName());
			model.put("hAxisTitle", "year");
		}
		return Response.ok(new Viewable("/analytical/charts", model)).build();
	}

	@GET
	@Produces({ "text/csv" })
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype1/{indicatorTypeCode1}/indicatortype2/{indicatorTypeCode2}/csv")
	public String getDataForYearAndSourceAndIndicatorTypesAsCSV(@PathParam("year") final int year, @PathParam("sourceCode") final String sourceCode,
			@PathParam("indicatorTypeCode1") final String indicatorTypeCode1, @PathParam("indicatorTypeCode2") final String indicatorTypeCode2) throws TypeMismatchException {
		List<String> indicatorTypes = new ArrayList<>();
		indicatorTypes.add(indicatorTypeCode1);
		indicatorTypes.add(indicatorTypeCode2);

		final DataTable dataTable = curatedDataService.listIndicatorsByYearAndSourceAndIndicatorTypes(year, sourceCode, indicatorTypes);

		final String result = CsvRenderer.renderDataTable(dataTable, ULocale.ENGLISH, ",").toString();

		logger.debug("about to return from getDataForYearAndSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype1/{indicatorTypeCode1}/indicatortype2/{indicatorTypeCode2}/json")
	public String getDataForYearAndSourceAndIndicatorTypes(@PathParam("year") final int year, @PathParam("sourceCode") final String sourceCode,
			@PathParam("indicatorTypeCode1") final String indicatorTypeCode1, @PathParam("indicatorTypeCode2") final String indicatorTypeCode2) throws TypeMismatchException {
		List<String> indicatorTypes = new ArrayList<>();
		indicatorTypes.add(indicatorTypeCode1);
		indicatorTypes.add(indicatorTypeCode2);

		final DataTable dataTable = curatedDataService.listIndicatorsByYearAndSourceAndIndicatorTypes(year, sourceCode, indicatorTypes);

		final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();

		logger.debug("about to return from getDataForYearAndSourceAndIndicatorType");
		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype1/{indicatorTypeCode1}/indicatortype2/{indicatorTypeCode2}/{chartType}")
	public Response getChartForYearAndSourceAndIndicatorTypes(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode1") final String indicatorTypeCode1,
			@PathParam("indicatorTypeCode2") final String indicatorTypeCode2, @PathParam("chartType") final String chartType) throws TypeMismatchException {

		final Map<String, String> model = new HashMap<String, String>();
		model.put("chartType", chartType);
		// FIXME Build a title based on the different indicator types
		final IndicatorType indicatorType = curatedDataService.getIndicatorTypeByCode(indicatorTypeCode1);
		model.put("title", indicatorType.getDisplayableTitle());
		return Response.ok(new Viewable("/analytical/charts", model)).build();
	}

	@GET
	@Produces({ "text/csv" })
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype1/{indicatorTypeCode1}/indicatortype2/{indicatorTypeCode2}/indicatortype3/{indicatorTypeCode3}/csv")
	public String getDataForYearAndSourceAnd3IndicatorTypesAsCSV(@PathParam("year") final int year, @PathParam("sourceCode") final String sourceCode,
			@PathParam("indicatorTypeCode1") final String indicatorTypeCode1, @PathParam("indicatorTypeCode2") final String indicatorTypeCode2,
			@PathParam("indicatorTypeCode3") final String indicatorTypeCode3) throws TypeMismatchException {
		List<String> indicatorTypeCodes = new ArrayList<>();
		indicatorTypeCodes.add(indicatorTypeCode1);
		indicatorTypeCodes.add(indicatorTypeCode2);
		indicatorTypeCodes.add(indicatorTypeCode3);
		
		Collections.sort(indicatorTypeCodes);

		//FIXME we have a sorting problem here
		final DataTable dataTable = curatedDataService.listIndicatorsByYearAndSourceAndIndicatorTypes(year, sourceCode, indicatorTypeCodes);

		final String result = CsvRenderer.renderDataTable(dataTable, ULocale.ENGLISH, ",").toString();

		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype1/{indicatorTypeCode1}/indicatortype2/{indicatorTypeCode2}/indicatortype3/{indicatorTypeCode3}/json")
	public String getDataForYearAndSourceAnd3IndicatorTypes(@PathParam("year") final int year, @PathParam("sourceCode") final String sourceCode,
			@PathParam("indicatorTypeCode1") final String indicatorTypeCode1, @PathParam("indicatorTypeCode2") final String indicatorTypeCode2,
			@PathParam("indicatorTypeCode3") final String indicatorTypeCode3) throws TypeMismatchException {
		List<String> indicatorTypeCodes = new ArrayList<>();
		indicatorTypeCodes.add(indicatorTypeCode1);
		indicatorTypeCodes.add(indicatorTypeCode2);
		indicatorTypeCodes.add(indicatorTypeCode3);
		
		Collections.sort(indicatorTypeCodes);

		//FIXME we have a sorting problem here
		final DataTable dataTable = curatedDataService.listIndicatorsByYearAndSourceAndIndicatorTypes(year, sourceCode, indicatorTypeCodes);

		final String result = JsonRenderer.renderDataTable(dataTable, true, false, false).toString();

		logger.debug(result);

		return result;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/yearly/year/{year}/source/{sourceCode}/indicatortype1/{indicatorTypeCode1}/indicatortype2/{indicatorTypeCode2}/indicatortype3/{indicatorTypeCode3}/{chartType}")
	public Response getChartForYearAndSourceAnd3IndicatorTypes(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode1") final String indicatorTypeCode1,
			@PathParam("indicatorTypeCode2") final String indicatorTypeCode2, @PathParam("indicatorTypeCode3") final String indicatorTypeCode3, @PathParam("chartType") final String chartType)
			throws TypeMismatchException {

		List<String> indicatorTypeCodes = new ArrayList<>();
		indicatorTypeCodes.add(indicatorTypeCode1);
		indicatorTypeCodes.add(indicatorTypeCode2);
		indicatorTypeCodes.add(indicatorTypeCode3);
		
		Collections.sort(indicatorTypeCodes);
		
		final Map<String, String> model = new HashMap<String, String>();
		model.put("chartType", chartType);
		final IndicatorType indicatorType1 = curatedDataService.getIndicatorTypeByCode(indicatorTypeCodes.get(0));
		final IndicatorType indicatorType2 = curatedDataService.getIndicatorTypeByCode(indicatorTypeCodes.get(1));
		final IndicatorType indicatorType3 = curatedDataService.getIndicatorTypeByCode(indicatorTypeCodes.get(2));
		model.put("hAxisTitle", indicatorType1.getDisplayableTitle());
		model.put("vAxisTitle", indicatorType2.getDisplayableTitle());
		model.put("title", indicatorType3.getDisplayableTitle());
		return Response.ok(new Viewable("/analytical/charts", model)).build();
	}
}
