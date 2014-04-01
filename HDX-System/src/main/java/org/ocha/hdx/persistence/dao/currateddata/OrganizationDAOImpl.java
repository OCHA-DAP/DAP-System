/**
 *
 */
package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.Organization;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 *
 */
//@Component(value="organizationDAO")
public class OrganizationDAOImpl implements OrganizationDAO {

	@PersistenceContext
	private EntityManager em;


	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO#listSources()
	 */
	@Override
	public List<Organization> listOrganizations() {
		final TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO#createOrganization(java.lang.String, org.ocha.hdx.persistence.entity.i18n.Text, org.ocha.hdx.persistence.entity.i18n.Text)
	 */
	@Override
	@Transactional
	public void createOrganization(final String orgLink, final Text fullName, final Text shortName) {
		final Organization org	= new Organization(orgLink, fullName, shortName);
		em.persist(org);

	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO#getOrganizationById(long)
	 */
	@Override
	public Organization getOrganizationById(final long id) {
		return em.find(Organization.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO#deleteOrganization(long)
	 */
	@Override
	@Transactional
	public void deleteOrganization(final long id) {
		//this.em.createQuery("DELETE FROM Organization o WHERE o.id = :orgId").setParameter("orgId", id ).executeUpdate();
		final Organization o 	= em.find(Organization.class, id);
		em.remove(o);
		em.remove(o.getShortName());
		em.remove(o.getFullName());

	}

	/*
	 * (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO#updateOrganization(long, org.ocha.hdx.persistence.entity.i18n.Text, org.ocha.hdx.persistence.entity.i18n.Text)
	 *
	 */
	@Override
	@Transactional
	public void updateOrganization(final long id, final String orgLink, final Text fullName, final Text shortName) {
		final Organization organization	= em.find(Organization.class, id);
		final Text oldFullName	= organization.getFullName();
		final Text oldShortName	= organization.getShortName();
		organization.setFullName(fullName);
		organization.setShortName(shortName);
		organization.setOrgLink(orgLink);
		em.remove(oldFullName);
		em.remove(oldShortName);
	}

	/*
	 * (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO#updateOrganization(long, org.ocha.hdx.persistence.entity.i18n.Text, org.ocha.hdx.persistence.entity.i18n.Text)
	 *
	 */
	@Override
	@Transactional
	public void updateOrganization(final long id, final String orgLink, final String newFullName, final String newShortName) {
		final Organization organization	= em.find(Organization.class, id);
		organization.getFullName().setDefaultValue(newFullName);
		organization.getShortName().setDefaultValue(newShortName);
		organization.setOrgLink(orgLink);
	}
}
