package org.ocha.dap.persistence.dao.currateddata;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.dap.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class SourceDAOImplTest {

	@Autowired
	private SourceDAO sourceDAO;

	@Test
	public void testListSources() {
		try {
			sourceDAO.getSourceByCode("CodeFromUnitTest");
			Assert.fail("Should have raised a NoResultException");
		} catch (final NoResultException e) {
			// expected
		}

		Assert.assertEquals(0, sourceDAO.listSources().size());
		sourceDAO.addSource("WB", "World Bank");
		final Source source = sourceDAO.getSourceByCode("WB");
		Assert.assertEquals("World Bank", source.getName());
		Assert.assertEquals(1, sourceDAO.listSources().size());
	}
}
