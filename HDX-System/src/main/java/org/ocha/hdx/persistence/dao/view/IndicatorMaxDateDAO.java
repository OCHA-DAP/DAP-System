package org.ocha.hdx.persistence.dao.view;

import java.util.List;

import org.ocha.hdx.persistence.entity.view.IndicatorMaxDate;

public interface IndicatorMaxDateDAO {
	public List<IndicatorMaxDate> getValues(final List<String> entityCodes, final List<String> indicatorTypeCodes, final List<String> sourceCodes);

}
