DO $$
BEGIN

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'cell_type') THEN
		CREATE TYPE CELL_TYPE AS ENUM ('BASE', 'GRASS', 'EARTH', 'MOUNTAIN', 'FOREST', 'DESERT', 'CONCRETE', 'WATER', 'ICE');
	END IF;

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'game_status') THEN
		CREATE TYPE GAME_STATUS AS ENUM ('OPEN', 'PLAYING', 'CALCULATING', 'FINISHED');
	END IF;

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'movement_type') THEN
		CREATE TYPE MOVEMENT_TYPE AS ENUM ('NONE', 'UP_LEFT', 'UP', 'UP_RIGHT', 'DOWN_RIGHT', 'DOWN', 'DOWN_LEFT');
	END IF;

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'player_status') THEN
		CREATE TYPE PLAYER_STATUS AS ENUM ('PLAYING', 'WAITING', 'VICTORIOUS', 'DEFEATED', 'SURRENDERED');
	END IF;

	IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'unit_type') THEN
		CREATE TYPE UNIT_TYPE AS ENUM ('SQUAD', 'MORTAR', 'HUMVEE', 'LIGHT_TANK', 'HEAVY_TANK', 'HELICOPTER');
	END IF;
    
END$$;