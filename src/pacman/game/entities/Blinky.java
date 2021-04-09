package pacman.game.entities;

import java.util.List;

import pacman.app.Application;
import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Blinky extends Ghost {
	public Blinky(int x, int y, boolean spawning, Direction spawnDirection) {
		super("blinky", x, y, new Spritesheet("assets/classicmaze/blinky.png", 0.1, 5, 2, 2, 2, 2, 2), spawning, spawnDirection);
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
		List<Direction> directions = getAllPossibleWays();
		float chasingX, chasingY;
		if (chase) {
			chasingX = Application.getPlayer().getX();
			chasingY = Application.getPlayer().getY();
		} else {
			chasingX = game.getCurrentMaze().getWidth();
			chasingY = game.getCurrentMaze().getHeight();
		}
		if (directions.size() == 1) {
			this.direction = directions.get(0);
		} else {
			for (Direction direction : directions) {
				for (Direction directionBis : Direction.fromAtoB(this.x, this.y, Application.getPlayer().getX(), Application.getPlayer().getY())) {
					if (directions.contains(directionBis)) {
						float distance = game.getDistanceInDirection(this, direction);
					}
				}
			}
		}
	}
}
