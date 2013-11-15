package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.springframework.transaction.annotation.Transactional;

public class EntityTypeDAOImpl implements EntityTypeDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EntityType> listEntityTypes() {
		final TypedQuery<EntityType> query = em.createQuery("SELECT et FROM EntityType et ORDER BY et.code", EntityType.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void addEntityType(final String code, final String name) {
		final EntityType entityType = new EntityType();
		entityType.setCode(code);
		entityType.setName(name);
		em.persist(entityType);
	}

	@Override
	public EntityType getEntityTypeByCode(final String code) {
		final TypedQuery<EntityType> query = em.createQuery("SELECT et FROM EntityType et Where et.code = :code", EntityType.class).setParameter("code", code);
		return query.getSingleResult();
	}

}
