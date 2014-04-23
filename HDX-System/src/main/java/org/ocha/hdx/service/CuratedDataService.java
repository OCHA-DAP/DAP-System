package org.ocha.hdx.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Organization;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

public interface CuratedDataService {

	/*
	 * Entity types
	 */
	public List<EntityType> listEntityTypes();

	public void createEntityType(final String code, final String name);

	public EntityType getEntityType(final long id);

	public void deleteEntityType(final long entityTypeId);

	public void updateEntityType(final long entityTypeId, final String newName);

	/*
	 * Entities
	 */
	public List<Entity> listEntities();

	public void createEntity(final String code, final String defaultName, final String entityTypeCode);

	public Entity getEntity(final long id);

	public Entity getEntityByCodeAndType(final String code, final String type);

	public void deleteEntity(final long entityId);

	public void updateEntity(final long entityId, String newName);

	/*
	 * Indicator types
	 */
	public List<IndicatorType> listIndicatorTypes();

	public void createIndicatorType(final String code, final String defaultName, final long unitId, final String valueType);

	public IndicatorType getIndicatorType(final long id);

	public IndicatorType getIndicatorTypeByCode(final String code);

	public void deleteIndicatorType(final long indicatorTypeId);

	public void updateIndicatorType(final long indicatorTypeId, final String newName, final long newUnit, final String newValueType);

	/*
	 * Organizations
	 */
	public List<Organization> listOrganizations();

	public void createOrganization(final String shortName, final String fullName, final String link);

	public Organization getOrganization(final Long id);

	public void deleteOrganization(final long organizationId);

	public void updateOrganization(long organizationId, final String newShortName, final String newFullName, final String newLink);

	/*
	 * Sources
	 */
	public List<Source> listSources();

	public List<Source> listSourcesForIndicatorType(final String indicatorTypeCode);

	public void createSource(final String code, final String defaultValue, final String link, final Long organization);

	public Source getSource(final Long id);

	public Source getSourceByCode(final String code);

	public void deleteSource(final long sourceId);

	public void updateSource(final long sourceId, final String newName, final String newLink, final Long newOrganization);

	/*
	 * Imports from CKAN
	 */
	public List<ImportFromCKAN> listImportsFromCKAN();

	public void deleteImportFromCKAN(final long id);

	/*
	 * Indicators
	 */

	/**
	 * Add an indicator for the provided parameters
	 * 
	 * The importFromCKAN param is not provided. This will be added to the default "dummy" import
	 */
	public void createIndicator(final String sourceCode, final long entityId, final String indicatorTypeCode, final Date start, final Date end, final Periodicity periodicity,
			final IndicatorValue value, final String initialValue, final String sourceLink);

	public void deleteIndicator(final long indicatorId);

	public void createIndicator(final PreparedIndicator preparedIndicator, ImportFromCKAN importFromCKAN);

	public List<Indicator> listLastIndicators(final int limit);

	/**
	 * returns a 2D Datatable with Entities as columns, and periods as rows. Something like Year Germany Russia France 2011 2012 2013
	 * 
	 * @param countryCodes
	 *            optional. Return all the known entities if null
	 * @throws TypeMismatchException
	 *             If some data doest not match the expect type of the column
	 */
	public DataTable listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, List<String> countryCodes)
			throws TypeMismatchException;

	/**
	 * returns a 2D Datatable with Sources as columns, and periods as rows. Something like Year WB UN ACLED 2011 2012 2013
	 * 
	 * @throws TypeMismatchException
	 *             If some data doest not match the expect type of the column
	 */
	public DataTable listIndicatorsByPeriodicityAndEntityAndIndicatorType(final Periodicity periodicity, final String entityType, final String entityCode, final String indicatorTypeCode)
			throws TypeMismatchException;

	/**
	 * returns a 1D Datatable with entities as rows Country indicatorType Name Germany Russia France
	 * 
	 * @throws TypeMismatchException
	 *             If some data doest not match the expect type of the column
	 */
	public DataTable listIndicatorsByYearAndSourceAndIndicatorType(final int year, final String sourceCode, final String indicatorTypeCode) throws TypeMismatchException;

	public DataTable listIndicatorsByYearAndSourcesAndIndicatorTypes(final int year, final String sourceCode1, final String indicatorTypeCode1, final String sourceCode2,
			final String indicatorTypeCode2, final String sourceCode3, final String indicatorTypeCode3, final List<String> countryCodes) throws TypeMismatchException;

	public List<Source> getExistingSourcesForYearAndIndicatorType(final int year, final String indicatorTypeCode);

	public List<Source> getExistingSourcesForIndicatorType(final String indicatorTypeCode);

	/*
	 * public List<Indicator> listIndicatorsForCountryOverview(final String countryCode, final String languageCode);
	 */

	/*
	 * Metadata
	 */
	public List<DataSerieMetadata> getMetadataForDataSerie(final DataSerie dataSerie);

	public void updateMetadataForIndicatorTypeAndSource(String which, String data, String languageCode, String indicatorTypeCode, String sourceCode);

	public void deleteMetadata(Long id);

	/*
	 * Validation notes
	 */
	public void updateValidationNotesForIndicatorTypeAndSource(String validationNotes, String indicatorTypeCode, String sourceCode);

	/*
	 * Region dictionaries
	 */
	public List<RegionDictionary> listRegionDictionaries();

	public void createRegionDictionary(final String unnormalizedName, final String importer, final long entityId);

	public void deleteRegionDictionary(RegionDictionary regionDictionary);

	/*
	 * Source dictionaries
	 */
	public List<SourceDictionary> listSourceDictionaries();

	public void createSourceDictionary(final String unnormalizedName, final String importer, final long sourceId);

	public void deleteSourceDictionary(String unnormalizedName, String importer);

	/*
	 * Indicator type dictionaries
	 */
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries();

	public void createIndicatorTypeDictionary(final String unnormalizedName, final String importer, final long indicatorType);

	public void createIndicator(Indicator indicator, ImportFromCKAN importFromCKAN);

	public void deleteIndicatorTypeDictionary(IndicatorTypeDictionary indicatorTypeDictionary);

	/**
	 * Units
	 */
	public List<Unit> listUnits();

	public void createUnit(String code, String name);

	public void deleteUnit(Long id);

	public void updateUnit(Long id, String name);

	public Unit getUnit(Long id);

	/*
	 * Reports
	 */

	/* Country reports */

	// Country overview
	public List<Object[]> listIndicatorsForCountryOverview(String countryCode, String languageCode);

	public Map<Integer, List<Object[]>> listIndicatorsForCountryCrisisHistory(String countryCode, int fromYear, int toYear, String languageCode);

	public Map<Integer, List<Object[]>> list5YearsIndicatorsForCountry(String countryCode, int fromYear, int toYear, String languageCode);

	// Country socio-economic
	public Map<Integer, List<Object[]>> listIndicatorsForCountrySocioEconomic(String countryCode, int fromYear, int toYear, String languageCode);

	// Country vulnerability
	public Map<Integer, List<Object[]>> listIndicatorsForCountryVulnerability(String countryCode, int fromYear, int toYear, String languageCode);

	// Country capacity
	public Map<Integer, List<Object[]>> listIndicatorsForCountryCapacity(String countryCode, int fromYear, int toYear, String languageCode);

	// Country other
	public Map<Integer, List<Object[]>> listIndicatorsForCountryOther(String countryCode, int fromYear, int toYear, String languageCode);

	/* Indicator reports */

	// Indicator overview
	public Object[] getIndicatorTypeOverview(String indicatorTypeCode, String sourceCode, String language);

}
