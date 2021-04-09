package pacman.game.entities;

import java.awt.Color;

import pacman.app.Application;
import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Blinky extends Ghost {
	public Blinky(int x, int y, int width, int height, boolean spawning, Direction spawnDirection) {
		super("blinky", x, y, width, height, new Spritesheet("assets/classicmaze/blinky.png", 0.1, 5, 2, 2, 2, 2, 2),
				spawning, spawnDirection);
		debugColor = new Color(0xFF0000);
	}

	@Override
	public void act(double delta) {
		switch (mode) {
		case CHASE:
			targetX = Application.getPlayer().getX();
			targetY = Application.getPlayer().getY();
			break;
		default:
		case SCATTER:
			targetX = game.getCurrentMaze().getWidth()/game.getCurrentMaze().getTileSize() - 3;
			targetY = -4;
			break;
		case EATEN:
			targetX = game.getCurrentMaze().getGhostSpawnX();
			targetY = game.getCurrentMaze().getGhostSpawnY();
			break;
		}
		super.act(delta);
	}
}
