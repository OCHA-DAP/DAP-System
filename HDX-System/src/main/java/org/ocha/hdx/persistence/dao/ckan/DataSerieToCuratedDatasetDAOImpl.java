package org.ocha.hdx.persistence.dao.ckan;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.ckan.DataSerieToCuratedDataset;

public class DataSerieToCuratedDatasetDAOImpl implements DataSerieToCuratedDatasetDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<DataSerieToCuratedDataset> getDatasetsWithUnsyncedMetadata() {
		final TypedQuery<DataSerieToCuratedDataset> query = em.createQuery("SELECT dstcd FROM DataSerieToCuratedDataset dstcd WHERE dstcd.lastMetadataUpdate > dstcd.lastMetadataPush",
				DataSerieToCuratedDataset.class);
		return query.getResultList();
	}

}
