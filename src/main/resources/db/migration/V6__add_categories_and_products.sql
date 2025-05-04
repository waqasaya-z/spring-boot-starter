create table categories
(
    id   TINYINT auto_increment
        primary key,
    name varchar(255) null
);

create table products
(
    id          BIGINT auto_increment
        primary key,
    name        varchar(255)   not null,
    price       decimal(10, 2) not null,
    category_id TINYINT           not null,
    constraint products_categories_id_fk
        foreign key (category_id) references categories (id)
                ON DELETE RESTRICT
);

