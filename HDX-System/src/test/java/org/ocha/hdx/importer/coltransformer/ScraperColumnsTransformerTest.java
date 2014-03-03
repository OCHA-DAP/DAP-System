package org.ocha.hdx.importer.coltransformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.ocha.hdx.config.ConfigurationConstants;
import org.ocha.hdx.persistence.entity.configs.AbstractConfigEntry;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;

public class ScraperColumnsTransformerTest {

	@Test
	public void testExpectedDatePatterns() {
		final Pattern expectedTimeFormatPattern = Pattern.compile(ScraperColumnsTransformer.EXPECTED_TIME_PATTERN);

		final String option1 = "YYYY";
		final String option2 = "YYYY/P1Y";
		final String option3 = "YYYY/P10Y";

		final Matcher matcher1 = expectedTimeFormatPattern.matcher(option1);

		assertTrue(matcher1.matches());
		System.out.println(" Groups1: " + matcher1.groupCount() + " - " + matcher1.group(2));
		assertNull(matcher1.group(ScraperColumnsTransformer.PERIODICITY_GROUP));

		final Matcher matcher2 = expectedTimeFormatPattern.matcher(option2);

		assertTrue(matcher2.matches());
		System.out.println(" Groups2: " + matcher2.groupCount() + " - " + matcher2.group(2));
		assertEquals("1", matcher2.group(ScraperColumnsTransformer.PERIODICITY_GROUP));

		final Matcher matcher3 = expectedTimeFormatPattern.matcher(option3);

		assertTrue(matcher3.matches());
		System.out.println(" Groups3: " + matcher3.groupCount() + " - " + matcher3.group(2));
		assertEquals("10", matcher3.group(ScraperColumnsTransformer.PERIODICITY_GROUP));

	}

	@Test
	public void testStartDatePattern() {
		final Pattern expectedTimeFormatPattern = Pattern.compile(ScraperColumnsTransformer.EXPECTED_START_TIME_PATTERN);

		final String option1 = "YYYY(+1)";
		final String option2 = "YYYY-01-01";
		final String option3 = "YYYY(-2)-20-11";

		final Matcher matcher1 = expectedTimeFormatPattern.matcher(option1);
		assertTrue(matcher1.matches());
		assertNull(matcher1.group(ScraperColumnsTransformer.DAY_GROUP));
		assertNull(matcher1.group(ScraperColumnsTransformer.MONTH_GROUP));
		assertEquals("+", matcher1.group(ScraperColumnsTransformer.SIGN_GROUP));
		assertEquals("1", matcher1.group(ScraperColumnsTransformer.OFFSET_GROUP));

		final Matcher matcher2 = expectedTimeFormatPattern.matcher(option2);
		assertTrue(matcher2.matches());
		assertEquals("01", matcher2.group(ScraperColumnsTransformer.DAY_GROUP));
		assertEquals("01", matcher2.group(ScraperColumnsTransformer.MONTH_GROUP));
		assertNull(matcher2.group(ScraperColumnsTransformer.SIGN_GROUP));
		assertNull(matcher2.group(ScraperColumnsTransformer.OFFSET_GROUP));


		final Matcher matcher3 = expectedTimeFormatPattern.matcher(option3);
		assertTrue(matcher3.matches());
		assertEquals("20", matcher3.group(ScraperColumnsTransformer.DAY_GROUP));
		assertEquals("11", matcher3.group(ScraperColumnsTransformer.MONTH_GROUP));
		assertEquals("-", matcher3.group(ScraperColumnsTransformer.SIGN_GROUP));
		assertEquals("2", matcher3.group(ScraperColumnsTransformer.OFFSET_GROUP));



	}
	@Test
	public void testActualDatePattern() {
		final Pattern pattern = Pattern.compile(ScraperColumnsTransformer.ACTUAL_DATE_PATTERN);

		final String option1	= "1995/P5Y";

		final Matcher matcher1		= pattern.matcher(option1);

		assertTrue(matcher1.matches());
		assertEquals("1995", matcher1.group(ScraperColumnsTransformer.YEAR_GROUP));

	}

	@Test
	public void testPeriodConfigurations () {
		final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

		final Map<String, AbstractConfigEntry> indConfig	= new HashMap<String, AbstractConfigEntry>();

		indConfig.put(ConfigurationConstants.EXPECTED_TIME_FORMAT, new AbstractConfigEntry(ConfigurationConstants.EXPECTED_TIME_FORMAT, "YYYY/P5Y") {});
		indConfig.put(ConfigurationConstants.EXPECTED_START_TIME_FORMAT, new AbstractConfigEntry(ConfigurationConstants.EXPECTED_START_TIME_FORMAT, "YYYY(-1)-20-11") {});

		final ScraperColumnsTransformer transformer	= new ScraperColumnsTransformer(null, indConfig, null);

		final String [] line = {"","","","1995/P5Y"};
		final Date startDate	= transformer.getStartDate(line);
		final LocalDate localStartDate	=	LocalDate.parse("20/11/1994", DATE_FORMATTER);
		assertEquals(localStartDate.toDate(), startDate);
		assertEquals(Periodicity.FIVE_YEARS, transformer.getPeriodicity(line));

		final Date endDate	= transformer.getEndDate(line);
		final LocalDate localEndDate	=	LocalDate.parse("20/11/1999", DATE_FORMATTER);
		assertEquals(localEndDate.toDate(),endDate);
	}

	@Test
	public void testExactDateConfiguration () {
		final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("dd/MM/yyyy");

		final Map<String, AbstractConfigEntry> indConfig	= new HashMap<String, AbstractConfigEntry>();

		indConfig.put(ConfigurationConstants.EXPECTED_TIME_FORMAT, new AbstractConfigEntry(ConfigurationConstants.EXPECTED_TIME_FORMAT, "dd-MM-yyyy") {});
		indConfig.put(ConfigurationConstants.EXPECTED_START_TIME_FORMAT, new AbstractConfigEntry(ConfigurationConstants.EXPECTED_START_TIME_FORMAT, "dd-MM-yyyy") {});

		final ScraperColumnsTransformer transformer	= new ScraperColumnsTransformer(null, indConfig, null);

		final String [] line = {"","","","13-02-2013"};
		final Date startDate	= transformer.getStartDate(line);
		final LocalDate localStartDate	=	LocalDate.parse("13/02/2013", DATE_FORMATTER);
		assertEquals(localStartDate.toDate(), startDate);
		assertEquals(Periodicity.NONE, transformer.getPeriodicity(line));

		final Date endDate	= transformer.getEndDate(line);
		assertEquals(localStartDate.toDate(),endDate);

	}
}
