CREATE SCHEMA IF NOT EXISTS video_game_collection;
CREATE SCHEMA IF NOT EXISTS video_game_collection_test;

 use video_game_collection;
-- use video_game_collection_test;

create table if not exists game (
        game_id int not null auto_increment primary key,
    console_id int not null,
    publisher_id int not null,
    type_id int not null
);

create table if not exists console (
        console_id int not null auto_increment primary key,
    name varchar(50) not null,
    year char(4) not null
);

create table if not exists publisher (
        publisher_id int not null auto_increment primary key,
    name varchar(50) not null,
    website varchar(75)
);

create table if not exists type (
        type_id int not null auto_increment primary key,
    name varchar(50) not null,
    description varchar(255) not null
);

/* Foreign Keys: game */
alter table game add constraint fk_game_console foreign key (console_id) references console(console_id);
alter table game add constraint fk_game_publisher foreign key (publisher_id) references publisher(publisher_id);
alter table game add constraint fk_game_type foreign key (type_id) references type(type_id);