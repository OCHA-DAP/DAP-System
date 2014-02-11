package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.i18n.Text;
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
	public void createEntityType(final String code, final Text name) {
		final EntityType entityType = new EntityType();
		entityType.setCode(code);
		entityType.setName(name);
		em.persist(entityType);
	}

	@Override
	public EntityType getEntityTypeById(final long id) {
		return em.find(EntityType.class, id);
	}

	@Override
	public EntityType getEntityTypeByCode(final String code) {
		final TypedQuery<EntityType> query = em.createQuery("SELECT et FROM EntityType et Where et.code = :code", EntityType.class).setParameter("code", code);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public void deleteEntityTypeByCode(final String code) {
		em.remove(getEntityTypeByCode(code));
	}

	@Override
	@Transactional
	public void updateEntityType(final long entityTypeId, final String newName) {
		final EntityType entityType = em.find(EntityType.class, entityTypeId);
		entityType.getName().setDefaultValue(newName);
	}

	@Override
	@Transactional
	public void deleteEntityType(final long entityTypeId) {
		em.remove(getEntityTypeById(entityTypeId));
	}
}
