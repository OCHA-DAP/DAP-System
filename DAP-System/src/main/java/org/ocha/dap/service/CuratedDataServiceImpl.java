package org.ocha.dap.service;

import java.util.Date;
import java.util.List;

import org.ocha.dap.importer.PreparedIndicator;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityDAO;
import org.ocha.dap.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.dap.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.dap.persistence.dao.currateddata.SourceDAO;
import org.ocha.dap.persistence.entity.ImportFromCKAN;
import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.EntityType;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CuratedDataServiceImpl implements CuratedDataService {

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
	public List<Source> listSources() {
		return sourceDAO.listSources();
	}

	@Override
	public void addSource(final String code, final String name) {
		sourceDAO.addSource(code, name);
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
	public List<Indicator> listIndicatorsByPeriodicityAndSourceAndIndicatorType(final Periodicity periodicity, final String sourceCode, final String indicatorTypeCode) {
		return indicatorDAO.listIndicatorsByPeriodicityAndSourceAndIndicatorType(periodicity, sourceCode, indicatorTypeCode);
	}

}