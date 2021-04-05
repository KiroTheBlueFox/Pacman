package pacman.game.maze.pellets;

import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;

public class PowerPellet extends Pellet {
	public PowerPellet(Maze maze, int x, int y, int size, int score) {
		super(maze, x, y, size, score);
	}
	
	@Override
	public void draw(Graphics2D brush) {
		brush.fillRect(x-size/2, y-size/4, size, size/2);
		brush.fillRect(x-size/4, y-size/2, size/2, size);
		brush.fillRect(x-size/8*3, y-size/8*3, size/8*6, size/8*6);
	}
	
	@Override
	public void act(PacMan player, double delta) {
		player.addScore(score);
		Application.playSound(Clips.powerPellet, false);
	}
}