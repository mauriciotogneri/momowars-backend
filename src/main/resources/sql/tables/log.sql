CREATE TABLE log (
	id BIGSERIAL PRIMARY KEY,
	timestamp TIMESTAMP WITH TIME ZONE NOT NULL,
	ip VARCHAR(40) NOT NULL,
	request_method VARCHAR(7) NOT NULL,
	request_path TEXT NOT NULL,
	request_headers TEXT,
	request_entity TEXT,
	response_status INTEGER,
	response_headers TEXT,
	response_entity TEXT,
	duration INTEGER);