# Coffee Inventory DAO

This project involves the creation of a Java DAO for an existing database structure.

## Structure
Your solution must have the following structural elements:

* Your solution must be in an IntelliJ project called ```CoffeeInventoryDao-Firstname-Lastname``` where Firstname and Lastname are your first and last name respectively.
* Your solution must utilize JdbcTemplates and Prepared Statements.
* Your solution must contain a full set of unit/integration tests.

## Methodology

* You must utilize TDD and Red Green Refactor when developing this project.
* You must use Pivotal Tracker to track your tasks for this project.

## Requirements/Features

This project is a DAO and relational database that keeps track of coffee in inventory. 

* Your DAO API must allow callers to create, read, read all, update, and delete Coffees and Roasters in the system. The system must also allow callers to find Coffees by Roaster Id and Coffees by Type.
* Your solution must be based on the database created by the SQL script below.

```sql
create schema if not exists coffee_inventory;
use coffee_inventory;

create table if not exists coffee (
	coffee_id int not null auto_increment primary key,
    roaster_id int not null,
    name varchar(50) not null,
    count int not null,
    unit_price decimal(5,2) not null,
    description varchar(255), 
    type varchar(50)
);

create table if not exists roaster (
	roaster_id int not null auto_increment primary key,
    name varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state char(2) not null,
    postal_code varchar(25) not null,
    phone varchar(15) not null,
    email varchar(60) not null,
    note varchar(255)
);

/* Foreign Keys: coffee */
alter table coffee add constraint fk_coffee_roaster foreign key (roaster_id) references roaster(roaster_id);
```

---

© 2019 Trilogy Education Services





