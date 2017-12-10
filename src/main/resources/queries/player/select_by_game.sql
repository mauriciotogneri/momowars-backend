SELECT player.id, player.account, account.nickname, player.resources, player.status
	FROM players
	INNER JOIN account ON player.account = account.id
	WHERE (game = ?)