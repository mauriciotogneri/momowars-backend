SELECT account.*
	FROM account
	INNER JOIN player ON player.account = account.id
	WHERE (player.game = ?)