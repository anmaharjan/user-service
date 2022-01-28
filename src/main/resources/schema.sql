create table if not exists roles
(
    id   bigint auto_increment
        primary key,
    role varchar(15) null
);

create table if not exists users
(
    id            bigint auto_increment
        primary key,
    email         varchar(50)  null,
    intro         longtext     null,
    mobile        varchar(15)  null,
    first_name    varchar(100) null,
    last_name     varchar(100) null,
    middle_name   varchar(100) null,
    password      varchar(255) null,
    profile       longtext     null,
    registered_at datetime     null
);

create table if not exists users_roles
(
    user_id  bigint not null,
    roles_id bigint not null
);