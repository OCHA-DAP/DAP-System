package org.ocha.dap.validation;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.ocha.dap.model.ValidationReport;
import org.ocha.dap.model.ValidationReportEntry;
import org.ocha.dap.model.ValidationStatus;
import org.ocha.dap.persistence.entity.ckan.CKANDataset;
import org.ocha.dap.tools.IOTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScraperValidator implements DAPValidator {

	private static final Logger log = LoggerFactory.getLogger(ScraperValidator.class);

	@Override
	public ValidationReport evaluateFile(final File file) {
		final ValidationReport report = new ValidationReport(CKANDataset.Type.SCRAPER);
		extractZipContent(file);
		final File parent = file.getParentFile();

		final File datasetFile = new File(parent, "dataset.csv");
		if (datasetFile.exists()) {
			report.addEntry(ValidationStatus.SUCCESS, "dataset.csv does exist");
			report.addEntries(validateDatasetFile(datasetFile));
		} else {
			report.addEntry(ValidationStatus.ERROR, "dataset.csv does not exist");
		}

		final File indicatorFile = new File(parent, "indicator.csv");
		if (indicatorFile.exists()) {
			report.addEntry(ValidationStatus.SUCCESS, "indicator.csv does exist");
			report.addEntries(validateIndicatorFile(indicatorFile));
		} else {
			report.addEntry(ValidationStatus.ERROR, "indicator.csv does not exist");
		}

		final File valueFile = new File(parent, "value.csv");
		if (valueFile.exists()) {
			report.addEntry(ValidationStatus.SUCCESS, "value.csv does exist");
			report.addEntries(validateValueFile(valueFile));
		} else {
			report.addEntry(ValidationStatus.ERROR, "value.csv does not exist");
		}

		return report;
	}

	List<ValidationReportEntry> validateDatasetFile(final File datasetFile) {
		final List<ValidationReportEntry> result = new ArrayList<>();
		try (final BufferedReader br = new BufferedReader(new FileReader(datasetFile))) {
			String line;
			// first validating first line
			line = br.readLine();
			// use comma as separator
			final String[] values = line.split(",");
			if (values.length != 4 || !values[0].equals("dsID") || !values[1].equals("last_updated") || !values[2].equals("last_scraped") || !values[3].equals("name")) {
				result.add(new ValidationReportEntry(ValidationStatus.ERROR, "Incorrect first line for Dataset file"));
			}
			while ((line = br.readLine()) != null) {
				if (values.length != 4) {
					result.add(new ValidationReportEntry(ValidationStatus.ERROR, String.format(
							"A ligne contains an incorrect number of values, expected : 4, actual : %d", values.length)));
				}
			}
		} catch (final IOException e) {
			result.add(new ValidationReportEntry(ValidationStatus.ERROR, "Error caused by an exception"));
		}
		return result;

	}

	private List<ValidationReportEntry> validateIndicatorFile(final File indicatorFile) {
		final List<ValidationReportEntry> result = new ArrayList<>();
		// TODO
		return result;

	}

	private List<ValidationReportEntry> validateValueFile(final File valueFile) {
		final List<ValidationReportEntry> result = new ArrayList<>();
		// TODO
		return result;

	}

	private void extractZipContent(final File zipFile) {
		final int BUFFER = 2048;

		ZipFile zip = null;
		try {
			final File parent = zipFile.getParentFile();
			zip = new ZipFile(zipFile);

			final Enumeration<? extends ZipEntry> zipFileEntries = zip.entries();

			BufferedInputStream is = null;
			FileOutputStream fos = null;
			BufferedOutputStream dest = null;
			try {
				// Process each entry
				while (zipFileEntries.hasMoreElements()) {
					// grab a zip file entry
					final ZipEntry entry = zipFileEntries.nextElement();
					final String currentEntry = entry.getName();
					final File destFile = new File(parent, currentEntry);
					// destFile = new File(newPath, destFile.getName());
					final File destinationParent = destFile.getParentFile();

					// create the parent directory structure if needed
					if (destinationParent.mkdirs()) {
						log.debug(String.format("Failed to perform mkdirs for path : %s", destinationParent.getAbsolutePath()));
					}

					if (!entry.isDirectory()) {
						is = new BufferedInputStream(zip.getInputStream(entry));
						int currentByte;
						// establish buffer for writing file
						final byte data[] = new byte[BUFFER];

						// write the current file to disk
						fos = new FileOutputStream(destFile);
						dest = new BufferedOutputStream(fos, BUFFER);

						// read and write until last byte is encountered
						while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
							dest.write(data, 0, currentByte);
						}
						dest.flush();
					}
				}
			} catch (final Exception e) {
				log.debug(e.toString(), e);
			} finally {
				IOTools.closeResource(is);
				IOTools.closeResource(fos);
				IOTools.closeResource(dest);
			}
		} catch (final Exception e) {
			log.debug(e.toString(), e);
		} finally {
			IOTools.closeResource(zip);
		}
	}

}
