
    drop table ckan_dataset;

    drop table ckan_resource;

    drop table dap_user;

    create table ckan_dataset (
        name varchar(255) not null,
        status varchar(255) not null,
        type varchar(255),
        primary key (name)
    );

    create table ckan_resource (
        id varchar(255) not null,
        revision_id varchar(255) not null,
        detectionDate timestamp not null,
        downloadDate timestamp,
        parentDataset_id varchar(255) not null,
        parentDataset_revision_id varchar(255) not null,
        parentDataset_revision_timestamp timestamp not null,
        revision_timestamp timestamp not null,
        workflowState varchar(255) not null,
        primary key (id, revision_id)
    );

    create table dap_user (
        id varchar(255) not null,
        ckanApiKey varchar(255),
        password varchar(255),
        primary key (id)
    );
