# Video Game Collection DAO (optional)

This optional project involves the creation of a Java DAO for an existing database structure.

## Structure
Your solution must have the following structural elements:

* Your solution must be in an IntelliJ project called ```VideoGameCollectionDao-Firstname-Lastname``` where Firstname and Lastname are your first and last name respectively.
* Your solution must utilize JdbcTemplates and prepared statements.
* Your solution must contain a full set of unit/integration tests.

## Methodology

* You must utilize TDD and Red Green Refactor when developing this project.
* You must use Pivotal Tracker to track your tasks for this project.

## Requirements/Features

This project is a DAO and relational database that keeps track of coffee in inventory. 

* Your DAO API must allow callers to create, read, read all, update, and delete Games, Consoles, Publishers, and Types in the system. The system must also allow callers to find Games by Console, Games by Publishers, and Games by Type.
* Your solution must be based on the database created by the SQL script below.

```sql
create schema if not exists video_game_collection;
use video_game_collection;

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
    descrption varchar(255) not null
);

/* Foreign Keys: game */
alter table game add constraint fk_game_console foreign key (console_id) references console(console_id);
alter table game add constraint fk_game_publisher foreign key (publisher_id) references publisher(publisher_id);
alter table game add constraint fk_game_type foreign key (type_id) references type(type_id);
```

---

© 2019 Trilogy Education Services





