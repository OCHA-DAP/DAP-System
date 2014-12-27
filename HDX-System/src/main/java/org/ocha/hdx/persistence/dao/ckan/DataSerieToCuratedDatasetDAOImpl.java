package org.ocha.hdx.persistence.dao.ckan;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.springframework.transaction.annotation.Transactional;

public class DataSerieToCuratedDatasetDAOImpl implements DataSerieToCuratedDatasetDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DataSerieToCuratedDataset> getDatasetsWithUnsyncedMetadata() {
		final TypedQuery<DataSerieToCuratedDataset> query = em.createQuery(
				"SELECT dstcd FROM DataSerieToCuratedDataset dstcd WHERE dstcd.lastMetadataUpdate > dstcd.lastMetadataPush OR dstcd.lastMetadataPush IS NULL", DataSerieToCuratedDataset.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public boolean updateLastMetadataTimestamp(final DataSerie dataSerie, final Date newTimestamp) {
		final DataSerieToCuratedDataset singleResult = getDataSerieToCuratedDataset(dataSerie);
		if (singleResult == null) {
			return false;
		} else {
			singleResult.setLastMetadataUpdate(newTimestamp);
			return true;
		}
	}

	@Override
	@Transactional
	public boolean updateLastDataTimestamp(final DataSerie dataSerie, final Date newTimestamp) {
		final DataSerieToCuratedDataset singleResult = getDataSerieToCuratedDataset(dataSerie);
		if (singleResult == null) {
			return false;
		} else {
			singleResult.setLastDataUpdate(newTimestamp);
			return true;
		}
	}

	@Override
	public DataSerieToCuratedDataset getDataSerieToCuratedDataset(final DataSerie dataSerie) {
		final TypedQuery<DataSerieToCuratedDataset> query = em.createQuery(
				"SELECT dstcd FROM DataSerieToCuratedDataset dstcd WHERE dstcd.source.code = :sourceCode And dstcd.indicatorType.code = :indicatorTypeCode", DataSerieToCuratedDataset.class);
		query.setParameter("sourceCode", dataSerie.getSourceCode());
		query.setParameter("indicatorTypeCode", dataSerie.getIndicatorCode());

		try {
			return query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}

	}

	@Override
	@Transactional
	public void createDataSerieToCuratedDataset(final Source source, final IndicatorType indicatorType) {
		final DataSerieToCuratedDataset dataSerieToCuratedDataset = new DataSerieToCuratedDataset();
		dataSerieToCuratedDataset.setSource(source);
		dataSerieToCuratedDataset.setIndicatorType(indicatorType);
		em.persist(dataSerieToCuratedDataset);
	}
}
