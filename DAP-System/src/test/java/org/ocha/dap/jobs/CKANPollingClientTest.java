package org.ocha.dap.jobs;

import org.junit.Assert;
import org.junit.Test;
import org.ocha.dap.dto.DatasetDTO;

public class CKANPollingClientTest {

	@Test
	public void testGetDatasetDTOFromQuery() {
		final CKANPollingClient client = new CKANPollingClient("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		final DatasetDTO dto = client.getDatasetDTOFromQuery("mali-hp-data-test", null, null);
		Assert.assertTrue(dto.isSuccess());
	}

	@Test
	public void testGetPrivateDatasetDTOFromQuery() {
		final CKANPollingClient client = new CKANPollingClient("ckan.megginson.com", "079f6194-45e1-4534-8ca7-1bd4130ef897");
		{
			final DatasetDTO dto = client.getDatasetDTOFromQuery("testforauth", null, null);
			// Cannot access private dataset without API key
			Assert.assertNull(dto);
		}
		
		{
			final DatasetDTO dto = client.getDatasetDTOFromQuery("testforauth", "079f6194-45e1-4534-8ca7-1bd4130ef897", null);
			Assert.assertTrue(dto.isSuccess());
		}
	}

}
