package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.i18n.Text;

public interface EntityDAO {

	public List<Entity> listEntities();


	public void createEntity(String code, Text name, final EntityType entityType);

	public Entity getEntityByCodeAndType(String code, String type);

	public Entity getEntityById(long id);

	public void deleteEntityByCodeAndType(String code, String type);

	public void deleteEntity(long entityId);

	public void updateEntity(long entityId, String newName);

}
