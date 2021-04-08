package pacman.game.entities;

import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Inky extends Ghost {
	public Inky(int x, int y, boolean spawning, Direction spawnDirection) {
		super("inky", x, y, new Spritesheet("assets/classicmaze/inky.png", 0.1, 5, 2, 2, 2, 2, 2), spawning, spawnDirection);
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
	}
}
