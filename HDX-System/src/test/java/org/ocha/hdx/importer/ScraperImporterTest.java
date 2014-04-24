package org.ocha.hdx.importer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.springframework.core.io.ClassPathResource;

public class ScraperImporterTest {

	@Test
	public void testPrepareDataForImport() throws IOException {
		{
			final ScraperImporter scraperImporter = new ScraperImporter(null, null);

			final File csvValueFile = new ClassPathResource("samples/scraper/csv.zip").getFile();

			final PreparedData preparedData = scraperImporter.prepareDataForImport(csvValueFile);
			Assert.assertTrue(preparedData.isSuccess());
			Assert.assertEquals(50081, preparedData.getIndicatorsToImport().size());

			for (final PreparedIndicator preparedIndicator : preparedData.getIndicatorsToImport()) {
				if (preparedIndicator.getSourceCode().equals("WB")) {
					Assert.fail("Source WB was not in the file");
				}
			}
		}

		{
			final Source wb = new Source();
			wb.setCode("WB");
			final List<SourceDictionary> dictionaries = new ArrayList<>();
			dictionaries.add(new SourceDictionary("\"World Bank\"", "scraper", wb, null));
			final ScraperImporter scraperImporter = new ScraperImporter(dictionaries, null);

			final File csvValueFile = new ClassPathResource("samples/scraper/csv.zip").getFile();

			final PreparedData preparedData = scraperImporter.prepareDataForImport(csvValueFile);
			Assert.assertTrue(preparedData.isSuccess());
			Assert.assertEquals(50081, preparedData.getIndicatorsToImport().size());

			for (final PreparedIndicator preparedIndicator : preparedData.getIndicatorsToImport()) {
				if (preparedIndicator.getSourceCode().equals("\"World Bank\"")) {
					Assert.fail("Source \"World Bank\" should have been replaced by WB");
				}
			}
		}
	}
}
