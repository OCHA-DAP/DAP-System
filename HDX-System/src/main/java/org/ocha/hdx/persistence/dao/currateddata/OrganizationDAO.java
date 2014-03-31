/**
 *
 */
package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Organization;
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 * @author alexandru-m-g
 *
 */
public interface OrganizationDAO {
	public List<Organization> listOrganizations();

	public void createOrganization(String orgLink, Text fullName, Text shortName);

	public Organization getOrganizationById(long id);

	public void deleteOrganization(long id);

	/**
	 * This function will remove the old text objects
	 * @param id
	 * @param orgLink
	 * @param fullName
	 * @param shortName
	 */
	public void updateOrganization(long id, String orgLink, Text fullName, Text shortName);

	/**
	 * This method updates the Text objects.
	 * @param id
	 * @param orgLink
	 * @param newFullName
	 * @param newShortName
	 */
	public void updateOrganization(long id, String orgLink, String newFullName, String newShortName);
}
