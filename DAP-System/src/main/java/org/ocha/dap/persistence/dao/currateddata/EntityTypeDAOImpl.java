package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.curateddata.EntityType;

public class EntityTypeDAOImpl implements EntityTypeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EntityType> listEntityTypes() {
		final TypedQuery<EntityType> query = em.createQuery("SELECT et FROM EntityType et ORDER BY et.code", EntityType.class);
		return query.getResultList();
	}

}
