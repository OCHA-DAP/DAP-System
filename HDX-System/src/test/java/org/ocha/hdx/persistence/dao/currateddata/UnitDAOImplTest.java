package org.ocha.hdx.persistence.dao.currateddata;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Alexandru Artimon
 * @since 20/02/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/ctx-config-test.xml", "classpath:/ctx-core.xml", "classpath:/ctx-dao.xml", "classpath:/ctx-persistence-test.xml" })
public class UnitDAOImplTest {
    private static Logger logger = LoggerFactory.getLogger(SourceDAOImplTest.class);
    private final String unit1Name = "unit1";
    private final String unit1Code = "unit1Code";

    private final String unit2Name = "unit2";
    private final String unit2Code = "unit2Code";

    @Autowired
    private UnitDAO unitDAO;

    @Autowired
    private TextDAO textDAO;

    @Test
    public void testCreateDeleteUnit(){
        logger.info("Testing create and delete for Unit ...");

        final Text unit1Text = textDAO.createText(unit1Name);
        final Unit unit1 = unitDAO.createUnit(unit1Code, unit1Text);
        final Unit unit1Copy = unitDAO.getUnitByCode(unit1Code);

        Assert.assertEquals(unit1.getId(), unit1Copy.getId());
        Assert.assertEquals(unit1Name, unit1Copy.getName().getDefaultValue());
        Assert.assertEquals(1, unitDAO.listUnits().size());

        unitDAO.updateUnit(unit1.getId(), unit2Name);

        final Unit unitUpdated = unitDAO.getUnitById(unit1.getId());
        Assert.assertEquals(unit1.getId(), unitUpdated.getId());
        Assert.assertEquals(unit1.getCode(), unitUpdated.getCode());
        Assert.assertEquals(unit2Name, unitUpdated.getName().getDefaultValue());
        Assert.assertFalse(unit1.getName().getDefaultValue().equals(unitUpdated.getName().getDefaultValue()));

        //cleanup

        unitDAO.deleteUnit(unit1Copy.getId());
        try{
            unitDAO.getUnitByCode(unit1Code);
            Assert.fail("Unit shouldn't exist");
        } catch(NoResultException e) {
            //ok
        }
        textDAO.deleteText(unit1Text.getId());

    }

    @Test
    public void testListFindUnit(){
        logger.info("Testing list and find for Unit ...");
        Assert.assertEquals(0, unitDAO.listUnits().size());

        final Text unit1Text = textDAO.createText(unit1Name);
        final Unit unit1 = unitDAO.createUnit(unit1Code, unit1Text);

        Assert.assertEquals(1, unitDAO.listUnits().size());

        final Text unit2Text = textDAO.createText(unit2Name);
        final Unit unit2 = unitDAO.createUnit(unit2Code, unit2Text);

        Assert.assertEquals(2, unitDAO.listUnits().size());

        //test get unit by code method
        final Unit unit1Copy = unitDAO.getUnitByCode(unit1Code);

        Assert.assertEquals(unit1.getId(), unit1Copy.getId());
        Assert.assertEquals(unit1.getCode(), unit1Copy.getCode());
        Assert.assertEquals(unit1.getName().getDefaultValue(), unit1Copy.getName().getDefaultValue());

        //test get unit by id method
        final Unit unit2Copy = unitDAO.getUnitById(unit2.getId());

        Assert.assertEquals(unit2.getId(), unit2Copy.getId());
        Assert.assertEquals(unit2.getCode(), unit2Copy.getCode());
        Assert.assertEquals(unit2.getName().getDefaultValue(), unit2Copy.getName().getDefaultValue());

        unitDAO.deleteUnit(unit1.getId());
        try{
            unitDAO.getUnitByCode(unit1Code);
            Assert.fail("Unit shouldn't exist");
        } catch(NoResultException e) {
            //ok
        }
        Assert.assertEquals(1, unitDAO.listUnits().size());

        unitDAO.deleteUnit(unit2.getId());
        try{
            unitDAO.getUnitByCode(unit2Code);
            Assert.fail("Unit shouldn't exist");
        } catch(NoResultException e) {
            //ok
        }
        Assert.assertEquals(0, unitDAO.listUnits().size());

        //cleanup
        textDAO.deleteText(unit1Text.getId());
        textDAO.deleteText(unit2Text.getId());

    }

}
