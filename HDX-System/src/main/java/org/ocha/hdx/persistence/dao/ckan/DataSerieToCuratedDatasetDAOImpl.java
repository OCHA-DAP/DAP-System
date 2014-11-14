package org.ocha.hdx.persistence.dao.ckan;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.springframework.transaction.annotation.Transactional;

public class DataSerieToCuratedDatasetDAOImpl implements DataSerieToCuratedDatasetDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DataSerieToCuratedDataset> getDatasetsWithUnsyncedMetadata() {
		final TypedQuery<DataSerieToCuratedDataset> query = em.createQuery("SELECT dstcd FROM DataSerieToCuratedDataset dstcd WHERE dstcd.lastMetadataUpdate > dstcd.lastMetadataPush",
				DataSerieToCuratedDataset.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void updateLastMetadataTimestamp(final DataSerie dataSerie, final Date newTimestamp) {
		final TypedQuery<DataSerieToCuratedDataset> query = em.createQuery(
				"SELECT dstcd FROM DataSerieToCuratedDataset dstcd WHERE dstcd.source.code = :sourceCode And dstcd.indicatorType.code = :indicatorTypeCode", DataSerieToCuratedDataset.class);
		query.setParameter("sourceCode", dataSerie.getSourceCode());
		query.setParameter("indicatorTypeCode", dataSerie.getIndicatorCode());

		final DataSerieToCuratedDataset singleResult = query.getSingleResult();
		singleResult.setLastMetadataUpdate(newTimestamp);
	}

	@Override
	@Transactional
	public void updateLastDataTimestamp(final DataSerie dataSerie, final Date newTimestamp) {
		final TypedQuery<DataSerieToCuratedDataset> query = em.createQuery(
				"SELECT dstcd FROM DataSerieToCuratedDataset dstcd WHERE dstcd.source.code = :sourceCode And dstcd.indicatorType.code = :indicatorTypeCode", DataSerieToCuratedDataset.class);
		query.setParameter("sourceCode", dataSerie.getSourceCode());
		query.setParameter("indicatorTypeCode", dataSerie.getIndicatorCode());

		final DataSerieToCuratedDataset singleResult = query.getSingleResult();
		singleResult.setLastDataUpdate(newTimestamp);
	}

}
