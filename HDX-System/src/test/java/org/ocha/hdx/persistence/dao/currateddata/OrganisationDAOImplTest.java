/**
 *
 */
package org.ocha.hdx.persistence.dao.currateddata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.Organisation;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author alexandru-m-g
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class OrganisationDAOImplTest {

	final String SHORT_NAME_TEXT	= "Short Name";
	final String FULL_NAME_TEXT		= "Full Name";
	final String URL				= "URL";

	@Autowired
	private OrganisationDAO organisationDAO;

	@Autowired
	private TextDAO textDAO;

	@Test
	public void testListOrganisations(){
		final int LIST_SIZE		= 3;


		final List<Organisation> emptyList	= this.organisationDAO.listOrganizations();
		assertEquals(0, emptyList.size());

		for (int i=0; i<LIST_SIZE; i++ ) {
			final Text shortName	= this.textDAO.createText(this.SHORT_NAME_TEXT + i);

			final Text fullName		= this.textDAO.createText(this.FULL_NAME_TEXT + i);

			this.organisationDAO.createOrganisation(this.URL + i, fullName, shortName);

		}

		final List<Organisation> fullList	= this.organisationDAO.listOrganizations();
		assertEquals( LIST_SIZE, fullList.size());
		for (int i = 0; i < fullList.size(); i++) {
			final Organisation tempOrg = fullList.get(i);

			assertEquals(this.FULL_NAME_TEXT+i, tempOrg.getFullName().getDefaultValue() );
			assertEquals(this.SHORT_NAME_TEXT+i, tempOrg.getShortName().getDefaultValue());
			assertEquals(this.URL+i, tempOrg.getOrgLink());
		}

		for (int i = 0; i < fullList.size(); i++) {
			final Organisation tempOrg = fullList.get(i);

			this.organisationDAO.deleteOrganisation(tempOrg.getId());
		}

		final List<Organisation> deletedList	= this.organisationDAO.listOrganizations();
		assertEquals(0, deletedList.size());

	}

	@Test
	public void testCreateOrganisation() {
		this.organisationDAO.createOrganisation(this.URL, this.textDAO.createText(this.FULL_NAME_TEXT), this.textDAO.createText(this.SHORT_NAME_TEXT));

		final List<Organisation> list	= this.organisationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organisation org				= list.get(0);
		assertEquals(this.FULL_NAME_TEXT, org.getFullName().getDefaultValue());
		assertEquals(this.SHORT_NAME_TEXT, org.getShortName().getDefaultValue());
		assertEquals(this.URL, org.getOrgLink());

		this.organisationDAO.deleteOrganisation(org.getId());

	}

	@Test
	public void testUpdateOrganisation() {
		final String MODIFIED	= "modified";
		this.organisationDAO.createOrganisation(this.URL, this.textDAO.createText(this.FULL_NAME_TEXT), this.textDAO.createText(this.SHORT_NAME_TEXT));

		final List<Organisation> list	= this.organisationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organisation org				= list.get(0);
		assertEquals(this.FULL_NAME_TEXT, org.getFullName().getDefaultValue());
		assertEquals(this.SHORT_NAME_TEXT, org.getShortName().getDefaultValue());
		assertEquals(this.URL, org.getOrgLink());

		final Long oldFullNameId		= org.getFullName().getId();
		final Long oldShortNameId		= org.getShortName().getId();


		this.organisationDAO.updateOrganisation(org.getId(), MODIFIED
				+ this.URL,
				this.textDAO.createText(MODIFIED + this.FULL_NAME_TEXT),
				this.textDAO.createText(MODIFIED + this.SHORT_NAME_TEXT));

		this.checkTextDeleted(oldFullNameId);
		this.checkTextDeleted(oldShortNameId);


		final List<Organisation> modfiedList	= this.organisationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organisation modifiedOrg			= modfiedList.get(0);
		assertEquals(MODIFIED + this.FULL_NAME_TEXT, modifiedOrg.getFullName().getDefaultValue());
		assertEquals(MODIFIED + this.SHORT_NAME_TEXT, modifiedOrg.getShortName().getDefaultValue());
		assertEquals(MODIFIED + this.URL, modifiedOrg.getOrgLink());

		this.organisationDAO.deleteOrganisation(modifiedOrg.getId());

	}


	@Test
	public void testDeleteOrganisation() {
		this.organisationDAO.createOrganisation(this.URL, this.textDAO.createText(this.FULL_NAME_TEXT), this.textDAO.createText(this.SHORT_NAME_TEXT));

		final List<Organisation> list		= this.organisationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organisation org				= list.get(0);
		assertNotNull(org.getId());

		final Long oldFullNameId		= org.getFullName().getId();
		final Long oldShortNameId		= org.getShortName().getId();

		this.organisationDAO.deleteOrganisation(org.getId());

		checkTextDeleted(oldFullNameId);
		checkTextDeleted(oldShortNameId);

		final List<Organisation> deletedList	= this.organisationDAO.listOrganizations();
		assertEquals(0, deletedList.size());

	}

	private void checkTextDeleted(final Long textId) {
		final Text deletedText	= this.textDAO.getTextById(textId);
		assertNull(deletedText);
	}

}
