package org.ocha.hdx.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.importer.TimeRange;
import org.ocha.hdx.model.api.CellDescriptor;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.dictionary.IndicatorTypeDictionaryDAO;
import org.ocha.hdx.persistence.dao.dictionary.RegionDictionaryDAO;
import org.ocha.hdx.persistence.dao.dictionary.SourceDictionaryDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.hdx.persistence.entity.dictionary.RegionDictionary;
import org.ocha.hdx.persistence.entity.dictionary.SourceDictionary;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	private IndicatorDAO indicatorDAO;

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private RegionDictionaryDAO regionDictionaryDAO;

	@Autowired
	private SourceDictionaryDAO sourceDictionaryDAO;

	@Autowired
	private IndicatorTypeDictionaryDAO indicatorTypeDictionaryDAO;

	/*
	 * Entity types
	 */
	@Override
	public List<EntityType> listEntityTypes() {
		return entityTypeDAO.listEntityTypes();
	}

	@Override
	@Transactional
	public void createEntityType(final String code, final String name) {
		final Text text = textDAO.createText(name);
		entityTypeDAO.createEntityType(code, text);
	}

	@Override
	public EntityType getEntityType(final long id) {
		return entityTypeDAO.getEntityTypeById(id);
	}

	@Override
	@Transactional
	public void updateEntityType(final long entityTypeId, final String newName) {
		entityTypeDAO.updateEntityType(entityTypeId, newName);
	}

	@Override
	@Transactional
	public void deleteEntityType(final long entityTypeId) {
		entityTypeDAO.deleteEntityType(entityTypeId);
	}

	/*
	 * Entities
	 */
	@Override
	public List<Entity> listEntities() {
		return entityDAO.listEntities();
	}

	@Override
	@Transactional
	public void createEntity(final String code, final String defaultName, final String entityTypeCode) {
		final EntityType entityType = entityTypeDAO.getEntityTypeByCode(entityTypeCode);

		final Text text = textDAO.createText(defaultName);
		entityDAO.createEntity(code, text, entityType);
	}

	@Override
	public Entity getEntity(final long id) {
		return entityDAO.getEntityById(id);
	}

	@Override
	public Entity getEntityByCodeAndType(final String code, final String type) {
		return entityDAO.getEntityByCodeAndType(code, type);
	}

	@Override
	@Transactional
	public void updateEntity(final long entityId, final String newName) {
		entityDAO.updateEntity(entityId, newName);
	}

	@Override
	@Transactional
	public void deleteEntity(final long entityId) {
		entityDAO.deleteEntity(entityId);
	}

	/*
	 * Indicator types
	 */
	@Override
	public List<IndicatorType> listIndicatorTypes() {
		return indicatorTypeDAO.listIndicatorTypes();
	}

	@Override
	@Transactional
	public void createIndicatorType(final String code, final String defaultName, final String unit, final String valueType) {
		final Text text = textDAO.createText(defaultName);
		indicatorTypeDAO.createIndicatorType(code, text, unit, org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType.valueOf(valueType));
	}

	@Override
	public IndicatorType getIndicatorType(final long id) {
		return indicatorTypeDAO.getIndicatorTypeById(id);
	}

	@Override
	public IndicatorType getIndicatorTypeByCode(final String code) {
		return indicatorTypeDAO.getIndicatorTypeByCode(code);
	}

	@Override
	@Transactional
	public void updateIndicatorType(final long indicatorTypeId, final String newName, final String newUnit, final String newValueType) {
		indicatorTypeDAO.updateIndicatorType(indicatorTypeId, newName, newUnit, org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType.valueOf(newValueType));
	}

	@Override
	@Transactional
	public void deleteIndicatorType(final long indicatorTypeId) {
		indicatorTypeDAO.deleteIndicatorType(indicatorTypeId);
	}

	/*
	 * Sources
	 */
	@Override
	public List<Source> listSources() {
		return sourceDAO.listSources();
	}

	@Override
	@Transactional
	public void createSource(final String code, final String defaultValue, final String link) {
		final Text name = textDAO.createText(defaultValue);
		sourceDAO.createSource(code, name, link);
	}

	@Override
	public Source getSource(final Long id) {
		return sourceDAO.getSourceById(id);
	}

	@Override
	public Source getSourceByCode(final String code) {
		return sourceDAO.getSourceByCode(code);
	}

	@Override
	@Transactional
	public void updateSource(final long sourceId, final String newName, final String newLink) {
		sourceDAO.updateSource(sourceId, newName, newLink);
	}

	@Override
	@Transactional
	public void deleteSource(final long sourceId) {
		sourceDAO.deleteSource(sourceId);
	}

	/*
	 * Imports from CKAN
	 */
	@Override
	public List<ImportFromCKAN> listImportsFromCKAN() {
		return importFromCKANDAO.listImportsFromCKAN();
	}

	@Override
	@Transactional
	public void deleteImportFromCKAN(final long id) {
		indicatorDAO.deleteAllIndicatorsFromImport(id);
		importFromCKANDAO.deleteImportFromCKAN(id);
	}

	/*
	 * Indicators
	 */
	@Override
	@Transactional
	public void createIndicator(final String sourceCode, final long entityId, final String indicatorTypeCode, final Date start, final Date end, final Periodicity periodicity,
			final IndicatorValue value, final String initialValue, final String sourceLink) {
		final Source source = sourceDAO.getSourceByCode(sourceCode);
		final Entity entity = entityDAO.getEntityById(entityId);
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode);

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.getDummyImport();

		indicatorDAO.createIndicator(source, entity, indicatorType, start, end, periodicity, value, initialValue, sourceLink, importFromCKAN);

	}

	@Override
	@Transactional
	public void createIndicator(final PreparedIndicator preparedIndicator, final ImportFromCKAN importFromCKAN) {
		final Source source = sourceDAO.getSourceByCode(preparedIndicator.getSourceCode());
		final Entity entity = entityDAO.getEntityByCodeAndType(preparedIndicator.getEntityCode(), preparedIndicator.getEntityTypeCode());
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(preparedIndicator.getIndicatorTypeCode());

		indicatorDAO.createIndicator(source, entity, indicatorType, preparedIndicator.getStart(), preparedIndicator.getEnd(), preparedIndicator.getPeriodicity(), preparedIndicator.getValue(),
				preparedIndicator.getInitialValue(), preparedIndicator.getSourceLink(), importFromCKAN);

	}

	@Override
	@Transactional
	public void deleteIndicator(final long indicatorId) {
		indicatorDAO.deleteIndicator(indicatorId);

	}

	@Override
	public List<Indicator> listLastIndicators(final int limit) {
		return indicatorDAO.listLastIndicators(limit);
	}

	@Override
	public DataTable listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes)
			throws TypeMismatchException {

		// must be sorted by start, entity
		final List<Indicator> indicators = indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(periodicity, sourceCode, indicatorTypeCode, countryCodes);
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
		final List<Indicator> indicators = indicatorDAO.listIndicatorsByPeriodicityAndEntityAndIndicatorType(periodicity, entityType, entityCode, indicatorTypeCode);

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
		final List<Indicator> indicators = indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(year, sourceCode, indicatorTypeCode, null);

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
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode1, ValueType.NUMBER, indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode1).getName().getDefaultValue()));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode2, ValueType.NUMBER, indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode2).getName().getDefaultValue()));
		// This is a Hack to have the third indicator moved to the 4th
		dataTable.addColumn(new ColumnDescription("d", ValueType.NUMBER, "d"));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode3, ValueType.NUMBER, indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode3).getName().getDefaultValue()));

		final Map<String, TableRow> rows = new HashMap<String, TableRow>();
		addColumnData(year, rows, sourceCode1, indicatorTypeCode1, countryCodes);
		addColumnData(year, rows, sourceCode2, indicatorTypeCode2, countryCodes);
		// This is a Hack to have the third indicator moved to the 4th
		addACellToEveryRow(rows);
		addColumnData(year, rows, sourceCode3, indicatorTypeCode3, countryCodes);

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
	@Override
	public List<Indicator> listIndicatorsForCountryOverview(final String countryCode, final String languageCode) {
		final List<Indicator> result = indicatorDAO.listIndicatorsForCountryOverview(countryCode, languageCode);

		return result;
	}
	*/

	private Map<String, TableRow> addColumnData(final int year, final Map<String, TableRow> rows, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final List<Indicator> indicators = indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(year, sourceCode, indicatorTypeCode, countryCodes);

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

	private Map<String, TableRow> addACellToEveryRow(final Map<String, TableRow> rows) {
		for (final TableRow row : rows.values()) {
			row.addCell(0);
		}
		return rows;
	}

	@Override
	public List<Source> getExistingSourcesForYearAndIndicatorType(final int year, final String indicatorTypeCode) {
		final List<String> sourceCodes = indicatorDAO.getExistingSourcesCodesForYearAndIndicatorType(year, indicatorTypeCode);

		final List<Source> sources = new ArrayList<>();
		for (final String code : sourceCodes) {
			sources.add(sourceDAO.getSourceByCode(code));
		}
		return sources;
	}

	@Override
	public List<Source> getExistingSourcesForIndicatorType(final String indicatorTypeCode) {
		final List<String> sourceCodes = indicatorDAO.getExistingSourcesCodesForIndicatorType(indicatorTypeCode);

		final List<Source> sources = new ArrayList<>();
		for (final String code : sourceCodes) {
			sources.add(sourceDAO.getSourceByCode(code));
		}
		return sources;
	}

	/*
	 * Region dictionaries
	 */
	@Override
	public List<RegionDictionary> listRegionDictionaries() {
		return regionDictionaryDAO.listRegionDictionaries();
	}

	@Override
	public void createRegionDictionary(final String unnormalizedName, final String importer, final long entityId) {
		final Entity entity = entityDAO.getEntityById(entityId);
		regionDictionaryDAO.createRegionDictionary(unnormalizedName, importer, entity);
	}

	@Override
	public void deleteRegionDictionary(final RegionDictionary regionDictionary) {
		regionDictionaryDAO.deleteRegionDictionary(regionDictionary);
	}

	/*
	 * Source dictionaries
	 */
	@Override
	public List<SourceDictionary> listSourceDictionaries() {
		return sourceDictionaryDAO.listSourceDictionaries();
	}

	@Override
	public void createSourceDictionary(final String unnormalizedName, final String importer, final long sourceId) {
		final Source source = sourceDAO.getSourceById(sourceId);
		sourceDictionaryDAO.createSourceDictionary(unnormalizedName, importer, source);

	}

	/*
	 * Indicator type dictionaries
	 */
	@Override
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries() {
		return indicatorTypeDictionaryDAO.listIndicatorTypeDictionaries();
	}

	@Override
	public void createIndicatorTypeDictionary(final String unnormalizedName, final String importer, final long indicatorTypeId) {
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeById(indicatorTypeId);
		indicatorTypeDictionaryDAO.createIndicatorTypeDictionary(unnormalizedName, importer, indicatorType);
	}

	@Override
	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary) {
		indicatorTypeDictionaryDAO.deleteIndicatorTypeDictionary(indicatorTypeDictionary);

	}

}