package org.ocha.hdx.persistence.dao.view;

import org.ocha.hdx.persistence.entity.view.IndicatorTypeOverview;

public interface IndicatorTypeOverviewDAO {

	public IndicatorTypeOverview getIndicatorTypeOverview(String indicatorTypeCode, String sourceCode);

}
