package org.ocha.dap.persistence.dao.i18n;

import java.util.List;

import org.ocha.dap.persistence.entity.i18n.Text;
import org.ocha.dap.persistence.entity.i18n.Translation;

public interface TextDAO {

	/*
	 * Text management
	 */
	
	public Text createText(final String defaultValue);

	public Text getTextById(long id);
	
	public void deleteText(Text text);

	
	/*
	 * Translation management
	 */
	
	public Translation createTranslationForText(final long textId, final String languageCode, final String translationValue);

	public List<Translation> getTranslationsForText(final long textId);

	public void deleteTranslation(long textId, String languageCode);

	public void updateTranslation(long textId, String languageCode, String translationValue);

}
