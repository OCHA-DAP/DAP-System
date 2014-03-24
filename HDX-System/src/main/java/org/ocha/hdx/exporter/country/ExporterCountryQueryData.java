package org.ocha.hdx.exporter.country;

import org.ocha.hdx.exporter.QueryData;
import org.ocha.hdx.exporter.helper.ReadmeHelper;

/**
 * Data query for a country.
 * 
 * @author bmichiels
 */
public class ExporterCountryQueryData extends QueryData {

	/**
	 * The country code (e.g. COL, USA, BEL, etc.).
	 */
	private String countryCode;
	
	/**
	 * The year from which the report will start (e.g. 1998).
	 * If set to 0, the report will start with the earliest data available.
	 */
	private Integer fromYear;

	/**
	 * The year to which the report will go (e.g. 2010).
	 * If set to 0, the report go to the latest data available.
	 */
	private Integer toYear;
	
	/**
	 * The language into which the report will be generated.
	 * TODO Not implemented yet. All texts are set in the default value.
	 */
	private String language;
	
	/**
	 * The helper to build the readme part of the report.
	 */
	private ReadmeHelper readmeHelper;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public Integer getFromYear() {
		return fromYear;
	}

	public void setFromYear(final Integer fromYear) {
		this.fromYear = fromYear;
	}

	public Integer getToYear() {
		return toYear;
	}

	public void setToYear(final Integer toYear) {
		this.toYear = toYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

	public ReadmeHelper getReadmeHelper() {
		return readmeHelper;
	}

	public void setReadmeHelper(final ReadmeHelper readmeHelper) {
		this.readmeHelper = readmeHelper;
	}
}
