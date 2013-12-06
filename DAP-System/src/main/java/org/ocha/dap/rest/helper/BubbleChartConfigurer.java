package org.ocha.dap.rest.helper;

import java.util.List;
import java.util.Map;

import org.ocha.dap.persistence.entity.curateddata.IndicatorType;
import org.ocha.dap.persistence.entity.curateddata.Source;

/**
 * A simple POJO to be able to access some data in the BubbleChart.jsp
 * 
 * @author Samuel Eustachi
 *
 */
public class BubbleChartConfigurer {

	private Map<String, String> model;
	private List<Source> sources;
	private List<IndicatorType> indicatorTypes;

	private String source1;
	private String source2;
	private String source3;

	private String indicatorType1;
	private String indicatorType2;
	private String indicatorType3;

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

	public String getSource1() {
		return source1;
	}

	public void setSource1(final String source1) {
		this.source1 = source1;
	}

	public String getSource2() {
		return source2;
	}

	public void setSource2(final String source2) {
		this.source2 = source2;
	}

	public String getSource3() {
		return source3;
	}

	public void setSource3(final String source3) {
		this.source3 = source3;
	}

	public String getIndicatorType1() {
		return indicatorType1;
	}

	public void setIndicatorType1(final String indicatorType1) {
		this.indicatorType1 = indicatorType1;
	}

	public String getIndicatorType2() {
		return indicatorType2;
	}

	public void setIndicatorType2(final String indicatorType2) {
		this.indicatorType2 = indicatorType2;
	}

	public String getIndicatorType3() {
		return indicatorType3;
	}

	public void setIndicatorType3(final String indicatorType3) {
		this.indicatorType3 = indicatorType3;
	}

}
