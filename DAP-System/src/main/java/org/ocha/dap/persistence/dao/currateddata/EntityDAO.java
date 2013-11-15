package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;

public interface EntityDAO {
	
	public List<Entity> listEntities();
	
	public void addEntity(String code, String name, final EntityType entityType);

}
