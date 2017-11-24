DO $$
BEGIN

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'game_status') THEN
		CREATE TYPE GAME_STATUS AS ENUM ('OPEN', 'PLAYING', 'CALCULATING', 'FINISHED');
	END IF;
	
END $$;