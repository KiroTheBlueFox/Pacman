package pacman.game.maze;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import pacman.game.maze.pellets.Pellet;

public abstract class Maze {
	protected boolean[][] walls;
	protected Pellet[][] pellets;
	protected List<Rectangle> ghostZones;
	protected int width, height, tileSize, playerSpawnX, playerSpawnY, pelletCount;
	
	public Maze(int width, int height, int tileSize, int playerSpawnX, int playerSpawnY) {
		walls = new boolean[width/tileSize][height/tileSize];
		pellets = new Pellet[width/tileSize][height/tileSize];
		ghostZones = new ArrayList<Rectangle>();
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		this.playerSpawnX = playerSpawnX;
		this.playerSpawnY = playerSpawnY;
		this.pelletCount = 0;
		initWalls();
		initPellets();
		initGhostZones();
	}
	
	public abstract void draw(Graphics2D brush);
	
	public void drawDebug(Graphics2D brush) {
		for (int i = 0; i < walls.length; i++) {
			for (int j = 0; j < walls[i].length; j++) {
				if (walls[i][j])
					brush.fillRect(i*tileSize, j*tileSize, tileSize, tileSize);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getPlayerSpawnX() {
		return playerSpawnX;
	}
	
	public int getPlayerSpawnY() {
		return playerSpawnY;
	}
	
	protected abstract void initWalls();
	protected abstract void initPellets();
	protected abstract void initGhostZones();
	
	public int getPelletCount() {
		return pelletCount;
	}
	
	public void addPellet() {
		this.pelletCount++;
	}
	
	public void removePellet() {
		this.pelletCount--;
	}
	
	public boolean[][] getWalls() {
		return walls;
	}
	
	public List<Rectangle> getGhostZones() {
		return ghostZones;
	}
	
	public Pellet[][] getPellets() {
		return pellets;
	}
}
