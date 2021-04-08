package pacman.game.entities;

import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Ghost extends Entity {
	protected boolean spawning = true;
	
	public Ghost(String id, int x, int y, Spritesheet spritesheet, boolean spawning, Direction spawnDirection) {
		super(id, x, y, 16, 16, spritesheet);
		this.spawning = spawning;
		this.direction = spawnDirection;
	}
}
