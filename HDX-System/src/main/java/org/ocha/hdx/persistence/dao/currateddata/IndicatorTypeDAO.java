package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.i18n.Text;

public interface IndicatorTypeDAO {

	public List<IndicatorType> listIndicatorTypes();

	public void createIndicatorType(String code, Text name, String unit, ValueType valueType);

	public IndicatorType getIndicatorTypeByCode(String code);

	public IndicatorType getIndicatorTypeById(long id);

	public void deleteIndicatorTypeByCode(String code);

	public void deleteIndicatorType(long indicatorTypeId);

	public void updateIndicatorType(long indicatorTypeId, String newName, String newUnit, ValueType valueType);

}
