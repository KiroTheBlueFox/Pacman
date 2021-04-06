package pacman.game.maze.pellets;

import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;

public class Pellet {
	public static boolean highPitch = true;
	protected int x, y, size, score;
	protected Maze maze;
	
	public Pellet(Maze maze, int x, int y, int size, int score) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.maze = maze;
		this.score = score;
	}
	
	public void draw(Graphics2D brush) {
		brush.fillRect(8+x*maze.getTileSize()-size/2, 8+y*maze.getTileSize()-size/2, size, size);
	}
	
	public void act(PacMan player, double delta) {
		player.addScore(score);
		if (highPitch) {
			if (!Clips.munch1.isActive()) {
				if (Application.playSound(Clips.munch2, false))
					highPitch = false;
			}
		} else {
			if (!Clips.munch2.isActive()) {
				if (Application.playSound(Clips.munch1, false))
					highPitch = true;
			}
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
