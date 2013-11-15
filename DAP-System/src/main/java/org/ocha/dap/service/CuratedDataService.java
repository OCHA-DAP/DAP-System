package org.ocha.dap.service;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.EntityType;

public interface CuratedDataService {
	
	public List<EntityType> listEntityTypes();

}
