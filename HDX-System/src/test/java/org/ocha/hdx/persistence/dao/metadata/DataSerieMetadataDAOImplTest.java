/**
 *
 */
package org.ocha.hdx.persistence.dao.metadata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.model.DataSerie;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata;
import org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper.DummyEntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author alexandru-m-g
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class DataSerieMetadataDAOImplTest {

	private static final int NUM_OF_ITEMS = 3;

	@Autowired
	private DataSerieMetadataDAO dataSerieMetadataDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private UnitDAO unitDAO;

	@Autowired
	DummyEntityCreatorWrapper dummyEntityCreatorWrapper;

	private Source source;

	private IndicatorType indicatorType;

	private DummyEntityCreator dummyEntityCreator;

	@Before
	public void setUp() throws Exception {
		dummyEntityCreator = dummyEntityCreatorWrapper.generateNewEntityCreator();
		dummyEntityCreator.createNeededIndicatorTypeAndSource();

		indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		source = sourceDAO.getSourceByCode("esa-unpd-WPP2012");
	}

	@After
	public void tearDown() throws Exception {

		dummyEntityCreator.deleteNeededIndicatorTypeAndSource();
	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAOImpl#listDataSerieMetadata()}.
	 */
	@Test
	@Transactional
	public final void testListDataSerieMetadata() {
		final List<DataSerieMetadata> initialList = dataSerieMetadataDAO.listDataSerieMetadata();
		assertEquals(0, initialList.size());

		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			final Text text = textDAO.createText("Dummy Value " + i);

			final DataSerieMetadata dataSerieMetadata = dataSerieMetadataDAO.createDataSerieMetadata(indicatorType, source, MetadataName.METHODOLOGY, text);
		}

		final List<DataSerieMetadata> modifiedList = dataSerieMetadataDAO.listDataSerieMetadata();

		assertEquals(NUM_OF_ITEMS, modifiedList.size());
		for (int i = 0; i < modifiedList.size(); i++) {
			final DataSerieMetadata dataSerieMetadata = modifiedList.get(i);

			assertEquals(source.getCode(), dataSerieMetadata.getSource().getCode());
			assertEquals(indicatorType.getCode(), dataSerieMetadata.getIndicatorType().getCode());
			assertEquals(MetadataName.METHODOLOGY, dataSerieMetadata.getEntryKey());
			assertEquals("Dummy Value " + i, dataSerieMetadata.getEntryValue().getDefaultValue());
		}
		for (final DataSerieMetadata dataSerieMetadata : modifiedList) {
			dataSerieMetadataDAO.deleteDataSerieMetadata(dataSerieMetadata.getId());
		}
		assertEquals(0, dataSerieMetadataDAO.listDataSerieMetadata().size());
	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAOImpl#listDataSerieMetadataByIndicatorTypeIdAndSourceId(long, long)}.
	 */
	@Test
	@Transactional
	public final void testListDataSerieMetadataByIndicatorTypeAndSource() {
		final Text src2Text = textDAO.createText("Source 2");
		sourceDAO.createSource("test-src-code2", src2Text, "www.test2.com", null);
		final Source source2 = sourceDAO.getSourceByCode("test-src-code2");

		final Text indType2Text = textDAO.createText("Indicator Type 2");

		final Text unitText = textDAO.createText("Text Unit 2");
		final Unit unit = unitDAO.createUnit("Text Unit 2", unitText);

		indicatorTypeDAO.createIndicatorType("indicator-type-2", indType2Text, unit, ValueType.NUMBER);
		final IndicatorType indicatorType2 = indicatorTypeDAO.getIndicatorTypeByCode("indicator-type-2");

		final List<DataSerieMetadata> initialList = dataSerieMetadataDAO.listDataSerieMetadata();
		assertEquals(0, initialList.size());

		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			final Text text = textDAO.createText("Dummy Value " + i);
			if ((i % 2) == 0) {
				dataSerieMetadataDAO.createDataSerieMetadata(indicatorType, source, MetadataName.METHODOLOGY, text);
			} else {
				dataSerieMetadataDAO.createDataSerieMetadata(indicatorType2, source2, MetadataName.METHODOLOGY, text);
			}
		}

		final List<DataSerieMetadata> modifiedList = dataSerieMetadataDAO.listDataSerieMetadataByIndicatorTypeIdAndSourceId(indicatorType2.getId(), source2.getId());

		assertEquals(NUM_OF_ITEMS / 2, modifiedList.size());
		for (int i = 0; i < modifiedList.size(); i++) {
			final int realIndex = (2 * i) + 1;
			final DataSerieMetadata dataSerieMetadata = modifiedList.get(i);

			assertEquals(source2.getCode(), dataSerieMetadata.getSource().getCode());
			assertEquals(indicatorType2.getCode(), dataSerieMetadata.getIndicatorType().getCode());
			assertEquals(MetadataName.METHODOLOGY, dataSerieMetadata.getEntryKey());
			assertEquals("Dummy Value " + realIndex, dataSerieMetadata.getEntryValue().getDefaultValue());
		}

		final List<DataSerieMetadata> modifiedList2 = dataSerieMetadataDAO.listDataSerieMetadataByIndicatorTypeCodeAndSourceCode(new DataSerie(indicatorType2.getCode(), source2.getCode()));

		assertEquals(NUM_OF_ITEMS / 2, modifiedList2.size());
		for (int i = 0; i < modifiedList2.size(); i++) {
			final int realIndex = (2 * i) + 1;
			final DataSerieMetadata dataSerieMetadata = modifiedList2.get(i);

			assertEquals(source2.getCode(), dataSerieMetadata.getSource().getCode());
			assertEquals(indicatorType2.getCode(), dataSerieMetadata.getIndicatorType().getCode());
			assertEquals(MetadataName.METHODOLOGY, dataSerieMetadata.getEntryKey());
			assertEquals("Dummy Value " + realIndex, dataSerieMetadata.getEntryValue().getDefaultValue());
		}

		for (final DataSerieMetadata dataSerieMetadata : dataSerieMetadataDAO.listDataSerieMetadata()) {
			dataSerieMetadataDAO.deleteDataSerieMetadata(dataSerieMetadata.getId());
		}
		assertEquals(0, dataSerieMetadataDAO.listDataSerieMetadata().size());

		indicatorTypeDAO.deleteIndicatorType(indicatorType2.getId());
		unitDAO.deleteUnit(unit.getId());
		textDAO.deleteText(indType2Text.getId());
		sourceDAO.deleteSource(source2.getId());
		textDAO.deleteText(src2Text.getId());
	}

	/**
	 * Test method for
	 * {@link org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAOImpl#createDataSerieMetadata(org.ocha.hdx.persistence.entity.curateddata.IndicatorType, org.ocha.hdx.persistence.entity.curateddata.Source, org.ocha.hdx.persistence.entity.metadata.DataSerieMetadata.MetadataName, org.ocha.hdx.persistence.entity.i18n.Text)}
	 * .
	 */
	@Test
	@Transactional
	public final void testCreateDataSerieMetadata() {
		final Text text = textDAO.createText("Dummy Value");
		final DataSerieMetadata dataSerieMetadata = dataSerieMetadataDAO.createDataSerieMetadata(indicatorType, source, MetadataName.METHODOLOGY, text);
		assertTrue(dataSerieMetadata.getId() > 0);

		final DataSerieMetadata dbDataSerieMetadata = dataSerieMetadataDAO.getDataSerieMetadataById(dataSerieMetadata.getId());
		assertNotNull(dbDataSerieMetadata);

		assertEquals(source.getCode(), dbDataSerieMetadata.getSource().getCode());
		assertEquals(indicatorType.getCode(), dbDataSerieMetadata.getIndicatorType().getCode());
		assertEquals(MetadataName.METHODOLOGY, dbDataSerieMetadata.getEntryKey());
		assertEquals("Dummy Value", dbDataSerieMetadata.getEntryValue().getDefaultValue());

		dataSerieMetadataDAO.deleteDataSerieMetadata(dbDataSerieMetadata.getId());

		assertNull(dataSerieMetadataDAO.getDataSerieMetadataById(dataSerieMetadata.getId()));

		textDAO.deleteText(text.getId());

	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAOImpl#deleteDataSerieMetadata(long)}.
	 */
	@Test
	@Transactional
	public final void testDeleteDataSerieMetadata() {
		final Text text = textDAO.createText("Dummy Value");
		final DataSerieMetadata dataSerieMetadata = dataSerieMetadataDAO.createDataSerieMetadata(indicatorType, source, MetadataName.METHODOLOGY, text);
		assertTrue(dataSerieMetadata.getId() > 0);

		final DataSerieMetadata dbDataSerieMetadata = dataSerieMetadataDAO.getDataSerieMetadataById(dataSerieMetadata.getId());
		assertNotNull(dbDataSerieMetadata);

		assertEquals(source.getCode(), dbDataSerieMetadata.getSource().getCode());
		assertEquals(indicatorType.getCode(), dbDataSerieMetadata.getIndicatorType().getCode());
		assertEquals(MetadataName.METHODOLOGY, dbDataSerieMetadata.getEntryKey());
		assertEquals("Dummy Value", dbDataSerieMetadata.getEntryValue().getDefaultValue());

		dataSerieMetadataDAO.deleteDataSerieMetadata(dbDataSerieMetadata.getId());

		assertNull(dataSerieMetadataDAO.getDataSerieMetadataById(dataSerieMetadata.getId()));
		textDAO.deleteText(text.getId());
	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.DataSerieMetadataDAOImpl#updateDataSerieMetadata(long, org.ocha.hdx.persistence.entity.i18n.Text)}.
	 */
	@Test
	@Transactional
	public final void testUpdateDataSerieMetadata() {
		final Text text = textDAO.createText("Dummy Value");
		final DataSerieMetadata dataSerieMetadata = dataSerieMetadataDAO.createDataSerieMetadata(indicatorType, source, MetadataName.METHODOLOGY, text);
		assertTrue(dataSerieMetadata.getId() > 0);

		final DataSerieMetadata dbDataSerieMetadata = dataSerieMetadataDAO.getDataSerieMetadataById(dataSerieMetadata.getId());
		assertNotNull(dbDataSerieMetadata);

		assertEquals(source.getCode(), dbDataSerieMetadata.getSource().getCode());
		assertEquals(indicatorType.getCode(), dbDataSerieMetadata.getIndicatorType().getCode());
		assertEquals(MetadataName.METHODOLOGY, dbDataSerieMetadata.getEntryKey());
		assertEquals("Dummy Value", dbDataSerieMetadata.getEntryValue().getDefaultValue());

		final Text text2 = textDAO.createText("Dummy Value 2");
		dataSerieMetadataDAO.updateDataSerieMetadata(dbDataSerieMetadata.getId(), text2);

		final DataSerieMetadata modifiedDataSerieMetadata = dataSerieMetadataDAO.getDataSerieMetadataById(dataSerieMetadata.getId());
		assertNotNull(modifiedDataSerieMetadata);
		assertEquals("Dummy Value 2", modifiedDataSerieMetadata.getEntryValue().getDefaultValue());

		dataSerieMetadataDAO.updateDataSerieMetadata(modifiedDataSerieMetadata.getIndicatorType().getCode(), modifiedDataSerieMetadata.getSource().getCode(), modifiedDataSerieMetadata.getEntryKey()
				.toString(), "Dummy Value 3");
		final DataSerieMetadata remodifiedDataSerieMetadata = dataSerieMetadataDAO.getDataSerieMetadataById(modifiedDataSerieMetadata.getId());
		assertNotNull(remodifiedDataSerieMetadata);
		assertEquals(modifiedDataSerieMetadata.getId(), remodifiedDataSerieMetadata.getId());
		assertEquals("Dummy Value 3", remodifiedDataSerieMetadata.getEntryValue().getDefaultValue());

		dataSerieMetadataDAO.deleteDataSerieMetadata(modifiedDataSerieMetadata.getId());
		textDAO.deleteText(text.getId());
	}
}
