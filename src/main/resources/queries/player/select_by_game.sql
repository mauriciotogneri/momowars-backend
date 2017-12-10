SELECT player.id, player.account, account.nickname, player.resources, player.status
	FROM player
	INNER JOIN account ON player.account = account.id
	WHERE (player.game = ?)