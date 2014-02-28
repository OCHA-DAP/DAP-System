package org.ocha.hdx.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.service.IndicatorCreationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScraperImporter implements HDXWithCountryListImporter {

	private static Logger logger = LoggerFactory.getLogger(ScraperImporter.class);

	private final List<String> acceptedIndicatorTypes = new ArrayList<>();

	private final IndicatorCreationService indicatorCreationService;

	private PreparedData preparedData;

	Map<String, String> sourcesMap = new HashMap<>();

	public ScraperImporter(final List<SourceDictionary> sourceDictionaries, final IndicatorCreationService indicatorCreationService) {
		super();

		this.indicatorCreationService	= indicatorCreationService;

		if (sourceDictionaries != null) {
			for (final SourceDictionary sourceDictionary : sourceDictionaries) {
				this.sourcesMap.put(sourceDictionary.getId().getUnnormalizedName(), sourceDictionary.getSource().getCode());
			}
		}
		this.acceptedIndicatorTypes.add("PVX040");
		this.acceptedIndicatorTypes.add("PSP080");
		this.acceptedIndicatorTypes.add("PSE030");
		this.acceptedIndicatorTypes.add("PCX051");
		this.acceptedIndicatorTypes.add("PVF020");
		this.acceptedIndicatorTypes.add("PSP010");
		this.acceptedIndicatorTypes.add("_emdat:total_affected");

		/*
		 * acceptedIndicatorTypes.add("CD010"); acceptedIndicatorTypes.add("CD030"); acceptedIndicatorTypes.add("CD050"); acceptedIndicatorTypes.add("CD070"); acceptedIndicatorTypes.add("CD080");
		 * acceptedIndicatorTypes.add("CD090"); acceptedIndicatorTypes.add("CG020"); acceptedIndicatorTypes.add("CG030"); acceptedIndicatorTypes.add("CG060"); acceptedIndicatorTypes.add("CG070");
		 * acceptedIndicatorTypes.add("CG080"); acceptedIndicatorTypes.add("CG100"); acceptedIndicatorTypes.add("CG120"); acceptedIndicatorTypes.add("CG140"); acceptedIndicatorTypes.add("CG150");
		 * acceptedIndicatorTypes.add("CG260"); acceptedIndicatorTypes.add("CG290"); acceptedIndicatorTypes.add("_m49-name"); acceptedIndicatorTypes.add("_unterm:ISO Country alpha-2-code");
		 */

		/*
		 * acceptedIndicatorTypes.add("CH070"); acceptedIndicatorTypes.add("CH080"); acceptedIndicatorTypes.add("CH090"); acceptedIndicatorTypes.add("CH100"); acceptedIndicatorTypes.add("PSE030");
		 * acceptedIndicatorTypes.add("PSE090"); acceptedIndicatorTypes.add("PSE120"); acceptedIndicatorTypes.add("PSE130"); acceptedIndicatorTypes.add("PSP080"); acceptedIndicatorTypes.add("PSE140");
		 * acceptedIndicatorTypes.add("PSE150"); acceptedIndicatorTypes.add("PSE200"); acceptedIndicatorTypes.add("PSP010"); acceptedIndicatorTypes.add("PSP060"); acceptedIndicatorTypes.add("PSP090");
		 * acceptedIndicatorTypes.add("PSP110"); acceptedIndicatorTypes.add("PVE130"); acceptedIndicatorTypes.add("PVF020"); acceptedIndicatorTypes.add("PVH140"); acceptedIndicatorTypes.add("PVL040");
		 * acceptedIndicatorTypes.add("PVN010"); acceptedIndicatorTypes.add("PVW010"); acceptedIndicatorTypes.add("PVW040"); acceptedIndicatorTypes.add("PCX051"); acceptedIndicatorTypes.add("PCX080");
		 * acceptedIndicatorTypes.add("PCX090"); acceptedIndicatorTypes.add("PCX100"); acceptedIndicatorTypes.add("_Children 1 year old immunized against measles, percentage");
		 * acceptedIndicatorTypes.add("_emdat:no_homeless"); acceptedIndicatorTypes.add("_emdat:no_injured"); acceptedIndicatorTypes.add("_emdat:total_affected");
		 * acceptedIndicatorTypes.add("_GNI, PPP (current international $)"); acceptedIndicatorTypes.add("_Internet users per 100 inhabitants"); acceptedIndicatorTypes.add("_Land area (sq. km)");
		 * acceptedIndicatorTypes.add("_Net ODA received per capita (current US$)"); acceptedIndicatorTypes.add("_Number of infant deaths"); acceptedIndicatorTypes.add("_Population, total");
		 * acceptedIndicatorTypes.add("_Population undernourished, millions"); acceptedIndicatorTypes.add("_Population undernourished, percentage");
		 * acceptedIndicatorTypes.add("_reliefweb_Humanitarian_Bulletin"); acceptedIndicatorTypes.add("_reliefweb_Humanitarian_Dashboard");
		 * acceptedIndicatorTypes.add("_reliefweb_Humanitarian_Snapshot"); acceptedIndicatorTypes.add("_reliefweb_Infographic"); acceptedIndicatorTypes.add("_reliefweb_Key_Messages");
		 * acceptedIndicatorTypes.add("_reliefweb_Other"); acceptedIndicatorTypes.add("_reliefweb_Press_Release"); acceptedIndicatorTypes.add("_reliefweb_Press_Review");
		 * acceptedIndicatorTypes.add("_reliefweb_Reference_Map"); acceptedIndicatorTypes.add("_reliefweb_Situation_Report"); acceptedIndicatorTypes.add("_reliefweb_Statement/Speech");
		 * acceptedIndicatorTypes.add("_reliefweb_Thematic_Map");
		 */

	}

	@Override
	public Map<String, String> getCountryList(final File file) {
		final Map<String, String> result = new HashMap<String, String>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				// use comma as separator
				final String[] values = line.split(",");
				if ("_m49-name".equals(values[2])) {
					result.put(values[1], values[4]);
				}
			}

			return result;
		} catch (final IOException e) {
			return result;
		}
	}

	@Override
	public PreparedData prepareDataForImport(final File file) {
		// get the dictionary entries for the scraper importer
		// TODO should it be linked to the enum CKANDataset.Type ?

		final List<PreparedIndicator> preparedIndicators = new ArrayList<>();
		final File parent = file.getParentFile();
		final File valueFile = new File(parent, "value.csv");

		try (final BufferedReader br = new BufferedReader(new FileReader(valueFile))) {
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");
				if (this.acceptedIndicatorTypes.contains(values[2])) {
					final PreparedIndicator preparedIndicator = new PreparedIndicator();

					if (this.sourcesMap.containsKey(values[0])) {
						preparedIndicator.setSourceCode(this.sourcesMap.get(values[0]));
					} else {
						preparedIndicator.setSourceCode(values[0]);
					}
					preparedIndicator.setEntityCode(values[1]);
					preparedIndicator.setEntityTypeCode("country");
					preparedIndicator.setIndicatorTypeCode(values[2]);

					final TimeRange timeRange = new TimeRange(values[3]);
					preparedIndicator.setStart(timeRange.getStart());
					preparedIndicator.setEnd(timeRange.getEnd());
					preparedIndicator.setPeriodicity(timeRange.getPeriodicity());

					final Double valueAsDouble = Double.parseDouble(values[4]);
					// FIXME we should deal about units later, here for
					// population we must X1000
					if ("PSP010".equals(values[2])) {
						preparedIndicator.setValue(new IndicatorValue(valueAsDouble * 1000));
					} else {
						preparedIndicator.setValue(new IndicatorValue(valueAsDouble));
					}

					preparedIndicator.setInitialValue(values[4]);

					preparedIndicators.add(preparedIndicator);
				}
			}
			this.preparedData	=  new PreparedData(true, preparedIndicators);
		} catch (final Exception e) {
			logger.debug(e.toString(), e);
			this.preparedData	= new PreparedData(false, null);
		}

		return this.preparedData;
		}

	@Override
	public List<Indicator> transformToFinalFormat() {
		final List<Indicator> list	= new ArrayList<Indicator>();
		for (final PreparedIndicator preparedIndicator : this.preparedData.getIndicatorsToImport()) {
			try {
				final Indicator indicator	= this.indicatorCreationService.createIndicator(preparedIndicator);
				list.add(indicator);
			} catch (final Exception e) {
				logger.debug(String.format("Error trying to create preparedIndicator : %s", preparedIndicator.toString()));
			}
		}
		return list;
	}
}
