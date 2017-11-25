CREATE TABLE account_games (
	account INTEGER NOT NULL REFERENCES account(id),
	game INTEGER NOT NULL REFERENCES game(id),
	PRIMARY KEY(account, game));