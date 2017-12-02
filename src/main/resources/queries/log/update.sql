UPDATE log
	SET response_status = ?, response_headers = ?, response_entity = ?, duration = ?
	WHERE (id = ?)