package org.ocha.dap.model.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataTable {

	private final List<String> firstRow = new ArrayList<>();
	private final Map<String, String[]> otherRows = new HashMap<>();

	public DataTable(final Set<String> columns) {
		super();
		firstRow.addAll(columns);
	}

	public void addRow(final String firstValueOfRow) {
		final String[] newRow = new String[firstRow.size()];
		newRow[0] = firstValueOfRow;
		this.otherRows.put(firstValueOfRow, newRow);
	}

	public void addValue(final String row, final String column, final String value) {
		otherRows.get(row)[firstRow.indexOf(column)] = value;
	}

	public List<String> getFirstRow() {
		return firstRow;
	}

	public Map<String, String[]> getOtherRows() {
		return otherRows;
	}

}
