SELECT array_agg(match.id) as ids, array_agg(match.status) as statuses
	FROM player
	INNER JOIN match ON player.match = match.id
	WHERE (player.account = ?)