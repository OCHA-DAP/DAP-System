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
import org.ocha.hdx.validation.itemvalidator.MinMaxValidator;
import org.ocha.hdx.validation.prevalidator.AllowedIndicatorTypesValidators;
import org.ocha.hdx.validation.prevalidator.ColumnNumPreValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author alexandru-m-g
 * 
 */
@Component
public class DummyConfigurationCreator {

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

		final ResourceConfigEntry entry1 = new ResourceConfigEntry(ConfigurationConstants.VALIDATORS, MinMaxValidator.NAME);
		final ResourceConfigEntry entry2 = new ResourceConfigEntry(ConfigurationConstants.PREVALIDATORS, ColumnNumPreValidator.NAME
				+ ConfigurationConstants.SEPARATOR + AllowedIndicatorTypesValidators.NAME);
		final ResourceConfigEntry entry3 = new ResourceConfigEntry(ConfigurationConstants.NUM_OF_ALLOWED_COLUMNS, "7");
		final ResourceConfigEntry entry4 = new ResourceConfigEntry(ConfigurationConstants.ALLOWED_INDICATOR_TYPES, 
				"PVX040" + ConfigurationConstants.SEPARATOR + 
				"PSP080" + ConfigurationConstants.SEPARATOR + 
				"PSE030" + ConfigurationConstants.SEPARATOR +
				"PCX051" + ConfigurationConstants.SEPARATOR +
				"PVF020" + ConfigurationConstants.SEPARATOR +
				"PSP010" + ConfigurationConstants.SEPARATOR +
				"_emdat:total_affected");		

		entries.add(entry1);
		entries.add(entry2);
		entries.add(entry3);
		entries.add(entry4);

		configuration.setGeneralConfigEntries(entries);

	}

	private void createIndicatorConfigEntries(final ResourceConfiguration configuration) {
		final Set<IndicatorResourceConfigEntry> entries = new HashSet<IndicatorResourceConfigEntry>();

		final IndicatorType type = this.typeDAO.getIndicatorTypeByCode("PSP080");
		final Source source = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");

		final IndicatorResourceConfigEntry entry1 = new IndicatorResourceConfigEntry(ConfigurationConstants.MAX_VALUE, "24000", source, type);
		final IndicatorResourceConfigEntry entry2 = new IndicatorResourceConfigEntry(ConfigurationConstants.MIN_VALUE, "0.022", source, type);

		entries.add(entry1);
		entries.add(entry2);

		configuration.setIndicatorConfigEntries(entries);
	}

}
