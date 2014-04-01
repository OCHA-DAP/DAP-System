package org.ocha.hdx.validation.util;

import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.Unit;
import org.ocha.hdx.persistence.entity.i18n.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyEntityCreatorWrapper {
	@Autowired
	private SourceDAO sourceDAO;

	@Autowired
	private IndicatorTypeDAO indicatorTypeDAO;

	@Autowired
	private TextDAO textDAO;

	@Autowired
	private UnitDAO unitDAO;

	@Autowired
	private EntityDAO entityDAO;

	@Autowired
	private EntityTypeDAO entityTypeDAO;

	public DummyEntityCreator generateNewEntityCreator() {
		return new DummyEntityCreator();
	}

	public class DummyEntityCreator {

		private Text srcText;
		private Text indTypeText;
		private Text countryType;
		private Text entityText;

		public void createNeededIndicatorTypeAndSource() {
			srcText = textDAO.createText("Source 1");
			sourceDAO.createSource("esa-unpd-WPP2012", srcText, "www.test.com", null);

			indTypeText = textDAO.createText("Population Density");

			final Text unitText = textDAO.createText("persons per square km");
			final Unit unit = unitDAO.createUnit("persons per square km", unitText);

			indicatorTypeDAO.createIndicatorType("PSP080", indTypeText, unit, ValueType.NUMBER);


			countryType = textDAO.createText("Country");
			entityTypeDAO.createEntityType("country", countryType);
			final EntityType country = entityTypeDAO.getEntityTypeByCode("country");

			entityText = textDAO.createText("Finland");
			entityDAO.createEntity("Fi", entityText, country);
		}

		public void deleteNeededIndicatorTypeAndSource() {
			sourceDAO.deleteSourceByCode("esa-unpd-WPP2012");
			textDAO.deleteText(srcText.getId());

			indicatorTypeDAO.deleteIndicatorTypeByCode("PSP080");
			textDAO.deleteText(indTypeText.getId());

			entityDAO.deleteEntityByCodeAndType("Fi", "country");
			textDAO.deleteText(entityText.getId());

			entityTypeDAO.deleteEntityTypeByCode("country");
			textDAO.deleteText(countryType.getId());
		}

	}

}
