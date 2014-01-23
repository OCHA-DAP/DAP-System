package org.ocha.dap.persistence.dao.i18n;

import org.ocha.dap.persistence.entity.i18n.Text;

public interface TextDAO {

	public Text createText(final String defaultValue);

	public Text getTextById(long id);
	
	public void deleteText(Text text);

}
