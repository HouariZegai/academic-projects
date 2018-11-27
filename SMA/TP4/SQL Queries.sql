DROP DATABASE IF EXISTS tp4_sma;

-- Create database 
CREATE DATABASE IF NOT EXISTS tp4_sma;

USE tp4_sma;

-- Structure of table person
CREATE TABLE person (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100),
	surname VARCHAR(100),
	birthdate DATE
);

-- Data of table person
INSERT INTO person (name, surname, birthdate) VALUES ('Houari', 'Wari', '1996-11-17');
INSERT INTO person (name, surname, birthdate) VALUES ('Mohammed', 'moh', '1995-01-15');
INSERT INTO person (name, surname, birthdate) VALUES ('Omar', 'oummar', '1975-09-15');
INSERT INTO person (name, surname, birthdate) VALUES ('Saddik', 'sadik', '1985-08-15');
