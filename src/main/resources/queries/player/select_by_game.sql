SELECT player.id, player.game, player.account, account.nickname, player.resources, player.status
	FROM player
	INNER JOIN account ON player.account = account.id
	WHERE (player.game = ?)