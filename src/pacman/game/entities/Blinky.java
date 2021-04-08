package pacman.game.entities;

import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Blinky extends Ghost {
	public Blinky(int x, int y, boolean spawning, Direction spawnDirection) {
		super("blinky", x, y, new Spritesheet("assets/classicmaze/blinky.png", 0.1, 5, 2, 2, 2, 2, 2), spawning, spawnDirection);
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
	}
}
