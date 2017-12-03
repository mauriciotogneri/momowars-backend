SELECT player.id, player.account, account.nickname, player.resources, player.status
	FROM game_players
	INNER JOIN player ON game_players.player = player.id
	INNER JOIN account ON player.account = account.id
	WHERE (game = ?)