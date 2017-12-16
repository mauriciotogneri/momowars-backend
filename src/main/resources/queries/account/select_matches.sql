SELECT match.*
	FROM player
	INNER JOIN match ON player.match = match.id
	WHERE (player.account = ?)