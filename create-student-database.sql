CREATE DATABASE IF NOT EXISTS student_tracker; 
USE student_tracker;

CREATE TABLE IF NOT EXISTS `student`(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(45) DEFAULT NULL,
	last_name VARCHAR(45) DEFAULT NULL,
	email VARCHAR(45) DEFAULT NULL
);

INSERT INTO `student` (first_name, last_name, email) 
VALUES ('Mary', 'Public', 'mary@luv2code.com'), 
('John','Doe','john@luv2code.com'),
('Ajay','Rao','ajay@luv2code.com'),
('Bill','Neely','bill@luv2code.com'),
('Maxwell','Dixon','max@luv2code.com');
