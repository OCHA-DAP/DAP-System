package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.i18n.Text;

public interface SourceDAO {

	public List<Source> listSources();

	public void addSource(String code, Text name);

	public Source getSourceByCode(String code);

	public Source getSourceById(long id);

	public void deleteSourceByCode(String code);

}
