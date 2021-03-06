package org.ocha.hdx.dto.apiv3;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.ocha.hdx.tools.GSONBuilderWrapper;
import org.springframework.core.io.ClassPathResource;

import com.google.gson.Gson;

public class HdxPackageUpdateMetadataDTOTest {

	@Test
	public void testGson() throws IOException {
		final HdxPackageUpdateMetadataDTO dto = new HdxPackageUpdateMetadataDTO();

		dto.setId("The uuid of the ckan dataset");
		dto.setName("The name of the ckan dataset");
		dto.setDataset_date("11/02/2014-11/20/2014");
		dto.setDataset_source("The organization name");
		dto.setDataset_source_short_name("The organization short name");
		dto.setDescription("the dataset summary");
		dto.setSource_code("WFP");
		dto.setIndicator_type("the name of the indicator type");
		dto.setIndicator_type_code("PVX040");
		dto.setLast_data_update_date("2001-09-09T03:46:40.000000");
		dto.setLast_metadata_update_date("2001-09-09T03:46:40.000000");
		dto.setMethodology("the methodology");
		dto.setMore_info("more info");
		dto.setTerms_of_use("terms of use");
		dto.setValidation_notes_and_comments("Notes and comments");

		dto.addGroup("USA");

		final Gson gson = GSONBuilderWrapper.getGSONIgnoreNulls();
		final String json = gson.toJson(dto);

		final File jsonExpectedResult = new ClassPathResource("samples/json/packageupdate.json").getFile();

		Assert.assertEquals(FileUtils.readFileToString(jsonExpectedResult), json);
	}
}
