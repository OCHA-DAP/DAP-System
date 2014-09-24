/**
 *
 */
package org.ocha.hdx.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
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
				Arrays.asList("esa-unpd-WPP2012", "acled"), Arrays.asList("Ro", "Fi"), 2012, 2013, null, null, 1, 2, "en");
		assertNotNull(resultWrapper1);
		assertEquals("There should be 3 indicators matching the criteria in total", new Integer(3), resultWrapper1.getTotalCount());
		assertEquals("There should be exactly 1 indicator matching the criteria on the requested page", 1, resultWrapper1.getResults().size());
		assertEquals("Total number of pages should be 2", new Integer(2), resultWrapper1.getTotalNumOfPages());
		assertEquals("Current page should be 1", new Integer(1), resultWrapper1.getCurrentPage());

		assertEquals("The cache should not be hit", 0, this.indicatorResultCache.stats().hitCount());
		final ApiResultWrapper<ApiIndicatorValue> resultWrapper2 = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(Arrays.asList("PSP080", "FY620"),
				Arrays.asList("esa-unpd-WPP2012", "acled"), Arrays.asList("Ro", "Fi"), 2012, 2013, null, null, 1, 2, "en");

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
				Arrays.asList("esa-unpd-WPP2012", "acled"), Arrays.asList("Ro", "Fi"), null, null, PeriodType.LATEST_YEAR, null, 0, 2, "en");
		assertNotNull(resultWrapper1);
		assertEquals("There should be 1 indicator matching the criteria in total", new Integer(1), resultWrapper1.getTotalCount());
		assertEquals("There should be exactly 1 indicator matching the criteria on the requested page", 1, resultWrapper1.getResults().size());
		assertEquals("Total number of pages should be 1", new Integer(1), resultWrapper1.getTotalNumOfPages());
		assertEquals("Current page should be 0", new Integer(0), resultWrapper1.getCurrentPage());

		this.indicatorDAO.deleteAllIndicatorsFromImport(importFromCKAN.getId());
		this.importFromCKANDAO.deleteImportFromCKAN(importFromCKAN.getId());
	}

}
