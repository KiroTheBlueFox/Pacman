package pacman.game.maze.special;

import java.awt.Rectangle;

import pacman.game.entities.Ghost;

public class GhostZone {
	private Rectangle rect;
	private Ghost[] ghosts;
	
	public GhostZone(Rectangle rect, Ghost... ghosts) {
		this.rect = rect;
		this.ghosts = ghosts;
	}
	
	public Rectangle getRect() {
		return rect;
	}
	
	public Ghost[] getGhosts() {
		return ghosts;
	}
}
