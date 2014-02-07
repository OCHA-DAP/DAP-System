package org.ocha.hdx.persistence.dao.i18n;

import java.util.List;

import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.i18n.Translation;

public interface TextDAO {

	/*
	 * Text management
	 */

	public Text createText(final String defaultValue);

	public Text getTextById(long id);

	public void deleteText(long id);

	/*
	 * Translation management
	 */

	public Translation createTranslationForText(final long textId, final String languageCode, final String translationValue);

	public List<Translation> getTranslationsForText(final long textId);

	public void deleteTranslation(long textId, String languageCode);

	public void updateTranslation(long textId, String languageCode, String translationValue);

}
