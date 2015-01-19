package org.ocha.hdx.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ocha.hdx.dto.apiv3.HdxPackageUpdateMetadataDTO;
import org.ocha.hdx.service.CkanSynchronizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@PermitAll
@Path("/public/debug")
@Component
public class PublicDebugResources {

	@Autowired
	private CkanSynchronizerService ckanSynchronizerService;

	@GET
	@Path("/pendingpackagesupdates")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HdxPackageUpdateMetadataDTO> listPendingPackageUpdate() {
		return ckanSynchronizerService.getDatasetsWithUnsyncedMetadata();

	}

}
