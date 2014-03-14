/**
 *
 */
package org.ocha.hdx.config;


/**
 * @author alexandru-m-g
 * 
 */
public class ConfigurationConstants {

	public static final String SEPARATOR = "&&";

	public enum GeneralConfiguration {
		MIN_NUM_OF_COLUMNS("Minimum number of columns"), PREVALIDATORS("Pre-validators"), ALLOWED_INDICATOR_TYPES("Allowed indicator type codes");

		private final String label;

		public String getLabel() {
			return this.label;
		}

		private GeneralConfiguration(final String lbl) {
			this.label = lbl;
		}

	}

	public enum IndicatorConfiguration {
		MAX_VALUE("Max Value"), MIN_VALUE("Min Value"), VALIDATORS("Validators"), EXPECTED_TIME_FORMAT("Expected time format"), EXPECTED_START_TIME_FORMAT("Expected start time format"), INDICATOR_VALUE_TYPE(
				"Indicator Value Type");

		private final String label;

		public String getLabel() {
			return this.label;
		}

		private IndicatorConfiguration(final String lbl) {
			this.label = lbl;
		}

	}

	/*
	 * Indicator specific configurations public static final String MAX_VALUE = "Max Value"; public static final String MIN_VALUE =
	 * "Min Value"; public static final String VALIDATORS = "Validators"; public static final String EXPECTED_TIME_FORMAT =
	 * "Expected time format";
	 * 
	 * public static final String EXPECTED_START_TIME_FORMAT = "Expected start time format";
	 * 
	 * public static final String INDICATOR_VALUE_TYPE = "Indicator Value Type";
	 */

}
