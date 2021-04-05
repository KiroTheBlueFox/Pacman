package pacman.game.maze;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import pacman.game.maze.pellets.Pellet;
import pacman.game.maze.pellets.PowerPellet;

public abstract class Maze {
	protected List<Rectangle> walls;
	protected List<Pellet> pellets;
	protected List<PowerPellet> powerPellets;
	protected Rectangle ghostZone;
	protected int width, height, playerSpawnX, playerSpawnY;
	
	public Maze(int width, int height, int playerSpawnX, int playerSpawnY) {
		walls = new ArrayList<Rectangle>();
		pellets = new ArrayList<Pellet>();
		powerPellets = new ArrayList<PowerPellet>();
		this.width = width;
		this.height = height;
		this.playerSpawnX = playerSpawnX;
		this.playerSpawnY = playerSpawnY;
		initWalls();
		initPellets();
		initPowerPellets();
		initGhostZone();
	}
	
	public abstract void draw(Graphics2D brush);
	
	public void drawDebug(Graphics2D brush) {
		this.walls.forEach((wall) -> {
			brush.drawRect(wall.x, wall.y, wall.width, wall.height);
		});
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getPlayerSpawnX() {
		return playerSpawnX;
	}
	
	public int getPlayerSpawnY() {
		return playerSpawnY;
	}
	
	protected abstract void initWalls();
	protected abstract void initPellets();
	protected abstract void initPowerPellets();
	protected abstract void initGhostZone();
	
	public List<Rectangle> getWalls() {
		return walls;
	}
	
	public Rectangle getGhostZone() {
		return ghostZone;
	}
	
	public List<Pellet> getPellets() {
		return pellets;
	}
	
	public List<PowerPellet> getPowerPellets() {
		return powerPellets;
	}
}
