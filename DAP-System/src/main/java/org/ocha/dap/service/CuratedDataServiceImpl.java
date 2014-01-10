package org.ocha.dap.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ocha.dap.importer.PreparedIndicator;
import org.ocha.dap.importer.TimeRange;
import org.ocha.dap.model.api.CellDescriptor;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.SourceDAO;
import org.ocha.dap.persistence.dao.dictionary.IndicatorTypeDictionaryDAO;
import org.ocha.dap.persistence.dao.dictionary.RegionDictionaryDAO;
import org.ocha.dap.persistence.dao.dictionary.SourceDictionaryDAO;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.ocha.dap.persistence.entity.dictionary.IndicatorTypeDictionary;
import org.ocha.dap.persistence.entity.dictionary.RegionDictionary;
import org.ocha.dap.persistence.entity.dictionary.SourceDictionary;
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

	@Override
	public List<EntityType> listEntityTypes() {
		return entityTypeDAO.listEntityTypes();
	}

	@Override
	public void addEntityType(final String code, final String name) {
		entityTypeDAO.addEntityType(code, name);
	}

	@Override
	public List<Entity> listEntities() {
		return entityDAO.listEntities();
	}

	@Override
	public Entity getEntityByCodeAndType(final String code, final String type) {
		return entityDAO.getEntityByCodeAndType(code, type);
	}

	@Override
	@Transactional
	public void addEntity(final String code, final String name, final String entityTypeCode) {
		final EntityType entityType = entityTypeDAO.getEntityTypeByCode(entityTypeCode);
		entityDAO.addEntity(code, name, entityType);
	}

	@Override
	public List<IndicatorType> listIndicatorTypes() {
		return indicatorTypeDAO.listIndicatorTypes();
	}

	@Override
	public void addIndicatorType(final String code, final String name, final String unit) {
		indicatorTypeDAO.addIndicatorType(code, name, unit);
	}

	@Override
	public IndicatorType getIndicatorTypeByCode(final String code) {
		return indicatorTypeDAO.getIndicatorTypeByCode(code);
	}

	@Override
	public List<Source> listSources() {
		return sourceDAO.listSources();
	}

	@Override
	public void addSource(final String code, final String name) {
		sourceDAO.addSource(code, name);
	}

	@Override
	public Source getSourceByCode(final String code) {
		return sourceDAO.getSourceByCode(code);
	}

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

	@Override
	@Transactional
	public void addIndicator(final String sourceCode, final long entityId, final String indicatorTypeCode, final Date start, final Date end, final Periodicity periodicity, final boolean numeric,
			final String value, final String initialValue) {
		final Source source = sourceDAO.getSourceByCode(sourceCode);
		final Entity entity = entityDAO.getEntityById(entityId);
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode);

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.getDummyImport();
		indicatorDAO.addIndicator(source, entity, indicatorType, start, end, periodicity, numeric, value, initialValue, importFromCKAN);

	}

	@Override
	@Transactional
	public void addIndicator(final PreparedIndicator preparedIndicator, final ImportFromCKAN importFromCKAN) {
		final Source source = sourceDAO.getSourceByCode(preparedIndicator.getSourceCode());
		final Entity entity = entityDAO.getEntityByCodeAndType(preparedIndicator.getEntityCode(), preparedIndicator.getEntityTypeCode());
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode(preparedIndicator.getIndicatorTypeCode());

		indicatorDAO.addIndicator(source, entity, indicatorType, preparedIndicator.getStart(), preparedIndicator.getEnd(), preparedIndicator.getPeriodicity(), preparedIndicator.isNumeric(),
				preparedIndicator.getValue(), preparedIndicator.getInitialValue(), importFromCKAN);

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
			dataTable.addColumn(new ColumnDescription(entity.getCode(), ValueType.NUMBER, entity.getName()));
			for (final String year : years) {
				final Indicator cellIndicator = tableContent.get(new CellDescriptor(year, entity.getCode()));
				if (cellIndicator == null) {
					rows.get(year).addCell(NumberValue.getNullValue());
				} else {
					rows.get(year).addCell(Double.parseDouble(cellIndicator.getValue()));
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

		// FIXME probably has a problem if some data are missing in the Table, see how it is fixed for
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
			if (!dataTable.containsColumn(code))
				dataTable.addColumn(new ColumnDescription(indicator.getSource().getCode(), ValueType.NUMBER, indicator.getSource().getName()));

		}
		for (final Indicator indicator : indicators) {
			final TimeRange timeRange = new TimeRange(indicator.getStart(), indicator.getEnd(), indicator.getPeriodicity());
			if (timeRange.equals(previousTR)) {
				currentRow.addCell(Double.parseDouble(indicator.getValue()));
			} else {
				final TableRow aRow = new TableRow();
				aRow.addCell(timeRange.getTimeRangeAsSimpleString());
				currentRow = aRow;
				aRow.addCell(Double.parseDouble(indicator.getValue()));
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
			aRow.addCell(indicator.getEntity().getName());
			aRow.addCell(Double.parseDouble(indicator.getValue()));
			dataTable.addRow(aRow);
		}
		return dataTable;
	}

	@Override
	public DataTable listIndicatorsByYearAndSourcesAndIndicatorTypes(final int year, final String sourceCode1, final String indicatorTypeCode1, final String sourceCode2,
			final String indicatorTypeCode2, final String sourceCode3, final String indicatorTypeCode3, final List<String> countryCodes) throws TypeMismatchException {
		final DataTable dataTable = new DataTable();
		dataTable.addColumn(new ColumnDescription("Entity", ValueType.TEXT, "Entity"));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode1, ValueType.NUMBER, indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode1).getName()));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode2, ValueType.NUMBER, indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode2).getName()));
		// This is a Hack to have the third indicator moved to the 4th
		dataTable.addColumn(new ColumnDescription("d", ValueType.NUMBER, "d"));
		dataTable.addColumn(new ColumnDescription(indicatorTypeCode3, ValueType.NUMBER, indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode3).getName()));

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

	private Map<String, TableRow> addColumnData(final int year, final Map<String, TableRow> rows, final String sourceCode, final String indicatorTypeCode, final List<String> countryCodes) {
		final List<Indicator> indicators = indicatorDAO.listIndicatorsByYearAndSourceAndIndicatorType(year, sourceCode, indicatorTypeCode, countryCodes);

		for (final Indicator indicator : indicators) {
			if (rows.containsKey(indicator.getEntity().getCode())) {
				rows.get(indicator.getEntity().getCode()).addCell(Double.parseDouble(indicator.getValue()));
			} else {
				final TableRow aRow = new TableRow();
				aRow.addCell(indicator.getEntity().getName());
				aRow.addCell(Double.parseDouble(indicator.getValue()));
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

	@Override
	public List<RegionDictionary> listRegionDictionaries() {
		return regionDictionaryDAO.listRegionDictionaries();
	}

	@Override
	public List<SourceDictionary> listSourceDictionaries() {
		return sourceDictionaryDAO.listSourceDictionaries();
	}

	@Override
	public List<IndicatorTypeDictionary> listIndicatorTypeDictionaries() {
		return indicatorTypeDictionaryDAO.listIndicatorTypeDictionaries();
	}

	@Override
	public void addRegionDictionary(final String unnormalizedName, final String importer, final long entityId) {
		final Entity entity = entityDAO.getEntityById(entityId);
		regionDictionaryDAO.addRegionDictionary(unnormalizedName, importer, entity);
	}

	@Override
	public void deleteRegionDictionary(final RegionDictionary regionDictionary) {
		regionDictionaryDAO.deleteRegionDictionary(regionDictionary);
	}

	@Override
	public void addSourceDictionary(final String unnormalizedName, final String importer, final long sourceId) {
		final Source source = sourceDAO.getSourceById(sourceId);
		sourceDictionaryDAO.addSourceDictionary(unnormalizedName, importer, source);

	}

	@Override
	public void addIndicatorTypeDictionary(final String unnormalizedName, final String importer, final long indicatorTypeId) {
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeById(indicatorTypeId);
		indicatorTypeDictionaryDAO.addIndicatorTypeDictionary(unnormalizedName, importer, indicatorType);
	}

	@Override
	public void deleteIndicatorTypeDictionary(final IndicatorTypeDictionary indicatorTypeDictionary) {
		indicatorTypeDictionaryDAO.deleteIndicatorTypeDictionary(indicatorTypeDictionary);

	}
}