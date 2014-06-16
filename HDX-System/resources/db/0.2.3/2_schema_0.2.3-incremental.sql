ALTER TABLE ckan_resource ADD COLUMN import_report oid;

ALTER TABLE entity ADD COLUMN parent_id int8;

alter table entity 
        add constraint fk_entity_to_parent 
        foreign key (parent_id) 
        references entity;