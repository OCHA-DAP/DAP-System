ALTER TABLE ckan_resource ADD COLUMN import_report oid;

ALTER TABLE entity ADD COLUMN parent_id int8;

alter table entity 
        add constraint fk_entity_to_parent 
        foreign key (parent_id) 
        references entity;
        
CREATE OR REPLACE VIEW 
	hdx_view_indicator_type_count 
AS SELECT
	i.type_id as id, 
	it.code as code, 
	count(*) as count
	FROM 
		hdx_indicator i, 
		indicator_type it 
	WHERE
		i.type_id = it.id 
	GROUP BY
		i.type_id, 
		it.code 
	ORDER BY
		it.code; 

