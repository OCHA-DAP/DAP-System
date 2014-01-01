package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Source;

public interface SourceDAO {

	public List<Source> listSources();

	public void addSource(String code, String name);

	public Source getSourceByCode(String code);

	public Source getSourceById(long id);

	public void deleteSourceByCode(String code);

}
