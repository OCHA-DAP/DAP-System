/**
 *
 */
package org.ocha.hdx.persistence.dao.currateddata;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.model.api2util.IntermediaryIndicatorValue;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper;
import org.ocha.hdx.validation.util.DummyEntityCreatorWrapper.DummyEntityCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author alexandru-m-g
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class IndicatorDaoImplGeneralFilterTest {

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private DummyEntityCreatorWrapper dummyEntityCreatorWrapper;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	private DummyEntityCreator entityCreator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		this.entityCreator = this.dummyEntityCreatorWrapper.generateNewEntityCreator();

		this.entityCreator.createNeededIndicatorTypeAndSource();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.entityCreator.deleteNeededIndicatorTypeAndSource();
	}

	/**
	 * Test method for {@link org.ocha.hdx.persistence.dao.currateddata.IndicatorDAOImpl#listIndicatorsByCriteria(java.util.List, java.util.List, java.util.List, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String)}.
	 */
	@Test
	public final void testListIndicatorsByCriteria() {
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");

		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();

		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012, date2012, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS,
				"http://www.example.com", importFromCKAN);

		final Source src2 = this.sourceDAO.getSourceByCode("acled");
		final IndicatorType type2 = this.indicatorTypeDAO.getIndicatorTypeByCode("FY620");
		final Entity entity2 = this.entityDAO.getEntityByCodeAndType("Ro", "country");

		this.indicatorDAO.createIndicator(src2, entity2, type2, date2013, date2013, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS,
				"http://www.example2.com", importFromCKAN);

		final List<IntermediaryIndicatorValue> indicatorList1 =
				this.indicatorDAO.listIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fi"), 2012, 2012, 0, 10, "en");
		assertNotNull(indicatorList1);
		assertTrue("There should be exactly 1 indicator matching the criteria", indicatorList1.size() == 1);

		final List<IntermediaryIndicatorValue> indicatorList2 =
				this.indicatorDAO.listIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("fake"), Arrays.asList("Fi"), 2012, 2012, 0, 10, "en");
		assertTrue("There should be zero indicators matching the criteria", indicatorList2.size() == 0);

		final List<IntermediaryIndicatorValue> indicatorList3 =
				this.indicatorDAO.listIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fake"), 2012, 2012, 0, 10, "en");
		assertTrue("There should be zero indicators matching the criteria", indicatorList3.size() == 0);

		final List<IntermediaryIndicatorValue> indicatorList4 =
				this.indicatorDAO.listIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fi"), 2013, 2013, 0, 10, "en");
		assertTrue("There should be zero indicators matching the criteria", indicatorList4.size() == 0);

		final List<IntermediaryIndicatorValue> indicatorList5 =
				this.indicatorDAO.listIndicatorsByCriteria(Arrays.asList("PSP080", "FY620"), Arrays.asList("esa-unpd-WPP2012", "acled"), Arrays.asList("Ro","Fi"), 2012, 2013, 0, 10, "en");
		assertTrue("There should be exactly 2 indicators matching the criteria", indicatorList5.size() == 2);

		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}


	@Test
	public final void testCountIndicatorsByCriteria() {
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");

		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();

		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012, date2012, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS,
				"http://www.example.com", importFromCKAN);

		final Source src2 = this.sourceDAO.getSourceByCode("acled");
		final IndicatorType type2 = this.indicatorTypeDAO.getIndicatorTypeByCode("FY620");
		final Entity entity2 = this.entityDAO.getEntityByCodeAndType("Ro", "country");

		this.indicatorDAO.createIndicator(src2, entity2, type2, date2013, date2013, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS,
				"http://www.example2.com", importFromCKAN);

		final Long count1 =
				this.indicatorDAO.countIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fi"), 2012, 2012);
		assertNotNull(count1);
		assertTrue("There should be exactly 1 indicator matching the criteria", count1 == 1);

		final Long count2 =
				this.indicatorDAO.countIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("fake"), Arrays.asList("Fi"), 2012, 2012);
		assertTrue("There should be zero indicators matching the criteria", count2 == 0);

		final Long count3 =
				this.indicatorDAO.countIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fake"), 2012, 2012);
		assertTrue("There should be zero indicators matching the criteria", count3 == 0);

		final Long count4 =
				this.indicatorDAO.countIndicatorsByCriteria(Arrays.asList("PSP080"), Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fi"), 2013, 2013);
		assertTrue("There should be zero indicators matching the criteria", count4 == 0);

		final Long count5 =
				this.indicatorDAO.countIndicatorsByCriteria(Arrays.asList("PSP080", "FY620"), Arrays.asList("esa-unpd-WPP2012", "acled"), Arrays.asList("Ro","Fi"), 2012, 2013);
		assertTrue("There should be exactly 2 indicators matching the criteria", count5 == 2);

		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}

}
