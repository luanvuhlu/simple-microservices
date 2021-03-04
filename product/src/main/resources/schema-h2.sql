CREATE SCHEMA IF NOT EXISTS product;
use product;

DROP TABLE IF EXISTS product;
CREATE table product(
id int NOT NULL,
name VARCHAR(250) NOT NULL,
category_id int DEFAULT NULL,
created_time DATE DEFAULT NULL,
updated_time DATE DEFAULT NULL,
PRIMARY KEY(id)
);
DROP TABLE IF EXISTS category;
CREATE table category(
id int NOT NULL,
name VARCHAR(250) NOT NULL,
created_time DATE DEFAULT NULL,
updated_time DATE DEFAULT NULL,
PRIMARY KEY(id)
)