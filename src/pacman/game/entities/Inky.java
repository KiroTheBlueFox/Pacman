package pacman.game.entities;

import java.awt.Color;

import pacman.app.Application;
import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Inky extends Ghost {
	public static final int DISTANCE = 2;
	
	public Inky(int x, int y, int width, int height, boolean spawning, Direction spawnDirection) {
		super("inky", x, y, width, height, new Spritesheet("assets/classicmaze/inky.png", 0.1, 5, 2, 2, 2, 2, 2),
				spawning, spawnDirection);
		debugColor = new Color(0xFFFF);
	}

	@Override
	public void act(double delta) {
		switch (mode) {
		case CHASE:
			Ghost blinky = game.getGhostById("blinky");
			int playerTargetX = Application.getPlayer().getX(),
				playerTargetY = Application.getPlayer().getY(),
				newTargetX = Application.getPlayer().getX(),
				newTargetY = Application.getPlayer().getY();
			if (blinky != null) {
				switch (Application.getPlayer().getLastDirection()) {
				case DOWN:
					playerTargetY -= DISTANCE;
					break;
				case LEFT:
					playerTargetX -= DISTANCE;
					break;
				case RIGHT:
					playerTargetX += DISTANCE;
					break;
				case UP:
					playerTargetX -= DISTANCE;
					playerTargetY -= DISTANCE;
					break;
				}
				int distanceX = blinky.getX()-playerTargetX,
					distanceY = blinky.getY()-playerTargetY;
				newTargetX = playerTargetX-distanceX;
				newTargetY = playerTargetY-distanceY;
			}
			targetX = newTargetX;
			targetY = newTargetY;
			break;
		default:
		case SCATTER:
			targetX = game.getCurrentMaze().getWidth()/game.getCurrentMaze().getTileSize() - 1;
			targetY = game.getCurrentMaze().getHeight()/game.getCurrentMaze().getTileSize();
			break;
		case EATEN:
			targetX = game.getCurrentMaze().getGhostSpawnX();
			targetY = game.getCurrentMaze().getGhostSpawnY();
			break;
		}
		super.act(delta);
	}
}
