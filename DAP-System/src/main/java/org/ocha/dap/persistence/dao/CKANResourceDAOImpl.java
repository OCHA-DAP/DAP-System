package org.ocha.dap.persistence.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.dap.persistence.entity.CKANResource;
import org.ocha.dap.persistence.entity.CKANResource.WorkflowState;
import org.springframework.transaction.annotation.Transactional;

public class CKANResourceDAOImpl implements CKANResourceDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void newCKANResourceDetected(final String id, final String revision_id, final Date revision_timestamp, final String parentDataset_id,
			final String parentDataset_revision_id, final Date parentDataset_revision_timestamp) {
		final CKANResource ckanResource = new CKANResource(id, revision_id, !ckanResourceExists(id));
		ckanResource.setRevision_timestamp(revision_timestamp);
		ckanResource.setParentDataset_id(parentDataset_id);
		ckanResource.setParentDataset_revision_id(parentDataset_revision_id);
		ckanResource.setParentDataset_revision_timestamp(parentDataset_revision_timestamp);
		ckanResource.setDetectionDate(new Date());
		ckanResource.setDownloadDate(null);

		em.persist(ckanResource);

	}

	/**
	 * a new (id,revision_id) can be a brand new resource or a revision of an
	 * existing resource revisions of a resource will have a distinct
	 * revision_id, but will share the same id So we want to know if there is
	 * already a ckan resource with the given ID
	 */
	private boolean ckanResourceExists(final String id) {
		return !listCKANResourceRevisions(id).isEmpty();
	}

	@Override
	@Transactional
	public void flagCKANResourceAsDownloaded(final String id, final String revision_id) {
		final CKANResource ckanResourceToFlag = em.find(CKANResource.class, new CKANResource.Id(id, revision_id));
		ckanResourceToFlag.setWorkflowState(WorkflowState.DOWNLOADED);
		ckanResourceToFlag.setDownloadDate(new Date());
	}

	@Override
	@Transactional
	public void flagCKANResourceAsOutdated(final String id, final String revision_id) {
		final CKANResource ckanResourceToFlag = em.find(CKANResource.class, new CKANResource.Id(id, revision_id));
		ckanResourceToFlag.setWorkflowState(WorkflowState.OUTDATED);
	}

	@Override
	public CKANResource getCKANResource(final String id, final String revision_id) {
		return em.find(CKANResource.class, new CKANResource.Id(id, revision_id));
	}
	
	@Override
	public List<CKANResource> listCKANResourceRevisions(final String id) {
		final TypedQuery<CKANResource> query = em.createQuery("SELECT r FROM CKANResource r WHERE r.id.id = :id", CKANResource.class).setParameter("id", id);
		return query.getResultList();
	}

	@Override
	public List<CKANResource> listCKANResources() {
		final TypedQuery<CKANResource> query = em.createQuery("SELECT r FROM CKANResource r ORDER BY id.id, detectionDate desc", CKANResource.class);
		return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteAllCKANResourcesRecords() {
		em.createQuery("DELETE FROM CKANResource").executeUpdate();

	}

}
