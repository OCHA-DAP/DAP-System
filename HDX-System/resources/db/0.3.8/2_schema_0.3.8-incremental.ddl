CREATE OR REPLACE VIEW 
	hdx_view_indicator_max_date 
	AS 
	SELECT i.id as id, i.number_value as value, it.code as indicator_type_code, itt.default_value as indicator_type_name,
	e.code as location_code, et.default_value as location_name,
	s.code as source_code, st.default_value as source_name, i.start_time as start_time
	 FROM hdx_indicator i INNER JOIN 
	(SELECT entity_id, source_id, type_id, MAX(start_time) as max_date 
		FROM hdx_indicator GROUP BY entity_id, source_id, type_id) i2 
	ON i.entity_id = i2.entity_id AND i.source_id = i2.source_id AND i.type_id = i2.type_id AND i.start_time = i2.max_date,
	indicator_type it,
	source s,
	entity e, 
	text itt,
	text st,
	text et
	WHERE i.type_id = it.id AND i.source_id = s.id AND i.entity_id = e.id
	AND itt.id = it.text_id AND st.id = s.text_id AND et.id = e.text_id ;