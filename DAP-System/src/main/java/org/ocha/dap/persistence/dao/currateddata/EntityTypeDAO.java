package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.EntityType;

public interface EntityTypeDAO {
	
	public List<EntityType> listEntityTypes();
	public void addEntityType(String code, String name);
	
	public EntityType getEntityTypeByCode(String code);

}
