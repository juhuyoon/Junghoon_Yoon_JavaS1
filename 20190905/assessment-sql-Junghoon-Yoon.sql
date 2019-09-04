-- A little HELLO WORLD SQL Style

SELECT 'HELLO WORLD';
USE northwind;

-- List of all products

SELECT * FROM products;
SELECT * FROM northwind.products;

-- List of all employees
SELECT * FROM northwind.employees;

-- Let's just get the product_code and category
SELECT product_code, category from northwind.products;

-- List all the customers
SELECT * FROM customers;

-- List all the orders
SELECT * FROM orders;

-- Let's find just the cameras
SELECT * FROM northwind.products WHERE category = 'Camera';

-- Let's find all the employees who are design engineers
SELECT * FROM employees WHERE job_title = 'Design Engineer';

-- Let's find all the employees from either Texas or Minnesota
SELECT * FROM northwind.employees WHERE state = 'Texas' OR  state = 'Minnesota';

-- Let's find all the orders that had a shipping fee of between 6 and 8 dollars that were shipped to Peru or Poland
SELECT * FROM orders WHERE shipping_fee BETWEEN 6 AND 8 AND (ship_country = 'Peru' OR ship_country = 'Poland');
SELECT * FROM orders WHERE shipping_fee BETWEEN 6 AND 8 AND ship_country IN('Peru', 'Poland');

-- List all the orders shipped to Brazil
SELECT * FROM orders WHERE ship_country = 'Brazil';
-- List all the orders shipped to Alice Warren or Wanda Hill.
SELECT * FROM orders WHERE ship_name = 'Alice Warren' OR ship_name = 'Wanda Hill';
-- List all the customers from Ohio, Iowa, and Texas. 
SELECT * FROM customers WHERE LOWER(state) IN('ohio', 'iowa', 'texas');

SELECT * FROM customers WHERE city LIKE 'O%';

-- List all first names of customers where Starts with A, third character is lower a
SELECT * FROM customers WHERE first_name LIKE 'A_a%';

SELECT * FROM customers WHERE first_name LIKE '%t';

SELECT * FROM customers WHERE first_name LIKE 'T__m%';

-- I can't read this note. Do we have someone working here with the initials JB?
SELECT * FROM employees WHERE first_name LIKE 'J%' AND last_name LIKE 'B%';

-- Find all customers with a co.uk email address
SELECT * FROM customers WHERE email like '%co.uk';

-- All orders where shipping city starts with S and has 'n' as the 3rd letter
SELECT * FROM orders WHERE ship_city LIKE 'S_n%';

-- List all customers with last names that start with "Si";
SELECT * FROM customers WHERE last_name LIKE 'Si%';

-- List all employees with .edu email addresses
SELECT * FROM employees WHERE email LIKE '%.edu';

-- List all customers that do not have a phone number
SELECT * FROM customers WHERE phone LIKE null;

-- Inner Joins
-- Let's get the names of the people that the completed orders for Keith Lawrence were shipped to:
	SELECT orders.ship_name FROM northwind.orders
    INNER JOIN northwind.employees 
    ON orders.employee_id = employees.id
    WHERE employees.first_name = 'Keith' and employees.last_name = 'Lawrence'
    AND orders.order_status = 'Complete';

-- Let's get the customer names and the names of the people that all the completed orders for Keith Lawrence were shipped to.
	SELECT orders.ship_name, customers.first_name, customers.last_name
    FROM northwind.orders
    INNER JOIN northwind.employees ON orders.employee_id = employees_id
    INNER JOIN northwind_customers ON orders.customers_id = customers_id
    WHERE employees.first_name = 'Keith'
    AND employees.last_name = 'Lawrence'
    AND orders.order_status = 'Complete';
    
    -- Let's get the names, categories, and quantities for all the items in a given order ID 4009
    SELECT products.product_name, products.category, order_details.quantity
    FROM northwind.products
    INNER JOIN order_details
    ON order_details.product_id = products.id
    WHERE order_details.order_id = 4009;
    
    -- Let's get the order numbers for all the orders for all of the employees
    SELECT emp.first_name, emp.last_name, ord.id
    FROM northwind.employees emp
    LEFT JOIN northwind.orders ord ON ord.employee_id = emp.id;
    
    


-- What are the categories of products in the database?
SELECT category FROM northwind.products;
-- Camera, Laptop, Tablet, Phone

-- What products are made by Dell?
SELECT * FROM northwind.products WHERE product_name LIKE 'Dell%';

-- List all the orders shipped to Pennsylvania.
SELECT * FROM orders WHERE ship_state = 'Pennsylvania';

-- List the first name and last name of all employees with names that start with w
SELECT * FROM employees WHERE first_name REGEXP '^[B].*$';
SELECT * FROM employees WHERE first_name LIKE 'B%';

-- List all customers from zipcodes that start with 55
SELECT * FROM customers WHERE postal_code LIKE '55%' ;

-- List all customers from zipcodes that end with 0?
SELECT * FROM customers WHERE postal_code LIKE '%0';

-- List the first name, last name, and email for all customers with a .org email address
SELECT first_name, last_name, email FROM customers WHERE email LIKE '%.org';

-- List the first name, last name, and phone number for all customers from the 202 area code
SELECT first_name, last_name, phone FROM customers WHERE phone LIKE '%202%';

-- List the order id for each order placed by George Wilson 
    
	SELECT orders.id FROM northwind.orders
	INNER JOIN customers
    ON customers.id = orders.customer_id
	WHERE customers.id = '3';
 
 
     
SELECT * FROM order_details;
SELECT * FROM orders;
SELECT * FROM products;


-- List all the products and quantities associated with order 4003
-- Product ID to order details
	SELECT order_details.quantity, products.product_name FROM northwind.order_details
    INNER JOIN products
    ON products.id = order_details.product_id
    WHERE order_details.order_id = 4003;



-- Car lot Schema
DROP DATABASE IF EXISTS car_lot;
create schema car_lot;
use car_lot;

create table car (
	id		INT NOT NULL,
    make	VARCHAR(50),
    model	VARCHAR(50),
    model_year	VARCHAR(4),
    color	VARCHAR(50),
	PRIMARY KEY (id)
);

-- Add the following cars to inventory
INSERT INTO car (id, make, model, model_year, color) values (1, 'Honda', 'Accord', '2012', 'Red');
INSERT INTO car (id, make, model, model_year, color) values (2, 'Chevy', 'Impala', '2017', 'Black');
INSERT INTO car (id, make, model, model_year, color) values (3, 'Ford', 'F-150', '2019', 'Silver');
INSERT INTO car (id, make, model, model_year, color) values (4, 'Subaru', 'Outback', '2020', 'White');
INSERT INTO car (id, make, model, model_year, color) values (5, 'Ford', 'Mustang', '2015', 'Silver');
INSERT INTO car (id, make, model, model_year, color) values (6, 'Honda', 'Ridgeline', '2018', 'Blue');
INSERT INTO car (id, make, model, model_year, color) values (7, 'Chevy', 'Silverado', '2017', 'Gray');

SELECT * FROM car;

-- Make the following updates to the database
UPDATE car SET color = 'Black' WHERE make LIKE 'Honda';
UPDATE car SET make = 'Chevrolet' WHERE make LIKE 'Chevy';
UPDATE car SET model_year = '2019' WHERE model_year = '2020';

-- Delete the following
DELETE FROM car WHERE color = 'Blue';
DELETE FROM car WHERE make = 'Ford';
DELETE FROM car WHERE model_year BETWEEN '2012' AND '2017';