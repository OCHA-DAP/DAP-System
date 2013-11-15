package org.ocha.dap.service;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-service.xml", "classpath:/ctx-persistence-test.xml" })
public class CuratedDataServiceImplTest {

	@Autowired
	CuratedDataService curatedDataService;

	@Test
	public void testListEntityTypes() {
		Assert.assertEquals(0, curatedDataService.listEntities().size());
		Assert.assertEquals(0, curatedDataService.listEntityTypes().size());

		try {
			curatedDataService.addEntity("RU", "Russia", "country");
			Assert.fail("entity type Country does not exist");
		} catch (final NoResultException e) {
			// expected
		}
		
		curatedDataService.addEntityType("country", "Country");
		curatedDataService.addEntity("RU", "Russia", "country");
		
		Assert.assertEquals(1, curatedDataService.listEntities().size());
		Assert.assertEquals(1, curatedDataService.listEntityTypes().size());
	}

}
