package pacman.game.maze.classic.pellets;

import java.util.TimerTask;

import pacman.app.Application;
import pacman.game.Game;
import pacman.game.api.GhostMode;
import pacman.game.entities.Entity;
import pacman.game.entities.Ghost;
import pacman.game.entities.PacMan;

public class PowerPelletAction extends TimerTask {
	@Override
	public void run() {
		Game game = Application.getGame();
		game.setPowerPellet(false);
		for (Entity entity : game.getEntities()) {
			if (entity instanceof Ghost) {
				Ghost ghost = (Ghost) entity;
				if (ghost.getMode() == GhostMode.FRIGHTENED) {
					ghost.setMode(game.getGameLevel().getDifficulty().getModeForTime(ghost.getLifeTime()), true);
					ghost.setFrightenedCooldown(false);
				}
			} else if (entity instanceof PacMan) {
				((PacMan) entity).resetGhostCombo();
			}
		}
	}
}
