-- Structure of table Employee
CREATE TABLE employee (
	id INT PRIMARY KEY,
	name VARCHAR(150),
	salary REAL
);

-- Data of table Employee
INSERT INTO employee 
	VALUES (1, 'Houari', 12000.00);
INSERT INTO employee 
	VALUES (2, 'Mohammed', 20000.00);
INSERT INTO employee 
	VALUES (3, 'Omar', 3000.00);

-- Transaction with ROLLBACK
BEGIN;
DELETE FROM employee WHERE salary < 4000;
ROLLBACK;

-- Show data of table
SELECT * FROM employee;

-- Transaction with COMMIT;
BEGIN;
DELETE FROM employee WHERE salary < 4000;
COMMIT;