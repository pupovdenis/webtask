create table books (
    id int8 not null,
    author varchar(255),
    is_private boolean,
    name varchar(255),
    release_date timestamp,
    primary key (id)
);