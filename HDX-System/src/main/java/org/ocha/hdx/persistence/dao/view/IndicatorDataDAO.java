package org.ocha.hdx.persistence.dao.view;

import java.util.List;
import java.util.Map;

import org.ocha.hdx.persistence.entity.view.IndicatorData;

public interface IndicatorDataDAO {

	public Map<Long, Map<String, IndicatorData>> getIndicatorData(String indicatorTypeCode, String sourceCode, Long fromYear, Long toYear);

	public List<IndicatorData> getIndicatorData(String entityCode, List<String> indicatorTypeCodes);

}
