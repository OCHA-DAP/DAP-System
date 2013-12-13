package org.ocha.dap.rest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ocha.dap.IntegrationTestSetUpAndTearDown;
import org.ocha.dap.persistence.dao.ImportFromCKANDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoaderListener;

import com.riffpie.common.testing.AbstractSpringAwareJerseyTest;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class APIResourceTest extends AbstractSpringAwareJerseyTest {

	@Autowired
	private IntegrationTestSetUpAndTearDown integrationTestSetUpAndTearDown;

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	public APIResourceTest() {
		super(
				new WebAppDescriptor.Builder()
						.contextPath("APIResourceTest")
						.contextParam(
								"contextConfigLocation",
								"classpath:/ctx-config-test.xml, classpath:/ctx-integration-test.xml, classpath:/ctx-core.xml, classpath:/ctx-dao.xml, classpath:/ctx-service.xml, classpath:/ctx-persistence-test.xml, classpath:/ctx-rest.xml")
						.servletClass(SpringServlet.class).initParam("com.sun.jersey.api.json.POJOMappingFeature", "true").contextListenerClass(ContextLoaderListener.class).build());
	}

	@Before
	public void setUp2() {
		integrationTestSetUpAndTearDown.setUp();
	}

	@After
	public void tearDown2() {
		integrationTestSetUpAndTearDown.tearDown();
	}

	@Test
	public void testGetYearlyDataForSourceAndIndicatorType() {

		final WebResource wr = resource();

		final String result = wr.path("/api/yearly/source/{sourceCode}/indicatortype/{indicatorTypeCode}/json").get(String.class);
		Assert.assertEquals(78, result.length());

		// FIXME continue with real data

	}
}
