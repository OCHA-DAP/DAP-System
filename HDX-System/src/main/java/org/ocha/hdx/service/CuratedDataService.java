package org.ocha.hdx.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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
import org.ocha.hdx.persistence.entity.curateddata.IndicatorTypeCount;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Organization;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

public interface CuratedDataService {

	/*
	 * Entity types
	 */
	public List<EntityType> listEntityTypes();

	public EntityType createEntityType(final String code, final String name);

	public EntityType getEntityType(final long id);

	public EntityType getEntityTypeByCode(final String code);

	public void deleteEntityType(final long entityTypeId);

	public void updateEntityType(final long entityTypeId, final String newName);

	/*
	 * Entities
	 */
	public List<Entity> listEntities();

	public Entity createEntity(final String code, final String defaultName, final String entityTypeCode, final Long parentId);

	public void createEntitiesFromCSVFile(File csvFile) throws IOException, Exception;

	public Entity getEntity(final long id);

	public Entity getEntityByCodeAndType(final String code, final String type);

	public void deleteEntity(final long entityId);

	public void updateEntity(final long entityId, final String newName, final Long parentId);

	/*
	 * Indicator types
	 */
	public List<IndicatorType> listIndicatorTypes();

	public List<IndicatorTypeCount> listIndicatorTypeCounts();

	public void createIndicatorType(final String code, final String defaultName, final long unitId, final String valueType);

	public IndicatorType getIndicatorType(final long id);

	public IndicatorType getIndicatorTypeByCode(final String code);

	public void deleteIndicatorType(final long indicatorTypeId);

	public void updateIndicatorType(final long indicatorTypeId, final String newCode, final String newName, final long newUnit, final String newValueType);

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

	public Map<Long, Long> countIndicatorsByImport();

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

	// public void createIndicator(final PreparedIndicator preparedIndicator, ImportFromCKAN importFromCKAN);

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

	public Map<String, Timestamp> getMinMaxDatesForDataSeries(DataSerie dataSeries);

	public List<DataSerieMetadata> getMetadataForIndicatorTypeCode(String indicatorTypeCode);

	public void updateMetadataForIndicatorTypeAndSource(MetadataName entryKey, String data, String languageCode, String indicatorTypeCode, String sourceCode);

	public void deleteMetadata(Long id);

	/*
	 * Validation notes
	 */
	public void updateValidationNotesForIndicatorTypeAndSource(String validationNotes, String indicatorTypeCode, String sourceCode);

	/*
	 * Region dictionaries
	 */
	public List<RegionDictionary> listRegionDictionaries();

	public List<RegionDictionary> listRegionDictionaries(final long configId);

	public void createRegionDictionary(final long configId, final long entityId, final String unnormalizedName);

	public void deleteRegionDictionary(long id);

	/*
	 * Source dictionaries
	 */
	public List<SourceDictionary> listSourceDictionaries();

	public List<SourceDictionary> listSourceDictionaries(final long configId);

	public void createSourceDictionary(final long configId, final long sourceId, final String unnormalizedName);

	public void deleteSourceDictionary(long id);

	/*
	 * Indicator type dictionaries
	 */
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries();

	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries(final long configId);

	public void createIndicatorTypeDictionary(final long configId, final long indicatorTypeId, final String unnormalizedName);

	public void createIndicator(Indicator indicator, ImportFromCKAN importFromCKAN);

	public boolean indicatorExists(final PreparedIndicator preparedIndicator);

	public void deleteIndicatorTypeDictionary(long id);

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
	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode, final String[] indicatorsList);

	// Country indicators
	public Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String languageCode, final List<DataSerie> dataSeries);

	// Country indicators
	public Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String languageCode, final Periodicity periodicity,
			final List<DataSerie> dataSeries);

	/* Indicator reports */

	// Indicator overview
	public Object[] getIndicatorTypeOverview(String indicatorTypeCode, String sourceCode, String language);

}
