package org.ocha.hdx.persistence.dao.config;

import java.util.List;
import java.util.Set;

import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;

public interface ResourceConfigurationDao {
	public List<ResourceConfiguration> listResourceConfigurations();

	public ResourceConfiguration createResourceConfig(String name,
			Set<ResourceConfigEntry> generalConfigList,
			Set<IndicatorResourceConfigEntry> indicatorConfigList);

	public ResourceConfiguration getResourceConfigurationById(long id);

	public void deleteResourceConfiguration(long id);

	public void updateResourceConfiguration(final long id,
			String name, Set<ResourceConfigEntry> generalConfigList,
			Set<IndicatorResourceConfigEntry> indicatorConfigList);
}
