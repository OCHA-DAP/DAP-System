package org.ocha.hdx.validation.util;

import org.ocha.hdx.persistence.dao.currateddata.EntityDAO;
import org.ocha.hdx.persistence.dao.currateddata.EntityTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
import org.ocha.hdx.persistence.entity.curateddata.EntityType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType;
import org.ocha.hdx.persistence.entity.curateddata.IndicatorType.ValueType;
import org.ocha.hdx.persistence.entity.curateddata.Source;
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

		// Vars for scraper
		private Text srcText;
		private Text indTypeText;
		private Text countryType;
		private Text entityText;

		// Vars for wfp
		private Text srcWfpText;
		private Source srcWfp;
		private Text countryText;
		private Text subnationalText;
		private EntityType countryEntityType;
		private EntityType subnationalEntityType;
		private Text poorText;
		private Text borderlineText;
		private Text acceptableText;
		private Text percentageUnitText;
		private Unit percentageUnit;
		private IndicatorType poor;
		private IndicatorType borderline;
		private IndicatorType acceptable;

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

		public void createNeededIndicatorTypeAndSourceForWfp() {
			srcWfpText = textDAO.createText("World Food Program");
			srcWfp = sourceDAO.createSource("WFP", srcWfpText, "www.wfp.org", null);

			poorText = textDAO.createText("Poor");
			borderlineText = textDAO.createText("Borderline");
			acceptableText = textDAO.createText("Acceptable");

			percentageUnitText = textDAO.createText("Percentage");
			percentageUnit = unitDAO.createUnit("percentage of population", percentageUnitText);

			poor = indicatorTypeDAO.createIndicatorType("PVF040", poorText, percentageUnit, ValueType.NUMBER);
			borderline = indicatorTypeDAO.createIndicatorType("PVF050", borderlineText, percentageUnit, ValueType.NUMBER);
			acceptable = indicatorTypeDAO.createIndicatorType("WFP_ACCEPTABLE", acceptableText, percentageUnit, ValueType.NUMBER);

			countryText = textDAO.createText("Country");
			countryEntityType = entityTypeDAO.createEntityType("country", countryText);

			subnationalText = textDAO.createText("Subnational");
			subnationalEntityType = entityTypeDAO.createEntityType("subnational", subnationalText);
		}

		public void deleteNeededIndicatorTypeAndSourceForWfp() {
			indicatorTypeDAO.deleteIndicatorType(poor.getId());
			indicatorTypeDAO.deleteIndicatorType(borderline.getId());
			indicatorTypeDAO.deleteIndicatorType(acceptable.getId());

			unitDAO.deleteUnit(percentageUnit.getId());

			entityTypeDAO.deleteEntityType(subnationalEntityType.getId());
			entityTypeDAO.deleteEntityType(countryEntityType.getId());
			
			sourceDAO.deleteSource(srcWfp.getId());

			textDAO.deleteText(srcWfpText.getId());
			textDAO.deleteText(countryText.getId());
			textDAO.deleteText(subnationalText.getId());
			textDAO.deleteText(poorText.getId());
			textDAO.deleteText(borderlineText.getId());
			textDAO.deleteText(acceptableText.getId());
			textDAO.deleteText(percentageUnitText.getId());
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
