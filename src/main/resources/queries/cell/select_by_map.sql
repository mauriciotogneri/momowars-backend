SELECT cell.*
	FROM map_cells
	INNER JOIN cell ON map_cells.cell = cell.id
	WHERE map_cells.map = ?