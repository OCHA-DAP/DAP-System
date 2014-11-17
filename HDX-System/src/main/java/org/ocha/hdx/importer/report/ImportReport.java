package org.ocha.hdx.importer.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.tools.GSONBuilderWrapper;

public class ImportReport implements Serializable {

	private String id;
	private String revisionId;

	private static final long serialVersionUID = -7350328255412730901L;

	private String message;

	private int totalNbOfRecords;
	private int nbOfNewRecords;
	private int nbOfAlreadyExistingRecords;
	private int nbOfRecordsInError;

	private ImportStatus overallStatus;
	private final Map<DataSerie, ImportReportEntry> entries;

	public ImportReport(final String id, final String revisionId) {
		this();
		this.id = id;
		this.revisionId = revisionId;
	}

	public ImportReport() {
		super();
		this.totalNbOfRecords = 0;
		this.nbOfNewRecords = 0;
		this.nbOfAlreadyExistingRecords = 0;
		this.nbOfRecordsInError = 0;

		this.overallStatus = ImportStatus.SUCCESS;
		this.entries = new HashMap<>();
	}

	public ImportStatus getOverallStatus() {
		return this.overallStatus;
	}

	public boolean isSuccess() {
		return ImportStatus.SUCCESS.equals(this.overallStatus);
	}

	public void setErrorMessage(final String message) {
		this.message = message;
		this.overallStatus = ImportStatus.ERROR;
	}

	public void addNewRecord(final DataSerie dataSerie) {
		this.totalNbOfRecords++;
		this.nbOfNewRecords++;
		final ImportReportEntry entryForDataSerie = this.getEntryForDataSerie(dataSerie);
		entryForDataSerie.addNewRecord();
	}

	public void addAlreadyExistingRecord(final DataSerie dataSerie) {
		this.totalNbOfRecords++;
		this.nbOfAlreadyExistingRecords++;
		final ImportReportEntry entryForDataSerie = this.getEntryForDataSerie(dataSerie);
		entryForDataSerie.addAlreadyExistingRecord();
	}

	public void addRecordInError(final DataSerie dataSerie) {
		this.overallStatus = ImportStatus.ERROR;
		this.totalNbOfRecords++;
		this.nbOfRecordsInError++;
		final ImportReportEntry entryForDataSerie = this.getEntryForDataSerie(dataSerie);
		entryForDataSerie.addRecordInError();
	}

	private ImportReportEntry getEntryForDataSerie(final DataSerie dataSerie) {
		final ImportReportEntry importReportEntry = this.entries.get(dataSerie);
		if (importReportEntry != null) {
			return importReportEntry;
		} else {
			final ImportReportEntry newImportReportEntry = new ImportReportEntry(dataSerie);
			this.entries.put(dataSerie, newImportReportEntry);
			return newImportReportEntry;
		}
	}

	public String getId() {
		return this.id;
	}

	public String getRevisionId() {
		return this.revisionId;
	}

	public String getMessage() {
		return this.message;
	}

	public int getTotalNbOfRecords() {
		return this.totalNbOfRecords;
	}

	public int getNbOfNewRecords() {
		return this.nbOfNewRecords;
	}

	public int getNbOfAlreadyExistingRecords() {
		return this.nbOfAlreadyExistingRecords;
	}

	public int getNbOfRecordsInError() {
		return this.nbOfRecordsInError;
	}

	public Map<DataSerie, ImportReportEntry> getEntries() {
		return this.entries;
	}

	public String toJson() {
		return GSONBuilderWrapper.getGSON().toJson(this);
	}

	public static ImportReport fromJson(final String json) {
		return GSONBuilderWrapper.getGSON().fromJson(json, ImportReport.class);

	}

	/**
	 * @param id the id to set
	 */
	public void setId(final String id) {
		this.id = id;
	}

	/**
	 * @param revisionId the revisionId to set
	 */
	public void setRevisionId(final String revisionId) {
		this.revisionId = revisionId;
	}


}
