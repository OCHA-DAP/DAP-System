package org.ocha.dap.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.ocha.dap.service.CuratedDataService;
import org.ocha.dap.tools.GSONBuilderWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public String getYearlyDataForSourceAndIndicatorType(@PathParam("sourceCode") final String sourceCode, @PathParam("indicatorTypeCode") final String indicatorTypeCode) {
		GSONBuilderWrapper.getGSON();
		return null;
	}
}
