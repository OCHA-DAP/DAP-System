package org.ocha.hdx.persistence.dao.config;

import java.util.List;
import java.util.Set;

import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

public interface ResourceConfigurationDAO {
	public List<ResourceConfiguration> listResourceConfigurations();

	public ResourceConfiguration createResourceConfiguration(String name, Set<ResourceConfigEntry> generalConfigList, Set<IndicatorResourceConfigEntry> indicatorConfigList);

	public ResourceConfiguration getResourceConfigurationById(long id);

	public ResourceConfigEntry getResourceConfigEntryById(long id);

	public IndicatorResourceConfigEntry getIndicatorResourceConfigEntryById(long id);

	public void deleteResourceConfiguration(long id);

	public void updateResourceConfiguration(final long id, String name, Set<ResourceConfigEntry> generalConfigList, Set<IndicatorResourceConfigEntry> indicatorConfigList);

	public void addGeneralConfiguration(long id, String key, String value);

	public void deleteGeneralConfiguration(long rcID, long id);

	public void updateGeneralConfiguration(long id, String key, String value);

	public void addIndicatorConfiguration(long rcID, long indTypeID, long srcID, String key, String value);

	public void deleteIndicatorConfiguration(long rcID, long id);

	public void updateIndicatorConfiguration(long id, long indTypeID, long srcID, String key, String value);

}
