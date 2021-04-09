package pacman.game.entities;

import java.awt.Color;

import pacman.app.Application;
import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Pinky extends Ghost {
	public static final int DISTANCE = 4;
	
	public Pinky(int x, int y, int width, int height, boolean spawning, Direction spawnDirection) {
		super("pinky", x, y, width, height, new Spritesheet("assets/classicmaze/pinky.png", 0.1, 5, 2, 2, 2, 2, 2),
				spawning, spawnDirection);
		debugColor = new Color(0xFFB8FF);
	}

	@Override
	public void act(double delta) {
		switch (mode) {
		case CHASE:
			this.targetX = Application.getPlayer().getX();
			this.targetY = Application.getPlayer().getY();
			switch (Application.getPlayer().getLastDirection()) {
			case DOWN:
				this.targetY += DISTANCE;
				break;
			case LEFT:
				this.targetX -= DISTANCE;
				break;
			case RIGHT:
				this.targetX += DISTANCE;
				break;
			case UP:
				this.targetX -= DISTANCE;
				this.targetY -= DISTANCE;
				break;
			}
			break;
		default:
		case SCATTER:
			targetX = 2;
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
