package pacman.game.maze.classic.pellets;

import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.api.GhostMode;
import pacman.game.entities.Entity;
import pacman.game.entities.Ghost;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;

public class PowerPellet extends Pellet {
	public static final int POWER_PELLET_ANIMATION_INDEX = 1;
	
	public PowerPellet(Maze maze, int x, int y, int size, int score) {
		super(maze, x, y, size, score);
	}

	@Override
	public void draw(Graphics2D brush) {
		brush.setRenderingHints(maze.getGame().noAntialiasingRH);
		spritesheet.drawSprite(brush, (int) ((x + 0.5f) * maze.getTileSize()), (int) ((y + 0.5f) * maze.getTileSize()),
				POWER_PELLET_ANIMATION_INDEX, 0);
		brush.setRenderingHints(maze.getGame().antialiasingRH);
	}

	@Override
	public void act(PacMan player, double delta) {
		player.addScore(score);
		maze.getGame().setPowerPellet(true);
		Application.playSound(Clips.powerPellet, 3, true);
		for (Entity entity : maze.getGame().getEntities()) {
			if (entity instanceof Ghost) {
				Ghost ghost = ((Ghost) entity);
				if (ghost.getMode() != GhostMode.EATEN) {
					ghost.setMode(GhostMode.FRIGHTENED, true);
				}
			}
		}
		maze.getGame().refreshPowerPelletTimer();
	}
}
