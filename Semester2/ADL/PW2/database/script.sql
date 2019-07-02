
CREATE DATABASE adl_tp_db;

USE adl_tp_db;

CREATE TABLE users (
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(25),
	password VARCHAR(25),
	phone VARCHAR(15)
);