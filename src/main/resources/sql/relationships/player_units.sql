CREATE TABLE IF NOT EXISTS player_units (
	player INTEGER NOT NULL REFERENCES player(id),
	unit INTEGER NOT NULL REFERENCES unit(id),
	PRIMARY KEY(player, unit));