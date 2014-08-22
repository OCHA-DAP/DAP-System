package org.ocha.hdx.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.IntegrationTestSetUpAndTearDown;
import org.ocha.hdx.model.api2.ApiIndicatorValue;
import org.ocha.hdx.model.api2.ApiResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-integration-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml",
		"classpath:/ctx-persistence-test.xml" })
public class ApiV2BackendServiceImplTest {

	@Autowired
	private IntegrationTestSetUpAndTearDown integrationTestSetUpAndTearDown;

	@Autowired
	private ApiV2BackendService apiV2BackendService;

	@Before
	public void setUp() {
		this.integrationTestSetUpAndTearDown.setUp();
	}

	@After
	public void tearDown() {
		this.integrationTestSetUpAndTearDown.tearDown();
	}

	@Test
	public void testListIndicatorsByCriteriaWithPagination() {
		final List<String> countries = new ArrayList<>();
		{
			final ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(null, null, null, null, null, 0, 1000, null);
			Assert.assertEquals(2, listIndicatorsByCriteriaWithPagination.getTotalCount().intValue());
		}

		{
			countries.add("FAKE");
			final ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(null, null, countries, null, null, 0, 1000,
					null);
			Assert.assertEquals(0, listIndicatorsByCriteriaWithPagination.getTotalCount().intValue());
			countries.clear();
		}

		{
			countries.add("RUS");
			final ApiResultWrapper<ApiIndicatorValue> listIndicatorsByCriteriaWithPagination = this.apiV2BackendService.listIndicatorsByCriteriaWithPagination(null, null, countries, null, null, 0, 1000,
					null);
			Assert.assertEquals(1, listIndicatorsByCriteriaWithPagination.getTotalCount().intValue());
		}

	}

	@Test
	@Ignore
	public void testListIndicatorsByCriteria() {
		fail("Not yet implemented");
	}

}
