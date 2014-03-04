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

	// public enum GeneralConfiguration {
	// MIN_NUM_OF_COLUMNS("Minimum number of columns"), PREVALIDATORS("Pre-validators"),
	// ALLOWED_INDICATOR_TYPES("Allowed indicator type codes");
	//
	// private final String label;
	//
	// public String getLabel() {
	// return label;
	// }
	//
	// private GeneralConfiguration(final String lbl) {
	// this.label = lbl;
	// }
	//
	// }

	/* General Configurations */
	// public static final String MIN_NUM_OF_COLUMNS = "Minimum number of columns";
	// public static final String PREVALIDATORS = "Pre-validators";
	// public static final String ALLOWED_INDICATOR_TYPES = "Allowed indicator type codes";

	/* Indicator specific configurations */
	public static final String MAX_VALUE = "Max Value";
	public static final String MIN_VALUE = "Min Value";
	public static final String VALIDATORS = "Validators";
	public static final String EXPECTED_TIME_FORMAT = "Expected time format";

	public static final String EXPECTED_START_TIME_FORMAT = "Expected start time format";

	public static final String INDICATOR_VALUE_TYPE = "Indicator Value Type";

}
