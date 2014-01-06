package org.ocha.dap.rest.helper;

import java.util.List;

import org.ocha.dap.persistence.entity.curateddata.Entity;
import org.ocha.dap.persistence.entity.curateddata.Indicator;
import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;

public class DisplayIndicators {

	private List<Indicator> indicators;

	private List<Source> sources;
	private List<Entity> entities;
	private List<IndicatorType> indicatorTypes;

	private Indicator.Periodicity[] periodicities;

	public List<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(final List<Indicator> indicators) {
		this.indicators = indicators;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(final List<Source> sources) {
		this.sources = sources;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(final List<Entity> entities) {
		this.entities = entities;
	}

	public List<IndicatorType> getIndicatorTypes() {
		return indicatorTypes;
	}

	public void setIndicatorTypes(final List<IndicatorType> indicatorTypes) {
		this.indicatorTypes = indicatorTypes;
	}

	public Indicator.Periodicity[] getPeriodicities() {
		return periodicities;
	}

	public void setPeriodicities(final Indicator.Periodicity[] periodicities) {
		this.periodicities = periodicities;
	}

}
