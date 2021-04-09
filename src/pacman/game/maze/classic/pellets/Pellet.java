package pacman.game.maze.classic.pellets;

import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;
import pacman.utils.Spritesheet;

public class Pellet {
	protected static final int PELLET_ANIMATION_INDEX = 0;
	protected static Spritesheet spritesheet = new Spritesheet("assets/classicmaze/pellets.png", 0, 2, 1, 1);
	public static boolean highPitch = true;
	protected int x, y, size, score;
	protected Maze maze;
	
	public Pellet(Maze maze, int x, int y, int size, int score) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.maze = maze;
		this.score = score;
		maze.addPellet(true);
	}
	
	public void draw(Graphics2D brush) {
		brush.setRenderingHints(maze.getGame().noAntialiasingRH);
		spritesheet.drawSprite(brush, (int) ((x + 0.5f) * maze.getTileSize()), (int) ((y + 0.5f) * maze.getTileSize()),
				PELLET_ANIMATION_INDEX, 0);
		brush.setRenderingHints(maze.getGame().antialiasingRH);
	}
	
	public void act(PacMan player, double delta) {
		player.addScore(score);
		if (highPitch) {
			if (Application.playSound(Clips.munch2, 1, true))
				highPitch = false;
		} else {
			if (Application.playSound(Clips.munch1, 1, true))
				highPitch = true;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getScore() {
		return score;
	}
}
