package org.ocha.hdx.rest.helper;

import java.util.List;
import java.util.Map;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;

public class IndicatorAndSourceChartConfigurer {

	private Map<String, String> model;

	private List<Source> sources;
	private List<IndicatorType> indicatorTypes;

	private String source;
	private String indicatorType;

	public Map<String, String> getModel() {
		return model;
	}

	public void setModel(final Map<String, String> model) {
		this.model = model;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(final List<Source> sources) {
		this.sources = sources;
	}

	public List<IndicatorType> getIndicatorTypes() {
		return indicatorTypes;
	}

	public void setIndicatorTypes(final List<IndicatorType> indicatorTypes) {
		this.indicatorTypes = indicatorTypes;
	}

	public String getSource() {
		return source;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	public String getIndicatorType() {
		return indicatorType;
	}

	public void setIndicatorType(final String indicatorType) {
		this.indicatorType = indicatorType;
	}

}
