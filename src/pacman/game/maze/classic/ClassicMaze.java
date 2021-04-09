package pacman.game.maze.classic;

import java.awt.Graphics2D;
import java.awt.Image;

import pacman.game.entities.Blinky;
import pacman.game.entities.Clyde;
import pacman.game.entities.Inky;
import pacman.game.entities.Pinky;
import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.game.maze.classic.pellets.PowerPellet;
import pacman.utils.Direction;
import pacman.utils.ImageUtils;

public class ClassicMaze extends Maze {
	public static final int PELLET_SIZE = 4, PELLET_SCORE = 10, POWER_PELLET_SIZE = 16, POWER_PELLET_SCORE = 50;
	protected Image texture;

	public ClassicMaze() {
		super(224, 248, 8, 13, 23, 13, 11, "assets/classicmaze/");
		texture = loadTextureFromFolder("maze.png");
	}

	protected ClassicMaze(String folder) {
		super(224, 248, 8, 13, 23, 13, 11, folder);
	}

	@Override
	public void draw(Graphics2D brush) {
		if (texture != null) {
			brush.setRenderingHints(getGame().noAntialiasingRH);
			brush.drawImage(texture, 0, 0, null);
			brush.setRenderingHints(getGame().antialiasingRH);
		}
	}

	@Override
	protected void initWalls() {
		this.walls = new boolean[this.width / this.tileSize][this.height / this.tileSize];
		int[][] colorArray = ImageUtils.imageToArray(loadTextureFromFolder(DEFAULT_MAP));
		for (int x = 0; x < colorArray.length; x++) {
			for (int y = 0; y < colorArray[x].length; y++) {
				switch (colorArray[x][y]) {
				case DEFAULT_WALL_COLOR:
					this.walls[x][y] = true;
					break;
				default:
				case DEFAULT_PATH_COLOR:
					this.walls[x][y] = false;
					break;
				}
			}
		}
	}

	@Override
	protected void initPellets() {
		this.pellets = new Pellet[this.width / this.tileSize][this.height / this.tileSize];
		int[][] colorArray = ImageUtils.imageToArray(loadTextureFromFolder(DEFAULT_PELLET_MAP));
		for (int x = 0; x < colorArray.length; x++) {
			for (int y = 0; y < colorArray[x].length; y++) {
				switch (colorArray[x][y]) {
				default:
				case DEFAULT_NO_PELLET_COLOR:
					this.pellets[x][y] = null;
					break;
				case DEFAULT_PELLET_COLOR:
					this.pellets[x][y] = new Pellet(this, x, y, PELLET_SIZE, PELLET_SCORE);
					break;
				case DEFAULT_POWER_PELLET_COLOR:
					this.pellets[x][y] = new PowerPellet(this, x, y, POWER_PELLET_SIZE, POWER_PELLET_SCORE);
					break;
				}
			}
		}
	}

	@Override
	protected void initGhostZone() {
	}

	@Override
	protected void initGhosts() {
		game.addActor(new Blinky(ghostSpawnX, ghostSpawnY, tileSize, tileSize, false, Direction.LEFT));
		game.addActor(new Inky(12, 14, tileSize, tileSize, true, Direction.UP));
		game.addActor(new Pinky(14, 14, tileSize, tileSize, true, Direction.DOWN));
		game.addActor(new Clyde(16, 14, tileSize, tileSize, true, Direction.UP));
	}

	@Override
	public void close() {
		texture.flush();
	}
}
