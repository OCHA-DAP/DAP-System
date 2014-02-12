/**
 *
 */
package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.curateddata.Organisation;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 *
 */
//@Component(value="organisationDAO")
public class OrganisationDAOImpl implements OrganisationDAO {

	@PersistenceContext
	private EntityManager em;


	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganisationDAO#listSources()
	 */
	@Override
	public List<Organisation> listOrganizations() {
		final TypedQuery<Organisation> query = this.em.createQuery("SELECT o FROM Organisation o", Organisation.class);
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganisationDAO#createOrganisation(java.lang.String, org.ocha.hdx.persistence.entity.i18n.Text, org.ocha.hdx.persistence.entity.i18n.Text)
	 */
	@Override
	@Transactional
	public void createOrganisation(final String orgLink, final Text fullName, final Text shortName) {
		final Organisation org	= new Organisation(orgLink, fullName, shortName);
		this.em.persist(org);

	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganisationDAO#getOrganisationById(long)
	 */
	@Override
	public Organisation getOrganisationById(final long id) {
		return this.em.find(Organisation.class, id);
	}

	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganisationDAO#deleteOrganisation(long)
	 */
	@Override
	@Transactional
	public void deleteOrganisation(final long id) {
		//this.em.createQuery("DELETE FROM Organisation o WHERE o.id = :orgId").setParameter("orgId", id ).executeUpdate();
		final Organisation o 	= this.em.find(Organisation.class, id);
		this.em.remove(o);
		this.em.remove(o.getShortName());
		this.em.remove(o.getFullName());

	}

	/*
	 * (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.currateddata.OrganisationDAO#updateOrganisation(long, org.ocha.hdx.persistence.entity.i18n.Text, org.ocha.hdx.persistence.entity.i18n.Text)
	 *
	 */
	@Override
	@Transactional
	public void updateOrganisation(final long id, final String orgLink, final Text fullName, final Text shortName) {
		final Organisation organisation	= this.em.find(Organisation.class, id);
		final Text oldFullName	= organisation.getFullName();
		final Text oldShortName	= organisation.getShortName();
		organisation.setFullName(fullName);
		organisation.setShortName(shortName);
		organisation.setOrgLink(orgLink);
		this.em.remove(oldFullName);
		this.em.remove(oldShortName);
	}

}
