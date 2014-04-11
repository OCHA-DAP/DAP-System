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