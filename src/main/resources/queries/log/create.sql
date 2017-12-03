INSERT
	INTO log
	(timestamp, ip, request_method, request_path, request_headers, request_entity)
    VALUES (?, ?, ?, ?, ?, ?)