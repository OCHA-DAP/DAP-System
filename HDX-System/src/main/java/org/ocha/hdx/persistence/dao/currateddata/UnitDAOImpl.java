package org.ocha.hdx.persistence.dao.currateddata;

import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Alexandru Artimon
 * @since 19/02/14
 */
public class UnitDAOImpl implements UnitDAO {
    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Unit createUnit(String code, Text name) {
        final Unit unit = new Unit();
        unit.setCode(code);
        unit.setName(name);
        em.persist(unit);
        return unit;
    }

    @Override
    public Unit getUnitByCode(String code) {
        final TypedQuery<Unit> query = em.createQuery("SELECT u FROM Unit u where u.code = :code", Unit.class).setParameter("code", code);
        return query.getSingleResult();
    }

    @Override
    public Unit getUnitById(Long id) {
        return em.find(Unit.class, id);
    }

    @Override
    public List<Unit> listUnits() {
        final TypedQuery<Unit> query = em.createQuery("SELECT u FROM Unit u ORDER BY u.code", Unit.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteUnit(Long id) {
        em.createQuery("DELETE FROM Unit u WHERE u.id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void updateUnit(Long id, String name) {
        final Unit unit = em.find(Unit.class, id);
        unit.getName().setDefaultValue(name);
    }

}
