package pacman.game.entities;

import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Clyde extends Ghost {
	public Clyde(int x, int y, float width, float height, boolean spawning, Direction spawnDirection) {
		super("clyde", x, y, width, height, new Spritesheet("assets/classicmaze/clyde.png", 0.1, 5, 2, 2, 2, 2, 2), spawning, spawnDirection);
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
	}
}
