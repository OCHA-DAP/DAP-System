package org.ocha.hdx.model;

import java.util.Arrays;
import java.util.List;

public class DataSerie implements Comparable<DataSerie> {

	// List of indicators for country overview
	public static final String[] COUNTRY_OVERVIEW_indicatorsList = new String[] { "CD010", "CD030", "CD050", "CD060", "CD070", "CD080", "CD090", "CG020", "CG030", "CG060", "CG070", "CG080", "CG100",
			"CG120", "CG140", "CG150", "CG260", "CG290", "_m49-name", "_unterm:ISO Country alpha-2-code" };

	// List of indicators for country RW overview
	public static final String[] COUNTRY_OVERVIEW_RW_indicatorsList = new String[] { "RW001", "RW002" };

	// List of indicators for country FTS overview
	public static final String[] COUNTRY_OVERVIEW_FTS_indicatorsList = new String[] { "FY010", "FY020", "FY040", "FY190", "FY200", "FY210", "FY240", "FY360", "FY370", "FY380", "FY500", "FY510",
			"FY520", "FY540", "FY550", "FY620", "FY630", "FA010", "FA140" };

	// List of indicators relevant for country - crisis history
	public static final List<DataSerie> COUNTRY_CRISIS_HISTORY_dataSeries = Arrays.asList(new DataSerie("CH070", "emdat"), new DataSerie("CH080", "emdat"), new DataSerie("CH090", "emdat"),
			new DataSerie("CH100", "emdat"));

	// List of indicators relevant for country - socio-economic
	public static final List<DataSerie> COUNTRY_SOCIO_ECONOMIC_dataSeries = Arrays.asList(new DataSerie("PSE030", "world-bank"), new DataSerie("PSE090", "world-bank"), new DataSerie("PSE120",
			"world-bank"), new DataSerie("PSE130", "world-bank"), new DataSerie("PSP080", "esa-unpd-wpp2012"), new DataSerie("PSE140", "world-bank"), new DataSerie("PSE150", "world-bank"),
			new DataSerie("PSE200", "world-bank"), new DataSerie("PSP010", "esa-unpd-wpp2012"), new DataSerie("PSP060", "world-bank"), new DataSerie("PSP090", "world-bank"), new DataSerie("PSP110",
					"world-bank"));

	// List of indicators relevant for country - vulnerability
	public static final List<DataSerie> COUNTRY_VULNERABILITY_dataSeries = Arrays.asList(new DataSerie("PVE130", "mdgs"), new DataSerie("PVF020", "faostat3"), new DataSerie("PVH140", "mdgs"),
			new DataSerie("PVL040", "world-bank"), new DataSerie("PVN010", "fao-foodsec"), new DataSerie("PVW010", "mdgs"), new DataSerie("PVW040", "mdgs"));

	// List of indicators relevant for country - capacity
	public static final List<DataSerie> COUNTRY_CAPACITY_dataSeries = Arrays.asList(new DataSerie("PCX051", "mdgs"), new DataSerie("PCX080", "mdgs"), new DataSerie("PCX090", "world-bank"),
			new DataSerie("PCX100", "world-bank"));

	// List of indicators relevant for country - other
	public static final List<DataSerie> COUNTRY_OTHER_dataSeries = Arrays.asList(new DataSerie("_Children 1 year old immunized against measles, percentage", "mdgs"), new DataSerie(
			"_emdat:no_homeless", "emdat"), new DataSerie("_emdat:no_injured", "emdat"), new DataSerie("_emdat:total_affected", "emdat"), new DataSerie("_GNI, PPP (current international $)",
			"world-bank"), new DataSerie("_Internet users per 100 inhabitants", "mdgs"), new DataSerie("_Land area (sq. km)", "world-bank"), new DataSerie(
			"_Net ODA received per capita (current US$)", "world-bank"), new DataSerie("_Number of infant deaths", "world-bank"), new DataSerie("_Population, total", "world-bank"), new DataSerie(
			"_Population undernourished, millions", "mdgs"), new DataSerie("_Population undernourished, percentage", "mdgs"), new DataSerie("_reliefweb_Humanitarian_Bulletin", "reliefweb-api"),
			new DataSerie("_reliefweb_Humanitarian_Dashboard", "reliefweb-api"), new DataSerie("_reliefweb_Humanitarian_Snapshot", "reliefweb-api"), new DataSerie("_reliefweb_Infographic",
					"reliefweb-api"), new DataSerie("_reliefweb_Key_Messages", "reliefweb-api"), new DataSerie("_reliefweb_Other", "reliefweb-api"), new DataSerie("_reliefweb_Press_Release",
					"reliefweb-api"), new DataSerie("_reliefweb_Press_Review", "reliefweb-api"), new DataSerie("_reliefweb_Reference_Map", "reliefweb-api"), new DataSerie(
					"_reliefweb_Situation_Report", "reliefweb-api"), new DataSerie("_reliefweb_Statement/Speech", "reliefweb-api"), new DataSerie("_reliefweb_Thematic_Map", "reliefweb-api"));

	// List of indicators relevant for country - 5 years
	public static final List<DataSerie> COUNTRY_5_YEARS_dataSeries = Arrays.asList(new DataSerie("_WPP2012_MORT_F02_CRUDE_DEATH_RATE", "esa-unpd-wpp2012"),
			new DataSerie("PSP050", "esa-unpd-wpp2012"), new DataSerie("PVH010", "esa-unpd-wpp2012"), new DataSerie("PVH050", "esa-unpd-wpp2012"), new DataSerie("PVH100", "esa-unpd-wpp2012"),
			new DataSerie("PVH150", "esa-unpd-wpp2012"));

	// List of indicators relevant for country - RW
	public static final List<DataSerie> COUNTRY_RW_dataSeries = Arrays.asList(new DataSerie("RW002", "RW"), new DataSerie("RW001", "RW"));

	// List of indicators relevant for country - FTS
	public static final List<DataSerie> COUNTRY_FTS_dataSeries = Arrays.asList(new DataSerie("FY010", "fts"), new DataSerie("FY020", "fts"), new DataSerie("FY040", "fts"), new DataSerie("FY190",
			"fts"), new DataSerie("FY200", "fts"), new DataSerie("FY210", "fts"), new DataSerie("FY240", "fts"), new DataSerie("FY360", "fts"), new DataSerie("FY370", "fts"), new DataSerie("FY380",
			"fts"), new DataSerie("FY500", "fts"), new DataSerie("FY510", "fts"), new DataSerie("FY520", "fts"), new DataSerie("FY540", "fts"), new DataSerie("FY550", "fts"), new DataSerie("FY620",
			"fts"), new DataSerie("FY630", "fts"), new DataSerie("FA010", "fts"), new DataSerie("FA140", "fts"));

	private final String indicatorCode;
	private final String sourceCode;

	public DataSerie(final String indicatorCode, final String sourceCode) {
		super();
		this.indicatorCode = indicatorCode;
		this.sourceCode = sourceCode;
	}

	public String getIndicatorCode() {
		return indicatorCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	@Override
	public int compareTo(final DataSerie o) {
		final int indicatorTypeComparison = indicatorCode.compareTo(o.indicatorCode);
		if (indicatorTypeComparison != 0) {
			return indicatorTypeComparison;
		} else {
			return sourceCode.compareTo(o.sourceCode);
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((indicatorCode == null) ? 0 : indicatorCode.hashCode());
		result = (prime * result) + ((sourceCode == null) ? 0 : sourceCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final DataSerie other = (DataSerie) obj;
		if (indicatorCode == null) {
			if (other.indicatorCode != null) {
				return false;
			}
		} else if (!indicatorCode.equals(other.indicatorCode)) {
			return false;
		}
		if (sourceCode == null) {
			if (other.sourceCode != null) {
				return false;
			}
		} else if (!sourceCode.equals(other.sourceCode)) {
			return false;
		}
		return true;
	}
}
