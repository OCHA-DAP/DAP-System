package org.ocha.dap.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

		return evaluateDummyCSVFile(revisionFile);

	}

	@Override
	public boolean evaluateDummyCSVFile(final File file) {
		try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
			final Map<String, Integer> totalForCountries = new HashMap<>();
			String line;
			while ((line = br.readLine()) != null) {

				// use comma as separator
				final String[] values = line.split(",");

				if(values.length != 4)
					return false;
				
				
				final String country = values[0];
				final Integer value = Integer.parseInt(values[2]);
				
				final Integer total = totalForCountries.get(country);
				if(total != null){
					totalForCountries.put(country, total + value);
				}else{
					totalForCountries.put(country, value);
				}

			}
			for(final Integer value : totalForCountries.values()){
				if(value != 100)
					return false;
			}
			
			return true;
		} catch (final IOException e) {
			return false;
		}
	}

}
