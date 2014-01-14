package org.ocha.dap.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.ocha.dap.rest.helper.DisplayEntities;
import org.ocha.dap.rest.helper.DisplayIndicatorTypeDictionaries;
import org.ocha.dap.rest.helper.DisplayIndicators;
import org.ocha.dap.rest.helper.DisplayRegionDictionaries;
import org.ocha.dap.rest.helper.DisplaySourceDictionaries;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.service.CuratedDataService;
import org.ocha.dap.service.DAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Create a session from the token
 * 
 * @param token
 *            the token
 * @return response with session
 * @throws AuthenticationException
 *             if authentication exception occurs
 * @throws URISyntaxException
 *             the URI syntax exception occurs
 */

// 1800 seconds = 30 minutes
// FIXME add an error message
@RolesAllowed("admin")
@Path("/admin")
@Produces(MediaType.TEXT_HTML)
@Component
public class AdminResource {
	private static Logger logger = LoggerFactory.getLogger(AdminResource.class);

	private static String SESSION_PARAM_UID = "SESSION_PARAM_UID";

	@Context
	private HttpServletRequest request;

	@Autowired
	private DAPService dapService;

	@Autowired
	private CuratedDataService curatedDataService;

	/**
	 * Create a session from the token
	 * 
	 * @param token
	 *            the token
	 * @return response with session
	 * @throws AuthenticationException
	 *             if authentication exception occurs
	 * @throws URISyntaxException
	 *             the URI syntax exception occurs
	 */
	@PermitAll
	@POST
	@Path("/login/")
	public Response login(@FormParam("userId") final String userId, @FormParam("password") final String password, @Context final UriInfo uriInfo, @Context final SecurityContext sc)
			throws AuthenticationException, URISyntaxException {
		logger.debug(String.format("Entering login for user : %s", userId));
		if (dapService != null && dapService.authenticate(userId, password)) {
			final HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_PARAM_UID, userId);
			// 1800 seconds = 30 minutes
			session.setMaxInactiveInterval(1800);

			if ("admin".equals(dapService.getUserById(userId).getRole())) {
				final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();
				return Response.seeOther(newURI).build();
			} else {
				final URI newURI = uriInfo.getBaseUriBuilder().path("api/yearly/source/acled/indicatortype/PVX040/BarChart/").build();
				return Response.seeOther(newURI).build();
			}

		}

		throw new AuthenticationException();
	}

	@PermitAll
	@GET
	@Path("/login/")
	public Response loginForm() {
		return Response.ok(new Viewable("/login", null)).build();
	}

	@PermitAll
	@GET
	@Path("/logout/")
	public Response logout(@Context final UriInfo uriInfo) {
		final HttpSession session = request.getSession(false);
		session.invalidate();
		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/login/").build();
		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/users/")
	public Response displayUsersList() {
		return Response.ok(new Viewable("/admin/users", dapService.listUsers())).build();
	}

	@POST
	@Path("/users/")
	public Response createUser(@FormParam("id") final String id, @FormParam("password") final String password, @FormParam("password2") final String password2,
			@FormParam("ckanApiKey") final String ckanApiKey, @FormParam("role") final String role) throws Exception {
		if (!password.equals(password2)) {
			// FIXME add an error message
			return displayUsersList();
		}
		dapService.createUser(id, password, role, ckanApiKey);

		return displayUsersList();
	}

	@GET
	@Path("/status/datasets/")
	public Response getCKANDatasetsStatus() {
		return Response.ok(new Viewable("/admin/datasets", dapService.listCKANDatasets())).build();
	}

	@POST
	@Path("/status/datasets/flagDatasetAsToBeCurated")
	public Response flagDatasetAsToBeCurated(@FormParam("datasetName") final String datasetName, @FormParam("type") final CKANDataset.Type type, @Context final UriInfo uriInfo)
			throws URISyntaxException {
		dapService.flagDatasetAsToBeCurated(datasetName, type);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();
		return Response.seeOther(newURI).build();
	}

	@POST
	@Path("/status/datasets/flagDatasetAsIgnored")
	public Response flagDatasetAsIgnored(@FormParam("datasetName") final String datasetName, @Context final UriInfo uriInfo) throws URISyntaxException {
		dapService.flagDatasetAsIgnored(datasetName);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();
		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/resources/")
	public Response getCKANResourcesStatus() {
		return Response.ok(new Viewable("/admin/resources", dapService.listCKANResources())).build();
	}

	@GET
	@Path("/status/resources/{id}/{revision_id}/report")
	public Response getCKANResourceReport(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id) {
		return Response.ok(new Viewable("/admin/report", dapService.getCKANResource(id, revision_id))).build();
	}

	@GET
	@Path("/status/manuallyTriggerDownload/{id}/{revision_id}")
	public Response manuallyTriggerDownload(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id, @Context final UriInfo uriInfo) throws URISyntaxException,
			IOException {
		dapService.downloadFileForCKANResource(id, revision_id);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerEvaluation/{id}/{revision_id}")
	public Response manuallyTriggerEvaluation(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id, @Context final UriInfo uriInfo) throws URISyntaxException,
			IOException {
		dapService.evaluateFileForCKANResource(id, revision_id);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerImport/{id}/{revision_id}")
	public Response manuallyTriggerImport(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id, @Context final UriInfo uriInfo) throws URISyntaxException, IOException {
		dapService.transformAndImportDataFromFileForCKANResource(id, revision_id);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerDatasetsDetection")
	public Response manuallyTriggerDatasetsDetection(@Context final UriInfo uriInfo) throws URISyntaxException {
		dapService.checkForNewCKANDatasets();

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerResourcesDetection")
	public Response manuallyTriggerResourcesDetection(@Context final UriInfo uriInfo) throws URISyntaxException {
		dapService.checkForNewCKANResources();

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/curated/entitytypes")
	public Response displayEntityTypesList() {
		return Response.ok(new Viewable("/admin/entityTypes", curatedDataService.listEntityTypes())).build();
	}

	@POST
	@Path("/curated/entitytypes")
	public Response addEntityType(@FormParam("code") final String code, @FormParam("name") final String name) {
		curatedDataService.addEntityType(code, name);
		return displayEntityTypesList();
	}

	@GET
	@Path("/curated/entities")
	public Response displayEntitiesList() {
		final DisplayEntities displayEntities = new DisplayEntities();
		displayEntities.setEntities(curatedDataService.listEntities());
		displayEntities.setEntityTypes(curatedDataService.listEntityTypes());
		return Response.ok(new Viewable("/admin/entities", displayEntities)).build();
	}

	@POST
	@Path("/curated/entities")
	public Response addEntity(@FormParam("code") final String code, @FormParam("name") final String name, @FormParam("entityTypeCode") final String entityTypeCode) {
		curatedDataService.addEntity(code, name, entityTypeCode);
		return displayEntitiesList();
	}

	@GET
	@Path("/curated/indicatortypes")
	public Response displayIndicatorTypesList() {
		return Response.ok(new Viewable("/admin/indicatorTypes", curatedDataService.listIndicatorTypes())).build();
	}

	@POST
	@Path("/curated/indicatortypes")
	public Response addIndicatorType(@FormParam("code") final String code, @FormParam("name") final String name, @FormParam("unit") final String unit) {
		curatedDataService.addIndicatorType(code, name, unit);
		return displayIndicatorTypesList();
	}

	@GET
	@Path("/curated/sources")
	public Response displaySourcesList() {
		return Response.ok(new Viewable("/admin/sources", curatedDataService.listSources())).build();
	}

	@POST
	@Path("/curated/sources")
	public Response addSource(@FormParam("code") final String code, @FormParam("name") final String name) {
		curatedDataService.addSource(code, name);
		return displaySourcesList();
	}

	@GET
	@Path("/curated/importsfromckan")
	public Response displayImportFromCKANList() {
		return Response.ok(new Viewable("/admin/importsFromCKAN", curatedDataService.listImportsFromCKAN())).build();
	}

	@POST
	@Path("/curated/importsfromckan/delete")
	public Response deleteImportFromCKAN(@FormParam("importToDeleteId") final long importToDeleteId) {
		curatedDataService.deleteImportFromCKAN(importToDeleteId);
		return displayImportFromCKANList();
	}

	@GET
	@Path("/curated/indicators/")
	public Response displayIndicatorsList() {
		final DisplayIndicators displayIndicators = new DisplayIndicators();
		displayIndicators.setIndicators(curatedDataService.listLastIndicators(100));
		displayIndicators.setSources(curatedDataService.listSources());
		displayIndicators.setEntities(curatedDataService.listEntities());
		displayIndicators.setIndicatorTypes(curatedDataService.listIndicatorTypes());
		displayIndicators.setPeriodicities(Indicator.Periodicity.values());
		return Response.ok(new Viewable("/admin/indicators", displayIndicators)).build();
	}

	@POST
	@Path("/curated/indicators")
	public Response addIndicator(@FormParam("sourceCode") final String sourceCode, @FormParam("entityId") final long entityId, @FormParam("indicatorTypeCode") final String indicatorTypeCode,
			@FormParam("start") final String start, @FormParam("end") final String end, @FormParam("periodicity") final Periodicity periodicity, @FormParam("numeric") final boolean numeric,
			@FormParam("value") final String value, @FormParam("initialValue") final String initialValue) {
		final DateTimeFormatter fmt = ISODateTimeFormat.date();
		final Date startDate = fmt.parseDateTime(start).toDate();
		final Date endDate = fmt.parseDateTime(end).toDate();
		curatedDataService.addIndicator(sourceCode, entityId, indicatorTypeCode, startDate, endDate, periodicity, numeric, value, initialValue);
		return displayIndicatorsList();
	}

	@POST
	@Path("/curated/indicators/submitdelete")
	public Response deleteIndicator(@FormParam("indicatorId") final long indicatorId, @Context final UriInfo uriInfo) {
		curatedDataService.deleteIndicator(indicatorId);
		
		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/curated/indicators/").build();
		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/dictionaries/regions")
	public Response displayRegionDictionariesList() {
		final DisplayRegionDictionaries displayRegionDictionaries = new DisplayRegionDictionaries();
		displayRegionDictionaries.setEntities(curatedDataService.listEntities());
		displayRegionDictionaries.setRegionDictionaries(curatedDataService.listRegionDictionaries());
		return Response.ok(new Viewable("/admin/regionDictionaries", displayRegionDictionaries)).build();
	}

	@POST
	@Path("/dictionaries/regions")
	public Response addRegionDictionaryEntry(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @FormParam("entity") final long entity) {
		curatedDataService.addRegionDictionary(unnormalizedName, importer, entity);
		return displayRegionDictionariesList();
	}

	@GET
	@Path("/dictionaries/sources")
	public Response displaySourceDictionariesList() {
		final DisplaySourceDictionaries displaySourceDictionaries = new DisplaySourceDictionaries();
		displaySourceDictionaries.setSources(curatedDataService.listSources());
		displaySourceDictionaries.setSourceDictionaries(curatedDataService.listSourceDictionaries());
		return Response.ok(new Viewable("/admin/sourceDictionaries", displaySourceDictionaries)).build();
	}

	@POST
	@Path("/dictionaries/sources")
	public Response addSourceDictionaryEntry(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @FormParam("source") final long source) {
		curatedDataService.addSourceDictionary(unnormalizedName, importer, source);
		return displaySourceDictionariesList();
	}

	@POST
	@Path("/dictionaries/regions/submitdelete")
	public Response deleteRegionDictionary(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @Context final UriInfo uriInfo)
			throws URISyntaxException {
		final RegionDictionary regionDictionary = new RegionDictionary(unnormalizedName, importer);
		curatedDataService.deleteRegionDictionary(regionDictionary);
		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/dictionaries/regions/").build();
		return Response.seeOther(newURI).build();

	}

	@GET
	@Path("/dictionaries/indicatorTypes")
	public Response displayIndicatorTypeDictionariesList() {
		final DisplayIndicatorTypeDictionaries displayIndicatorTypeDictionaries = new DisplayIndicatorTypeDictionaries();
		displayIndicatorTypeDictionaries.setIndicatorTypes(curatedDataService.listIndicatorTypes());
		displayIndicatorTypeDictionaries.setIndicatorTypeDictionaries(curatedDataService.listIndicatorTypeDictionaries());
		return Response.ok(new Viewable("/admin/indicatorTypeDictionaries", displayIndicatorTypeDictionaries)).build();
	}

	@POST
	@Path("/dictionaries/indicatorTypes")
	public Response addIndicatorTypeDictionaryEntry(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer,
			@FormParam("indicatorType") final long indicatorType) {
		curatedDataService.addIndicatorTypeDictionary(unnormalizedName, importer, indicatorType);
		return displayIndicatorTypeDictionariesList();
	}

	@POST
	@Path("/dictionaries/indicatorTypes/submitdelete")
	public Response deleteIndicatorTypeDictionary(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @Context final UriInfo uriInfo)
			throws URISyntaxException {
		final IndicatorTypeDictionary indicatorTypeDictionary = new IndicatorTypeDictionary(unnormalizedName, importer);
		curatedDataService.deleteIndicatorTypeDictionary(indicatorTypeDictionary);
		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/dictionaries/indicatorTypes/").build();
		return Response.seeOther(newURI).build();

	}

}
