CREATE TABLE IF NOT EXISTS player (
	id SERIAL PRIMARY KEY,
	account INTEGER NOT NULL REFERENCES account(id),
	color TEXT NOT NULL,
	resources INTEGER NOT NULL CHECK (resources >= 0),
	status PLAYER_STATUS NOT NULL);