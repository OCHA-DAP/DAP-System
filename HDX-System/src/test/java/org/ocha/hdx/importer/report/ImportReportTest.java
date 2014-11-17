package org.ocha.hdx.importer.report;

import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.ocha.hdx.model.DataSerie;

public class ImportReportTest {

	@Test
	public void testJSonSerialization() {
		final ImportReport importReport = new ImportReport();

		final DataSerie dataSerie1 = new DataSerie("ABC023", "OCHA");
		importReport.addNewRecord(dataSerie1);
		importReport.addNewRecord(dataSerie1);
		importReport.addAlreadyExistingRecord(dataSerie1);
		importReport.addRecordInError(dataSerie1);

		final String json = importReport.toJson();

		final ImportReport result = ImportReport.fromJson(json);
		Assert.assertEquals(importReport.getMessage(), result.getMessage());
		Assert.assertEquals(importReport.getNbOfAlreadyExistingRecords(), result.getNbOfAlreadyExistingRecords());
		Assert.assertEquals(importReport.getNbOfNewRecords(), result.getNbOfNewRecords());
		Assert.assertEquals(importReport.getNbOfRecordsInError(), result.getNbOfRecordsInError());

		final Map<DataSerie, ImportReportEntry> resultEntries = result.getEntries();
		for (final DataSerie key : resultEntries.keySet()) {
			final ImportReportEntry resultEntry = resultEntries.get(key);
			final ImportReportEntry originalEntry = importReport.getEntries().get(key);

			Assert.assertEquals(originalEntry.getNbOfAlreadyExistingRecords(), resultEntry.getNbOfAlreadyExistingRecords());
			Assert.assertEquals(originalEntry.getNbOfNewRecords(), resultEntry.getNbOfNewRecords());
			Assert.assertEquals(originalEntry.getNbOfRecordsInError(), resultEntry.getNbOfRecordsInError());
		}
	}
}
