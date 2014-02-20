/**
 *
 */
package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.Organisation;
import org.ocha.hdx.persistence.entity.i18n.Text;

/**
 * @author alexandru-m-g
 *
 */
public interface OrganisationDAO {
	public List<Organisation> listOrganizations();

	public void createOrganisation(String orgLink, Text fullName, Text shortName);

	public Organisation getOrganisationById(long id);

	public void deleteOrganisation(long id);

	/**
	 * This function will remove the old text objects
	 * @param id
	 * @param orgLink
	 * @param fullName
	 * @param shortName
	 */
	public void updateOrganisation(long id, String orgLink, Text fullName, Text shortName);
}
