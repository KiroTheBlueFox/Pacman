package pacman.game.maze.classic.pellets;

import java.util.TimerTask;

import pacman.app.Application;
import pacman.game.Game;
import pacman.game.entities.Entity;
import pacman.game.entities.Ghost;

public class PowerPelletCooldownAction extends TimerTask {
	@Override
	public void run() {
		Game game = Application.getGame();
		for (Entity actor : game.getActors()) {
			if (actor instanceof Ghost) {
				((Ghost) actor).setFrightenedCooldown(true);
			}
		}
	}
}
