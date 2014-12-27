
    create table hdx_dataserie_to_curated_dataset (
        id int8 not null,
        datasetName varchar(255),
        last_data_push date,
        last_data_update date,
        last_metadata_push date,
        last_metadata_update date,
        indicator_type_id int8 not null,
        source_id int8 not null,
        primary key (id),
        unique (source_id, indicator_type_id)
    );
    
    
        alter table hdx_dataserie_to_curated_dataset 
        add constraint fk__dataserie_to_curated_data_to_source 
        foreign key (source_id) 
        references source;

    alter table hdx_dataserie_to_curated_dataset 
        add constraint fk__dataserie_to_curated_data_to_indicator_type 
        foreign key (indicator_type_id) 
        references indicator_type;

