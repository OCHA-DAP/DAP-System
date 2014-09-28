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
		totalNbOfRecords = 0;
		nbOfNewRecords = 0;
		nbOfAlreadyExistingRecords = 0;
		nbOfRecordsInError = 0;

		this.overallStatus = ImportStatus.SUCCESS;
		entries = new HashMap<>();
	}

	public ImportStatus getOverallStatus() {
		return overallStatus;
	}

	public boolean isSuccess() {
		return ImportStatus.SUCCESS.equals(overallStatus);
	}

	public void setErrorMessage(final String message) {
		this.message = message;
		overallStatus = ImportStatus.ERROR;
	}

	public void addNewRecord(final DataSerie dataSerie) {
		totalNbOfRecords++;
		nbOfNewRecords++;
		final ImportReportEntry entryForDataSerie = getEntryForDataSerie(dataSerie);
		entryForDataSerie.addNewRecord();
	}

	public void addAlreadyExistingRecord(final DataSerie dataSerie) {
		totalNbOfRecords++;
		nbOfAlreadyExistingRecords++;
		final ImportReportEntry entryForDataSerie = getEntryForDataSerie(dataSerie);
		entryForDataSerie.addAlreadyExistingRecord();
	}

	public void addRecordInError(final DataSerie dataSerie) {
		overallStatus = ImportStatus.ERROR;
		totalNbOfRecords++;
		nbOfRecordsInError++;
		final ImportReportEntry entryForDataSerie = getEntryForDataSerie(dataSerie);
		entryForDataSerie.addRecordInError();
	}

	private ImportReportEntry getEntryForDataSerie(final DataSerie dataSerie) {
		final ImportReportEntry importReportEntry = entries.get(dataSerie);
		if (importReportEntry != null) {
			return importReportEntry;
		} else {
			final ImportReportEntry newImportReportEntry = new ImportReportEntry(dataSerie);
			entries.put(dataSerie, newImportReportEntry);
			return newImportReportEntry;
		}
	}

	public String getId() {
		return id;
	}

	public String getRevisionId() {
		return revisionId;
	}

	public String getMessage() {
		return message;
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

	public Map<DataSerie, ImportReportEntry> getEntries() {
		return entries;
	}

	public String toJson() {
		return GSONBuilderWrapper.getGSON().toJson(this);
	}

	public static ImportReport fromJson(final String json) {
		return GSONBuilderWrapper.getGSON().fromJson(json, ImportReport.class);

	}
}
