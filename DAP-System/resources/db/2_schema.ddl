
    alter table entity 
        drop constraint fk_entity_to_type;

    alter table indicator 
        drop constraint fk_indicator_to_source;

    alter table indicator 
        drop constraint fk_indicator_to_entity;

    alter table indicator 
        drop constraint fk_import_from_ckan;

    alter table indicator 
        drop constraint fk_indicator_to_type;

    drop table ckan_dataset;

    drop table ckan_resource;

    drop table dap_user;

    drop table entity;

    drop table entity_type;

    drop table import_from_ckan;

    drop table indicator;

    drop table indicator_type;

    drop table region_dictionary;

    drop table source;

    drop sequence hibernate_sequence;

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
        primary key (id, revision_id)
    );

    create table dap_user (
        id varchar(255) not null,
        ckanApiKey varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table entity (
        id int8 not null,
        code varchar(255) not null,
        name varchar(255) not null,
        entity_type_id int8,
        primary key (id)
    );

    create table entity_type (
        id int8 not null,
        code varchar(255) not null unique,
        name varchar(255) not null,
        primary key (id)
    );

    create table import_from_ckan (
        id int8 not null,
        resource_id varchar(255) not null,
        revision_id varchar(255) not null,
        timestamp timestamp not null,
        primary key (id)
    );

    create table indicator (
        id int8 not null,
        end_time timestamp,
        initial_value varchar(255) not null,
        numeric bool not null,
        periodicity varchar(255) not null,
        start_time timestamp not null,
        value varchar(255) not null,
        entity_id int8,
        import_from_ckan_id int8,
        source_id int8,
        type_id int8,
        primary key (id),
        unique (source_id, entity_id, type_id, start_time)
    );

    create table indicator_type (
        id int8 not null,
        code varchar(255) not null,
        name varchar(255) not null,
        unit varchar(255),
        primary key (id)
    );

    create table region_dictionary (
        source varchar(255) not null,
        unnormalized_name varchar(255) not null,
        entity_code varchar(255) not null,
        entity_type varchar(255) not null,
        primary key (source, unnormalized_name)
    );

    create table source (
        id int8 not null,
        code varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    );

    alter table entity 
        add constraint fk_entity_to_type 
        foreign key (entity_type_id) 
        references entity_type;

    alter table indicator 
        add constraint fk_indicator_to_source 
        foreign key (source_id) 
        references source;

    alter table indicator 
        add constraint fk_indicator_to_entity 
        foreign key (entity_id) 
        references entity;

    alter table indicator 
        add constraint fk_import_from_ckan 
        foreign key (import_from_ckan_id) 
        references import_from_ckan;

    alter table indicator 
        add constraint fk_indicator_to_type 
        foreign key (type_id) 
        references indicator_type;

    create sequence hibernate_sequence;