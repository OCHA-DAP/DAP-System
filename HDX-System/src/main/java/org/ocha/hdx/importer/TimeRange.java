package org.ocha.hdx.importer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;

public class TimeRange {

	private final Date start;
	private final Date end;
	private final Periodicity periodicity;

	/**
	 * Tries to smartly determine the timeRange from a single String
	 * 
	 * @param rangeAsString
	 * @throws IllegalArgumentException
	 *             if the provided String do not match any pattern, and cannot be interpreted as a valid TimeRange
	 */
	public TimeRange(final String rangeAsString) throws IllegalArgumentException {
		super();

		// First very straightforward implementation relies on the length
		if (rangeAsString == null) {
			throw new IllegalArgumentException("No Time Range could be build from a null String");
		}

		if (rangeAsString.length() == 4) {
			final DateTime dateTime = new DateTime(Integer.parseInt(rangeAsString), 1, 1, 0, 0);
			start = dateTime.toDate();
			end = dateTime.plusYears(1).toDate();

			periodicity = Periodicity.YEAR;
		} else if ((rangeAsString.length() == 8) && rangeAsString.endsWith("/P1Y")) {
			final DateTime dateTime = new DateTime(Integer.parseInt(rangeAsString.substring(0, 4)), 1, 1, 0, 0);
			start = dateTime.toDate();
			end = dateTime.plusYears(1).toDate();

			periodicity = Periodicity.YEAR;
		} else if ((rangeAsString.length() == 9) && rangeAsString.endsWith("/P10Y")) {
			final DateTime dateTime = new DateTime(Integer.parseInt(rangeAsString.substring(0, 4)), 1, 1, 0, 0);
			start = dateTime.toDate();
			end = dateTime.plusYears(10).toDate();

			periodicity = Periodicity.TEN_YEARS;
		}

		// Sample : 2013-02-13
		else if (rangeAsString.length() == 10) {
			final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = SDF.parse(rangeAsString);
			} catch (final ParseException e) {
				throw new IllegalArgumentException(String.format("No time range could be build from String : %s", rangeAsString));
			}
			final Calendar gc = new GregorianCalendar();
			gc.setTime(date);
			start = date;
			gc.add(Calendar.DATE, 1);
			end = gc.getTime() ;

			periodicity = Periodicity.DAY;
		} else

		{
			throw new IllegalArgumentException(String.format("No time range could be build from String : %s", rangeAsString));
		}

	}

	/**
	 * builds a time range of 1 year, from the January 1st of the year in param to the January 1st of the next year
	 * 
	 * @param year
	 */
	public TimeRange(final int year) {
		final DateTime dateTime = new DateTime(year, 1, 1, 0, 0);

		start = dateTime.toDate();
		end = dateTime.plusYears(1).toDate();
		periodicity = Periodicity.YEAR;
	}

	public TimeRange(final Date start, final Date end, final Periodicity periodicity) {
		super();
		this.start = start;
		this.end = end;
		this.periodicity = periodicity;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

	public String getTimeRangeAsSimpleString() {
		switch (periodicity) {
		case YEAR:
			return new Integer(new DateTime(start.getTime()).getYear()).toString();

		default:
			return start.toString();
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((end == null) ? 0 : end.hashCode());
		result = (prime * result) + ((periodicity == null) ? 0 : periodicity.hashCode());
		result = (prime * result) + ((start == null) ? 0 : start.hashCode());
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
		final TimeRange other = (TimeRange) obj;
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (periodicity != other.periodicity) {
			return false;
		}
		if (start == null) {
			if (other.start != null) {
				return false;
			}
		} else if (!start.equals(other.start)) {
			return false;
		}
		return true;
	}

}
