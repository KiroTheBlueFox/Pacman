package pacman.game.maze.hdclassic.pellets;

import java.awt.Graphics2D;

import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.PowerPellet;

public class HDPowerPellet extends PowerPellet {
	public HDPowerPellet(Maze maze, int x, int y, int size, int score) {
		super(maze, x, y, size, score);
	}
	
	@Override
	public void draw(Graphics2D brush) {
		brush.fillOval(8+x*maze.getTileSize()-size/2, 8+y*maze.getTileSize()-size/2, size, size);
	}
}
