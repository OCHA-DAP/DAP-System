/**
 *
 */
package org.ocha.hdx.service;

import java.util.ArrayList;
import java.util.List;

import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.importer.PreparedIndicator;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.entity.configs.IndicatorResourceConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Indicator;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author alexandru-m-g
 * 
 */
public class IndicatorCreationServiceImpl implements IndicatorCreationService {

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private EntityDAO entityDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ocha.hdx.service.IndicatorCreationService#createIndicator(org.ocha.hdx.importer.PreparedIndicator)
	 */
	@Override
	public Indicator createIndicator(final PreparedIndicator preparedIndicator) {
		final Source source = this.sourceDAO.getSourceByCode(preparedIndicator.getSourceCode());
		final Entity entity = this.entityDAO.getEntityByCodeAndType(preparedIndicator.getEntityCode(), preparedIndicator.getEntityTypeCode());
		final IndicatorType indicatorType = this.indicatorTypeDAO.getIndicatorTypeByCode(preparedIndicator.getIndicatorTypeCode());

		final Indicator indicator = new Indicator();
		indicator.setSource(source);
		indicator.setEntity(entity);
		indicator.setType(indicatorType);
		indicator.setStart(preparedIndicator.getStart());
		indicator.setEnd(preparedIndicator.getEnd());
		indicator.setPeriodicity(preparedIndicator.getPeriodicity());
		indicator.setValue(preparedIndicator.getValue());
		indicator.setInitialValue(preparedIndicator.getInitialValue());
		indicator.setSourceLink(preparedIndicator.getSourceLink());

		return indicator;
	}

	@Override
	public List<IndicatorResourceConfigEntry> findEmbeddedConfigs(final String indicatorTypeCode, final String sourceCode) {
		final List<IndicatorResourceConfigEntry> list = new ArrayList<IndicatorResourceConfigEntry>();

		final IndicatorType indicatorType = this.indicatorTypeDAO.getIndicatorTypeByCode(indicatorTypeCode);
		final Source source = this.sourceDAO.getSourceByCode(sourceCode);

		final ValueType valueType = indicatorType.getValueType();
		if (valueType != null) {
			final IndicatorResourceConfigEntry computedConigEntry = new IndicatorResourceConfigEntry(ConfigurationConstants.IndicatorConfiguration.INDICATOR_VALUE_TYPE.getLabel(),
					valueType.getLabel(), source, indicatorType);
			list.add(computedConigEntry);
		}
		return list;
	}

}
