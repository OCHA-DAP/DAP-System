ALTER TABLE hdx_indicator ADD COLUMN validation_status character varying(255) not null Default 'SUCCESS';

ALTER TABLE hdx_indicator ADD COLUMN expected_time_format varchar(255);
ALTER TABLE hdx_indicator ADD COLUMN interpreted_time_format varchar(255);
ALTER TABLE hdx_indicator ADD COLUMN lower_boundary float8;
ALTER TABLE hdx_indicator ADD COLUMN multiplier float8;
ALTER TABLE hdx_indicator ADD COLUMN upper_boundary float8;
ALTER TABLE hdx_indicator ADD COLUMN validation_message varchar(255);

ALTER TABLE hdx_additional_data RENAME TO hdx_dataserie_metadata;
ALTER SEQUENCE hdx_additional_data_seq RENAME TO hdx_dataserie_metadata_seq;

alter table hdx_dataserie_metadata 
        add constraint fk__dataserie_metadata_to_source 
        foreign key (source_id) 
        references source;

    alter table hdx_dataserie_metadata 
        add constraint fk__dataserie_metadata_to_indicator_type 
        foreign key (indicator_type_id) 
        references indicator_type;

    alter table hdx_dataserie_metadata 
        add constraint fk__dataserie_metadata_to_name_text 
        foreign key (entry_value_text_id) 
        references text;
        
        alter table hdx_dataserie_metadata 
        drop constraint fk_additional_data_to_source;

    alter table hdx_dataserie_metadata 
        drop constraint fk_additional_data_to_indicator_type;

    alter table hdx_dataserie_metadata 
        drop constraint fk_additional_data_to_name_text;
        
UPDATE source SET code = 'data.undp.org' WHERE code='hdrstats';
<<<<<<< HEAD
DELETE FROM resource_config_entry WHERE entry_key='Pre-validators';
DELETE FROM indicator_resource_config_entry WHERE entry_key='Validators';

/* adding configurations to datasets*/
ALTER TABLE ckan_dataset ADD COLUMN configuration_id int8;
alter table ckan_dataset 
        add constraint fk_ckan_dataset_to_resource_config 
        foreign key (configuration_id) 
        references resource_configuration;
=======

update source_dictionary set configuration_id=1;
update indicator_type_dictionary set configuration_id=1;
update region_dictionary set configuration_id=1;
>>>>>>> origin/importerConfiguration
