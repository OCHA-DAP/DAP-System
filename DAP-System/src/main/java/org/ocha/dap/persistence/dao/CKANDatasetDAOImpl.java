package org.ocha.dap.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.CKANDataset;
import org.ocha.dap.persistence.entity.CKANDataset.Type;
import org.springframework.transaction.annotation.Transactional;

public class CKANDatasetDAOImpl implements CKANDatasetDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void importDetectedDatasetsIfNotPresent(final List<String> datasets) {
		for(final String datasetId : datasets){
			final CKANDataset dataset = em.find(CKANDataset.class, datasetId);
			if(dataset == null){
				final CKANDataset newDataset = new CKANDataset(datasetId);
				em.persist(newDataset);
			}
		}
	}

	@Override
	@Transactional
	public void flagDatasetAsToBeCurated(final String datasetName, final Type type) {
		final CKANDataset dataset = em.find(CKANDataset.class, datasetName);
		dataset.setStatus(CKANDataset.Status.TO_BE_CURATED);
		dataset.setType(type);
	}

	@Override
	@Transactional
	public void flagDatasetAsIgnored(final String datasetId) {
		final CKANDataset dataset = em.find(CKANDataset.class, datasetId);
		dataset.setStatus(CKANDataset.Status.IGNORED);
	}

	@Override
	public List<CKANDataset> listCKANDatasets() {
		final TypedQuery<CKANDataset> query = em.createQuery("SELECT r FROM CKANDataset r", CKANDataset.class);
		return query.getResultList();
	}
	
	@Override
	public List<String> listToBeCuratedCKANDatasets() {
		final List<String> result = new ArrayList<>();
		final TypedQuery<CKANDataset> query = em.createQuery("SELECT r FROM CKANDataset r WHERE r.status='TO_BE_CURATED'", CKANDataset.class);
		final List<CKANDataset> toBeCuratedDatasets = query.getResultList();
		
		for(final CKANDataset toBeCuratedDataset : toBeCuratedDatasets){
			result.add(toBeCuratedDataset.getName());
		}
		return result;
	}

	@Override
	@Transactional
	public void deleteAllCKANDatasetsRecords() {
		em.createQuery("DELETE FROM CKANDataset").executeUpdate();
	}

	@Override
	public Type getTypeForName(final String name) {
		final CKANDataset dataset = em.find(CKANDataset.class, name);
		return dataset.getType();
	}

}
