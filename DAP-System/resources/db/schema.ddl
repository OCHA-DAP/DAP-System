
    drop table dap_user;

    create table dap_user (
        id varchar(255) not null,
        ckanApiKey varchar(255),
        password varchar(255),
        primary key (id)
    );
