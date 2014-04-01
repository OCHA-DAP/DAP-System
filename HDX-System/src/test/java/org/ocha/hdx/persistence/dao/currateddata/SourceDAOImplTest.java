package org.ocha.hdx.persistence.dao.currateddata;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.ImportFromCKANDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.ImportFromCKAN;
import org.ocha.hdx.persistence.entity.curateddata.Entity;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.Indicator.Periodicity;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorValue;
import org.ocha.hdx.persistence.entity.curateddata.Organization;
import org.ocha.hdx.persistence.entity.curateddata.Source;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class SourceDAOImplTest {

	private static Logger logger = LoggerFactory.getLogger(SourceDAOImplTest.class);

	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private IndicatorDAO indicatorDAO;

	@Autowired
	private ImportFromCKANDAO importFromCKANDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private UnitDAO unitDAO;

	@Autowired
	private OrganizationDAO organizationDAO;

	@Autowired
	private TextDAO textDAO;

	@Test
	public void testListSources() {
		
		try {
			sourceDAO.getSourceByCode("CodeFromUnitTest");
			Assert.fail("Should have raised a NoResultException");
		} catch (final NoResultException e) {
			// expected
		}
		
		Assert.assertEquals(0, sourceDAO.listSources().size());

		final Text text = textDAO.createText("World Bank");
		final Text ftext = textDAO.createText("fullName org");
		final Text stext = textDAO.createText("shortName org");
		organizationDAO.createOrganization("www.org.org", ftext, stext);
		final Organization organization = organizationDAO.listOrganizations().get(0);
		sourceDAO.createSource("WB", text, "www.test.com", organization);
		final Source wbSource = sourceDAO.getSourceByCode("WB");
		Assert.assertEquals("World Bank", wbSource.getName().getDefaultValue());
		Assert.assertEquals("www.test.com", wbSource.getOrgLink());
		Assert.assertEquals(organization.getId(), wbSource.getOrganization().getId());
		Assert.assertEquals(1, sourceDAO.listSources().size());
		

		final Text text2 = textDAO.createText("Test source");
		sourceDAO.createSource("TS", text2, "www.testsource.com", organization);
		final Source tsSource = sourceDAO.getSourceByCode("TS");

		final Text uText = textDAO.createText("Test unit");
		final Unit unit = unitDAO.createUnit("UC", uText);
		
		final Text itText = textDAO.createText("Test indicator type");
		indicatorTypeDAO.createIndicatorType("ITC1", itText, unit, IndicatorType.ValueType.STRING);
		final IndicatorType indicatorType = indicatorTypeDAO.getIndicatorTypeByCode("ITC1");
		
		final Text etText = textDAO.createText("Test entity type");
		entityTypeDAO.createEntityType("ETC", etText);
		final EntityType entityType = entityTypeDAO.getEntityTypeByCode("ETC");
		
		final Text eText = textDAO.createText("Test entity");
		entityDAO.createEntity("EC", eText, entityType);
		final Entity entity = entityDAO.getEntityByCodeAndType("EC", entityType.getCode());

		List<Source> listSourcesForIndicatorType = sourceDAO.listSourcesForIndicatorType(indicatorType.getCode());
		Assert.assertEquals(0, listSourcesForIndicatorType.size());

		final ImportFromCKAN importFromCKAN = importFromCKANDAO.createNewImportRecord("resourceId", "revisionId", new Date());
		indicatorDAO.createIndicator(wbSource, entity, indicatorType, new Date(), null, Periodicity.YEAR, new IndicatorValue("IV1"), "IVI", "sourceLink", importFromCKAN);
		
		listSourcesForIndicatorType = sourceDAO.listSourcesForIndicatorType(indicatorType.getCode());
		Assert.assertEquals(1, listSourcesForIndicatorType.size());
		
		indicatorDAO.createIndicator(tsSource, entity, indicatorType, new Date(), null, Periodicity.YEAR, new IndicatorValue("IV1"), "IVI", "sourceLink", importFromCKAN);
		
		listSourcesForIndicatorType = sourceDAO.listSourcesForIndicatorType(indicatorType.getCode());
		Assert.assertEquals(2, listSourcesForIndicatorType.size());
		
		indicatorDAO.deleteAllIndicators();
		
		listSourcesForIndicatorType = sourceDAO.listSourcesForIndicatorType(indicatorType.getCode());
		Assert.assertEquals(0, listSourcesForIndicatorType.size());

		entityDAO.deleteEntity(entity.getId());
		textDAO.deleteText(entity.getName().getId());

		entityTypeDAO.deleteEntityType(entityType.getId());
		textDAO.deleteText(entityType.getName().getId());

		indicatorTypeDAO.deleteIndicatorType(indicatorType.getId());
		textDAO.deleteText(indicatorType.getName().getId());

		unitDAO.deleteUnit(unit.getId());
		textDAO.deleteText(unit.getName().getId());

		sourceDAO.deleteSourceByCode("TS");
		textDAO.deleteText(tsSource.getName().getId());
		Assert.assertEquals(1, sourceDAO.listSources().size());
		
		sourceDAO.deleteSourceByCode("WB");
		textDAO.deleteText(wbSource.getName().getId());
		Assert.assertEquals(0, sourceDAO.listSources().size());

		organizationDAO.deleteOrganization(organization.getId());
	}

	@Test
	public void testCreateSource() {
		logger.info("Testing create source...");

		final Text s1 = textDAO.createText("Source 1");
		final Text ftext = textDAO.createText("fullName org");
		final Text stext = textDAO.createText("shortName org");
		organizationDAO.createOrganization("www.org.org", ftext, stext);
		final Organization organization = organizationDAO.listOrganizations().get(0);
		sourceDAO.createSource("S1", s1, "www.test.com", organization);

		final Source sourceForCode = sourceDAO.getSourceByCode("S1");
		Assert.assertEquals("Source 1", sourceForCode.getName().getDefaultValue());
		Assert.assertEquals(1, sourceDAO.listSources().size());

		final Source sourceById = sourceDAO.getSourceById(sourceForCode.getId());
		Assert.assertEquals("Source 1", sourceById.getName().getDefaultValue());

		logger.info("Testing delete source...");
		sourceDAO.deleteSourceByCode("S1");

		Assert.assertEquals(0, sourceDAO.listSources().size());

		organizationDAO.deleteOrganization(organization.getId());
	}

	@Test
	public void testUpdateSource() {
		logger.info("Testing update source...");

		final Text s1 = textDAO.createText("S1");
		final Text ftext = textDAO.createText("fullName org");
		final Text stext = textDAO.createText("shortName org");
		organizationDAO.createOrganization("www.org.org", ftext, stext);
		final Organization organization = organizationDAO.listOrganizations().get(0);
		sourceDAO.createSource("S1", s1, "www.test.com", organization);
		final Source source = sourceDAO.getSourceByCode("S1");

		sourceDAO.updateSource(source.getId(), "NewName", "NewLink", organization);
		final Source updatedSource = sourceDAO.getSourceById(source.getId());

		Assert.assertEquals("NewName", updatedSource.getName().getDefaultValue());
		Assert.assertEquals("NewLink", updatedSource.getOrgLink());
		Assert.assertEquals(source.getCode(), updatedSource.getCode());
		Assert.assertEquals(source.getId(), updatedSource.getId());

		sourceDAO.deleteSourceByCode("S1");

		organizationDAO.deleteOrganization(organization.getId());
	}

	@Test
	public void testDeleteSource() {
		logger.info("Testing delete source...");

		final Text s1 = textDAO.createText("S1");
		final Text ftext = textDAO.createText("fullName org");
		final Text stext = textDAO.createText("shortName org");
		organizationDAO.createOrganization("www.org.org", ftext, stext);
		final Organization organization = organizationDAO.listOrganizations().get(0);
		sourceDAO.createSource("S1", s1, "www.test.com", organization);

		final Source sourceForCode = sourceDAO.getSourceByCode("S1");
		final long id = sourceForCode.getId();

		sourceDAO.deleteSource(id);

		Assert.assertNull(sourceDAO.getSourceById(id));

		organizationDAO.deleteOrganization(organization.getId());
	}
}
