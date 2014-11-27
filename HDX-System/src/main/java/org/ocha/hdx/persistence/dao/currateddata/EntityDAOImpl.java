package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/*
 * Entities DAO Implementation
 */
public class EntityDAOImpl implements EntityDAO {

	private static Logger logger = LoggerFactory.getLogger(EntityDAOImpl.class);

	@Autowired
	EntityTypeDAO entityTypeDAO;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Entity> listEntities() {
		final TypedQuery<Entity> query = this.em.createQuery("SELECT e FROM Entity e ORDER BY e.code", Entity.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Entity createEntity(final String code, final Text name, final EntityType entityType) {
		final Entity entity = new Entity();
		entity.setCode(code);
		entity.setName(name);
		entity.setType(entityType);

		this.em.persist(entity);

		return entity;
	}

	@Override
	@Transactional
	public Entity createEntity(final String code, final Text name, final EntityType entityType, final long parentId) {
		final Entity entity = new Entity();
		entity.setCode(code);
		entity.setName(name);
		entity.setType(entityType);
		entity.setParent(this.getEntityById(parentId));

		this.em.persist(entity);

		return entity;
	}

	@Override
	public Entity getEntityByCodeAndType(final String code, final String type) {
		final TypedQuery<Entity> query = this.em.createQuery("SELECT e FROM Entity e Where e.code = :code AND e.type.code = :type", Entity.class).setParameter("code", code).setParameter("type", type);
		try {
			return query.getSingleResult();
		}
		catch (final NoResultException e) {
			logger.warn("No entity was found for code / type: " + code + "/" + type);
			throw e;
		}
	}

	@Override
	public Entity getEntityById(final long id) {
		return this.em.find(Entity.class, id);
	}

	@Override
	@Transactional
	public void deleteEntityByCodeAndType(final String code, final String type) {
		this.em.remove(this.getEntityByCodeAndType(code, type));
	}

	@Override
	@Transactional
	public void deleteEntity(final long entityId) {
		this.em.createQuery("DELETE FROM Entity i WHERE i.id = :entityId").setParameter("entityId", entityId).executeUpdate();
	}

	@Override
	@Transactional
	public void updateEntity(final long entityId, final String newName, final Long parentId) {
		final Entity entity = this.em.find(Entity.class, entityId);
		entity.getName().setDefaultValue(newName);
		if (null == parentId) {
			entity.setParent(null);
		} else {
			entity.setParent(this.getEntityById(parentId));
		}
	}

	@Override
	public Entity getEntityTreeFromCode(final String code, final String type) {
		// Could be done with FetchType.EAGER in Entity, but this way we keep a lazy loading for other methods
		final TypedQuery<Entity> query = this.em
				.createQuery("SELECT e FROM Entity e LEFT JOIN FETCH e.children adm1 LEFT JOIN FETCH adm1.children adm2 LEFT JOIN FETCH adm2.children Where e.code = :code AND e.type.code = :type",
						Entity.class).setParameter("code", code).setParameter("type", type);
		final Entity result = query.getSingleResult();
		return result;
	}
}
