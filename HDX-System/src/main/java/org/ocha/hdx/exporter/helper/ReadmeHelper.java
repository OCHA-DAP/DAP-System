package org.ocha.hdx.exporter.helper;

import java.util.List;

import org.ocha.hdx.exporter.QueryData;
import org.ocha.hdx.exporter.helper.ReadmeHelperImpl.ReadmeSentence;

/**
 * Helper interface for the readme parts of reports.
 * @author bmichiels
 */
public interface ReadmeHelper {
	public List<ReadmeSentence> getReadme(final Class<? extends QueryData> clazz);
}
