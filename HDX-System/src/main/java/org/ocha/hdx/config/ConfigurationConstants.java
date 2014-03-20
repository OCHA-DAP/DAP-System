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
		MIN_NUM_OF_COLUMNS("Minimum number of columns"), PREVALIDATORS("Pre-validators", true), ALLOWED_INDICATOR_TYPES("Allowed indicator type codes", true);

		private final String label;
		private final Boolean multipleValuesFlag;

		public Boolean getMultipleValuesFlag() {
			return this.multipleValuesFlag;
		}

		public String getLabel() {
			return this.label;
		}

		private GeneralConfiguration(final String lbl) {
			this(lbl, false);
		}

		private GeneralConfiguration(final String lbl, final Boolean flag) {
			this.label = lbl;
			this.multipleValuesFlag = flag;
		}

	}

	public enum IndicatorConfiguration {
		MAX_VALUE("Max Value"), MIN_VALUE("Min Value"), VALIDATORS("Validators", true), EXPECTED_TIME_FORMAT("Expected time format"), EXPECTED_START_TIME_FORMAT("Expected start time format"), INDICATOR_VALUE_TYPE(
				"Indicator Value Type");

		private final String label;
		private final Boolean multipleValuesFlag;

		public Boolean getMultipleValuesFlag() {
			return this.multipleValuesFlag;
		}

		public String getLabel() {
			return this.label;
		}

		private IndicatorConfiguration(final String lbl) {
			this(lbl, false);
		}

		private IndicatorConfiguration(final String lbl, final Boolean flag) {
			this.label = lbl;
			this.multipleValuesFlag = flag;
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
