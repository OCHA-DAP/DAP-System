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

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Periodicity getPeriodicity() {
		return periodicity;
	}

}
