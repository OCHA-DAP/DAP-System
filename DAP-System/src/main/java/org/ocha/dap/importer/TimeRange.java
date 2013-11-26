package org.ocha.dap.importer;

import java.util.Date;

import org.joda.time.DateTime;
import org.ocha.dap.persistence.entity.curateddata.Indicator.Periodicity;

public class TimeRange {

	private final Date start;
	private final Date end;
	private final Periodicity periodicity;

	public TimeRange(final String rangeAsString) throws IllegalArgumentException {
		super();

		// First very straightforward implementation relies on the length
		if (rangeAsString == null)
			throw new IllegalArgumentException("No Time Range could be build from a null String");

		if (rangeAsString.length() != 4)
			throw new IllegalArgumentException("Only 4 digits String are allowed for now");

		final DateTime dateTime = new DateTime(Integer.parseInt(rangeAsString), 1, 1, 0, 0);
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
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((periodicity == null) ? 0 : periodicity.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TimeRange other = (TimeRange) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (periodicity != other.periodicity)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

}
