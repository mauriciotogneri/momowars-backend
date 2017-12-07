UPDATE log
	SET response_status = ?, response_headers = ?, duration = ?
	WHERE (id = ?)