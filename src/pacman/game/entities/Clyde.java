package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.utils.Direction;
import pacman.utils.MathUtils;
import pacman.utils.Spritesheet;

public class Clyde extends Ghost {
	public static final int DISTANCE = 8;
	
	public Clyde(int x, int y, int width, int height, boolean spawning, Direction spawnDirection) {
		super("clyde", x, y, width, height, new Spritesheet("assets/classicmaze/clyde.png", 0.1, 5, 2, 2, 2, 2, 2),
				spawning, spawnDirection);
		debugColor = new Color(0xFFB851);
	}

	@Override
	public void act(double delta) {
		switch (mode) {
		case CHASE:
			int playerTargetX = Application.getPlayer().getX(),
				playerTargetY = Application.getPlayer().getY();
			if (MathUtils.distanceSquared(x, y, playerTargetX, playerTargetY) > Math.pow(DISTANCE, 2)) {
				targetX = playerTargetX;
				targetY = playerTargetY;
			} else {
				targetX = 0;
				targetY = game.getCurrentMaze().getHeight()/game.getCurrentMaze().getTileSize();
			}
			break;
		default:
		case SCATTER:
			targetX = 0;
			targetY = game.getCurrentMaze().getHeight()/game.getCurrentMaze().getTileSize();
			break;
		case EATEN:
			targetX = game.getCurrentMaze().getGhostSpawnX();
			targetY = game.getCurrentMaze().getGhostSpawnY();
			break;
		}
		super.act(delta);
	}
	
	@Override
	public void drawDebug(Graphics2D brush) {
		super.drawDebug(brush);
		brush.setRenderingHints(game.antialiasingRH);
		brush.setColor(debugColor);
		brush.drawOval((Application.getPlayer().getX() - DISTANCE) * game.getCurrentMaze().getTileSize(),
				(Application.getPlayer().getY() - DISTANCE) * game.getCurrentMaze().getTileSize(),
				DISTANCE * 2 * game.getCurrentMaze().getTileSize(), DISTANCE * 2 * game.getCurrentMaze().getTileSize());
		brush.setRenderingHints(game.noAntialiasingRH);
	}
}
