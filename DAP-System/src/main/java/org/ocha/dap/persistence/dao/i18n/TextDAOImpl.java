package org.ocha.dap.persistence.dao.i18n;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.i18n.Language;
import org.ocha.dap.persistence.entity.i18n.Text;
import org.ocha.dap.persistence.entity.i18n.Translation;
import org.springframework.transaction.annotation.Transactional;

public class TextDAOImpl implements TextDAO {

	@PersistenceContext
	private EntityManager em;

	/*
	 * Text management
	 */
	@Override
	@Transactional
	public Text createText(final String defaultValue) {
		final Text text = new Text(defaultValue);
		em.persist(text);
		return text;
	}

	@Override
	public Text getTextById(final long id) {
		return em.find(Text.class, id);
	}

	@Override
	@Transactional
	public void deleteText(final long id) {
		em.remove(getTextById(id));
	}

	/*
	 * Translation management
	 */
	@Override
	@Transactional
	public Translation createTranslationForText(final long textId, final String languageCode, final String translationValue) {
		final Translation translation = new Translation();
		final Text text = em.find(Text.class, textId);
		final Language language = em.find(Language.class, languageCode);

		final Translation.Id id = new Translation.Id(text, language);

		translation.setId(id);
		translation.setValue(translationValue);

		em.persist(translation);

		return translation;
	}

	@Override
	public List<Translation> getTranslationsForText(final long textId) {
		final TypedQuery<Translation> query = em.createQuery("SELECT t FROM Translation t Where t.id.text.id = :text", Translation.class).setParameter("text", textId);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateTranslation(final long textId, final String languageCode, final String newTranslationValue) {
		em.createQuery("UPDATE Translation t set t.value = :newTranslationValue WHERE t.id.text.id = :textId AND t.id.language.code = :languageCode").setParameter("textId", textId)
				.setParameter("languageCode", languageCode).setParameter("newTranslationValue", newTranslationValue).executeUpdate();
	}

	@Override
	@Transactional
	public void deleteTranslation(final long textId, final String languageCode) {
		em.createQuery("DELETE FROM Translation t WHERE t.id.text.id = :textId AND t.id.language.code = :languageCode").setParameter("textId", textId).setParameter("languageCode", languageCode)
				.executeUpdate();
	}
}
