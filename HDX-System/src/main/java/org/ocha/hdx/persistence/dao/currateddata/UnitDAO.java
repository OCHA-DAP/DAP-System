package org.ocha.hdx.persistence.dao.currateddata;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;

import java.util.List;

/**
 * @author Alexandru Artimon
 * @since 19/02/14
 */
public interface UnitDAO {

    public Unit createUnit(String code, Text name);
    public Unit getUnitByCode(String code);
    public Unit getUnitById(Long id);
    public List<Unit> listUnits();

    public void deleteUnit(Long id);
    public void updateUnit(Long id, String name);
}
