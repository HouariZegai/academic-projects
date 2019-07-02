# database adweb_db
CREATE DATABASE adweb_db;

USE adweb_db;

# student table
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(200) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `id_promotion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


# table promotion
CREATE TABLE promotion (
	id INT PRIMARY KEY,
    promotion VARCHAR(30)
);

# add operation
ALTER TABLE student ADD COLUMN (id_promotion INT REFERENCES promotion(id));

# data of table promotion
INSERT INTO promotion VALUES(1, 'Master 1 GL');