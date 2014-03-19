DROP VIEW hdx_view_report_indicator_data;
DROP VIEW hdx_view_report_indicator_type_overview;
DROP VIEW hdx_view_additional_data_text ;

CREATE OR REPLACE VIEW 
	hdx_view_additional_data_text 
AS SELECT 
	ad.id,
    ad.entry_key,
    ad.entry_value_text_id,
    ad.indicator_type_id,
    ad.source_id,
    t.default_value
   FROM hdx_additional_data ad,
    text t
  WHERE ad.entry_value_text_id = t.id;

CREATE OR REPLACE VIEW 
	hdx_view_report_indicator_type_overview 
AS SELECT DISTINCT
	it.id as "indicator_type_id", 
	it.code as "indicator_type_code",
	itt.default_value as "indicator_type_default_value",
	s.code as "source_code",
	st.default_value as "source_default_value",
	u.code as "unit_code",
	ut.default_value as "unit_default_value",
	dads.id as "data_summary_id",
	dads.default_value as "data_summary_default_value",
	dami.id as "more_info_id",
	dami.default_value as "more_info_default_value",
	datu.id as "terms_of_use_id",
	datu.default_value as "terms_of_use_default_value",
	damy.id as "methodology_id",
	damy.default_value as "methodology_default_value"
FROM
	indicator_type it
		LEFT OUTER JOIN hdx_view_additional_data_text dads on (it.id = dads.indicator_type_id and dads.entry_key = 'DATASET_SUMMARY')
		LEFT OUTER JOIN hdx_view_additional_data_text dami on (it.id = dami.indicator_type_id and dami.entry_key = 'MORE_INFO')
		LEFT OUTER JOIN hdx_view_additional_data_text datu on (it.id = datu.indicator_type_id and datu.entry_key = 'TERMS_OF_USE')
		LEFT OUTER JOIN hdx_view_additional_data_text damy on (it.id = damy.indicator_type_id and damy.entry_key = 'METHODOLOGY'),
	hdx_view_additional_data_text ad,
	text itt,
	source s,
	text st,
	hdx_unit u,
	text ut
WHERE
	itt.id = it.text_id 
	and st.id = s.text_id
	and u.id = it.unit_id
	and ut.id = u.text_id
	and s.id = ad.source_id
	and it.id = ad.indicator_type_id
order by
	it.id;
	
CREATE OR REPLACE VIEW 
	hdx_view_report_indicator_data 
AS SELECT 
	 i.id as "indicator_id"
	,EXTRACT(YEAR FROM i.start_time) as "indicator_year"
	,i.number_value as "indicator_value"
	,it.code as "indicator_type_code"
	,s.code as "source_code"
	,st.default_value as "source_default_value"
	,e.code as "country_code"
	,etx.default_value as "country_default_value"
from 
	 hdx_indicator i 
	,indicator_type it
	,source s 
	,text st 
	,entity e 
	,text etx 
	,entity_type et
where 
	    it.id = i.type_id
	and s.id = i.source_id
	and st.id = s.text_id
	and e.id = i.entity_id
	and etx.id = e.text_id
	and et.id = e.entity_type_id
	and et.code = 'country';
