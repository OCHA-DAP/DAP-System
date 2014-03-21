package org.ocha.hdx.exporter.helper;

import java.util.List;

import org.ocha.hdx.exporter.Exporter_XLSX;
import org.ocha.hdx.exporter.QueryData;
import org.ocha.hdx.exporter.helper.ReadmeHelperImpl.ReadmeSentence;

public interface ReadmeHelper {
	public List<ReadmeSentence> getReadme(final Class<? extends Exporter_XLSX<? extends QueryData>> clazz);
}
