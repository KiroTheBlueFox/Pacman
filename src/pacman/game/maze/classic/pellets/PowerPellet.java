package pacman.game.maze.classic.pellets;

import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.api.GhostMode;
import pacman.game.entities.Entity;
import pacman.game.entities.Ghost;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;
import pacman.utils.MathUtils;

public class PowerPellet extends Pellet {
	public static final int POWER_PELLET_ANIMATION_INDEX = 1;
	private PowerPelletAction action = new PowerPelletAction();
	private PowerPelletCooldownAction cooldownAction = new PowerPelletCooldownAction();
	
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
		Application.playSound(Clips.powerPellet, 3, true);
		for (Entity actor : maze.getGame().getActors()) {
			if (actor instanceof Ghost) {
				Ghost ghost = ((Ghost) actor);
				if (ghost.getMode() != GhostMode.EATEN) {
					ghost.setMode(GhostMode.FRIGHTENED, true);
				}
			}
		}
		action.cancel();
		action = new PowerPelletAction();
		cooldownAction.cancel();
		cooldownAction = new PowerPelletCooldownAction();
		Application.getPowerPelletTimer().schedule(cooldownAction, Math.round((MathUtils.clamp((int) Math.round((8000/(maze.getGame().getGameLevel().getSpeedFactor()*10))), 4000, 8000)*0.75d)));
		Application.getPowerPelletTimer().schedule(action, MathUtils.clamp((int) Math.round(8000/(maze.getGame().getGameLevel().getSpeedFactor()*10)), 4000, 8000));
	}
}
