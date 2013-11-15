package org.ocha.dap.service;

import java.util.List;

import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.springframework.beans.factory.annotation.Autowired;

public class CuratedDataServiceImpl implements CuratedDataService {
	
	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Override
	public List<EntityType> listEntityTypes() {
		return entityTypeDAO.listEntityTypes();
	}

	@Override
	public void addEntityType(final String code, final String name) {
		entityTypeDAO.addEntityType(code, name);
	}

}