package org.ocha.hdx.validation.util;

import org.ocha.hdx.persistence.dao.currateddata.IndicatorTypeDAO;
import org.ocha.hdx.persistence.dao.currateddata.SourceDAO;
import org.ocha.hdx.persistence.dao.currateddata.UnitDAO;
import org.ocha.hdx.persistence.dao.i18n.TextDAO;
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

	public DummyEntityCreator generateNewEntityCreator() {
		return new DummyEntityCreator();
	}

	public class DummyEntityCreator {

		private Text srcText;
		private Text indTypeText;

		public void createNeededIndicatorTypeAndSource() {
			this.srcText = DummyEntityCreatorWrapper.this.textDAO.createText("Source 1");
			DummyEntityCreatorWrapper.this.sourceDAO.createSource("esa-unpd-WPP2012", this.srcText, "www.test.com");

			this.indTypeText = DummyEntityCreatorWrapper.this.textDAO.createText("Population Density");

			final Text unitText = textDAO.createText("persons per square km");
			final Unit unit = unitDAO.createUnit("persons per square km", unitText);

			DummyEntityCreatorWrapper.this.indicatorTypeDAO.createIndicatorType("PSP080", this.indTypeText, unit, ValueType.NUMBER);
		}

		public void deleteNeededIndicatorTypeAndSource() {
			DummyEntityCreatorWrapper.this.sourceDAO.deleteSourceByCode("esa-unpd-WPP2012");
			DummyEntityCreatorWrapper.this.textDAO.deleteText(this.srcText.getId());

			DummyEntityCreatorWrapper.this.indicatorTypeDAO.deleteIndicatorTypeByCode("PSP080");
			DummyEntityCreatorWrapper.this.textDAO.deleteText(this.indTypeText.getId());
		}

	}

}
