/**
 *
 */
package org.ocha.hdx.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.annotation.Resource;

import org.joda.time.LocalDateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.PeriodType;
import org.ocha.hdx.model.api2util.RequestParamsWrapper.SortingOption;
import org.ocha.hdx.model.validation.ValidationStatus;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
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

import com.google.common.cache.LoadingCache;

/**
 * @author alexandru-m-g
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class ApiV2BackendServiceImplCacheTest {

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

	@Autowired
	private ApiV2BackendService apiV2BackendService;

	@Resource
	LoadingCache<RequestParamsWrapper, ApiResultWrapper<ApiIndicatorValue>> indicatorResultCache;

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

	@Test
	public final void testListIndicatorsByCriteriaWithPagination() {
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");

		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();

		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012, date2012, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS, "http://www.example.com",
				importFromCKAN);

		final Source src2 = this.sourceDAO.getSourceByCode("acled");
		final IndicatorType type2 = this.indicatorTypeDAO.getIndicatorTypeByCode("FY620");
		final Entity entity2 = this.entityDAO.getEntityByCodeAndType("Ro", "country");

		this.indicatorDAO.createIndicator(src2, entity2, type2, date2013, date2013, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS, "http://www.example2.com",
				importFromCKAN);

		this.indicatorDAO.createIndicator(src2, entity2, type2, date2012, date2013, Periodicity.MONTH, new IndicatorValue(30000.0), "30000$", ValidationStatus.SUCCESS, "http://www.example3.com",
				importFromCKAN);

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper1 = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080", "FY620"),
				Arrays.asList("esa-unpd-WPP2012", "acled"), null, Arrays.asList("Ro", "Fi"), 2012, 2013, null, null, 1, 2, "en");
		assertNotNull(resultWrapper1);
		assertEquals("There should be 3 indicators matching the criteria in total", new Integer(3), resultWrapper1.getTotalCount());
		assertEquals("There should be exactly 1 indicator matching the criteria on the requested page", 1, resultWrapper1.getResults().size());
		assertEquals("Total number of pages should be 2", new Integer(2), resultWrapper1.getTotalNumOfPages());
		assertEquals("Current page should be 1", new Integer(1), resultWrapper1.getCurrentPage());

		assertEquals("The cache should not be hit", 0, this.indicatorResultCache.stats().hitCount());
		final ApiResultWrapper<ApiIndicatorValue> resultWrapper2 = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080", "FY620"),
				Arrays.asList("esa-unpd-WPP2012", "acled"), null, Arrays.asList("Ro", "Fi"), 2012, 2013, null, null, 1, 2, "en");

		assertEquals("The cache should have been hit once", 1, this.indicatorResultCache.stats().hitCount());

		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}

	@Test
	public final void testListIndicatorsByLatestYearCriteria() {
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");

		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();

		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012, date2012, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS, "http://www.example.com",
				importFromCKAN);

		final Source src2 = this.sourceDAO.getSourceByCode("acled");
		final IndicatorType type2 = this.indicatorTypeDAO.getIndicatorTypeByCode("FY620");
		final Entity entity2 = this.entityDAO.getEntityByCodeAndType("Ro", "country");

		this.indicatorDAO.createIndicator(src2, entity2, type2, date2013, date2013, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS, "http://www.example2.com",
				importFromCKAN);

		this.indicatorDAO.createIndicator(src2, entity2, type2, date2012, date2013, Periodicity.MONTH, new IndicatorValue(30000.0), "30000$", ValidationStatus.SUCCESS, "http://www.example3.com",
				importFromCKAN);

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper1 = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080", "FY620"),
				Arrays.asList("esa-unpd-WPP2012", "acled"), null, Arrays.asList("Ro", "Fi"), null, null, PeriodType.LATEST_YEAR, null, 0, 2, "en");
		assertNotNull(resultWrapper1);
		assertEquals("There should be 1 indicator matching the criteria in total", new Integer(1), resultWrapper1.getTotalCount());
		assertEquals("There should be exactly 1 indicator matching the criteria on the requested page", 1, resultWrapper1.getResults().size());
		assertEquals("Total number of pages should be 1", new Integer(1), resultWrapper1.getTotalNumOfPages());
		assertEquals("Current page should be 0", new Integer(0), resultWrapper1.getCurrentPage());

		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}

	//	This test cannot run on derby: check https://github.com/OCHA-DAP/DAP-System/issues/304

	//	@Test
	//	public final void testListIndicatorsByLatestYearByCountryCriteria() {
	//		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
	//		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
	//		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
	//		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");
	//
	//		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
	//		final Date date2012January = dateTime2012.toDate();
	//		final Date date2012August = dateTime2012.plusMonths(6).toDate();
	//		final Date date2013 = dateTime2012.plusYears(1).toDate();
	//
	//		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012January, date2012January, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS, "http://www.example.com",
	//				importFromCKAN);
	//
	//		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012August, date2012August, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS, "http://www.example2.com",
	//				importFromCKAN);
	//
	//		this.indicatorDAO.createIndicator(src1, entity1, type1, date2013, date2013, Periodicity.MONTH, new IndicatorValue(30000.0), "30000$", ValidationStatus.SUCCESS, "http://www.example3.com",
	//				importFromCKAN);
	//
	//		final ApiResultWrapper<ApiIndicatorValue> resultWrapper1 = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080"),
	//				Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fi"), null, null, PeriodType.LATEST_YEAR_BY_COUNTRY, null, null, null, "en");
	//
	//		assertNotNull(resultWrapper1);
	//		assertEquals("There should be 1 indicator matching the criteria in total", new Integer(1), resultWrapper1.getTotalCount());
	//		assertTrue("The year should be 2013", resultWrapper1.getResults().get(0).getTime().contains("2013"));
	//
	//		final ApiResultWrapper<ApiIndicatorValue> resultWrapper2 = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080"),
	//				Arrays.asList("esa-unpd-WPP2012"), Arrays.asList("Fi"), 2012, 2012, PeriodType.LATEST_YEAR_BY_COUNTRY, null, null, null, "en");
	//
	//		assertNotNull(resultWrapper2);
	//		assertEquals("There should be 1 indicator matching the criteria in total", new Integer(1), resultWrapper2.getTotalCount());
	//		assertTrue("The year should be 2012", resultWrapper2.getResults().get(0).getTime().contains("2012-07"));
	//
	//		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
	//		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	//	}

	@Test
	public final void testListIndicatorsSorted() {
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");

		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();

		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012, date2012, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS, "http://www.example.com",
				importFromCKAN);

		final Source src2 = this.sourceDAO.getSourceByCode("acled");
		final Entity entity2 = this.entityDAO.getEntityByCodeAndType("Ro", "country");

		this.indicatorDAO.createIndicator(src2, entity2, type1, date2013, date2013, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS, "http://www.example2.com",
				importFromCKAN);

		this.indicatorDAO.createIndicator(src2, entity2, type1, date2012, date2013, Periodicity.MONTH, new IndicatorValue(30000.0), "30000$", ValidationStatus.SUCCESS, "http://www.example3.com",
				importFromCKAN);


		this.checkSorting(SortingOption.VALUE_ASC, new Comparator<ApiIndicatorValue>() {

			@Override
			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
				return new Double(o1.getValue()).compareTo(new Double(o2.getValue()));
			}
		});

		this.checkSorting(SortingOption.VALUE_DESC, new Comparator<ApiIndicatorValue>() {

			@Override
			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
				return new Double(o2.getValue()).compareTo(new Double(o1.getValue()));
			}
		});

		//		Below tests cannot run on derby: check https://github.com/OCHA-DAP/DAP-System/issues/304
		//
		//		this.checkSorting(SortingOption.COUNTRY_ASC, new Comparator<ApiIndicatorValue>() {
		//
		//			@Override
		//			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
		//				return o1.getLocationName().compareTo(o2.getLocationName());
		//			}
		//		});
		//
		//		this.checkSorting(SortingOption.COUNTRY_DESC, new Comparator<ApiIndicatorValue>() {
		//
		//			@Override
		//			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
		//				return o2.getLocationName().compareTo(o1.getLocationName());
		//			}
		//		});

		//		this.checkSorting(SortingOption.SOURCE_TYPE_ASC, new Comparator<ApiIndicatorValue>() {
		//
		//			@Override
		//			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
		//				return o1.getSourceName().compareTo(o2.getSourceName());
		//			}
		//		});
		//
		//		this.checkSorting(SortingOption.SOURCE_TYPE_DESC, new Comparator<ApiIndicatorValue>() {
		//
		//			@Override
		//			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
		//				return o2.getSourceName().compareTo(o1.getSourceName());
		//			}
		//		});
		//
		//		this.checkSorting(SortingOption.INDICATOR_TYPE_ASC, new Comparator<ApiIndicatorValue>() {
		//
		//			@Override
		//			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
		//				return o1.getIndicatorTypeName().compareTo(o2.getIndicatorTypeName());
		//			}
		//		});
		//
		//		this.checkSorting(SortingOption.INDICATOR_TYPE_DESC, new Comparator<ApiIndicatorValue>() {
		//
		//			@Override
		//			public int compare(final ApiIndicatorValue o1, final ApiIndicatorValue o2) {
		//				return o2.getIndicatorTypeName().compareTo(o1.getIndicatorTypeName());
		//			}
		//		});


		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}

	private void checkSorting(final SortingOption sortingOption,
			final Comparator<ApiIndicatorValue> comparator) {

		final ApiResultWrapper<ApiIndicatorValue> resultWrapper =
				this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080", "FY620"),
						Arrays.asList("esa-unpd-WPP2012", "acled"), null, Arrays.asList("Ro", "Fi"), null, null, null, sortingOption, 0, 4, "en");

		assertTrue("There should be at least 2 results", resultWrapper.getResults().size()>2);
		for (int i = 1; i < resultWrapper.getResults().size(); i++) {
			final ApiIndicatorValue indicatorValue1 = resultWrapper.getResults().get(i-1);
			final ApiIndicatorValue indicatorValue2 = resultWrapper.getResults().get(i);
			assertTrue(comparator.compare(indicatorValue1, indicatorValue2) <= 0 );
		}
	}


	@Test
	public final void testAvailablePeriods() {
		final ImportFromCKAN importFromCKAN = this.importFromCKANDAO.createNewImportRecord("anyResourceId", "anyRevisionId", new Date());
		final Source src1 = this.sourceDAO.getSourceByCode("esa-unpd-WPP2012");
		final IndicatorType type1 = this.indicatorTypeDAO.getIndicatorTypeByCode("PSP080");
		final Entity entity1 = this.entityDAO.getEntityByCodeAndType("Fi", "country");

		final LocalDateTime dateTime2012 = new LocalDateTime(2012, 1, 1, 0, 0);
		final Date date2012 = dateTime2012.toDate();
		final Date date2013 = dateTime2012.plusYears(1).toDate();
		final Date date2011 = dateTime2012.minusYears(1).toDate();

		this.indicatorDAO.createIndicator(src1, entity1, type1, date2012, date2012, Periodicity.MONTH, new IndicatorValue(10000.0), "10000$", ValidationStatus.SUCCESS, "http://www.example.com",
				importFromCKAN);

		final Source src2 = this.sourceDAO.getSourceByCode("acled");
		final Entity entity2 = this.entityDAO.getEntityByCodeAndType("Ro", "country");

		this.indicatorDAO.createIndicator(src2, entity2, type1, date2011, date2011, Periodicity.MONTH, new IndicatorValue(20000.0), "20000$", ValidationStatus.SUCCESS, "http://www.example2.com",
				importFromCKAN);

		this.indicatorDAO.createIndicator(src2, entity2, type1, date2013, date2013, Periodicity.MONTH, new IndicatorValue(30000.0), "30000$", ValidationStatus.SUCCESS, "http://www.example3.com",
				importFromCKAN);

		final ApiResultWrapper<Integer> resultWrapper =
				this.apiV2BackendService.listAvailablePeriods(Arrays.asList("PSP080", "FY620"),
						Arrays.asList("esa-unpd-WPP2012", "acled"), null, Arrays.asList("Ro", "Fi"), null, null);

		assertTrue(resultWrapper.getResults().size() == 3);
		assertTrue(resultWrapper.getResults().get(0)==2013);
		assertTrue(resultWrapper.getResults().get(1)==2012);
		assertTrue(resultWrapper.getResults().get(2)==2011);

		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}

}
