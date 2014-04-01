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
import org.ocha.hdx.persistence.entity.curateddata.Organization;
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
public class OrganizationDAOImplTest {

	final String SHORT_NAME_TEXT	= "Short Name";
	final String FULL_NAME_TEXT		= "Full Name";
	final String URL				= "URL";

	@Autowired
	private OrganizationDAO organizationDAO;

	@Autowired
	private TextDAO textDAO;

	@Test
	public void testListOrganizations(){
		final int LIST_SIZE		= 3;


		final List<Organization> emptyList	= organizationDAO.listOrganizations();
		assertEquals(0, emptyList.size());

		for (int i=0; i<LIST_SIZE; i++ ) {
			final Text shortName	= textDAO.createText(SHORT_NAME_TEXT + i);

			final Text fullName		= textDAO.createText(FULL_NAME_TEXT + i);

			organizationDAO.createOrganization(URL + i, fullName, shortName);

		}

		final List<Organization> fullList	= organizationDAO.listOrganizations();
		assertEquals( LIST_SIZE, fullList.size());
		for (int i = 0; i < fullList.size(); i++) {
			final Organization tempOrg = fullList.get(i);

			assertEquals(FULL_NAME_TEXT+i, tempOrg.getFullName().getDefaultValue() );
			assertEquals(SHORT_NAME_TEXT+i, tempOrg.getShortName().getDefaultValue());
			assertEquals(URL+i, tempOrg.getOrgLink());
		}

		for (int i = 0; i < fullList.size(); i++) {
			final Organization tempOrg = fullList.get(i);

			organizationDAO.deleteOrganization(tempOrg.getId());
		}

		final List<Organization> deletedList	= organizationDAO.listOrganizations();
		assertEquals(0, deletedList.size());

	}

	@Test
	public void testCreateOrganization() {
		organizationDAO.createOrganization(URL, textDAO.createText(FULL_NAME_TEXT), textDAO.createText(SHORT_NAME_TEXT));

		final List<Organization> list	= organizationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organization org				= list.get(0);
		assertEquals(FULL_NAME_TEXT, org.getFullName().getDefaultValue());
		assertEquals(SHORT_NAME_TEXT, org.getShortName().getDefaultValue());
		assertEquals(URL, org.getOrgLink());

		organizationDAO.deleteOrganization(org.getId());

	}

	@Test
	public void testUpdateOrganization() {
		final String MODIFIED	= "modified";
		organizationDAO.createOrganization(URL, textDAO.createText(FULL_NAME_TEXT), textDAO.createText(SHORT_NAME_TEXT));

		final List<Organization> list	= organizationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organization org				= list.get(0);
		assertEquals(FULL_NAME_TEXT, org.getFullName().getDefaultValue());
		assertEquals(SHORT_NAME_TEXT, org.getShortName().getDefaultValue());
		assertEquals(URL, org.getOrgLink());

		final Long oldFullNameId		= org.getFullName().getId();
		final Long oldShortNameId		= org.getShortName().getId();


		organizationDAO.updateOrganization(org.getId(), MODIFIED
				+ URL,
				textDAO.createText(MODIFIED + FULL_NAME_TEXT),
				textDAO.createText(MODIFIED + SHORT_NAME_TEXT));

		checkTextDeleted(oldFullNameId);
		checkTextDeleted(oldShortNameId);


		final List<Organization> modfiedList	= organizationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organization modifiedOrg			= modfiedList.get(0);
		assertEquals(MODIFIED + FULL_NAME_TEXT, modifiedOrg.getFullName().getDefaultValue());
		assertEquals(MODIFIED + SHORT_NAME_TEXT, modifiedOrg.getShortName().getDefaultValue());
		assertEquals(MODIFIED + URL, modifiedOrg.getOrgLink());

		organizationDAO.deleteOrganization(modifiedOrg.getId());

	}

	@Test
	public void testUpdate2Organization() {
		final String MODIFIED	= "modified";
		organizationDAO.createOrganization(URL, textDAO.createText(FULL_NAME_TEXT), textDAO.createText(SHORT_NAME_TEXT));

		final List<Organization> list	= organizationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organization org				= list.get(0);
		assertEquals(FULL_NAME_TEXT, org.getFullName().getDefaultValue());
		assertEquals(SHORT_NAME_TEXT, org.getShortName().getDefaultValue());
		assertEquals(URL, org.getOrgLink());

		organizationDAO.updateOrganization(org.getId(), MODIFIED
				+ URL,
				MODIFIED + FULL_NAME_TEXT,
				MODIFIED + SHORT_NAME_TEXT);

		final List<Organization> modifiedList	= organizationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organization modifiedOrg			= modifiedList.get(0);
		assertEquals(MODIFIED + FULL_NAME_TEXT, modifiedOrg.getFullName().getDefaultValue());
		assertEquals(MODIFIED + SHORT_NAME_TEXT, modifiedOrg.getShortName().getDefaultValue());
		assertEquals(MODIFIED + URL, modifiedOrg.getOrgLink());

		organizationDAO.deleteOrganization(modifiedOrg.getId());
	}


	@Test
	public void testDeleteOrganization() {
		organizationDAO.createOrganization(URL, textDAO.createText(FULL_NAME_TEXT), textDAO.createText(SHORT_NAME_TEXT));

		final List<Organization> list		= organizationDAO.listOrganizations();
		assertEquals(1, list.size());

		final Organization org				= list.get(0);
		assertNotNull(org.getId());

		final Long oldFullNameId		= org.getFullName().getId();
		final Long oldShortNameId		= org.getShortName().getId();

		organizationDAO.deleteOrganization(org.getId());

		checkTextDeleted(oldFullNameId);
		checkTextDeleted(oldShortNameId);

		final List<Organization> deletedList	= organizationDAO.listOrganizations();
		assertEquals(0, deletedList.size());

	}

	private void checkTextDeleted(final Long textId) {
		final Text deletedText	= textDAO.getTextById(textId);
		assertNull(deletedText);
	}

}
