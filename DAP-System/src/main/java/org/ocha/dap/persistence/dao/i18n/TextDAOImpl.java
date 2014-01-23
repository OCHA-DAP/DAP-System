package org.ocha.dap.persistence.dao.i18n;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ocha.dap.persistence.entity.i18n.Text;
import org.springframework.transaction.annotation.Transactional;

public class TextDAOImpl implements TextDAO {

	@PersistenceContext
	private EntityManager em;

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
	public void deleteText(final Text text) {
		em.remove(em.contains(text) ? text : em.merge(text));
	}

}
