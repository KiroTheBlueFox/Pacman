package pacman.game.entities;

import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Pinky extends Ghost {
	public Pinky(int x, int y, float width, float height, boolean spawning, Direction spawnDirection) {
		super("pinky", x, y, width, height, new Spritesheet("assets/classicmaze/pinky.png", 0.1, 5, 2, 2, 2, 2, 2), spawning, spawnDirection);
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
	}
}
