DROP TABLE IF EXISTS persons;

CREATE TABLE persons (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NULL,
  last_name VARCHAR(250) NULL
);