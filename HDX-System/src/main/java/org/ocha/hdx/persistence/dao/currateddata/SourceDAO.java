package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Organization;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;

public interface SourceDAO {

	public List<Source> listSources();

	public List<Source> listSourcesForIndicatorType(String indicatorTypeCode);

	public Source createSource(String code, Text name, String link, Organization organization);

	public Source getSourceByCode(String code);

	public Source getSourceById(long id);

	public void deleteSourceByCode(String code);

	public void deleteSource(long sourceId);

	public void updateSource(long sourceId, String newName, String link, Organization organization);
}
