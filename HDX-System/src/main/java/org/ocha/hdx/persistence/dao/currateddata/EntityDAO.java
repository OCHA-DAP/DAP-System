package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.i18n.Text;

public interface EntityDAO {

	public List<Entity> listEntities();

	public Entity createEntity(String code, Text name, final EntityType entityType);

	public Entity createEntity(String code, Text name, final EntityType entityType, long parentId);

	public Entity getEntityByCodeAndType(String code, String type);

	public Entity getEntityById(long id);

	public void deleteEntityByCodeAndType(String code, String type);

	public void deleteEntity(long entityId);

	public void updateEntity(long entityId, String newName, Long parentId);

	/**
	 * Up to 2 levels (entity --> children --> children)
	 * 
	 */
	public Entity getEntityTreeFromCode(String code, final String type);

}
