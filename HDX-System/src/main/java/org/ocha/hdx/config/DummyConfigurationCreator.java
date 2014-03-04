/**
 *
 */
package org.ocha.hdx.config;

import java.util.HashSet;
import java.util.Set;

import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.validation.itemvalidator.MinMaxValidatorCreator;
import org.ocha.hdx.validation.prevalidator.AllowedIndicatorTypesValidatorCreator;
import org.ocha.hdx.validation.prevalidator.ColumnNumPreValidatorCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g
 * 
 */
@Component
public class DummyConfigurationCreator {

	private static final String _EMDAT_TOTAL_AFFECTED = "_emdat:total_affected";

	private static final String PSP010 = "PSP010";

	private static final String PVF020 = "PVF020";

	private static final String PCX051 = "PCX051";

	private static final String PSE030 = "PSE030";

	private static final String PVX040 = "PVX040";

	public static final String PSP080 = "PSP080";

	public static final String ESA_UNPD_WPP2012 = "esa-unpd-WPP2012";

	@Autowired
	SourceDAO sourceDAO;

	@Autowired
	IndicatorTypeDAO typeDAO;

	public ResourceConfiguration createConfiguration() {
		final ResourceConfiguration configuration = new ResourceConfiguration();
		configuration.setName("Dummy Configuration");

		this.createGeneralConfigEntries(configuration);
		this.createIndicatorConfigEntries(configuration);

		return configuration;
	}

	private void createGeneralConfigEntries(final ResourceConfiguration configuration) {

		final Set<ResourceConfigEntry> entries = new HashSet<ResourceConfigEntry>();

		final ResourceConfigEntry entry1 = new ResourceConfigEntry(ConfigurationConstants.GeneralConfiguration.PREVALIDATORS.getLabel(), ColumnNumPreValidatorCreator.NAME
				+ ConfigurationConstants.SEPARATOR + AllowedIndicatorTypesValidatorCreator.NAME);
		final ResourceConfigEntry entry2 = new ResourceConfigEntry(ConfigurationConstants.GeneralConfiguration.MIN_NUM_OF_COLUMNS.getLabel(), "6");
		final ResourceConfigEntry entry3 = new ResourceConfigEntry(ConfigurationConstants.GeneralConfiguration.ALLOWED_INDICATOR_TYPES.getLabel(), PVX040 + ConfigurationConstants.SEPARATOR + PSP080
				+ ConfigurationConstants.SEPARATOR + PSE030 + ConfigurationConstants.SEPARATOR + PCX051 + ConfigurationConstants.SEPARATOR + PVF020 + ConfigurationConstants.SEPARATOR + PSP010
				+ ConfigurationConstants.SEPARATOR + _EMDAT_TOTAL_AFFECTED);

		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);

		configuration.setGeneralConfigEntries(entries);

	}

	private void createIndicatorConfigEntries(final ResourceConfiguration configuration) {
		final Set<IndicatorResourceConfigEntry> entries = new HashSet<IndicatorResourceConfigEntry>();

		final IndicatorType type = this.typeDAO.getIndicatorTypeByCode(PSP080);
		final Source source = this.sourceDAO.getSourceByCode(ESA_UNPD_WPP2012);

		final IndicatorResourceConfigEntry entry1 = new IndicatorResourceConfigEntry(ConfigurationConstants.MAX_VALUE, "24000", source, type);
		final IndicatorResourceConfigEntry entry2 = new IndicatorResourceConfigEntry(ConfigurationConstants.MIN_VALUE, "0.022", source, type);

		final IndicatorResourceConfigEntry entry3 = new IndicatorResourceConfigEntry(ConfigurationConstants.VALIDATORS, MinMaxValidatorCreator.NAME, source, type);

		final IndicatorResourceConfigEntry entry4 = new IndicatorResourceConfigEntry(ConfigurationConstants.EXPECTED_TIME_FORMAT, "YYYY", source, type);
		final IndicatorResourceConfigEntry entry5 = new IndicatorResourceConfigEntry(ConfigurationConstants.EXPECTED_START_TIME_FORMAT, "YYYY-01-01", source, type);

		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);
		entries.add(entry4);
		entries.add(entry5);

		configuration.setIndicatorConfigEntries(entries);
	}

}
