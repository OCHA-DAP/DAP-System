package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.IndicatorType;

public interface IndicatorTypeDAO {

	public List<IndicatorType> listIndicatorTypes();

	public void addIndicatorType(String code, String name, String unit);

	public IndicatorType getIndicatorTypeByCode(String code);

	public void deleteIndicatorTypeByCode(String code);

}
