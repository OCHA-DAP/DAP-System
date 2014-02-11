package org.ocha.hdx.rest.helper;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;

public class DisplayEntities {

	List<Entity> entities;
	List<EntityType> entityTypes;

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(final List<Entity> entities) {
		this.entities = entities;
	}

	public List<EntityType> getEntityTypes() {
		return entityTypes;
	}

	public void setEntityTypes(final List<EntityType> entityTypes) {
		this.entityTypes = entityTypes;
	}

}
