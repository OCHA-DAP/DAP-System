/**
 *
 */
package org.ocha.hdx.persistence.dao.metadata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.EntryKey;

/**
 * @author alexandru-m-g
 * 
 */
public interface DataSerieMetadataDAO {
	public List<DataSerieMetadata> listDataSerieMetadata();

	public List<DataSerieMetadata> listDataSerieMetadataByIndicatorTypeIdAndSourceId(long indicatorTypeId, long sourceId);

	public List<DataSerieMetadata> listDataSerieMetadataByIndicatorTypeCodeAndSourceCode(String indicatorTypeCode, String sourceCode);

	public DataSerieMetadata createDataSerieMetadata(IndicatorType type, Source source, EntryKey key, Text value);

	public void deleteDataSerieMetadata(long id);

	public DataSerieMetadata updateDataSerieMetadata(long id, Text value);

	public DataSerieMetadata updateDataSerieMetadata(String indicatorTypeCode, String sourceCode, String entryKey, String defaultValue);

	public DataSerieMetadata getDataSerieMetadataById(long id);

	public DataSerieMetadata getDataSerieMetadataByIndicatorTypeCodeAndSourceCodeAndEntryKey(final String indicatorTypeCode, final String sourceCode, final EntryKey entryKey);
}