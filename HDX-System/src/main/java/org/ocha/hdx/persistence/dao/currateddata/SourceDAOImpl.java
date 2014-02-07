package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.transaction.annotation.Transactional;

public class SourceDAOImpl implements SourceDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Source> listSources() {
		final TypedQuery<Source> query = em.createQuery("SELECT s FROM Source s ORDER BY s.code", Source.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void createSource(final String code, final Text name, final String link) {
		final Source source = new Source();
		source.setCode(code);
		source.setName(name);
		source.setOrgLink(link);
		em.persist(source);
	}

	@Override
	public Source getSourceByCode(final String code) {
		final TypedQuery<Source> query = em.createQuery("SELECT s FROM Source s Where s.code = :code", Source.class).setParameter("code", code);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public void deleteSourceByCode(final String code) {
		em.remove(getSourceByCode(code));
	}

	@Override
	public Source getSourceById(final long id) {
		return em.find(Source.class, id);
	}

	@Override
	@Transactional
	public void deleteSource(final long sourceId) {
		em.createQuery("DELETE FROM Source s WHERE s.id = :sourceId").setParameter("sourceId", sourceId).executeUpdate();
	}

	@Override
	@Transactional
	public void updateSource(final long sourceId, final String newName, final String newLink) {
		final Source source = em.find(Source.class, sourceId);
		source.getName().setDefaultValue(newName);
		source.setOrgLink(newLink);
	}

}
