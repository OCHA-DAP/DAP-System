package org.ocha.hdx.service;

import java.util.List;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;

public interface IndicatorCreationService {

	Indicator createIndicator(PreparedIndicator preparedIndicator);

	List<Indicator> createIndicators(List<PreparedIndicator> preparedIndicators);

	List<IndicatorResourceConfigEntry> findEmbeddedConfigs(String indicatorTypeCode, String sourceCode);
}
