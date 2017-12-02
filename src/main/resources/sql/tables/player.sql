CREATE TABLE player (
	id SERIAL PRIMARY KEY,
	account INTEGER NOT NULL REFERENCES account(id),
	resources INTEGER NOT NULL CHECK (resources >= 0),
	status PLAYER_STATUS NOT NULL);