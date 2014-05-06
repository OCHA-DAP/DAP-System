/**
 *
 */
package org.ocha.hdx.persistence.dao.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 * 
 */
public class ResourceConfigurationDAOImpl implements ResourceConfigurationDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#listSources ()
	 */
	@Override
	public List<ResourceConfiguration> listResourceConfigurations() {
		final TypedQuery<ResourceConfiguration> query = em.createQuery("SELECT rc FROM ResourceConfiguration rc ORDER BY rc.id", ResourceConfiguration.class);

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao# getResourceConfigurationById(long)
	 */
	@Override
	public ResourceConfiguration getResourceConfigurationById(final long id) {
		return em.find(ResourceConfiguration.class, id);
	}

	@Override
	public ResourceConfigEntry getResourceConfigEntryById(final long id) {
		return em.find(ResourceConfigEntry.class, id);
	}

	@Override
	public IndicatorResourceConfigEntry getIndicatorResourceConfigEntryById(final long id) {
		return em.find(IndicatorResourceConfigEntry.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao# deleteResourceConfiguration(long)
	 */
	@Override
	@Transactional
	public void deleteResourceConfiguration(final long id) {
		final ResourceConfiguration resourceConfiguration = getResourceConfigurationById(id);
		em.remove(resourceConfiguration);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#createResourceConfiguration(java.lang.String, java.util.List,
	 * java.util.List)
	 */
	@Override
	@Transactional
	public ResourceConfiguration createResourceConfiguration(final String name, final Set<ResourceConfigEntry> generalConfigSet, final Set<IndicatorResourceConfigEntry> indicatorConfigSet) {

		final ResourceConfiguration resourceConfiguration = new ResourceConfiguration(name, generalConfigSet, indicatorConfigSet);
		if (generalConfigSet != null) {
			for (final ResourceConfigEntry entry : generalConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}
		else {
			// Should be enums
			final Set<ResourceConfigEntry> configSet = new HashSet<ResourceConfigEntry>();
			resourceConfiguration.setGeneralConfigEntries(configSet);

			final ResourceConfigEntry min = new ResourceConfigEntry("Minimum number of columns", "0");
			min.setParentConfiguration(resourceConfiguration);
			configSet.add(min);
			final ResourceConfigEntry allow = new ResourceConfigEntry("Allowed indicator type codes", "");
			allow.setParentConfiguration(resourceConfiguration);
			configSet.add(allow);
			
		}

		if (indicatorConfigSet != null) {
			for (final IndicatorResourceConfigEntry entry : indicatorConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

		em.persist(resourceConfiguration);

		return resourceConfiguration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#updateResourceConfiguration(java.lang.String, java.util.List,
	 * java.util.List)
	 */
	@Override
	@Transactional
	public void updateResourceConfiguration(final long id, final String name, final Set<ResourceConfigEntry> generalConfigSet, final Set<IndicatorResourceConfigEntry> indicatorConfigSet) {

		final ResourceConfiguration resourceConfiguration = getResourceConfigurationById(id);
		if ((name != null) && (name.length() > 0)) {
			resourceConfiguration.setName(name);
		}
		if (generalConfigSet != null) {
			resourceConfiguration.getGeneralConfigEntries().clear();
			resourceConfiguration.getGeneralConfigEntries().addAll(generalConfigSet);
			for (final ResourceConfigEntry entry : generalConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

		if (indicatorConfigSet != null) {
			resourceConfiguration.getIndicatorConfigEntries().clear();
			resourceConfiguration.getIndicatorConfigEntries().addAll(indicatorConfigSet);
			for (final IndicatorResourceConfigEntry entry : indicatorConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

	}

	@Override
	@Transactional
	// if GC configuration exists it will be replaced.
	public void addGeneralConfiguration(final long id, final String key, final String value) {
		final ResourceConfiguration rc = getResourceConfigurationById(id);
		if (rc.getGeneralConfigEntries() == null) {
			rc.setGeneralConfigEntries(new HashSet<ResourceConfigEntry>());
		}
		final ResourceConfigEntry rce = new ResourceConfigEntry(key, value);
		rce.setParentConfiguration(rc);
		em.persist(rce);
		rc.getGeneralConfigEntries().add(rce);
	}

	@Override
	@Transactional
	public void deleteGeneralConfiguration(final long rcID, final long id) {
		final ResourceConfiguration rc = getResourceConfigurationById(rcID);
		final ResourceConfigEntry rce = getResourceConfigEntryById(id);
		if (rc.getGeneralConfigEntries().contains(rce)) {
			rc.getGeneralConfigEntries().remove(rce);
		}
		em.remove(rce);
	}

	@Override
	@Transactional
	public void updateGeneralConfiguration(final long id, final String key, final String value) {
		final ResourceConfigEntry rce = getResourceConfigEntryById(id);
		rce.setEntryKey(key);
		rce.setEntryValue(value);
	}

	@Override
	@Transactional
	public void addIndicatorConfiguration(final long rcID, final long indTypeID, final long srcID, final String key, final String value) {
		final ResourceConfiguration rc = getResourceConfigurationById(rcID);
		final Source source = sourceDAO.getSourceById(srcID);
		final IndicatorType indType = indicatorTypeDAO.getIndicatorTypeById(indTypeID);
		final IndicatorResourceConfigEntry irce = new IndicatorResourceConfigEntry(key, value, source, indType);

		if (rc.getIndicatorConfigEntries() == null) {
			rc.setIndicatorConfigEntries(new HashSet<IndicatorResourceConfigEntry>());
		}

		irce.setParentConfiguration(rc);
		em.persist(irce);
		rc.getIndicatorConfigEntries().add(irce);
	}

	@Override
	@Transactional
	public void deleteIndicatorConfiguration(final long rcID, final long id) {
		final ResourceConfiguration rc = getResourceConfigurationById(rcID);
		final IndicatorResourceConfigEntry irce = getIndicatorResourceConfigEntryById(id);
		if (rc.getIndicatorConfigEntries().contains(irce)) {
			rc.getIndicatorConfigEntries().remove(irce);
		}
		em.remove(irce);
	}

	@Override
	@Transactional
	public void updateIndicatorConfiguration(final long id, final long indTypeID, final long srcID, final String key, final String value) {
		final IndicatorResourceConfigEntry irce = getIndicatorResourceConfigEntryById(id);
		final Source source = sourceDAO.getSourceById(srcID);
		final IndicatorType indType = indicatorTypeDAO.getIndicatorTypeById(indTypeID);

		irce.setSource(source);
		irce.setIndicatorType(indType);
		irce.setEntryKey(key);
		irce.setEntryValue(value);
	}

}
