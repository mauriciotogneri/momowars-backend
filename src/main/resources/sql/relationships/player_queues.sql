CREATE TABLE IF NOT EXISTS player_queues (
	player INTEGER NOT NULL REFERENCES player(id),
	queue INTEGER NOT NULL REFERENCES queue(id),
	PRIMARY KEY(player, queue));