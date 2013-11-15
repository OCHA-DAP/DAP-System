package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class EntityDAOImpl implements EntityDAO {
	
	@Autowired
	EntityTypeDAO entityTypeDAO;
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Entity> listEntities() {
		final TypedQuery<Entity> query = em.createQuery("SELECT e FROM Entity e ORDER BY e.code", Entity.class);
		return query.getResultList();
	}

	
	@Override
	@Transactional
	public void addEntity(final String code, final String name, final EntityType entityType) {
		final Entity entity = new Entity();
		entity.setCode(code);
		entity.setName(name);
		entity.setType(entityType);
		em.persist(entity);
	}

}
