package org.ocha.dap.persistence.dao.currateddata;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.i18n.Text;

public interface SourceDAO {

	public List<Source> listSources();

	public void createSource(String code, Text name, String link);

	public Source getSourceByCode(String code);

	public Source getSourceById(long id);

	public void deleteSourceByCode(String code);

	public void deleteSource(long sourceId);

	public void updateSource(long sourceId, String newName, String link);
}
