package org.ocha.hdx.persistence.dao.currateddata;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DbUnitConfiguration(databaseConnection="datasource")
public class SourceDaoImplWithDbUnitTest {

	@Autowired
	private SourceDAO sourceDAO;

	@Test
	@DatabaseSetup("/META-INF/export.xml")
	public void listSources() {
		final List<Source> testList	= this.sourceDAO.listSources();
		Assert.assertEquals(18, this.sourceDAO.listSources().size());
	}

}
