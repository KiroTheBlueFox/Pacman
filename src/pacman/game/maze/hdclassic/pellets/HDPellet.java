package pacman.game.maze.hdclassic.pellets;

import java.awt.Graphics2D;

import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Spritesheet;

public class HDPellet extends Pellet {
	protected static Spritesheet spritesheet = new Spritesheet("assets/hdclassicmaze/pellets.png", 0, 2, 1, 1);

	public HDPellet(Maze maze, int x, int y, int size, int score) {
		super(maze, x, y, size, score);
	}

	public void draw(Graphics2D brush) {
		spritesheet.drawSprite(brush, (int) ((x + 0.5f) * maze.getTileSize()), (int) ((y + 0.5f) * maze.getTileSize()),
				PELLET_ANIMATION_INDEX, 0);
	}
}
