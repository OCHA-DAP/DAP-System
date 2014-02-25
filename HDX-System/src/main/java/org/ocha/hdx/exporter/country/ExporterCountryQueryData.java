package org.ocha.hdx.exporter.country;

import org.ocha.hdx.exporter.QueryData;

/**
 * Data query for a country.
 * @author bmichiels
 */
public class ExporterCountryQueryData extends QueryData {

	private String countryCode;
	private String fromYear;
	private String toYear;
	private String language;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	public String getFromYear() {
		return fromYear;
	}

	public void setFromYear(final String fromYear) {
		this.fromYear = fromYear;
	}

	public String getToYear() {
		return toYear;
	}

	public void setToYear(final String toYear) {
		this.toYear = toYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}
}
