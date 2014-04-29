alter table hdx_dataserie_metadata ADD CONSTRAINT source_indicator_entry UNIQUE(source_id, indicator_type_id, entry_key);

drop table indicator_type_dictionary;
drop table region_dictionary;
drop table source_dictionary;

create table indicator_type_dictionary (
        id int8 not null,
        unnormalized_name varchar(255) not null,
        indicator_type_id int8 not null,
        resource_configuration_id int8 not null,
        primary key (id)
    );
    
create table region_dictionary (
        id int8 not null,
        unnormalized_name varchar(255) not null,
        entity_id int8 not null,
        resource_configuration_id int8 not null,
        primary key (id)
    );
    
create table source_dictionary (
        id int8 not null,
        unnormalized_name varchar(255) not null,
        resource_configuration_id int8 not null,
        source_id int8 not null,
        primary key (id)
    );
    
    
    alter table indicator_type_dictionary 
        add constraint fk_indicator_type_dictionary_to_config 
        foreign key (resource_configuration_id) 
        references resource_configuration;
        
    alter table region_dictionary 
        add constraint fk_region_dictionary_to_config 
        foreign key (resource_configuration_id) 
        references resource_configuration;
        
    alter table source_dictionary 
        add constraint fk_source_dictionary_to_config 
        foreign key (resource_configuration_id) 
        references resource_configuration;
        
        
    create sequence indicator_type_dictionary_seq;
    create sequence region_dictionary_seq;
    create sequence source_dictionary_seq;