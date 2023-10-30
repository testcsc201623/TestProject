drop table if exists user_mst;
create table user_mst (
    user_id varchar(128) not null,
    password varchar(256) not null,
    user_name varchar(128) not null,
    admin_flg INT(1) not null,
    create_date_time DATETIME not null,
    update_date_time DATETIME not null,
    primary key(user_id)
);
drop table if exists title_tbl;
create table title_tbl (
    title_id int(8) not null,
    title varchar(128) not null,
    create_user_id varchar(128) not null,
    create_date_time DATETIME not null,
    update_date_time DATETIME not null,
    primary key(title_id)
);
drop table if exists thread_tbl;
create table thread_tbl(
    title_id int(8) not null,
    message_number int(4) not null,
    user_id varchar(128) not null,
    comment varchar(1024) not null,
    create_date_time DATETIME not null,
    update_date_time DATETIME not null,
    primary key(title_id, message_number)
);

drop table if exists title_permission_tbl;
create table title_permission_tbl (
    title_id int(8) not null,
    user_id varchar(128) not null,
    admin_flg INT(1) not null,
    primary key(title_id,user_id)
);

drop table if exists last_title_viewed_tbl;
create table last_title_viewed_tbl (
    user_id varchar(128) not null,
    title_id int(8) not null,
    create_date_time DATETIME not null,
    update_date_time DATETIME not null,
    primary key(user_id,title_id)
);