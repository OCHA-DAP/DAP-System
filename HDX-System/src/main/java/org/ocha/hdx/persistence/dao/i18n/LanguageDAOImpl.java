package org.ocha.hdx.persistence.dao.i18n;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.i18n.Language;
import org.springframework.transaction.annotation.Transactional;

public class LanguageDAOImpl implements LanguageDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Language> listLanguages() {
		final TypedQuery<Language> query = em.createQuery("SELECT l FROM Language l ORDER BY l.code", Language.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public Language createLanguage(final String code, final String nativeName) {
		final Language language = new Language();
		language.setCode(code);
		language.setNativeName(nativeName);
		em.persist(language);
		return language;
	}

	@Override
	public Language getLanguageByCode(final String code) {
		return em.find(Language.class, code);
	}

	@Override
	@Transactional
	public void updateLanguage(final String code, final String nativeName) {
		em.createQuery("UPDATE Language l set l.nativeName = :nativeName WHERE l.code = :code").setParameter("nativeName", nativeName).setParameter("code", code).executeUpdate();
	}

	@Override
	@Transactional
	public void deleteLanguage(final String code) {
		em.remove(getLanguageByCode(code));
	}
}
