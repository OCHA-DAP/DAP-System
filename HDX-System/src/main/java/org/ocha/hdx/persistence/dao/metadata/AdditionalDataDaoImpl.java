package org.ocha.hdx.persistence.dao.metadata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData.EntryKey;
import org.springframework.transaction.annotation.Transactional;

public class AdditionalDataDaoImpl implements AdditionalDataDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<AdditionalData> listAdditionalData() {
		final TypedQuery<AdditionalData> query = this.em.createQuery("SELECT ad FROM AdditionalData ad ORDER BY ad.id", AdditionalData.class);
		return query.getResultList();
	}

	@Override
	public List<AdditionalData> listAdditionalDataByIndicatorTypeIdAndSourceId(final long indicatorTypeId, final long sourceId) {
		final TypedQuery<AdditionalData> query = this.em.createQuery("SELECT ad FROM AdditionalData ad WHERE ad.indicatorType.id=:indicatorTypeId AND ad.source.id=:sourceId", AdditionalData.class);
		query.setParameter("indicatorTypeId", indicatorTypeId);
		query.setParameter("sourceId", sourceId);
		return query.getResultList();
	}

	@Override
	public List<AdditionalData> listAdditionalDataByIndicatorTypeCodeAndSourceCode(final String indicatorTypeCode, final String sourceCode) {
		final TypedQuery<AdditionalData> query = this.em.createQuery("SELECT ad FROM AdditionalData ad WHERE ad.indicatorType.code=:indicatorTypeCode AND ad.source.code=:sourceCode",
				AdditionalData.class);
		query.setParameter("indicatorTypeCode", indicatorTypeCode);
		query.setParameter("sourceCode", sourceCode);
		return query.getResultList();
	}

	@Override
	@Transactional
	public AdditionalData createAdditionalData(final IndicatorType type, final Source source, final EntryKey key, final Text value) {
		final AdditionalData additionalData = new AdditionalData(type, source, key, value);
		this.em.persist(additionalData);
		return additionalData;

	}

	@Override
	@Transactional
	public void deleteAdditionalData(final long id) {
		final AdditionalData additionalData = this.getAdditionalDataById(id);
		this.em.remove(additionalData);

	}

	@Override
	public AdditionalData getAdditionalDataById(final long id) {
		return this.em.find(AdditionalData.class, id);
	}

	@Override
	@Transactional
	public AdditionalData updateAdditionalData(final long id, final Text value) {
		final AdditionalData additionalData = this.getAdditionalDataById(id);
		additionalData.setEntryValue(value);
		return additionalData;
	}

	@Override
	public AdditionalData getAdditionalDataByIndicatorTypeCodeAndSourceCodeAndEntryKey(final String indicatorTypeCode, final String sourceCode, final EntryKey entryKey) {
		final TypedQuery<AdditionalData> query = this.em.createQuery(
				"SELECT ad FROM AdditionalData ad WHERE ad.indicatorType.code=:indicatorTypeCode AND ad.source.code=:sourceCode AND ad.entryKey = :entryKey", AdditionalData.class);
		query.setParameter("indicatorTypeCode", indicatorTypeCode);
		query.setParameter("sourceCode", sourceCode);
		query.setParameter("entryKey", entryKey);
		try {
			return query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}
}
