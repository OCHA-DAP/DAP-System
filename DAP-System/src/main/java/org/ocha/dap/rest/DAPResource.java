package org.ocha.dap.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

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

import org.ocha.dap.dto.apiv3.DatasetV3WrapperDTO;
import org.ocha.dap.persistence.entity.CKANDataset;
import org.ocha.dap.rest.helper.Index;
import org.ocha.dap.security.exception.AuthenticationException;
import org.ocha.dap.security.exception.InsufficientCredentialsException;
import org.ocha.dap.service.DAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.view.Viewable;

@Path("/admin")
@Produces(MediaType.TEXT_HTML)
@Component
public class DAPResource {

	private static Logger logger = LoggerFactory.getLogger(DAPResource.class);

	private static String SESSION_PARAM_UID = "SESSION_PARAM_UID";

	@Context
	private HttpServletRequest request;

	@Autowired
	private DAPService dapService;

	/**
	 * Create a session from the token
	 * 
	 * @param token
	 *            the token
	 * @return response with session
	 * @throws AuthenticationException
	 *             if authentication exception occur
	 * @throws URISyntaxException
	 *             the URI syntax exception occur
	 */
	@POST
	@Path("/login/")
	public Response login(@FormParam("userId") final String userId, @FormParam("password") final String password) throws AuthenticationException,
			URISyntaxException {
		logger.debug(String.format("Entering login for user : %s", userId));
		if (dapService != null && dapService.authenticate(userId, password)) {
			final HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_PARAM_UID, userId);
			// 1800 seconds = 30 minutes
			session.setMaxInactiveInterval(1800);

			URI newURI = null;
			newURI = new URI("/admin/status/datasets/");

			return Response.seeOther(newURI).build();
		}

		throw new AuthenticationException();
	}

	@GET
	@Path("/login/")
	public Response loginForm() {
		return Response.ok(new Viewable("/login", null)).build();
	}

	@GET
	@Path("/ckancontent/")
	public Response ckanContent() throws InsufficientCredentialsException {
		final HttpSession session = request.getSession(false);
		final String userId = session.getAttribute(SESSION_PARAM_UID).toString();
		final List<String> datasets = dapService.getDatasetsListFromCKAN(userId);
		final Index index = new Index();
		index.setUserId(userId);
		index.setDatasets(datasets);
		return Response.ok(new Viewable("/index", index)).build();
	}

	@GET
	@Path("/dataset/{datasetName}")
	public Response getDatasetContent(@PathParam("datasetName") final String datasetName) throws InsufficientCredentialsException {
		final HttpSession session = request.getSession(false);
		final String userId = session.getAttribute(SESSION_PARAM_UID).toString();
		final DatasetV3WrapperDTO result = dapService.getDatasetContentFromCKANV3(userId, datasetName);
		return Response.ok(new Viewable("/dataset", result)).build();
	}

	@GET
	@Path("/status/datasets/")
	public Response getCKANDatasetsStatus() {
		return Response.ok(new Viewable("/datasets", dapService.listCKANDatasets())).build();
	}

	@POST
	@Path("/status/datasets/")
	public Response flagDatasetAsToBeCurated(@FormParam("datasetName") final String datasetName, @FormParam("type") final CKANDataset.Type type)
			throws URISyntaxException {
		dapService.flagDatasetAsToBeCurated(datasetName, type);

		final URI newURI = new URI("/admin/status/datasets/");

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/resources/")
	public Response getCKANResourcesStatus() {
		return Response.ok(new Viewable("/resources", dapService.listCKANResources())).build();
	}

	@GET
	@Path("/status/manuallyTriggerDownload/{id}/{revision_id}")
	public Response manuallyTriggerDownload(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id) throws URISyntaxException,
			IOException {
		dapService.downloadFileForCKANResource(id, revision_id);

		final URI newURI = new URI("/admin/status/resources/");

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerEvaluation/{id}/{revision_id}")
	public Response manuallyTriggerEvaluation(@PathParam("id") final String id, @PathParam("revision_id") final String revision_id) throws URISyntaxException,
			IOException {
		dapService.evaluateFileForCKANResource(id, revision_id);

		URI newURI = null;
		newURI = new URI("/admin/status/resources/");

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerDatasetsDetection")
	public Response manuallyTriggerDatasetsDetection() throws URISyntaxException {
		dapService.checkForNewCKANDatasets();
		;

		URI newURI = null;
		newURI = new URI("/admin/status/datasets/");

		return Response.seeOther(newURI).build();
	}

	@GET
	@Path("/status/manuallyTriggerResourcesDetection")
	public Response manuallyTriggerResourcesDetection() throws URISyntaxException {
		dapService.checkForNewCKANResources();

		URI newURI = null;
		newURI = new URI("/admin/status/resources/");

		return Response.seeOther(newURI).build();
	}

}
