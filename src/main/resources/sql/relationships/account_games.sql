CREATE TABLE IF NOT EXISTS account_games (
	account INTEGER NOT NULL REFERENCES account(id),
	game INTEGER NOT NULL REFERENCES game(id),
	PRIMARY KEY(account, game));