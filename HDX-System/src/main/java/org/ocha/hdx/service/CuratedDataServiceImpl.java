package org.ocha.hdx.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.TimeRange;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.model.api.CellDescriptor;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.ckan.DataSerieToCuratedDatasetDAO;
import org.ocha.hdx.persistence.dao.config.ResourceConfigurationDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAOImpl.ImportValueStatus;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.OrganizationDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.dictionary.IndicatorTypeDictionaryDAO;
import org.ocha.hdx.persistence.dao.dictionary.RegionDictionaryDAO;
import org.ocha.hdx.persistence.dao.dictionary.SourceDictionaryDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.configs.ResourceConfiguration;
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
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.i18n.Translation;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import au.com.bytecode.opencsv.CSVReader;

import com.google.visualization.datasource.base.TypeMismatchException;
import com.google.visualization.datasource.datatable.ColumnDescription;
import com.google.visualization.datasource.datatable.DataTable;
import com.google.visualization.datasource.datatable.TableRow;
import com.google.visualization.datasource.datatable.value.NumberValue;
import com.google.visualization.datasource.datatable.value.ValueType;

public class CuratedDataServiceImpl implements CuratedDataService {

	private static Logger logger = LoggerFactory.getLogger(CuratedDataServiceImpl.class);

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private OrganizationDAO organizationDAO;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private RegionDictionaryDAO regionDictionaryDAO;

	@Autowired
	private SourceDictionaryDAO sourceDictionaryDAO;

	@Autowired
	private IndicatorTypeDictionaryDAO indicatorTypeDictionaryDAO;

	@Autowired
	private UnitDAO unitDAO;

	@Autowired
	private DataSerieMetadataDAO dataSerieMetadataDAO;

	@Autowired
	private ResourceConfigurationDAO resourceConfigurationDAO;

	@Autowired
	private DataSerieToCuratedDatasetDAO dataSerieToCuratedDatasetDAO;

	/*
	 * Entity types
	 */
	@Override
	public List<EntityType> listEntityTypes() {
		return this.entityTypeDAO.listEntityTypes();
	}

	@Override
	@Transactional
	public EntityType createEntityType(final String code, final String name) {
		final Text text = this.textDAO.createText(name);
		return this.entityTypeDAO.createEntityType(code, text);
	}

	@Override
	public EntityType getEntityType(final long id) {
		return this.entityTypeDAO.getEntityTypeById(id);
	}

	@Override
	public EntityType getEntityTypeByCode(final String code) {
		return this.entityTypeDAO.getEntityTypeByCode(code);
	}

	@Override
	@Transactional
	public void updateEntityType(final long entityTypeId, final String newName) {
		this.entityTypeDAO.updateEntityType(entityTypeId, newName);
	}

	@Override
	@Transactional
	public void deleteEntityType(final long entityTypeId) {
		this.entityTypeDAO.deleteEntityType(entityTypeId);
	}

	/*
	 * Entities
	 */
	@Override
	public List<Entity> listEntities() {
		return this.entityDAO.listEntities();
	}

	@Override
	@Transactional
	public Entity createEntity(final String code, final String defaultName, final String entityTypeCode, final Long parentId) {
		final EntityType entityType = this.entityTypeDAO.getEntityTypeByCode(entityTypeCode);
		Entity result = null;
		final Text text = this.textDAO.createText(defaultName);
		if (null == parentId) {
			result = this.entityDAO.createEntity(code, text, entityType);
		} else {
			result = this.entityDAO.createEntity(code, text, entityType, parentId);
		}
		return result;
	}

	@Override
	public void createEntitiesFromCSVFile(final File csvFile) throws IOException, Exception {
		CSVReader csvReader = null;
		List<String[]> entities = null;

		csvReader = new CSVReader(new FileReader(csvFile), '#');
		entities = csvReader.readAll();
		csvReader.close();

		if (null != entities) {
			for (final String[] entity : entities) {

				// Check if the entity already exists
				Entity exists = null;
				try {
					exists = this.getEntityByCodeAndType(entity[2], entity[0]);
				} catch (final NoResultException e) {
					// Should happen most frequently
				}
				if (null != exists) {
					logger.warn("Entity with type " + entity[0] + " and code " + entity[2] + " already exists ! Skipping.");
					continue;
				}

				// Handle the entity type
				EntityType entityType = null;
				try {
					entityType = this.getEntityTypeByCode(entity[0]);
				} catch (final NoResultException e) {
					// Can happen
				}

				// No existing entity type, so we create a new one
				if (null == entityType) {
					entityType = this.createEntityType(entity[0], entity[1]);
				}

				// Handle the entity parent
				Entity parent = null;
				if ((null != entity[4]) && !"".equals(entity[4]) && (null != entity[5]) && !"".equals(entity[5])) {
					try {
						parent = this.getEntityByCodeAndType(entity[4], entity[5]);
					} catch (final NoResultException e) {
						// Should not happen
						final String msg = "Entity parent with type " + entity[5] + " and code " + entity[4] + " does not exist ! Skipping.";
						logger.warn(msg);
						throw new Exception(msg);
					}
				}
				Long parentId = null;
				if (null != parent) {
					parentId = parent.getId();
				}
				this.createEntity(entity[2], entity[3], entityType.getCode(), parentId);

			}
		}

	}

	@Override
	public Entity getEntity(final long id) {
		return this.entityDAO.getEntityById(id);
	}

	@Override
	public Entity getEntityByCodeAndType(final String code, final String type) {
		return this.entityDAO.getEntityByCodeAndType(code, type);
	}

	@Override
	@Transactional
	public void updateEntity(final long entityId, final String newName, final Long parentId) {
		this.entityDAO.updateEntity(entityId, newName, parentId);
	}

	@Override
	@Transactional
	public void deleteEntity(final long entityId) {
		this.entityDAO.deleteEntity(entityId);
	}

	/*
	 * Indicator types
	 */
	@Override
	public List<IndicatorType> listIndicatorTypes() {
		return this.indicatorTypeDAO.listIndicatorTypes();
	}

	@Override
	public List<IndicatorTypeCount> listIndicatorTypeCounts() {
		return this.indicatorTypeDAO.listIndicatorTypeCounts();
	}

	@Override
	@Transactional
	public void createIndicatorType(final String code, final String defaultName, final long unitId, final String valueType) {
		final Text text = this.textDAO.createText(defaultName);
		final Unit unit = this.unitDAO.getUnitById(unitId);
		this.indicatorTypeDAO.createIndicatorType(code, text, unit, org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType.valueOf(valueType));
	}

	@Override
	public IndicatorType getIndicatorType(final long id) {
		return this.indicatorTypeDAO.getIndicatorTypeById(id);
	}

	@Override
	public IndicatorType getIndicatorTypeByCode(final String code) {
		return this.indicatorTypeDAO.getIndicatorTypeByCode(code);
	}

	@Override
	@Transactional
	public void updateIndicatorType(final long indicatorTypeId, final String newCode, final String newName, final long newUnit, final String newValueType) {
		final Unit unit = this.unitDAO.getUnitById(newUnit);
		this.indicatorTypeDAO.updateIndicatorType(indicatorTypeId, newCode, newName, unit, org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType.valueOf(newValueType));
	}

	@Override
	@Transactional
	public void deleteIndicatorType(final long indicatorTypeId) {
		this.dataSerieMetadataDAO.deleteDataSerieMetadataForIndicatorType(indicatorTypeId);
		this.indicatorTypeDAO.deleteIndicatorType(indicatorTypeId);
	}

	/*
	 * Organizations
	 */
	@Override
	public List<Organization> listOrganizations() {
		return this.organizationDAO.listOrganizations();
	}

	@Override
	@Transactional
	public void createOrganization(final String shortNameDefaultValue, final String fullNameDefaultValue, final String link) {
		final Text shortName = this.textDAO.createText(shortNameDefaultValue);
		final Text fullName = this.textDAO.createText(fullNameDefaultValue);
		this.organizationDAO.createOrganization(link, fullName, shortName);
	}

	@Override
	public Organization getOrganization(final Long id) {
		return this.organizationDAO.getOrganizationById(id);
	}

	@Override
	@Transactional
	public void updateOrganization(final long organizationId, final String newShortName, final String newFullName, final String newLink) {
		this.organizationDAO.updateOrganization(organizationId, newLink, newFullName, newShortName);
	}

	@Override
	@Transactional
	public void deleteOrganization(final long organizationId) {
		this.organizationDAO.deleteOrganization(organizationId);
	}

	/*
	 * Sources
	 */
	@Override
	public List<Source> listSources() {
		return this.sourceDAO.listSources();
	}

	@Override
	public List<Source> listSourcesForIndicatorType(final String indicatorTypeCode) {
		return this.sourceDAO.listSourcesForIndicatorType(indicatorTypeCode);
	}

	@Override
	@Transactional
	public void createSource(final String code, final String defaultValue, final String link, final Long organization) {
		final Text name = this.textDAO.createText(defaultValue);
		final Organization organization_ = this.organizationDAO.getOrganizationById(organization);
		this.sourceDAO.createSource(code, name, link, organization_);
	}

	@Override
	public Source getSource(final Long id) {
		return this.sourceDAO.getSourceById(id);
	}

	@Override
	public Source getSourceByCode(final String code) {
		return this.sourceDAO.getSourceByCode(code);
	}

	@Override
	@Transactional
	public void updateSource(final long sourceId, final String newName, final String newLink, final Long newOrganization) {
		final Organization organization = this.organizationDAO.getOrganizationById(newOrganization);
		this.sourceDAO.updateSource(sourceId, newName, newLink, organization);
	}

	@Override
	@Transactional
	public void deleteSource(final long sourceId) {
		this.sourceDAO.deleteSource(sourceId);
	}

	/*
	 * Imports from CKAN
	 */
	@Override
	public List<ImportFromCKAN> listImportsFromCKAN() {
		return this.importFromCKANDAO.listImportsFromCKAN();
	}

	@Override
	public Map<Long, Long> countIndicatorsByImport() {
		return this.indicatorDAO.countIndicatorsByImport();
	}

	@Override
	@Transactional
	public void deleteImportFromCKAN(final long id) {
		this.indicatorDAO.deleteAllIndicatorsFromImport(id);
		this.importFromCKANDAO.deleteImportFromCKAN(id);
	}

	/*
	 * Indicators
	 */
	@Override
	@Transactional
	public void createIndicator(final String sourceCode, final long entityId, final String indicatorTypeCode, final Date start, final Date end, final Periodicity periodicity,
			final IndicatorValue value, final String initialValue, final String sourceLink) {
		final Source source = this.sourceDAO.getSourceByCode(sourceCode);
		final Entity entity = this.entityDAO.getEntityById(entityId);
		final IndicatorType indicatorType = this.indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode);

		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.getDummyImport();

		this.indicatorDAO.createIndicator(source, entity, indicatorType, start, end, periodicity, value, initialValue, ValidationStatus.SUCCESS, sourceLink, importFromCKAN);

		final DataSerie dataSerie = new DataSerie(indicatorTypeCode, sourceCode);

		this.updateMetadataTimestamp(dataSerie);
		this.updateDataTimestamp(dataSerie);

	}

	@Override
	public ImportValueStatus updateIndicatorIfNecessary(final PreparedIndicator preparedIndicator, final ImportFromCKAN importFromCKAN) {
		return this.indicatorDAO.updateIndicatorIfNecessary(preparedIndicator, importFromCKAN);
	}

	// @Override
	// @Transactional
	// public void createIndicator(final PreparedIndicator preparedIndicator, final ImportFromCKAN importFromCKAN) {
	// final Source source = sourceDAO.getSourceByCode(preparedIndicator.getSourceCode());
	// final Entity entity = entityDAO.getEntityByCodeAndType(preparedIndicator.getEntityCode(), preparedIndicator.getEntityTypeCode());
	// final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(preparedIndicator.getIndicatorTypeCode());
	//
	// indicatorDAO.createIndicator(source, entity, indicatorType, preparedIndicator.getStart(), preparedIndicator.getEnd(), preparedIndicator.getPeriodicity(), preparedIndicator.getValue(),
	// preparedIndicator.getIndicatorImportConfig().getInitialValue(), preparedIndicator.getIndicatorImportConfig().getValidationStatus(), preparedIndicator.getSourceLink(), importFromCKAN);
	//
	// }

	@Override
	@Transactional
	public void createIndicator(final Indicator indicator, final ImportFromCKAN importFromCKAN) {

		this.indicatorDAO.createIndicator(indicator.getSource(), indicator.getEntity(), indicator.getType(), indicator.getStart(), indicator.getEnd(), indicator.getPeriodicity(),
				indicator.getValue(), indicator.getIndicatorImportConfig(), indicator.getSourceLink(), importFromCKAN);

		final DataSerie dataSerie = new DataSerie(indicator.getType().getCode(), indicator.getSource().getCode());
		this.updateMetadataTimestamp(dataSerie);
		this.updateDataTimestamp(dataSerie);

	}

	@Override
	@Transactional
	public void deleteIndicator(final long indicatorId) {
		this.indicatorDAO.deleteIndicator(indicatorId);

	}

	@Override
	public List<Indicator> listLastIndicators(final int limit) {
		return this.indicatorDAO.listLastIndicators(limit);
	}

	@Override
	public DataTable listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes)
			throws TypeMismatchException {

		// must be sorted by start, entity
		final List<Indicator> indicators = this.indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(periodicity, sourceCode, indicatorTypeCode, countryCodes);
		final List<String> years = new ArrayList<String>();
		final List<Entity> entities = new ArrayList<Entity>();
		final Map<CellDescriptor, Indicator> tableContent = new HashMap<CellDescriptor, Indicator>();
		for (final Indicator indicator : indicators) {
			final String year = new TimeRange(indicator.getStart(), indicator.getEnd(), indicator.getPeriodicity()).getTimeRangeAsSimpleString();
			if (!years.contains(year)) {
				logger.debug(String.format("Found year : %s", year));
				years.add(year);
			}

			if (!entities.contains(indicator.getEntity())) {
				logger.debug(String.format("Found entity : %s", indicator.getEntity()));
				entities.add(indicator.getEntity());
			}

			tableContent.put(new CellDescriptor(year, indicator.getEntity().getCode()), indicator);
		}

		final DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("Year", ValueType.TEXT, "Year"));
		final Map<String, TableRow> rows = new HashMap<String, TableRow>();

		for (final String year : years) {
			final TableRow aRow = new TableRow();
			aRow.addCell(year);
			logger.debug(String.format("Adding a row for year : %s", year));
			rows.put(year, aRow);
		}

		Collections.sort(entities);
		for (final Entity entity : entities) {
			// FIXME we might want to set here a translation instead of the default value
			dataTable.addColumn(new ColumnDescription(entity.getCode(), ValueType.NUMBER, entity.getName().getDefaultValue()));
			for (final String year : years) {
				final Indicator cellIndicator = tableContent.get(new CellDescriptor(year, entity.getCode()));
				if (cellIndicator == null) {
					rows.get(year).addCell(NumberValue.getNullValue());
				} else {
					rows.get(year).addCell(cellIndicator.getValue().getNumberValue());
				}
			}
		}

		final List<String> sortedKeys = new ArrayList<String>(rows.keySet());
		Collections.sort(sortedKeys);
		for (final String key : sortedKeys) {
			dataTable.addRow(rows.get(key));
		}
		return dataTable;
	}

	@Override
	public DataTable listIndicatorsByPeriodicityAndEntityAndIndicatorType(final Periodicity periodicity, final String entityType, final String entityCode, final String indicatorTypeCode)
			throws TypeMismatchException {

		// FIXME probably has a problem if some data are missing in the Table,
		// see how it is fixed for
		// listIndicatorsByPeriodicityAndSourceAndIndicatorType

		// must be sorted by start, source
		final List<Indicator> indicators = this.indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(periodicity, entityType, entityCode, indicatorTypeCode);

		final DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("Year", ValueType.TEXT, "Year"));

		TimeRange previousTR = null;
		TableRow currentRow = null;
		final List<TableRow> rows = new ArrayList<>();
		for (final Indicator indicator : indicators) {
			final String code = indicator.getSource().getCode();
			if (!dataTable.containsColumn(code)) {
				dataTable.addColumn(new ColumnDescription(indicator.getSource().getCode(), ValueType.NUMBER, indicator.getSource().getName().getDefaultValue()));
			}

		}
		for (final Indicator indicator : indicators) {
			final TimeRange timeRange = new TimeRange(indicator.getStart(), indicator.getEnd(), indicator.getPeriodicity());
			if (timeRange.equals(previousTR)) {
				// assuming we always deal with a numeric indicator type for this kind of output
				currentRow.addCell(indicator.getValue().getNumberValue());
			} else {
				final TableRow aRow = new TableRow();
				aRow.addCell(timeRange.getTimeRangeAsSimpleString());
				currentRow = aRow;
				// assuming we always deal with a numeric indicator type for this kind of output
				aRow.addCell(indicator.getValue().getNumberValue());
				rows.add(aRow);
				previousTR = timeRange;
			}
		}
		dataTable.addRows(rows);
		return dataTable;

	}

	@Override
	public DataTable listIndicatorsByYearAndSourceAndIndicatorType(final int year, final String sourceCode, final String indicatorTypeCode) throws TypeMismatchException {
		// No filter on country for now
		final List<Indicator> indicators = this.indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(year, sourceCode, indicatorTypeCode, null);

		final DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("Entity", ValueType.TEXT, "Entity"));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode, ValueType.NUMBER, indicatorTypeCode));
		for (final Indicator indicator : indicators) {
			final TableRow aRow = new TableRow();
			// FIXME we might want to set here a translation instead of the default value
			aRow.addCell(indicator.getEntity().getName().getDefaultValue());
			// assuming we always deal with a numeric indicator type for this kind of output
			aRow.addCell(indicator.getValue().getNumberValue());
			dataTable.addRow(aRow);
		}
		return dataTable;
	}

	@Override
	public DataTable listIndicatorsByYearAndSourcesAndIndicatorTypes(final int year, final String sourceCode1, final String indicatorTypeCode1, final String sourceCode2,
			final String indicatorTypeCode2, final String sourceCode3, final String indicatorTypeCode3, final List<String> countryCodes) throws TypeMismatchException {
		final DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("Entity", ValueType.TEXT, "Entity"));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode1, ValueType.NUMBER, this.indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode1).getName().getDefaultValue()));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode2, ValueType.NUMBER, this.indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode2).getName().getDefaultValue()));
		// This is a Hack to have the third indicator moved to the 4th
		dataTable.addColumn(new ColumnDescription("d", ValueType.NUMBER, "d"));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode3, ValueType.NUMBER, this.indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode3).getName().getDefaultValue()));

		final Map<String, TableRow> rows = new HashMap<String, TableRow>();
		this.addColumnData(year, rows, sourceCode1, indicatorTypeCode1, countryCodes);
		this.addColumnData(year, rows, sourceCode2, indicatorTypeCode2, countryCodes);
		// This is a Hack to have the third indicator moved to the 4th
		addACellToEveryRow(rows);
		this.addColumnData(year, rows, sourceCode3, indicatorTypeCode3, countryCodes);

		for (final TableRow row : rows.values()) {

			if (row.getCells().size() == 5) {
				dataTable.addRow(row);
			} else {
				logger.debug(String.format("Ignoring incomplete row : %s contains %d cells", row.getCell(0).getValue().toString(), row.getCells().size()));

				// /FIXME remove this
				// dataTable.addRow(row);
			}
		}
		return dataTable;
	}

	/*
	 * @Override public List<Indicator> listIndicatorsForCountryOverview(final String countryCode, final String languageCode) { final List<Indicator> result =
	 * indicatorDAO.listIndicatorsForCountryOverview(countryCode, languageCode);
	 * 
	 * return result; }
	 */

	private Map<String, TableRow> addColumnData(final int year, final Map<String, TableRow> rows, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final List<Indicator> indicators = this.indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(year, sourceCode, indicatorTypeCode, countryCodes);

		for (final Indicator indicator : indicators) {
			if (rows.containsKey(indicator.getEntity().getCode())) {
				// assuming we always deal with a numeric indicator type for this kind of output
				rows.get(indicator.getEntity().getCode()).addCell(indicator.getValue().getNumberValue());
			} else {
				final TableRow aRow = new TableRow();
				// FIXME we might want to set here a translation instead of the default value
				aRow.addCell(indicator.getEntity().getName().getDefaultValue());
				// assuming we always deal with a numeric indicator type for this kind of output
				aRow.addCell(indicator.getValue().getNumberValue());
				rows.put(indicator.getEntity().getCode(), aRow);
			}
		}
		return rows;
	}

	private static Map<String, TableRow> addACellToEveryRow(final Map<String, TableRow> rows) {
		for (final TableRow row : rows.values()) {
			row.addCell(0);
		}
		return rows;
	}

	@Override
	public List<Source> getExistingSourcesForYearAndIndicatorType(final int year, final String indicatorTypeCode) {
		final List<String> sourceCodes = this.indicatorDAO.getExistingSourcesCodesForYearAndIndicatorType(year, indicatorTypeCode);

		final List<Source> sources = new ArrayList<>();
		for (final String code : sourceCodes) {
			sources.add(this.sourceDAO.getSourceByCode(code));
		}
		return sources;
	}

	@Override
	public List<Source> getExistingSourcesForIndicatorType(final String indicatorTypeCode) {
		final List<String> sourceCodes = this.indicatorDAO.getExistingSourcesCodesForIndicatorType(indicatorTypeCode);

		final List<Source> sources = new ArrayList<>();
		for (final String code : sourceCodes) {
			sources.add(this.sourceDAO.getSourceByCode(code));
		}
		return sources;
	}

	/*
	 * Region dictionaries
	 */
	@Override
	public List<RegionDictionary> listRegionDictionaries() {
		return this.regionDictionaryDAO.listRegionDictionaries();
	}

	@Override
	public List<RegionDictionary> listRegionDictionaries(final long configId) {
		return this.regionDictionaryDAO.getRegionDictionariesByResourceConfiguration(this.resourceConfigurationDAO.getResourceConfigurationById(configId));
	}

	@Override
	public void createRegionDictionary(final long configId, final long entityId, final String unnormalizedName) {
		final Entity entity = this.entityDAO.getEntityById(entityId);
		final ResourceConfiguration config = this.resourceConfigurationDAO.getResourceConfigurationById(configId);
		this.regionDictionaryDAO.createRegionDictionary(config, entity, unnormalizedName);
	}

	@Override
	public void deleteRegionDictionary(final long id) {
		this.regionDictionaryDAO.deleteRegionDictionary(id);
	}

	/*
	 * Source dictionaries
	 */
	@Override
	public List<SourceDictionary> listSourceDictionaries() {
		return this.sourceDictionaryDAO.listSourceDictionaries();
	}

	@Override
	public List<SourceDictionary> listSourceDictionaries(final long configId) {
		return this.sourceDictionaryDAO.getSourceDictionariesByResourceConfiguration(this.resourceConfigurationDAO.getResourceConfigurationById(configId));
	}

	@Override
	public void createSourceDictionary(final long configId, final long sourceId, final String unnormalizedName) {
		final Source source = this.sourceDAO.getSourceById(sourceId);
		final ResourceConfiguration config = this.resourceConfigurationDAO.getResourceConfigurationById(configId);
		this.sourceDictionaryDAO.createSourceDictionary(config, source, unnormalizedName);
	}

	@Override
	public void deleteSourceDictionary(final long id) {
		this.sourceDictionaryDAO.deleteSourceDictionary(id);
	}

	/*
	 * Indicator type dictionaries
	 */
	@Override
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries() {
		return this.indicatorTypeDictionaryDAO.listIndicatorTypeDictionaries();
	}

	@Override
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries(final long configId) {
		return this.indicatorTypeDictionaryDAO.getIndicatorTypeDictionariesByResourceConfiguration(this.resourceConfigurationDAO.getResourceConfigurationById(configId));
	}

	@Override
	public void createIndicatorTypeDictionary(final long configId, final long indicatorTypeId, final String unnormalizedName) {
		final IndicatorType indicatorType = this.indicatorTypeDAO.getIndicatorTypeById(indicatorTypeId);
		final ResourceConfiguration config = this.resourceConfigurationDAO.getResourceConfigurationById(configId);
		this.indicatorTypeDictionaryDAO.createIndicatorTypeDictionary(config, indicatorType, unnormalizedName);
	}

	@Override
	public void deleteIndicatorTypeDictionary(final long id) {
		this.indicatorTypeDictionaryDAO.deleteIndicatorTypeDictionary(id);

	}

	/**
	 * Units
	 */
	@Override
	public List<Unit> listUnits() {
		return this.unitDAO.listUnits();
	}

	@Override
	@Transactional
	public void createUnit(final String code, final String name) {
		final Text nameText = this.textDAO.createText(name);
		this.unitDAO.createUnit(code, nameText);
	}

	@Override
	@Transactional
	public void deleteUnit(final Long id) {
		this.unitDAO.deleteUnit(id);
	}

	@Override
	public void updateUnit(final Long id, final String name) {
		this.unitDAO.updateUnit(id, name);
	}

	@Override
	public Unit getUnit(final Long id) {
		return this.unitDAO.getUnitById(id);
	}

	/*
	 * Reports.
	 */

	/* Countries */

	// Country overview
	@Override
	public List<Object[]> listIndicatorsForCountryOverview(final String countryCode, final String languageCode, final String[] indicatorsList) {
		return this.indicatorDAO.listIndicatorsForCountryOverview(countryCode, languageCode, indicatorsList);
	}

	// Country indicators
	@Override
	public Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String languageCode, final List<DataSerie> dataSeries) {
		return this.indicatorDAO.listIndicatorsForCountry(countryCode, fromYear, toYear, languageCode, dataSeries);
	}

	// Country indicators
	@Override
	public Map<Integer, List<Object[]>> listIndicatorsForCountry(final String countryCode, final int fromYear, final int toYear, final String languageCode, final Periodicity periodicity,
			final List<DataSerie> dataSeries) {
		return this.indicatorDAO.listIndicatorsForCountry(countryCode, fromYear, toYear, languageCode, periodicity, dataSeries);
	}

	/* Indicators */

	// Indicator overview
	@Override
	public Object[] getIndicatorTypeOverview(final String indicatorTypeCode, final String sourceCode, final String languageCode) {
		return this.indicatorDAO.getIndicatorTypeOverview(indicatorTypeCode, sourceCode, languageCode);
	}

	/* Metadata */

	@Override
	public List<DataSerieMetadata> getMetadataForDataSerie(final DataSerie dataSerie) {
		return this.dataSerieMetadataDAO.listDataSerieMetadataByIndicatorTypeCodeAndSourceCode(dataSerie);

	}

	@Override
	public Map<String, Timestamp> getMinMaxDatesForDataSeries(final DataSerie dataSeries) {
		return this.indicatorDAO.getMinMaxDatesForDataSeries(dataSeries);
	}

	@Override
	public List<DataSerieMetadata> getMetadataForIndicatorTypeCode(final String indicatorTypeCode) {
		return this.dataSerieMetadataDAO.listDataSerieMetadataByIndicatorTypeCode(indicatorTypeCode);
	}

	@Override
	public void updateMetadataForIndicatorTypeAndSource(final MetadataName entryKey, final String data, final String languageCode, final String indicatorTypeCode, final String sourceCode) {
		final DataSerieMetadata dataSerieMetadata = this.dataSerieMetadataDAO.getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey(indicatorTypeCode, sourceCode, entryKey);
		if (null == dataSerieMetadata) {
			final IndicatorType indicatorType = this.indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode);
			final Source source = this.sourceDAO.getSourceByCode(sourceCode);
			Text text = null;
			if ("default".equals(languageCode)) {
				text = this.textDAO.createText(data);
			} else {
				text = this.textDAO.createText("");
				this.textDAO.createTranslationForText(text.getId(), languageCode, data);
			}
			this.dataSerieMetadataDAO.createDataSerieMetadata(indicatorType, source, entryKey, text);
		} else {
			if ("default".equals(languageCode)) {
				this.dataSerieMetadataDAO.updateDataSerieMetadata(indicatorTypeCode, sourceCode, entryKey, data);
			} else {
				final List<Translation> translations = dataSerieMetadata.getEntryValue().getTranslations();
				if (null != translations) {
					boolean found = false;
					for (final Translation translation : translations) {
						if (languageCode.equals(translation.getId().getLanguage().getCode())) {
							this.textDAO.updateTranslation(dataSerieMetadata.getEntryValue().getId(), languageCode, data);
							found = true;
							break;
						}
					}
					if (!found) {
						this.textDAO.createTranslationForText(dataSerieMetadata.getEntryValue().getId(), languageCode, data);
					}
				} else {
					this.textDAO.createTranslationForText(dataSerieMetadata.getEntryValue().getId(), languageCode, data);
				}
			}
		}

		// we don't send anything to ckan if this is for a specific language, at least for now
		// this might trigger some unnecessary calls, but we might add extras to ckan, and this is convenient to maintain as is
		if ("default".equals(languageCode)) {
			updateMetadataTimestamp(new DataSerie(indicatorTypeCode, sourceCode));
		}
	}

	@Override
	public void deleteMetadata(final Long id) {
		this.dataSerieMetadataDAO.deleteDataSerieMetadata(id);
	}

	@Override
	public void updateMetadataTimestamp(final DataSerie dataSerie) {
		logger.debug(String.format("about to flag metadata as updated for datserie : %s", dataSerie));
		final boolean success = dataSerieToCuratedDatasetDAO.updateLastMetadataTimestamp(dataSerie);
		if (!success) {
			final Source source = sourceDAO.getSourceByCode(dataSerie.getSourceCode());
			final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(dataSerie.getIndicatorCode());
			dataSerieToCuratedDatasetDAO.createDataSerieToCuratedDataset(source, indicatorType);
			dataSerieToCuratedDatasetDAO.updateLastMetadataTimestamp(dataSerie);
		}
	}

	@Override
	@Transactional()
	public void updateDataTimestamp(final DataSerie dataSerie) {
		logger.debug(String.format("about to flag data as updated for datserie : %s", dataSerie));
		final boolean success = dataSerieToCuratedDatasetDAO.updateLastDataTimestamp(dataSerie);
		if (!success) {
			final Source source = sourceDAO.getSourceByCode(dataSerie.getSourceCode());
			final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(dataSerie.getIndicatorCode());
			dataSerieToCuratedDatasetDAO.createDataSerieToCuratedDataset(source, indicatorType);
			dataSerieToCuratedDatasetDAO.updateLastDataTimestamp(dataSerie);
			// also need to update metadata to update range of values
			dataSerieToCuratedDatasetDAO.updateLastMetadataTimestamp(dataSerie);
		}
	}

	/*
	 * Validation notes.
	 */

	@Override
	public void updateValidationNotesForIndicatorTypeAndSource(final String validationNotes, final String indicatorTypeCode, final String sourceCode) {
		this.updateMetadataForIndicatorTypeAndSource(MetadataName.VALIDATION_NOTES, validationNotes, "default", indicatorTypeCode, sourceCode);
	}

	@Override
	public List<String> listCountryCodesForDataSerie(final String indicatorTypeCode, final String sourceCode) {
		return indicatorDAO.listCountryCodesForDataSerie(indicatorTypeCode, sourceCode);
	}

}