CREATE TABLE IF NOT EXISTS game_players (
	game INTEGER NOT NULL REFERENCES game(id),
	player INTEGER NOT NULL REFERENCES player(id),
	PRIMARY KEY(game, player));