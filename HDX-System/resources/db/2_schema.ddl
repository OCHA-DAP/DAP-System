
    alter table ckan_resource 
        drop constraint fk_ckan_resource_to_resource_config;

    alter table entity 
        drop constraint fk_entity_to_type;

    alter table entity 
        drop constraint fk_entity_to_name_text;

    alter table entity_type 
        drop constraint fk_entity_type_to_name_text;

    alter table hdx_additional_data 
        drop constraint fk_additional_data_to_source;

    alter table hdx_additional_data 
        drop constraint fk_additional_data_to_indicator_type;

    alter table hdx_additional_data 
        drop constraint fk_additional_data_to_name_text;

    alter table hdx_indicator 
        drop constraint fk_indicator_to_source;

    alter table hdx_indicator 
        drop constraint fk_indicator_to_entity;

    alter table hdx_indicator 
        drop constraint fk_import_from_ckan;

    alter table hdx_indicator 
        drop constraint fk_indicator_value_to_text;

    alter table hdx_indicator 
        drop constraint fk_indicator_to_type;

    alter table hdx_translation 
        drop constraint fk_translation_to_text;

    alter table hdx_translation 
        drop constraint fk_translation_to_language;

    alter table hdx_unit 
        drop constraint fk_entity_to_name_text;

    alter table indicator_resource_config_entry 
        drop constraint fk_ind_resource_config_map_to_source;

    alter table indicator_resource_config_entry 
        drop constraint fk_ind_resource_config_map_to_indicator_type;

    alter table indicator_resource_config_entry 
        drop constraint fk_ind_resource_config_map_to_parent;

    alter table indicator_type 
        drop constraint fk_indicator_type_to_name_text;

    alter table indicator_type 
        drop constraint fk_indicator_type_to_unit;

    alter table indicator_type_dictionary 
        drop constraint fk_indicator_type_dictionary_to_indicator_type;

    alter table organisation 
        drop constraint fk_full_name_to_text;

    alter table organisation 
        drop constraint fk_short_name_to_text;

    alter table region_dictionary 
        drop constraint fk_region_dictionary_to_entity;

    alter table resource_config_entry 
        drop constraint fk_resource_config_map_to_parent;

    alter table source 
        drop constraint fk_source_to_name_text;

    alter table source 
        drop constraint fk_source_to_organisation;

    alter table source_dictionary 
        drop constraint fk_source_dictionary_to_source;

    drop table ckan_dataset;

    drop table ckan_resource;

    drop table entity;

    drop table entity_type;

    drop table hdx_additional_data;

    drop table hdx_indicator;

    drop table hdx_translation;

    drop table hdx_unit;

    drop table hdx_user;

    drop table import_from_ckan;

    drop table indicator_resource_config_entry;

    drop table indicator_type;

    drop table indicator_type_dictionary;

    drop table language;

    drop table organisation;

    drop table region_dictionary;

    drop table resource_config_entry;

    drop table resource_configuration;

    drop table source;

    drop table source_dictionary;

    drop table text;

    drop sequence entity_seq;

    drop sequence entity_type_seq;

    drop sequence hdx_additional_data_seq;

    drop sequence hdx_unit_seq;

    drop sequence import_from_ckan_seq;

    drop sequence indicator_resource_config_entry_seq;

    drop sequence indicator_seq;

    drop sequence indicator_type_seq;

    drop sequence organisation_seq;

    drop sequence resource_config_entry_seq;

    drop sequence resource_configuration_seq;

    drop sequence source_seq;

    drop sequence text_seq;

    create table ckan_dataset (
        name varchar(255) not null,
        author varchar(255),
        author_email varchar(255),
        maintainer varchar(255),
        maintainer_email varchar(255),
        status varchar(255) not null,
        title varchar(255) not null,
        type varchar(255),
        primary key (name)
    );

    create table ckan_resource (
        id varchar(255) not null,
        revision_id varchar(255) not null,
        detectionDate timestamp not null,
        downloadDate timestamp,
        evaluationDate timestamp,
        evaluator varchar(255),
        importDate timestamp,
        importer varchar(255),
        name varchar(255) not null,
        parentDataset_id varchar(255) not null,
        parentDataset_name varchar(255) not null,
        parentDataset_revision_id varchar(255) not null,
        parentDataset_revision_timestamp timestamp not null,
        revision_timestamp timestamp not null,
        validationReport oid,
        workflowState varchar(255) not null,
        resource_configuration_id int8,
        primary key (id, revision_id)
    );

    create table entity (
        id int8 not null,
        code varchar(255) not null,
        text_id int8,
        entity_type_id int8,
        primary key (id),
        unique (code, entity_type_id)
    );

    create table entity_type (
        id int8 not null,
        code varchar(255) not null unique,
        text_id int8,
        primary key (id)
    );

    create table hdx_additional_data (
        id int8 not null,
        entry_key varchar(255) not null,
        entry_value_text_id int8,
        indicator_type_id int8 not null,
        source_id int8 not null,
        primary key (id)
    );

    create table hdx_indicator (
        id int8 not null,
        end_time timestamp,
        initial_value varchar(255) not null,
        periodicity varchar(255) not null,
        source_link varchar(255),
        start_time timestamp not null,
        date_value date,
        datetime_value timestamp,
        number_value float8,
        string_value varchar(255),
        entity_id int8 not null,
        import_from_ckan_id int8,
        source_id int8 not null,
        type_id int8 not null,
        text_id int8,
        primary key (id),
        unique (source_id, entity_id, type_id, start_time, periodicity)
    );

    create table hdx_translation (
        value text not null,
        language varchar(255) not null,
        text int8 not null,
        primary key (language, text)
    );

    create table hdx_unit (
        id int8 not null,
        code varchar(255) not null,
        text_id int8,
        primary key (id)
    );

    create table hdx_user (
        id varchar(255) not null,
        ckanApiKey varchar(255),
        password varchar(255),
        role varchar(255),
        primary key (id)
    );

    create table import_from_ckan (
        id int8 not null,
        resource_id varchar(255) not null,
        revision_id varchar(255) not null,
        timestamp timestamp not null,
        primary key (id)
    );

    create table indicator_resource_config_entry (
        id int8 not null,
        entry_key varchar(255) not null,
        entry_value text not null,
        indicator_type_id int8 not null,
        resource_configuration_id int8 not null,
        source_id int8 not null,
        primary key (id)
    );

    create table indicator_type (
        id int8 not null,
        code varchar(255) not null unique,
        value_type varchar(255),
        text_id int8,
        unit_id int8,
        primary key (id)
    );

    create table indicator_type_dictionary (
        importer varchar(255) not null,
        unnormalized_name varchar(255) not null,
        indicator_type_id int8 not null,
        primary key (importer, unnormalized_name)
    );

    create table language (
        code varchar(255) not null,
        native_name varchar(255) not null,
        primary key (code)
    );

    create table organisation (
        id int8 not null,
        org_link varchar(255),
        full_name_id int8 not null,
        short_name_id int8 not null,
        primary key (id)
    );

    create table region_dictionary (
        importer varchar(255) not null,
        unnormalized_name varchar(255) not null,
        entity_id int8 not null,
        primary key (importer, unnormalized_name)
    );

    create table resource_config_entry (
        id int8 not null,
        entry_key varchar(255) not null,
        entry_value text not null,
        resource_configuration_id int8 not null,
        primary key (id)
    );

    create table resource_configuration (
        id int8 not null,
        name varchar(255) not null unique,
        primary key (id)
    );

    create table source (
        id int8 not null,
        code varchar(255) not null unique,
        org_link varchar(255),
        text_id int8 not null,
        organisation_id int8,
        primary key (id)
    );

    create table source_dictionary (
        importer varchar(255) not null,
        unnormalized_name varchar(255) not null,
        source_id int8 not null,
        primary key (importer, unnormalized_name)
    );

    create table text (
        id int8 not null,
        default_value text not null,
        primary key (id)
    );

    alter table ckan_resource 
        add constraint fk_ckan_resource_to_resource_config 
        foreign key (resource_configuration_id) 
        references resource_configuration;

    alter table entity 
        add constraint fk_entity_to_type 
        foreign key (entity_type_id) 
        references entity_type;

    alter table entity 
        add constraint fk_entity_to_name_text 
        foreign key (text_id) 
        references text;

    alter table entity_type 
        add constraint fk_entity_type_to_name_text 
        foreign key (text_id) 
        references text;

    alter table hdx_additional_data 
        add constraint fk_additional_data_to_source 
        foreign key (source_id) 
        references source;

    alter table hdx_additional_data 
        add constraint fk_additional_data_to_indicator_type 
        foreign key (indicator_type_id) 
        references indicator_type;

    alter table hdx_additional_data 
        add constraint fk_additional_data_to_name_text 
        foreign key (entry_value_text_id) 
        references text;

    alter table hdx_indicator 
        add constraint fk_indicator_to_source 
        foreign key (source_id) 
        references source;

    alter table hdx_indicator 
        add constraint fk_indicator_to_entity 
        foreign key (entity_id) 
        references entity;

    alter table hdx_indicator 
        add constraint fk_import_from_ckan 
        foreign key (import_from_ckan_id) 
        references import_from_ckan;

    alter table hdx_indicator 
        add constraint fk_indicator_value_to_text 
        foreign key (text_id) 
        references text;

    alter table hdx_indicator 
        add constraint fk_indicator_to_type 
        foreign key (type_id) 
        references indicator_type;

    alter table hdx_translation 
        add constraint fk_translation_to_text 
        foreign key (text) 
        references text;

    alter table hdx_translation 
        add constraint fk_translation_to_language 
        foreign key (language) 
        references language;

    alter table hdx_unit 
        add constraint fk_entity_to_name_text 
        foreign key (text_id) 
        references text;

    create index indicator_resource_config_entry_index on indicator_resource_config_entry (entry_key);

    alter table indicator_resource_config_entry 
        add constraint fk_ind_resource_config_map_to_source 
        foreign key (source_id) 
        references source;

    alter table indicator_resource_config_entry 
        add constraint fk_ind_resource_config_map_to_indicator_type 
        foreign key (indicator_type_id) 
        references indicator_type;

    alter table indicator_resource_config_entry 
        add constraint fk_ind_resource_config_map_to_parent 
        foreign key (resource_configuration_id) 
        references resource_configuration;

    alter table indicator_type 
        add constraint fk_indicator_type_to_name_text 
        foreign key (text_id) 
        references text;

    alter table indicator_type 
        add constraint fk_indicator_type_to_unit 
        foreign key (unit_id) 
        references hdx_unit;

    alter table indicator_type_dictionary 
        add constraint fk_indicator_type_dictionary_to_indicator_type 
        foreign key (indicator_type_id) 
        references indicator_type;

    alter table organisation 
        add constraint fk_full_name_to_text 
        foreign key (full_name_id) 
        references text;

    alter table organisation 
        add constraint fk_short_name_to_text 
        foreign key (short_name_id) 
        references text;

    alter table region_dictionary 
        add constraint fk_region_dictionary_to_entity 
        foreign key (entity_id) 
        references entity;

    create index resource_config_entry_index on resource_config_entry (entry_key);

    alter table resource_config_entry 
        add constraint fk_resource_config_map_to_parent 
        foreign key (resource_configuration_id) 
        references resource_configuration;

    create index nameIndex on resource_configuration (name);

    alter table source 
        add constraint fk_source_to_name_text 
        foreign key (text_id) 
        references text;

    alter table source 
        add constraint fk_source_to_organisation 
        foreign key (organisation_id) 
        references organisation;

    alter table source_dictionary 
        add constraint fk_source_dictionary_to_source 
        foreign key (source_id) 
        references source;

    create sequence entity_seq;

    create sequence entity_type_seq;

    create sequence hdx_additional_data_seq;

    create sequence hdx_unit_seq;

    create sequence import_from_ckan_seq;

    create sequence indicator_resource_config_entry_seq;

    create sequence indicator_seq;

    create sequence indicator_type_seq;

    create sequence organisation_seq;

    create sequence resource_config_entry_seq;

    create sequence resource_configuration_seq;

    create sequence source_seq;

    create sequence text_seq;
