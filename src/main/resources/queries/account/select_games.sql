SELECT game.*
	FROM account_games
	INNER JOIN game ON account_games.game = game.id
	WHERE (account = ?)