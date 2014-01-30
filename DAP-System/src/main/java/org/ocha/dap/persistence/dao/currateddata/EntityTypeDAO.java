package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.i18n.Text;

public interface EntityTypeDAO {

	public List<EntityType> listEntityTypes();

	public void createEntityType(String code, Text name);

	public EntityType getEntityTypeById(long id);

	public EntityType getEntityTypeByCode(final String code);

	public void updateEntityType(long entityTypeId, String newName);

	public void deleteEntityTypeByCode(final String code);

	public void deleteEntityType(long entityTypeId);


}
