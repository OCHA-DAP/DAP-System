package org.ocha.hdx.service;

import java.util.List;

import org.ocha.hdx.dto.apiv3.HdxPackageUpdateMetadataDTO;

public interface CkanSynchronizerService {

	public void updateMetadataToCkan();

	public List<HdxPackageUpdateMetadataDTO> getDatasetsWithUnsyncedMetadata();

}
