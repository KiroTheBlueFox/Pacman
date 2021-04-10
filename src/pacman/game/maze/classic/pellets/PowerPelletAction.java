package pacman.game.maze.classic.pellets;

import java.util.TimerTask;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.Game;
import pacman.game.api.GhostMode;
import pacman.game.entities.Entity;
import pacman.game.entities.Ghost;

public class PowerPelletAction extends TimerTask {
	@Override
	public void run() {
		Game game = Application.getGame();
		Application.stopSound(Clips.powerPellet);
		for (Entity actor : game.getActors()) {
			if (actor instanceof Ghost) {
				Ghost ghost = (Ghost) actor;
				if (ghost.getMode() != GhostMode.EATEN) {
					ghost.setMode(game.getGameLevel().getDifficulty().getModeForTime(ghost.getLifeTime()), true);
					ghost.setFrightenedCooldown(false);
				}
			}
		}
	}
}
