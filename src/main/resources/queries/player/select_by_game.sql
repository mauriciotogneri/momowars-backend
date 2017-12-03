SELECT player.*
	FROM game_players
	INNER JOIN player ON game_players.player = player.id
	WHERE (game = ?)