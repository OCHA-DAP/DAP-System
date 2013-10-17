package org.ocha.dap.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileEvaluatorAndExtractorImpl implements FileEvaluatorAndExtractor {

	private final File stagingDirectory;

	public FileEvaluatorAndExtractorImpl(final File stagingDirectory) {
		super();
		if (!stagingDirectory.isDirectory()) {
			throw new IllegalArgumentException("staging  directory doesn't exist: " + stagingDirectory.getAbsolutePath());
		}

		this.stagingDirectory = stagingDirectory;
	}

	@Override
	public boolean evaluateDummyCSVFile(final String id, final String revision_id) {
		final File reourceFolder = new File(stagingDirectory, id);
		final File revisionFile = new File(reourceFolder, revision_id);

		try (final BufferedReader br = new BufferedReader(new FileReader(revisionFile))) {

			// FIXME finish this
			return true;
		} catch (final IOException e) {
			return false;
		}
	}

}
