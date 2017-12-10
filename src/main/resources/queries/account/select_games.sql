SELECT game.*
	FROM player
	INNER JOIN game ON player.game = game.id
	WHERE (player.account = ?)