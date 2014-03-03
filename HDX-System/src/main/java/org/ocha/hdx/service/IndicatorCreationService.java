package org.ocha.hdx.service;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;

public interface IndicatorCreationService {

	Indicator createIndicator(PreparedIndicator preparedIndicator);
}
