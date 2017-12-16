SELECT player.id, player.match, player.account, account.nickname, player.resources, player.status
	FROM player
	INNER JOIN account ON player.account = account.id
	WHERE (player.match = ?)