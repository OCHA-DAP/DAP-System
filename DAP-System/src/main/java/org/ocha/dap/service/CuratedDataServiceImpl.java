package org.ocha.dap.service;

import java.util.List;

import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CuratedDataServiceImpl implements CuratedDataService {
	
	@Autowired
	private EntityTypeDAO entityTypeDAO;
	
	@Autowired
	private EntityDAO entityDAO;

	@Override
	public List<EntityType> listEntityTypes() {
		return entityTypeDAO.listEntityTypes();
	}

	@Override
	public void addEntityType(final String code, final String name) {
		entityTypeDAO.addEntityType(code, name);
	}

	@Override
	public List<Entity> listEntities() {
		return entityDAO.listEntities();
	}

	@Override
	@Transactional
	public void addEntity(final String code, final String name, final String entityTypeCode) {
		final EntityType entityType = entityTypeDAO.getEntityTypeByCode(entityTypeCode);
		entityDAO.addEntity(code, name, entityType);
	}

}