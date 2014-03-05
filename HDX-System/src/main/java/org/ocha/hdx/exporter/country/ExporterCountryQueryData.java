package org.ocha.hdx.exporter.country;

import org.ocha.hdx.exporter.QueryData;

/**
 * Data query for a country.
 * 
 * @author bmichiels
 */
public class ExporterCountryQueryData extends QueryData {

	private String countryCode;
	private Integer fromYear;
	private Integer toYear;
	private String language;

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
}
