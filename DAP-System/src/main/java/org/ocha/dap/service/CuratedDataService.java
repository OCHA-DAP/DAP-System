package org.ocha.dap.service;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;

public interface CuratedDataService {

	public List<EntityType> listEntityTypes();

	public void addEntityType(String code, String name);

	public List<Entity> listEntities();

	public void addEntity(final String code, final String name, final String entityTypeCode);

	public List<IndicatorType> listIndicatorTypes();

	public void addIndicatorType(String code, String name, String unit);

	public List<Source> listSources();

	public void addSource(String code, String name);

}
