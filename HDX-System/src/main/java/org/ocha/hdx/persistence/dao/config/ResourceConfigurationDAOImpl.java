/**
 *
 */
package org.ocha.hdx.persistence.dao.config;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfigEntry;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 *
 */
public class ResourceConfigurationDAOImpl implements ResourceConfigurationDao {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#listSources
	 * ()
	 */
	@Override
	public List<ResourceConfiguration> listResourceConfigurations() {
		final TypedQuery<ResourceConfiguration> query = this.em.createQuery(
				"SELECT rc FROM ResourceConfiguration rc ORDER BY rc.id",
				ResourceConfiguration.class);
		return query.getResultList();
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#
	 * getResourceConfigurationById(long)
	 */
	@Override
	public ResourceConfiguration getResourceConfigurationById(final long id) {
		return this.em.find(ResourceConfiguration.class, id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#
	 * deleteResourceConfiguration(long)
	 */
	@Override
	@Transactional
	public void deleteResourceConfiguration(final long id) {
		final ResourceConfiguration resourceConfiguration = this.getResourceConfigurationById(id);
		this.em.remove(resourceConfiguration);

	}


	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#createResourceConfig(java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	@Transactional
	public ResourceConfiguration createResourceConfig(final String name,
			final Set<ResourceConfigEntry> generalConfigSet,
			final Set<IndicatorResourceConfigEntry> indicatorConfigSet) {

		final ResourceConfiguration resourceConfiguration	 = new ResourceConfiguration(name, generalConfigSet, indicatorConfigSet);
		if ( generalConfigSet != null ) {
			for (final ResourceConfigEntry entry : generalConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

		if ( indicatorConfigSet != null ) {
			for (final IndicatorResourceConfigEntry entry : indicatorConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

		this.em.persist(resourceConfiguration);

		return resourceConfiguration;
	}


	/* (non-Javadoc)
	 * @see org.ocha.hdx.persistence.dao.config.ResourceConfigurationDao#updateResourceConfiguration(java.lang.String, java.util.List, java.util.List)
	 */
	@Override
	@Transactional
	public void updateResourceConfiguration(final long id, final String name,
			final Set<ResourceConfigEntry> generalConfigSet,
			final Set<IndicatorResourceConfigEntry> indicatorConfigSet) {

		final ResourceConfiguration resourceConfiguration	= this.getResourceConfigurationById(id);
		if ( name != null && name.length() > 0 ) {
			resourceConfiguration.setName(name);
		}
		if ( generalConfigSet != null ) {
			resourceConfiguration.getGeneralConfigEntries().clear();
			resourceConfiguration.getGeneralConfigEntries().addAll(generalConfigSet);
			for (final ResourceConfigEntry entry : generalConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

		if ( indicatorConfigSet != null ) {
			resourceConfiguration.getIndicatorConfigEntries().clear();
			resourceConfiguration.getIndicatorConfigEntries().addAll(indicatorConfigSet);
			for (final IndicatorResourceConfigEntry entry : indicatorConfigSet) {
				entry.setParentConfiguration(resourceConfiguration);
			}
		}

	}



}
