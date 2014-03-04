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
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData;
import org.ocha.hdx.persistence.entity.metadata.AdditionalData.EntryKey;
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
public class AdditionalDataDaoImplTest {

	private static final int NUM_OF_ITEMS = 3;

	@Autowired
	private AdditionalDataDao additionalDataDao;

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
		this.dummyEntityCreator = this.dummyEntityCreatorWrapper.generateNewEntityCreator();
		this.dummyEntityCreator.createNeededIndicatorTypeAndSource();

		this.indicatorType		= this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		this.source				= this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
	}

	@After
	public void tearDown() throws Exception {

		this.dummyEntityCreator.deleteNeededIndicatorTypeAndSource();
	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.AdditionalDataDaoImpl#listAdditionalData()}.
	 */
	@Test
	@Transactional
	public final void testListAdditionalData() {
		final List<AdditionalData> initialList = this.additionalDataDao.listAdditionalData();
		assertEquals(0, initialList.size());

		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			final Text text = this.textDAO.createText("Dummy Value " + i);

			final AdditionalData additionalData	= this.additionalDataDao.createAdditionalData(this.indicatorType, this.source, EntryKey.METHODOLOGY, text);
		}

		final List<AdditionalData> modifiedList = this.additionalDataDao.listAdditionalData();

		assertEquals(NUM_OF_ITEMS, modifiedList.size());
		for (int i = 0; i < modifiedList.size(); i++) {
			final AdditionalData additionalData = modifiedList.get(i);

			assertEquals(this.source.getCode(),additionalData.getSource().getCode() );
			assertEquals(this.indicatorType.getCode(),additionalData.getIndicatorType().getCode() );
			assertEquals(EntryKey.METHODOLOGY,additionalData.getEntryKey() );
			assertEquals("Dummy Value " + i,additionalData.getEntryValue().getDefaultValue() );
		}
		for (final AdditionalData additionalData : modifiedList) {
			this.additionalDataDao.deleteAdditionalData(additionalData.getId());
		}
		assertEquals(0, this.additionalDataDao.listAdditionalData().size());
	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.AdditionalDataDaoImpl#listAdditionalDataByIndicatorTypeIdAndSourceId(long, long)}.
	 */
	@Test
	@Transactional
	public final void testListAdditionalDataByIndicatorTypeAndSource() {
		final Text src2Text = this.textDAO.createText("Source 2");
		this.sourceDAO.createSource("test-src-code2", src2Text, "www.test2.com");
		final Source source2		= this.sourceDAO.getSourceByCode("test-src-code2");

		final Text indType2Text = this.textDAO.createText("Indicator Type 2");

		final Text unitText = this.textDAO.createText("Text Unit 2");
		final Unit unit 	= this.unitDAO.createUnit("Text Unit 2", unitText);

		this.indicatorTypeDAO.createIndicatorType("indicator-type-2", indType2Text, unit, ValueType.NUMBER);
		final IndicatorType indicatorType2	= this.indicatorTypeDAO.getIndicatorTypeByCode("indicator-type-2");


		final List<AdditionalData> initialList = this.additionalDataDao.listAdditionalData();
		assertEquals(0, initialList.size());

		for (int i = 0; i < NUM_OF_ITEMS; i++) {
			final Text text = this.textDAO.createText("Dummy Value " + i);
			if ( i%2 == 0 ) {
				this.additionalDataDao.createAdditionalData(this.indicatorType, this.source, EntryKey.METHODOLOGY, text);
			} else {
				this.additionalDataDao.createAdditionalData(indicatorType2, source2, EntryKey.METHODOLOGY, text);
			}
		}

		final List<AdditionalData> modifiedList = this.additionalDataDao.listAdditionalDataByIndicatorTypeIdAndSourceId(indicatorType2.getId(), source2.getId());

		assertEquals(NUM_OF_ITEMS/2, modifiedList.size());
		for (int i = 0; i < modifiedList.size(); i++) {
			final int realIndex	= 2*i +1;
			final AdditionalData additionalData = modifiedList.get(i);

			assertEquals(source2.getCode(),additionalData.getSource().getCode() );
			assertEquals(indicatorType2.getCode(),additionalData.getIndicatorType().getCode() );
			assertEquals(EntryKey.METHODOLOGY,additionalData.getEntryKey() );
			assertEquals("Dummy Value " + realIndex,additionalData.getEntryValue().getDefaultValue() );
		}

		final List<AdditionalData> modifiedList2 = this.additionalDataDao.listAdditionalDataByIndicatorTypeCodeAndSourceCode(indicatorType2.getCode(), source2.getCode());

		assertEquals(NUM_OF_ITEMS/2, modifiedList2.size());
		for (int i = 0; i < modifiedList2.size(); i++) {
			final int realIndex	= 2*i +1;
			final AdditionalData additionalData = modifiedList2.get(i);

			assertEquals(source2.getCode(),additionalData.getSource().getCode() );
			assertEquals(indicatorType2.getCode(),additionalData.getIndicatorType().getCode() );
			assertEquals(EntryKey.METHODOLOGY,additionalData.getEntryKey() );
			assertEquals("Dummy Value " + realIndex,additionalData.getEntryValue().getDefaultValue() );
		}

		for (final AdditionalData additionalData : this.additionalDataDao.listAdditionalData()) {
			this.additionalDataDao.deleteAdditionalData(additionalData.getId());
		}
		assertEquals(0, this.additionalDataDao.listAdditionalData().size());

		this.indicatorTypeDAO.deleteIndicatorType(indicatorType2.getId());
		this.unitDAO.deleteUnit(unit.getId());
		this.textDAO.deleteText(indType2Text.getId());
		this.sourceDAO.deleteSource(source2.getId());
		this.textDAO.deleteText(src2Text.getId());
	}


	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.AdditionalDataDaoImpl#createAdditionalData(org.ocha.hdx.persistence.entity.curateddata.IndicatorType, org.ocha.hdx.persistence.entity.curateddata.Source, org.ocha.hdx.persistence.entity.metadata.AdditionalData.EntryKey, org.ocha.hdx.persistence.entity.i18n.Text)}.
	 */
	@Test
	@Transactional
	public final void testCreateAdditionalData() {
		final Text text = this.textDAO.createText("Dummy Value");
		final AdditionalData additionalData	= this.additionalDataDao.createAdditionalData(this.indicatorType, this.source, EntryKey.METHODOLOGY, text);
		assertTrue(additionalData.getId() > 0);

		final AdditionalData dbAdditionalData	= this.additionalDataDao.getAdditionalDataById(additionalData.getId());
		assertNotNull(dbAdditionalData);

		assertEquals(this.source.getCode(),dbAdditionalData.getSource().getCode() );
		assertEquals(this.indicatorType.getCode(),dbAdditionalData.getIndicatorType().getCode() );
		assertEquals(EntryKey.METHODOLOGY,dbAdditionalData.getEntryKey() );
		assertEquals("Dummy Value",dbAdditionalData.getEntryValue().getDefaultValue() );

		this.additionalDataDao.deleteAdditionalData(dbAdditionalData.getId());

		assertNull(this.additionalDataDao.getAdditionalDataById(additionalData.getId()));

		this.textDAO.deleteText(text.getId());

	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.AdditionalDataDaoImpl#deleteAdditionalData(long)}.
	 */
	@Test
	@Transactional
	public final void testDeleteAdditionalData() {
		final Text text = this.textDAO.createText("Dummy Value");
		final AdditionalData additionalData	= this.additionalDataDao.createAdditionalData(this.indicatorType, this.source, EntryKey.METHODOLOGY, text);
		assertTrue(additionalData.getId() > 0);

		final AdditionalData dbAdditionalData	= this.additionalDataDao.getAdditionalDataById(additionalData.getId());
		assertNotNull(dbAdditionalData);

		assertEquals(this.source.getCode(),dbAdditionalData.getSource().getCode() );
		assertEquals(this.indicatorType.getCode(),dbAdditionalData.getIndicatorType().getCode() );
		assertEquals(EntryKey.METHODOLOGY,dbAdditionalData.getEntryKey() );
		assertEquals("Dummy Value",dbAdditionalData.getEntryValue().getDefaultValue() );

		this.additionalDataDao.deleteAdditionalData(dbAdditionalData.getId());

		assertNull(this.additionalDataDao.getAdditionalDataById(additionalData.getId()));
		this.textDAO.deleteText(text.getId());
	}


	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.metadata.AdditionalDataDaoImpl#updateAdditionalData(long, org.ocha.hdx.persistence.entity.i18n.Text)}.
	 */
	@Test
	@Transactional
	public final void testUpdateAdditionalData() {
		final Text text = this.textDAO.createText("Dummy Value");
		final AdditionalData additionalData	= this.additionalDataDao.createAdditionalData(this.indicatorType, this.source, EntryKey.METHODOLOGY, text);
		assertTrue(additionalData.getId() > 0);

		final AdditionalData dbAdditionalData	= this.additionalDataDao.getAdditionalDataById(additionalData.getId());
		assertNotNull(dbAdditionalData);

		assertEquals(this.source.getCode(),dbAdditionalData.getSource().getCode() );
		assertEquals(this.indicatorType.getCode(),dbAdditionalData.getIndicatorType().getCode() );
		assertEquals(EntryKey.METHODOLOGY,dbAdditionalData.getEntryKey() );
		assertEquals("Dummy Value",dbAdditionalData.getEntryValue().getDefaultValue() );

		final Text text2 = this.textDAO.createText("Dummy Value 2");
		this.additionalDataDao.updateAdditionalData(dbAdditionalData.getId(), text2);

		final AdditionalData modifiedAdditionalData	= this.additionalDataDao.getAdditionalDataById(additionalData.getId());
		assertNotNull(modifiedAdditionalData);
		assertEquals("Dummy Value 2",modifiedAdditionalData.getEntryValue().getDefaultValue() );

		this.additionalDataDao.deleteAdditionalData(modifiedAdditionalData.getId());
		this.textDAO.deleteText(text.getId());
	}

}
