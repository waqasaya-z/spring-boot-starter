create table tags
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table user_tags
(
    user_id bigint not null,
    tags_id int    not null,
    constraint user_tags_pk
        primary key (user_id, tags_id),
    constraint user_tags_tags_id_fk
        foreign key (tags_id) references tags (id) ON DELETE CASCADE,
    constraint user_tags_users_id_fk
        foreign key (user_id) references users (id) ON DELETE CASCADE
);

