/**
 *
 */
package org.ocha.hdx.persistence.dao.metadata;

import java.util.List;

import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData.EntryKey;

/**
 * @author alexandru-m-g
 * 
 */
public interface AdditionalDataDao {
	public List<AdditionalData> listAdditionalData();

	public List<AdditionalData> listAdditionalDataByIndicatorTypeIdAndSourceId(long indicatorTypeId, long sourceId);

	public List<AdditionalData> listAdditionalDataByIndicatorTypeCodeAndSourceCode(String indicatorTypeCode, String sourceCode);

	public AdditionalData createAdditionalData(IndicatorType type, Source source, EntryKey key, Text value);

	public void deleteAdditionalData(long id);

	public AdditionalData updateAdditionalData(long id, Text value);

	public AdditionalData getAdditionalDataById(long id);

	public AdditionalData getAdditionalDataByIndicatorTypeCodeAndSourceCodeAndEntryKey(final String indicatorTypeCode, final String sourceCode, final EntryKey entryKey);
}
