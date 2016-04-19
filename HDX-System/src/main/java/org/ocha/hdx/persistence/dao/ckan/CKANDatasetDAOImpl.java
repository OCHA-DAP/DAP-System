package org.ocha.hdx.persistence.dao.ckan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.dto.apiv3.DatasetV3DTO;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset;
import org.ocha.hdx.persistence.entity.ckan.CKANDataset.Type;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.springframework.transaction.annotation.Transactional;

public class CKANDatasetDAOImpl implements CKANDatasetDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void importDetectedDatasetsIfNotPresent(final List<DatasetV3DTO> datasets) {
		for (final DatasetV3DTO datasetV3DTO : datasets) {
			final CKANDataset dataset = em.find(CKANDataset.class, datasetV3DTO.getName());
			if (dataset == null) {
				final CKANDataset newDataset = new CKANDataset(datasetV3DTO.getName(), datasetV3DTO.getTitle(), datasetV3DTO.getMaintainer(), datasetV3DTO.getMaintainer_email(),
						datasetV3DTO.getAuthor(), datasetV3DTO.getAuthor_email());
				em.persist(newDataset);
			}
		}
	}

	@Override
	@Transactional
	public void flagDatasetAsToBeCurated(final String datasetName, final Type type, final ResourceConfiguration configuration) {
		final CKANDataset dataset = em.find(CKANDataset.class, datasetName);
		dataset.setStatus(CKANDataset.Status.TO_BE_CURATED);
		dataset.setType(type);
		dataset.setConfiguration(configuration);
	}

	@Override
	@Transactional
	public void flagDatasetAsIgnored(final String datasetName) {
		final CKANDataset dataset = em.find(CKANDataset.class, datasetName);
		dataset.setStatus(CKANDataset.Status.IGNORED);
		dataset.setType(null);
	}

	@Override
	@Transactional
	public void updateDataset(final String datasetName, final String importer, final Long configurationId) {
		final CKANDataset dataset = em.find(CKANDataset.class, datasetName);
		dataset.setStatus(CKANDataset.Status.TO_BE_CURATED);
		dataset.setType(CKANDataset.Type.valueOf(importer));
		if (null != configurationId) {
			final ResourceConfiguration resourceConfiguration = em.find(ResourceConfiguration.class, configurationId);
			dataset.setConfiguration(resourceConfiguration);
		}
	}

	@Override
	public List<CKANDataset> listCKANDatasets() {
		final TypedQuery<CKANDataset> query = em.createQuery("SELECT d FROM CKANDataset d ORDER BY d.name", CKANDataset.class);
		return query.getResultList();
	}

	@Override
	public Map<String, CKANDataset> listToBeCuratedCKANDatasets() {
		final Map<String, CKANDataset> result = new HashMap<>();
		final TypedQuery<CKANDataset> query = em.createQuery("SELECT r FROM CKANDataset r WHERE r.status='TO_BE_CURATED'", CKANDataset.class);
		final List<CKANDataset> toBeCuratedDatasets = query.getResultList();

		for (final CKANDataset toBeCuratedDataset : toBeCuratedDatasets) {
			result.put(toBeCuratedDataset.getName(), toBeCuratedDataset);
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

	@Override
	public String getMaintainerMailForName(final String name) {
		final CKANDataset dataset = em.find(CKANDataset.class, name);
		if ( dataset != null ){
			return dataset.getMaintainer_email();
		}
		else {
			/* This is the case for manually uploaded files to cps (name=="MANUAL_UPLOAD") */
			return null;
		}
	}

}
