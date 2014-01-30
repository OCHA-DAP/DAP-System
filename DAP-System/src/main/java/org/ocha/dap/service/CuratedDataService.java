package org.ocha.dap.service;

import java.util.Date;
import java.util.List;

import org.ocha.dap.importer.PreparedIndicator;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.IndicatorValue;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.ocha.dap.persistence.entity.dictionary.SourceDictionary;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.DataTable;

public interface CuratedDataService {

	/*
	 * Entity types
	 */
	public List<EntityType> listEntityTypes();

	public void addEntityType(final String code, final String name);

	public void deleteEntityType(final long entityTypeId);

	public void updateEntityType(final long entityTypeId, final String newName);

	/*
	 * Entities
	 */
	public List<Entity> listEntities();

	public void addEntity(final String code, final String defaultName, final String entityTypeCode);

	public Entity getEntityByCodeAndType(final String code, final String type);

	public void deleteEntity(final long entityId);

	public void updateEntity(final long entityId, String newName);

	/*
	 * Indicator types
	 */
	public List<IndicatorType> listIndicatorTypes();

	public void addIndicatorType(final String code, final String defaultName, final String unit, final String valueType);

	public IndicatorType getIndicatorTypeByCode(final String code);

	public void deleteIndicatorType(final long indicatorTypeId);

	public void updateIndicatorType(final long indicatorTypeId, final String newName, final String newUnit, final String newValueType);

	/*
	 * Sources
	 */
	public List<Source> listSources();

	public void addSource(final String code, final String name, final String link);

	public Source getSourceByCode(final String code);

	public void deleteSource(final long sourceId);

	public void updateSource(final long sourceId, final String newName, final String newLink);
	
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
	public void addIndicator(final String sourceCode, final long entityId, final String indicatorTypeCode, final Date start, final Date end, final Periodicity periodicity, final IndicatorValue value,
			final String initialValue);

	public void deleteIndicator(final long indicatorId);

	public void addIndicator(final PreparedIndicator preparedIndicator, ImportFromCKAN importFromCKAN);

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
	 * Region dictionaries
	 */
	public List<RegionDictionary> listRegionDictionaries();

	public void addRegionDictionary(final String unnormalizedName, final String importer, final long entityId);

	public void deleteRegionDictionary(RegionDictionary regionDictionary);

	/*
	 * Source dictionaries
	 */
	public List<SourceDictionary> listSourceDictionaries();

	public void addSourceDictionary(final String unnormalizedName, final String importer, final long sourceId);

	/*
	 * Indicator type dictionaries
	 */
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries();

	public void addIndicatorTypeDictionary(final String unnormalizedName, final String importer, final long indicatorType);

	public void deleteIndicatorTypeDictionary(IndicatorTypeDictionary indicatorTypeDictionary);

}
