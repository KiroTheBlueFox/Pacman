package pacman.game.maze.hdclassic.pellets;

import java.awt.Graphics2D;

import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.PowerPellet;
import pacman.utils.Spritesheet;

public class HDPowerPellet extends PowerPellet {
	protected static Spritesheet spritesheet = new Spritesheet("assets/hdclassicmaze/pellets.png", 0, 2, 1, 1);
	
	public HDPowerPellet(Maze maze, int x, int y, int size, int score) {
		super(maze, x, y, size, score);
	}
	
	@Override
	public void draw(Graphics2D brush) {
		spritesheet.drawSprite(brush, (int) ((x+0.5f)*maze.getTileSize()), (int) ((y+0.5f)*maze.getTileSize()), POWER_PELLET_ANIMATION_INDEX, 0);
	}
}
