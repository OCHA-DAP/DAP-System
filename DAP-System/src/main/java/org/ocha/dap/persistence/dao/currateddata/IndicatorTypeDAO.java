package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.i18n.Text;

public interface IndicatorTypeDAO {

	public List<IndicatorType> listIndicatorTypes();

	public void addIndicatorType(String code, Text name, String unit);

	public IndicatorType getIndicatorTypeByCode(String code);

	public IndicatorType getIndicatorTypeById(long id);

	public void deleteIndicatorTypeByCode(String code);

}
