package org.ocha.dap.model.api;

public class CellDescriptor {
	
	private final String row;
	private final String column;
	
	public CellDescriptor(final String row, final String column) {
		super();
		this.row = row;
		this.column = column;
	}

	public String getRow() {
		return row;
	}

		public String getColumn() {
		return column;
	}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((column == null) ? 0 : column.hashCode());
			result = prime * result + ((row == null) ? 0 : row.hashCode());
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
			final CellDescriptor other = (CellDescriptor) obj;
			if (column == null) {
				if (other.column != null)
					return false;
			} else if (!column.equals(other.column))
				return false;
			if (row == null) {
				if (other.row != null)
					return false;
			} else if (!row.equals(other.row))
				return false;
			return true;
		}

	
}
