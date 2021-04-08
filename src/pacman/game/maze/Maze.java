package pacman.game.maze;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pacman.game.Game;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.GameSpeed;

public abstract class Maze {
	protected boolean[][] walls;
	protected Pellet[][] pellets;
	protected List<Rectangle> ghostZones;
	protected int width, height, tileSize, playerSpawnX, playerSpawnY, pelletCount, startingPelletCount;
	protected String folder;
	protected Game game;
	
	public Maze(int width, int height, int tileSize, int playerSpawnX, int playerSpawnY, String folder) {
		walls = new boolean[width/tileSize][height/tileSize];
		pellets = new Pellet[width/tileSize][height/tileSize];
		ghostZones = new ArrayList<Rectangle>();
		this.width = width;
		this.height = height;
		this.tileSize = tileSize;
		this.playerSpawnX = playerSpawnX;
		this.playerSpawnY = playerSpawnY;
		this.pelletCount = 0;
		this.folder = folder.replace("\\", "/").replace("\\\\", "/");
		if (!folder.endsWith("/")) {
			folder += "/";
		}
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
	
	public String getFolder() {
		return folder;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	protected abstract void initWalls();
	protected abstract void initPellets();
	protected abstract void initGhostZones();
	
	public int getPelletCount() {
		return pelletCount;
	}
	
	public int getStartingPelletCount() {
		return startingPelletCount;
	}
	
	public void addPellet(boolean starting) {
		this.pelletCount++;
		if (starting)
			this.startingPelletCount++;
	}
	
	public void removePellet() {
		this.pelletCount--;
		if (this.pelletCount <= this.startingPelletCount*0.5f) {
			if (this.pelletCount <= this.startingPelletCount*0.2f) {
				if (game.getSpeed() != GameSpeed.FASTER)
					game.setSpeed(GameSpeed.FASTER);
			} else {
				if (game.getSpeed() != GameSpeed.FAST) {
					game.setSpeed(GameSpeed.FAST);
				}
			}
		}
		if (this.pelletCount <= 0) {
			endMaze();
		}
	}
	
	private void endMaze() {
		System.out.println("All pellets were eaten !");
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
	
	public abstract void close();
	
	/**
	 * Loads a texture from the folder defined in the constructor<br>
	 * If the texture path starts with a <code>/</code>, then it will load the texture from the root of the project<br>
	 * If not, then it will load the texture as said above.
	 * @param texture The texture path
	 * @return The BufferedImage
	 */
	protected BufferedImage loadTextureFromFolder(String texture) {
		try {
			texture = texture.replace("\\", "/").replace("\\\\", "/");
			if (texture.startsWith("/")) {
				return ImageIO.read(new File(texture.substring(1, texture.length())));
			} else {
				return ImageIO.read(new File(folder+texture));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
