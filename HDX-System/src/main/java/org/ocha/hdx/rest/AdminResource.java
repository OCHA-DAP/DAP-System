package org.ocha.hdx.rest;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.mvc.Viewable;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.ocha.hdx.persistence.entity.User;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;
import org.ocha.hdx.persistence.entity.i18n.Language;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.i18n.Translation;
import org.ocha.hdx.persistence.entity.i18n.Translation.Id;
import org.ocha.hdx.rest.helper.DisplayIndicatorTypeDictionaries;
import org.ocha.hdx.rest.helper.DisplayIndicators;
import org.ocha.hdx.rest.helper.DisplayRegionDictionaries;
import org.ocha.hdx.rest.helper.DisplaySourceDictionaries;
import org.ocha.hdx.service.CuratedDataService;
import org.ocha.hdx.service.HDXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.visualization.datasource.base.TypeMismatchException;

@RolesAllowed("admin")
@Path("/admin")
@Produces(MediaType.TEXT_HTML)
@Component
public class AdminResource {

	@Autowired
	private HDXService hdxService;

	@Autowired
	private CuratedDataService curatedDataService;

	/*
	 * Users management
	 */
	@GET
	@Path("/misc/users/")
	@SuppressWarnings("static-method")
	public Response displayUsersList() {
		return Response.ok(new Viewable("/admin/users")).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/misc/users/json")
	public String getUsers() throws TypeMismatchException {

		final List<User> listUsers = hdxService.listUsers();
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
	@Path("/misc/users/roles/json")
	public String getUserRoles(@QueryParam("var") final String var) throws TypeMismatchException {

		String result = ""; 
		
		final List<String> roles = hdxService.listRoles();
		final JsonArray jsonArray = new JsonArray();

		for (final String role : roles) {

			final JsonObject element = new JsonObject();
			element.addProperty("value", role);
			element.addProperty("text", role);
			jsonArray.add(element);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@POST
	@Path("/misc/users/submitCreate")
	public Response createUser(@FormParam("userId") final String userId, @FormParam("newPassword") final String newPassword, @FormParam("newCkanApiKey") final String newCkanApiKey,
			@FormParam("newRole") final String newRole) throws Exception {
		// TODO Perform validation

		hdxService.createUser(userId, newPassword, newRole, newCkanApiKey);
		return Response.ok().build();
	}

	@POST
	@Path("/misc/users/submitUpdate")
	public Response updateUser(@FormParam("userId") final String userId, @FormParam("newPassword") final String newPassword, @FormParam("newCkanApiKey") final String newCkanApiKey,
			@FormParam("newRole") final String newRole) throws Exception {
		hdxService.updateUser(userId, newPassword, newRole, newCkanApiKey);
		return Response.ok().build();
	}

	@POST
	@Path("/misc/users/submitDelete")
	public Response deleteUser(@FormParam("userId") final String userId) throws Exception {
		hdxService.deleteUser(userId);
		return Response.ok().build();
	}

	/*
	 * Languages management
	 */
	@GET
	@Path("/misc/languages/")
	@SuppressWarnings("static-method")
	public Response displayLanguagesList() {
		return Response.ok(new Viewable("/admin/languages")).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/misc/languages/json")
	public String getLanguages(@QueryParam("var") final String var) throws TypeMismatchException {
		
		String result = "";

		final List<Language> listLanguages = hdxService.listLanguages();
		final JsonArray jsonArray = new JsonArray();

		for (final Language language : listLanguages) {

			final JsonObject element = new JsonObject();
			element.addProperty("code", language.getCode());
			element.addProperty("native_name", language.getNativeName());
			jsonArray.add(element);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@POST
	@Path("/misc/languages/submitCreate")
	public Response createLanguage(@FormParam("code") final String languageCode, @FormParam("newNativeName") final String newNativeName) throws Exception {
		// TODO Perform validation
		hdxService.createLanguage(languageCode, newNativeName);
		return Response.ok().build();
	}

	@POST
	@Path("/misc/languages/submitUpdate")
	public Response updateLanguage(@FormParam("code") final String code, @FormParam("newNativeName") final String newNativeName) throws Exception {
		hdxService.updateLanguage(code, newNativeName);
		return Response.ok().build();
	}

	@POST
	@Path("/misc/languages/submitDelete")
	public Response deleteLanguage(@FormParam("code") final String code) throws Exception {
		hdxService.deleteLanguage(code);
		return Response.ok().build();
	}

	/*
	 * Translations management
	 */

	@POST
	@Path("/translations/submitCreate")
	public Response createTranslation(@FormParam("textId") final String textId, @FormParam("languageCode") final String languageCode, @FormParam("translationValue") final String translationValue)
			throws Exception, Exception {
		hdxService.createTranslation(Long.valueOf(textId), languageCode, translationValue);
		return Response.ok().build();
	}

	@POST
	@Path("/translations/submitUpdate")
	public Response updateTranslation(@FormParam("textId") final String textId, @FormParam("languageCode") final String languageCode, @FormParam("translationValue") final String translationValue)
			throws Exception {
		hdxService.updateTranslation(Long.valueOf(textId), languageCode, translationValue);
		return Response.ok().build();
	}

	@POST
	@Path("/translations/submitDelete")
	public Response deleteTranslation(@FormParam("textId") final String textId, @FormParam("languageCode") final String languageCode) throws Exception {
		hdxService.deleteTranslation(Long.valueOf(textId), languageCode);
		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/translations/getFor/json")
	public String getTranslationsFor(@QueryParam("resource") final String resource, @QueryParam("identifier") final String identifier) throws TypeMismatchException {
		List<Translation> translations = null;

		// Could do with Class.forName and annotations to know how to use identifier, or interface also...
		switch (resource) {
		case "source": {
			final Source theResource = curatedDataService.getSource(Long.valueOf(identifier));
			translations = theResource.getName().getTranslations();
		}
			break;
		case "entity": {
			final Entity theResource = curatedDataService.getEntity(Long.valueOf(identifier));
			translations = theResource.getName().getTranslations();
		}
			break;
		case "entityType": {
			final EntityType theResource = curatedDataService.getEntityType(Long.valueOf(identifier));
			translations = theResource.getName().getTranslations();
		}
			break;
		/*
		case "indicator": {
			final Indicator theResource = curatedDataService.getIndicator(Long.valueOf(identifier));
			translations = theResource.getName().getTranslations();
		}
			break;
		*/
		case "indicatorType": {
			final IndicatorType theResource = curatedDataService.getIndicatorType(Long.valueOf(identifier));
			translations = theResource.getName().getTranslations();
		}
			break;

		default:
			break;
		}

		final JsonArray jsonTranslations = new JsonArray();
		if (null != translations) {
			for (final Translation translation : translations) {
				final Id translationId = translation.getId();
				final Language language = translationId.getLanguage();
				final String code = language.getCode();
				final String value = translation.getValue();
				final JsonObject jsonTranslation = new JsonObject();
				jsonTranslation.addProperty("code", code);
				jsonTranslation.addProperty("value", value);

				jsonTranslations.add(jsonTranslation);
			}
		}
		return jsonTranslations.toString();
	}

	/*
	 * Status / datasets management
	 */
	@GET
	@Path("/status/datasets/")
	public Response getCKANDatasetsStatus() {
		return Response.ok(new Viewable("/admin/datasets", hdxService.listCKANDatasets())).build();
	}

	@POST
	@Path("/status/datasets/flagDatasetAsToBeCurated")
	public Response flagDatasetAsToBeCurated(@FormParam("datasetName") final String datasetName, @FormParam("type") final CKANDataset.Type type, @Context final UriInfo uriInfo)
			throws URISyntaxException {
		hdxService.flagDatasetAsToBeCurated(datasetName, type);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();
		return Response.seeOther(newURI).build();
	}

	@POST
	@Path("/status/datasets/flagDatasetAsIgnored")
	public Response flagDatasetAsIgnored(@FormParam("datasetName") final String datasetName, @Context final UriInfo uriInfo) throws URISyntaxException {
		hdxService.flagDatasetAsIgnored(datasetName);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();
		return Response.seeOther(newURI).build();
	}

	/*
	 * Status / resources management
	 */
	@GET
	@Path("/status/resources/")
	public Response getCKANResourcesStatus() {
		return Response.ok(new Viewable("/admin/resources", hdxService.listCKANResources())).build();
	}

	@GET
	@Path("/status/resources/{id}/{revision_id}/report")
	public Response getCKANResourceReport(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id) {
		return Response.ok(new Viewable("/admin/report", hdxService.getCKANResource(id, revision_id))).build();
	}

	/*
	 * Status / manual triggers management
	 */
	@GET
	@Path("/status/manuallyTriggerDownload/{id}/{revision_id}")
	public Response manuallyTriggerDownload(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id, @Context final UriInfo uriInfo) throws URISyntaxException,
			IOException {
		hdxService.downloadFileForCKANResource(id, revision_id);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerEvaluation/{id}/{revision_id}")
	public Response manuallyTriggerEvaluation(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id, @Context final UriInfo uriInfo) throws URISyntaxException,
			IOException {
		hdxService.evaluateFileForCKANResource(id, revision_id);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerImport/{id}/{revision_id}")
	public Response manuallyTriggerImport(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id, @Context final UriInfo uriInfo) throws URISyntaxException, IOException {
		hdxService.transformAndImportDataFromFileForCKANResource(id, revision_id);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerDatasetsDetection")
	public Response manuallyTriggerDatasetsDetection(@Context final UriInfo uriInfo) throws URISyntaxException {
		hdxService.checkForNewCKANDatasets();

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/datasets/").build();

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerResourcesDetection")
	public Response manuallyTriggerResourcesDetection(@Context final UriInfo uriInfo) throws URISyntaxException {
		hdxService.checkForNewCKANResources();

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/status/resources/").build();

		return Response.seeOther(newURI).build();
	}

	/*
	 * Curated / entity types management
	 */
	@GET
	@Path("/curated/entityTypes")
	public Response displayEntityTypesList() {
		return Response.ok(new Viewable("/admin/entityTypes", curatedDataService.listEntityTypes())).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/curated/entityTypes/json")
	public String getEntityTypes(@QueryParam("var") final String var) throws TypeMismatchException {

		String result = "";
		
		final List<EntityType> listEntityTypes = curatedDataService.listEntityTypes();
		final JsonArray jsonArray = new JsonArray();

		for (final EntityType entityType : listEntityTypes) {

			final JsonObject jsonEntityType = new JsonObject();
			jsonEntityType.addProperty("id", entityType.getId());
			jsonEntityType.addProperty("code", entityType.getCode());
			jsonEntityType.addProperty("name", entityType.getName().getDefaultValue());
			jsonEntityType.addProperty("text_id", entityType.getName().getId());
			final List<Translation> translations = entityType.getName().getTranslations();
			final JsonArray jsonTranslations = new JsonArray();
			for (final Translation translation : translations) {
				final Id translationId = translation.getId();
				final Language language = translationId.getLanguage();
				final String code = language.getCode();
				final String value = translation.getValue();
				final JsonObject jsonTranslation = new JsonObject();
				jsonTranslation.addProperty("code", code);
				jsonTranslation.addProperty("value", value);

				jsonTranslations.add(jsonTranslation);
			}
			jsonEntityType.add("translations", jsonTranslations);
			jsonArray.add(jsonEntityType);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@POST
	@Path("/curated/entityTypes/submitCreate")
	public Response createEntityType(@FormParam("code") final String code, @FormParam("name") final String name) {
		curatedDataService.createEntityType(code, name);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/entityTypes/submitDelete")
	public Response deleteEntityType(@FormParam("entityTypeId") final long entityTypeId) {
		curatedDataService.deleteEntityType(entityTypeId);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/entityTypes/submitUpdate")
	public Response updateEntityType(@FormParam("entityTypeId") final long entityTypeId, @FormParam("newName") final String newName) {
		curatedDataService.updateEntityType(entityTypeId, newName);
		return Response.ok().build();
	}

	/*
	 * Curated / entities management
	 */
	@GET
	@SuppressWarnings("static-method")
	@Path("/curated/entities/")
	public Response displayEntitiesList(/*@QueryParam("howMuch") final String howMuch*/) {
		/*
		String _howMuch = null;
		if((null == howMuch) || "".equals(howMuch)) {
			_howMuch = "10";
		}
		else {
			_howMuch = howMuch;
		}
		return Response.ok(new Viewable("/admin/entities?howMuch=" + _howMuch)).build();
		*/
		return Response.ok(new Viewable("/admin/entities")).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("curated/entities/json")
	public String getEntities(@QueryParam("var") final String var) throws TypeMismatchException {
		
		String result = "";
		
		final List<Entity> listEntities = curatedDataService.listEntities();
		final JsonArray jsonArray = new JsonArray();
		for (final Entity entity : listEntities) {
			final JsonObject jsonEntity = new JsonObject();
			jsonEntity.addProperty("id", entity.getId());
			jsonEntity.addProperty("entityType", entity.getType().getId());
			jsonEntity.addProperty("code", entity.getCode());
			jsonEntity.addProperty("name", entity.getName().getDefaultValue());
			jsonEntity.addProperty("text_id", entity.getName().getId());
			final List<Translation> translations = entity.getName().getTranslations();
			final JsonArray jsonTranslations = new JsonArray();
			for (final Translation translation : translations) {
				final Id translationId = translation.getId();
				final Language language = translationId.getLanguage();
				final String code = language.getCode();
				final String value = translation.getValue();
				final JsonObject jsonTranslation = new JsonObject();
				jsonTranslation.addProperty("code", code);
				jsonTranslation.addProperty("value", value);

				jsonTranslations.add(jsonTranslation);
			}
			jsonEntity.add("translations", jsonTranslations);

			jsonArray.add(jsonEntity);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("curated/entities/paginated/json")
	// From is zero-based
	public String getPaginatedEntities(@QueryParam("fromIndex") final int fromIndex, @QueryParam("howMuch") final int howMuch) throws TypeMismatchException {
		final List<Entity> allEntities = curatedDataService.listEntities();

		final int _totalNumber = allEntities.size();
		int _fromIndex = fromIndex;
		int _toIndex = fromIndex + howMuch;
		int _previousIndex = _fromIndex - howMuch;
		int _nextIndex = _toIndex;
		final int _firstIndex = 0;
		final int _lastIndex = _totalNumber - ((0 != howMuch) && (0 == (_totalNumber % howMuch)) ? howMuch : _totalNumber % howMuch);

		// Checks
		if (fromIndex >= _totalNumber) {
			// Setting to totalNumber to return an empty list
			_fromIndex = _totalNumber;
			_previousIndex = _totalNumber - howMuch;
			_nextIndex = -1;
		}
		if ((fromIndex + howMuch) >= _totalNumber) {
			// Setting to totalNumber to return an empty list
			_toIndex = _totalNumber;
			_previousIndex = _totalNumber - howMuch - (_totalNumber % howMuch);
			_nextIndex = -1;
		}

		final int _nbPages = (_totalNumber / howMuch) + (0 == (_totalNumber % howMuch) ? 0 : 1);
		final int _currentPage = ((_fromIndex + 1) / howMuch) + 1;
		final List<Entity> paginatedEntities = allEntities.subList(_fromIndex, _toIndex);
		final JsonObject jsonEntities = new JsonObject();
		final JsonArray jsonEntitiesArray = new JsonArray();
		for (final Entity entity : paginatedEntities) {
			final JsonObject jsonEntity = new JsonObject();
			jsonEntity.addProperty("id", entity.getId());
			jsonEntity.addProperty("entityType", entity.getType().getId());
			jsonEntity.addProperty("code", entity.getCode());
			jsonEntity.addProperty("name", entity.getName().getDefaultValue());
			jsonEntity.addProperty("text_id", entity.getName().getId());
			final List<Translation> translations = entity.getName().getTranslations();
			final JsonArray jsonTranslations = new JsonArray();
			for (final Translation translation : translations) {
				final Id translationId = translation.getId();
				final Language language = translationId.getLanguage();
				final String code = language.getCode();
				final String value = translation.getValue();
				final JsonObject jsonTranslation = new JsonObject();
				jsonTranslation.addProperty("code", code);
				jsonTranslation.addProperty("value", value);

				jsonTranslations.add(jsonTranslation);
			}
			jsonEntity.add("translations", jsonTranslations);

			jsonEntitiesArray.add(jsonEntity);
		}
		jsonEntities.add("entities", jsonEntitiesArray);
		addPagination(jsonEntities, _fromIndex, _toIndex, _nextIndex, _previousIndex, _totalNumber, _currentPage, _nbPages, _firstIndex, _lastIndex, howMuch);

		return jsonEntities.toString();
	}

	private static void addPagination(final JsonObject jsonObject, final int _fromIndex, final int _toIndex, final int _nextIndex, final int _previousIndex, final int _totalNumber,
			final int _currentPage, final int _nbPages, final int _firstIndex, final int _lastIndex, final int howMuch) {
		final JsonObject pagination = new JsonObject();
		pagination.addProperty("fromIndex", _fromIndex);
		pagination.addProperty("toIndex", _toIndex);
		pagination.addProperty("nextIndex", _nextIndex);
		pagination.addProperty("previousIndex", _previousIndex);
		pagination.addProperty("totalNumber", _totalNumber);
		pagination.addProperty("currentPage", _currentPage);
		pagination.addProperty("nbPages", _nbPages);
		pagination.addProperty("firstIndex", _firstIndex);
		pagination.addProperty("lastIndex", _lastIndex);
		pagination.addProperty("howMuch", howMuch);
		pagination.addProperty("maxSize", 5); // maximum number of pages displayed in the pagination widget

		jsonObject.add("pagination", pagination);
	}

	@POST
	@Path("/curated/entities/submitCreate")
	public Response createEntity(@FormParam("entityTypeCode") final String entityTypeCode, @FormParam("code") final String code, @FormParam("name") final String name) {
		curatedDataService.createEntity(code, name, entityTypeCode);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/entities/submitDelete")
	public Response deleteEntity(@FormParam("entityId") final long entityId, @Context final UriInfo uriInfo) {
		curatedDataService.deleteEntity(entityId);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/curated/entities/").build();
		return Response.seeOther(newURI).build();
	}

	@POST
	@Path("/curated/entities/submitUpdate")
	public Response updateEntity(@FormParam("entityId") final long entityId, @FormParam("newName") final String newName, @Context final UriInfo uriInfo) {
		curatedDataService.updateEntity(entityId, newName);

		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/curated/entities/").build();
		return Response.seeOther(newURI).build();
	}

	/*
	 * Curated / indicator types management
	 */
	@GET
	@Path("/curated/indicatorTypes")
	public Response displayIndicatorTypesList() {
		return Response.ok(new Viewable("/admin/indicatorTypes", curatedDataService.listIndicatorTypes())).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/curated/indicatorTypes/json")
	public String getIndicatorTypes(@QueryParam("var") final String var) throws TypeMismatchException {
		
		String result = "";
		
		final List<IndicatorType> listIndicatorTypes = curatedDataService.listIndicatorTypes();
		final JsonArray jsonArray = new JsonArray();
		for (final IndicatorType indicatorType : listIndicatorTypes) {
			final JsonObject jsonIndicatorType = indicatorTypeToJson(indicatorType);

			jsonArray.add(jsonIndicatorType);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@SuppressWarnings("static-method")
	private JsonObject indicatorTypeToJson(final IndicatorType indicatorType) {
		final JsonObject jsonIndicatorType = new JsonObject();
		jsonIndicatorType.addProperty("id", indicatorType.getId());
		jsonIndicatorType.addProperty("code", indicatorType.getCode());
		jsonIndicatorType.addProperty("name", indicatorType.getName().getDefaultValue());
		jsonIndicatorType.addProperty("unit", indicatorType.getUnit());
		jsonIndicatorType.addProperty("valueType", indicatorType.getValueType().toString());
		jsonIndicatorType.addProperty("text_id", indicatorType.getName().getId());
		final List<Translation> translations = indicatorType.getName().getTranslations();
		final JsonArray jsonTranslations = new JsonArray();
		for (final Translation translation : translations) {
			final Id translationId = translation.getId();
			final Language language = translationId.getLanguage();
			final String code = language.getCode();
			final String value = translation.getValue();
			final JsonObject jsonTranslation = new JsonObject();
			jsonTranslation.addProperty("code", code);
			jsonTranslation.addProperty("value", value);

			jsonTranslations.add(jsonTranslation);
		}
		jsonIndicatorType.add("translations", jsonTranslations);
		return jsonIndicatorType;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/curated/indicatorTypes/valueTypes/json")
	@SuppressWarnings("static-method")
	public String getIndicatorTypeValueTypes(@QueryParam("var") final String var) throws TypeMismatchException {
		
		String result = "";
		
		final JsonArray jsonArray = new JsonArray();
		for (final ValueType valueType : ValueType.values()) {
			final JsonObject jsonValueType = new JsonObject();
			jsonValueType.addProperty("value", valueType.toString());
			jsonValueType.addProperty("text", valueType.toString());
			jsonArray.add(jsonValueType);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@POST
	@Path("/curated/indicatorTypes/submitCreate")
	public Response createIndicatorType(@FormParam("code") final String code, @FormParam("name") final String name, @FormParam("unit") final String unit, @FormParam("valueType") final String valueType) {
		curatedDataService.createIndicatorType(code, name, unit, valueType);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/indicatorTypes/submitDelete")
	public Response deleteIndicatorType(@FormParam("indicatorTypeId") final long indicatorTypeId) {
		curatedDataService.deleteIndicatorType(indicatorTypeId);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/indicatorTypes/submitUpdate")
	public Response updateIndicatorType(@FormParam("indicatorTypeId") final long indicatorTypeId, @FormParam("newName") final String newName, @FormParam("newUnit") final String newUnit,
			@FormParam("newValueType") final String newValueType) {
		curatedDataService.updateIndicatorType(indicatorTypeId, newName, newUnit, newValueType);
		return Response.ok().build();
	}

	/*
	 * Curated / sources management
	 */
	@GET
	@Path("/curated/sources")
	public Response displaySourcesList() {
		return Response.ok(new Viewable("/admin/sources", curatedDataService.listSources())).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("curated/sources/json")
	public String getSources(@QueryParam("var") final String var) throws TypeMismatchException {
		
		String result = "";
		
		final List<Source> listSources = curatedDataService.listSources();
		final JsonArray jsonArray = new JsonArray();
		for (final Source source : listSources) {
			final JsonObject jsonSource = sourceToJson(source);

			jsonArray.add(jsonSource);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
	}

	@SuppressWarnings("static-method")
	private JsonObject sourceToJson(final Source source) {
		final JsonObject jsonSource = new JsonObject();
		jsonSource.addProperty("id", source.getId());
		jsonSource.addProperty("code", source.getCode());
		jsonSource.addProperty("name", source.getName().getDefaultValue());
		jsonSource.addProperty("link", source.getOrgLink());
		jsonSource.addProperty("text_id", source.getName().getId());
		final List<Translation> translations = source.getName().getTranslations();
		final JsonArray jsonTranslations = new JsonArray();
		for (final Translation translation : translations) {
			final Id translationId = translation.getId();
			final Language language = translationId.getLanguage();
			final String code = language.getCode();
			final String value = translation.getValue();
			final JsonObject jsonTranslation = new JsonObject();
			jsonTranslation.addProperty("code", code);
			jsonTranslation.addProperty("value", value);

			jsonTranslations.add(jsonTranslation);
		}
		jsonSource.add("translations", jsonTranslations);
		return jsonSource;
	}

	@POST
	@Path("/curated/sources/submitCreate")
	public Response createSource(@FormParam("code") final String code, @FormParam("name") final String name, @FormParam("link") final String link) {
		curatedDataService.createSource(code, name, link);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/sources/submitDelete")
	public Response deleteSource(@FormParam("sourceId") final long sourceId) {
		curatedDataService.deleteSource(sourceId);
		return Response.ok().build();
	}

	@POST
	@Path("/curated/sources/submitUpdate")
	public Response updateSource(@FormParam("sourceId") final long sourceId, @FormParam("newName") final String newName, @FormParam("newLink") final String newLink) {
		curatedDataService.updateSource(sourceId, newName, newLink);
		return Response.ok().build();
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("curated/indicators/json")
	public String getIndicators() throws TypeMismatchException {
		final List<Indicator> listIndicators = curatedDataService.listLastIndicators(100);
		final JsonArray jsonIndicators = new JsonArray();
		final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		for (final Indicator indicator : listIndicators) {
			final JsonObject jsonIndicator = new JsonObject();
			jsonIndicator.addProperty("id", indicator.getId());
			jsonIndicator.add("source", sourceToJson(indicator.getSource()));
			jsonIndicator.add("indicatorType", indicatorTypeToJson(indicator.getType()));
			jsonIndicator.addProperty("valueType", gson.toJson(indicator.getType().getValueType()));
			jsonIndicator.addProperty("startDate", gson.toJson(indicator.getStart()));
			jsonIndicator.addProperty("endDate", gson.toJson(indicator.getEnd()));
			jsonIndicator.addProperty("periodicity", gson.toJson(indicator.getPeriodicity()));
			jsonIndicator.addProperty("value", gson.toJson(indicator.getValue().toString()));
			jsonIndicator.addProperty("initialValue", indicator.getInitialValue());
			jsonIndicator.addProperty("importFromCkan", gson.toJson(indicator.getImportFromCKAN()));
			jsonIndicators.add(jsonIndicator);
		}
		return jsonIndicators.toString();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/curated/indicators/periodicities/json")
	@SuppressWarnings("static-method")
	public String getIndicatorPeriodicities(@QueryParam("var") final String var) throws TypeMismatchException {
		
		String result = "";
		
		final JsonArray jsonArray = new JsonArray();
		for (final Periodicity periodicity : Periodicity.values()) {
			final JsonObject jsonPeriodicity = new JsonObject();
			jsonPeriodicity.addProperty("value", periodicity.toString());
			jsonPeriodicity.addProperty("text", periodicity.toString());
			jsonArray.add(jsonPeriodicity);
		}
		if((null != var) && !"".equals(var)) {
			result = "var " + var + " = ";
		}
		return result + jsonArray.toString();
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
		displayIndicators.setValueTypes(IndicatorType.ValueType.values());
		return Response.ok(new Viewable("/admin/indicators", displayIndicators)).build();
	}

	@POST
	@Path("/curated/indicators/submitCreate")
	public Response createIndicator(@FormParam("sourceCode") final String sourceCode, @FormParam("entityId") final long entityId, @FormParam("indicatorTypeCode") final String indicatorTypeCode,
			@FormParam("start") final String start, @FormParam("end") final String end, @FormParam("periodicity") final Periodicity periodicity, @FormParam("valueType") final ValueType valueType,
			@FormParam("value") final String valueAsString, @FormParam("initialValue") final String initialValue, @FormParam("sourceLink") final String sourceLink) {
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
		curatedDataService.createIndicator(sourceCode, entityId, indicatorTypeCode, startDate, endDate, periodicity, value, initialValue, sourceLink);
		return displayIndicatorsList();
	}

	@POST
	@Path("/curated/indicators/submitDelete")
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
	public Response createRegionDictionaryEntry(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @FormParam("entity") final long entity) {
		curatedDataService.createRegionDictionary(unnormalizedName, importer, entity);
		return displayRegionDictionariesList();
	}

	@POST
	@Path("/dictionaries/regions/submitDelete")
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
	public Response createSourceDictionaryEntry(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @FormParam("source") final long source) {
		curatedDataService.createSourceDictionary(unnormalizedName, importer, source);
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
	public Response createIndicatorTypeDictionaryEntry(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer,
			@FormParam("indicatorType") final long indicatorType) {
		curatedDataService.createIndicatorTypeDictionary(unnormalizedName, importer, indicatorType);
		return displayIndicatorTypeDictionariesList();
	}

	@POST
	@Path("/dictionaries/indicatorTypes/submitDelete")
	public Response deleteIndicatorTypeDictionary(@FormParam("unnormalizedName") final String unnormalizedName, @FormParam("importer") final String importer, @Context final UriInfo uriInfo)
			throws URISyntaxException {
		final IndicatorTypeDictionary indicatorTypeDictionary = new IndicatorTypeDictionary(unnormalizedName, importer);
		curatedDataService.deleteIndicatorTypeDictionary(indicatorTypeDictionary);
		final URI newURI = uriInfo.getBaseUriBuilder().path("/admin/dictionaries/indicatorTypes/").build();
		return Response.seeOther(newURI).build();

	}

	/*
	 * Testing
	 */
	@GET
	@Path("/misc/test/")
	@SuppressWarnings("static-method")
	public Response test() {
		return Response.ok(new Viewable("/admin/admin-test")).build();
	}

}
