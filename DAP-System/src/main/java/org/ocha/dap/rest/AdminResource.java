package org.ocha.dap.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.ocha.dap.persistence.entity.User;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorValue;
import org.ocha.dap.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.ocha.dap.rest.helper.DisplayEntities;
import org.ocha.dap.rest.helper.DisplayIndicatorTypeDictionaries;
import org.ocha.dap.rest.helper.DisplayIndicators;
import org.ocha.dap.rest.helper.DisplayRegionDictionaries;
import org.ocha.dap.rest.helper.DisplaySourceDictionaries;
import org.ocha.dap.service.CuratedDataService;
import org.ocha.dap.service.DAPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.visualization.datasource.base.TypeMismatchException;

@RolesAllowed("admin")
@Path("/admin")
@Produces(MediaType.TEXT_HTML)
@Component
public class AdminResource {

	@Autowired
	private DAPService dapService;

	@Autowired
	private CuratedDataService curatedDataService;

	/*
	 * Users management
	 */
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/json")
	public String getUsers() throws TypeMismatchException {

		final List<User> listUsers = dapService.listUsers();
		final JsonArray jsonArray = new JsonArray();

		for (final User user : listUsers) {

			final JsonObject element = new JsonObject();
			element.addProperty("id", user.getId());
			element.addProperty("role", user.getRole());
			jsonArray.add(element);
		}
		return jsonArray.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/roles/json")
	public String getUserRoles() throws TypeMismatchException {

		final List<String> roles = dapService.listRoles();
		final JsonArray jsonArray = new JsonArray();

		for (final String role : roles) {

			final JsonObject element = new JsonObject();
			element.addProperty("value", role);
			element.addProperty("text", role);
			jsonArray.add(element);
		}
		return jsonArray.toString();
	}

	@POST
	@Path("/users/submitadd")
	public Response addUserupdateUser(@FormParam("userId") final String userId, @FormParam("newPassword") final String newPassword, @FormParam("newPassword2") final String newPassword2,
			@FormParam("newCkanApiKey") final String newCkanApiKey, @FormParam("newRole") final String newRole, @Context final UriInfo uriInfo) throws Exception {
		// TODO Perform validation
		
		dapService.createUser(userId, newPassword, newRole, newCkanApiKey);
		return Response.ok().build();
	}

	@POST
	@Path("/users/submitupdate")
	public Response updateUser(@FormParam("userId") final String userId, @FormParam("newPassword") final String newPassword, @FormParam("newPassword2") final String newPassword2,
			@FormParam("newCkanApiKey") final String newCkanApiKey, @FormParam("newRole") final String newRole, @Context final UriInfo uriInfo) throws Exception {
		dapService.updateUser(userId, newPassword, newRole, newCkanApiKey);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/users/").build();
		return Response.seeOther(newURI).build();
	}

	@POST
	@Path("/users/submitdelete")
	public Response deleteUser(@FormParam("userId") final String userId, @Context final UriInfo uriInfo) throws Exception {
		dapService.deleteUser(userId);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/users/").build();
		return Response.seeOther(newURI).build();
	}

	/*
	 * Status / datasets management
	 */
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

	/*
	 * Status / resources management
	 */
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

	/*
	 * Status / manual triggers management
	 */
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

	/*
	 * Curated / entity types management
	 */
	@GET
	@Path("/curated/entitytypes")
	public Response displayEntityTypesList() {
		return Response.ok(new Viewable("/admin/entityTypes", curatedDataService.listEntityTypes())).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/curated/entitytypes/json")
	public String getEntityTypes() throws TypeMismatchException {

		/*
		 * final DataTable dataTable = curatedDataService.listEntityTypesAsDataTable(); final String result =
		 * JsonRenderer.renderDataTable(dataTable, true, false, false).toString();
		 * logger.debug("about to return from listEntityTypesAsDataTable"); logger.debug(result); return result;
		 */

		final List<EntityType> listEntityTypes = curatedDataService.listEntityTypes();
		final JsonArray jsonArray = new JsonArray();

		for (final EntityType entityType : listEntityTypes) {

			final JsonObject element = new JsonObject();
			element.addProperty("id", entityType.getId());
			element.addProperty("code", entityType.getCode());
			element.addProperty("name", entityType.getName());
			jsonArray.add(element);
		}
		return jsonArray.toString();
	}

	@POST
	@Path("/curated/entitytypes")
	public Response addEntityType(@FormParam("code") final String code, @FormParam("name") final String name) {
		curatedDataService.addEntityType(code, name);
		return displayEntityTypesList();
	}

	/*
	 * Curated / entities management
	 */
	@GET
	@Path("/curated/entities/")
	public Response displayEntitiesList() {
		final DisplayEntities displayEntities = new DisplayEntities();
		displayEntities.setEntities(curatedDataService.listEntities());
		displayEntities.setEntityTypes(curatedDataService.listEntityTypes());
		return Response.ok(new Viewable("/admin/entities", displayEntities)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("curated/entities/json")
	public String getEntities() throws TypeMismatchException {
		final List<Entity> listEntities = curatedDataService.listEntities();
		final JsonArray jsonArray = new JsonArray();
		for (final Entity entity : listEntities) {
			final JsonObject element = new JsonObject();
			element.addProperty("id", entity.getId());
			element.addProperty("type", entity.getType().getId());
			element.addProperty("code", entity.getCode());
			// FIXME get a language instead of the default value
			element.addProperty("name", entity.getName().getDefaultValue());
			jsonArray.add(element);
		}
		return jsonArray.toString();
	}

	@POST
	@Path("/curated/entities/submitadd")
	public Response addEntity(@FormParam("entityTypeCode") final String entityTypeCode, @FormParam("code") final String code, @FormParam("name") final String name, @Context final UriInfo uriInfo) {
		curatedDataService.addEntity(code, name, entityTypeCode);

		return Response.ok().build();
	}

	@POST
	@Path("/curated/entities/submitdelete")
	public Response deleteEntity(@FormParam("entityId") final long entityId, @Context final UriInfo uriInfo) {
		curatedDataService.deleteEntity(entityId);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/curated/entities/").build();
		return Response.seeOther(newURI).build();
	}

	@POST
	@Path("/curated/entities/submitupdate")
	public Response updateEntity(@FormParam("entityId") final long entityId, @FormParam("newName") final String newName, @Context final UriInfo uriInfo) {
		curatedDataService.updateEntity(entityId, newName);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/curated/entities/").build();
		return Response.seeOther(newURI).build();
	}

	/*
	 * Curated / indicator types management
	 */
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

	/*
	 * Curated / sources management
	 */
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

	/*
	 * Curated / imports from CKAN management
	 */
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

	/*
	 * Curated / indicators management
	 */
	@GET
	@Path("/curated/indicators/")
	public Response displayIndicatorsList() {
		final DisplayIndicators displayIndicators = new DisplayIndicators();
		displayIndicators.setIndicators(curatedDataService.listLastIndicators(100));
		displayIndicators.setSources(curatedDataService.listSources());
		displayIndicators.setEntities(curatedDataService.listEntities());
		displayIndicators.setIndicatorTypes(curatedDataService.listIndicatorTypes());
		displayIndicators.setPeriodicities(Indicator.Periodicity.values());
		displayIndicators.setValueTypes(IndicatorType.ValueType.values());
		return Response.ok(new Viewable("/admin/indicators", displayIndicators)).build();
	}

	@POST
	@Path("/curated/indicators")
	public Response addIndicator(@FormParam("sourceCode") final String sourceCode, @FormParam("entityId") final long entityId, @FormParam("indicatorTypeCode") final String indicatorTypeCode,
			@FormParam("start") final String start, @FormParam("end") final String end, @FormParam("periodicity") final Periodicity periodicity, @FormParam("valueType") final ValueType valueType,
			@FormParam("value") final String valueAsString, @FormParam("initialValue") final String initialValue) {
		final DateTimeFormatter fmt = ISODateTimeFormat.date();
		final Date startDate = fmt.parseDateTime(start).toDate();
		final Date endDate = fmt.parseDateTime(end).toDate();
		final IndicatorValue value;
		switch (valueType) {
		case STRING:
			value = new IndicatorValue(valueAsString);
			break;
		case DATE:
		case DATETIME:
			value = new IndicatorValue(fmt.parseDateTime(valueAsString).toDate(), valueType);
			break;
		case NUMBER:
			value = new IndicatorValue(Double.parseDouble(valueAsString));
			break;
		case TEXT:
			value = new IndicatorValue(new Text(valueAsString));
			break;
		default:
			value = new IndicatorValue(valueAsString);
			break;
		}
		curatedDataService.addIndicator(sourceCode, entityId, indicatorTypeCode, startDate, endDate, periodicity, value, initialValue);
		return displayIndicatorsList();
	}

	@POST
	@Path("/curated/indicators/submitdelete")
	public Response deleteIndicator(@FormParam("indicatorId") final long indicatorId, @Context final UriInfo uriInfo) {
		curatedDataService.deleteIndicator(indicatorId);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/curated/indicators/").build();
		return Response.seeOther(newURI).build();
	}

	/*
	 * Dictionaries / regions management
	 */
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

	@POST
	@Path("/dictionaries/regions/submitdelete")
	public Response deleteRegionDictionary(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @Context final UriInfo uriInfo)
			throws URISyntaxException {
		final RegionDictionary regionDictionary = new RegionDictionary(unnormalizedName, importer);
		curatedDataService.deleteRegionDictionary(regionDictionary);
		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/dictionaries/regions/").build();
		return Response.seeOther(newURI).build();

	}

	/*
	 * Dictionaries / sources management
	 */
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

	/*
	 * Dictionaries / indicator types management
	 */
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
