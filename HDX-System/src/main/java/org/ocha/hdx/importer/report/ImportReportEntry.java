package org.ocha.hdx.importer.report;

import java.io.Serializable;

import org.ocha.hdx.model.DataSerie;

public class ImportReportEntry implements Serializable {

	private static final long serialVersionUID = 4090405946027327873L;

	private final DataSerie dataserie;

	private int totalNbOfRecords;
	private int nbOfNewRecords;
	private int nbOfAlreadyExistingRecords;
	private int nbOfRecordsInError;

	public ImportReportEntry(final DataSerie dataserie) {
		super();
		this.dataserie = dataserie;
		totalNbOfRecords = 0;
		nbOfNewRecords = 0;
		nbOfAlreadyExistingRecords = 0;
		nbOfRecordsInError = 0;
	}

	public void addNewRecord() {
		totalNbOfRecords++;
		nbOfNewRecords++;
	}

	public void addAlreadyExistingRecord() {
		totalNbOfRecords++;
		nbOfAlreadyExistingRecords++;
	}

	public void addRecordInError() {
		totalNbOfRecords++;
		nbOfRecordsInError++;
	}

	public DataSerie getDataserie() {
		return dataserie;
	}

	public int getTotalNbOfRecords() {
		return totalNbOfRecords;
	}

	public int getNbOfNewRecords() {
		return nbOfNewRecords;
	}

	public int getNbOfAlreadyExistingRecords() {
		return nbOfAlreadyExistingRecords;
	}

	public int getNbOfRecordsInError() {
		return nbOfRecordsInError;
	}

}