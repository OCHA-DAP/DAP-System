package org.ocha.hdx.persistence.dao.i18n;

import java.util.List;

import org.ocha.hdx.persistence.entity.i18n.Language;

public interface LanguageDAO {

	public List<Language> listLanguages();

	public Language createLanguage(final String code, final String nativeName);

	public Language getLanguageByCode(String code);

	public void updateLanguage(String code, String nativeName);

	public void deleteLanguage(String code);
}
