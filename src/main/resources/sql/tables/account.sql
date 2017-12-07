CREATE TABLE account (
	id SERIAL PRIMARY KEY,
	email VARCHAR(50) UNIQUE NOT NULL,
	nickname VARCHAR(30) NOT NULL,
	password VARCHAR(128) NULL,
	picture TEXT NULL,
	session VARCHAR(128) UNIQUE NOT NULL);