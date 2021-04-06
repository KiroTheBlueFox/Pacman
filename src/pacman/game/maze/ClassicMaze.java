package pacman.game.maze;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.game.maze.pellets.Pellet;
import pacman.game.maze.pellets.PowerPellet;

public class ClassicMaze extends Maze {
	private static final int PELLET_SIZE = 4, PELLET_SCORE = 10, POWER_PELLET_SIZE = 16, POWER_PELLET_SCORE = 50;

	public ClassicMaze() {
		super(448, 496, 16, 224, 376);
	}
	
	@Override
	public void draw(Graphics2D brush) {
		try {
			brush.drawImage(ImageIO.read(new File("assets/classicmaze/maze.png")), 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initWalls() {
		this.walls = new boolean[][]
				{{true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
				{true, false, false, false, false, false, false, false, false, true, true, true, true, true, false, true, true, true, true, true, false, false, false, false, true, true, false, false, false, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, false, false, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true},
				{true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, true, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true},
				{true, false, true, true, true, false, true, true, true, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true},
				{true, false, true, true, true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, false, true},
				{true, false, false, false, false, false, true, true, false, false, false, false, true, true, true, true, true, false, true, true, false, false, false, false, true, true, false, false, false, false, true},
				{true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true},
				{true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true},
				{true, false, false, false, false, false, true, true, false, false, false, false, true, true, true, true, true, false, true, true, false, false, false, false, true, true, false, false, false, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, false, true, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, true, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true},
				{true, false, true, true, true, false, true, true, true, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true, true, true, true, true, false, true},
				{true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, false, false, false, true, true, false, true},
				{true, false, true, true, true, false, true, true, false, true, true, true, true, true, false, true, true, true, true, true, false, true, true, false, true, true, false, true, true, false, true},
				{true, false, false, false, false, false, false, false, false, true, true, true, true, true, false, true, true, true, true, true, false, false, false, false, true, true, false, false, false, false, true},
				{true, true, true, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true}};
		/*walls.add(new Rectangle(0, 0, 448, 8));
		walls.add(new Rectangle(0, 8, 8, 144));
		walls.add(new Rectangle(440, 8, 8, 144));
		walls.add(new Rectangle(216, 8, 16, 64));
		walls.add(new Rectangle(0, 152, 88, 8));
		walls.add(new Rectangle(360, 152, 88, 8));
		walls.add(new Rectangle(80, 160, 8, 48));
		walls.add(new Rectangle(360, 160, 8, 48));
		walls.add(new Rectangle(-64, 208, 152, 8));
		walls.add(new Rectangle(360, 208, 152, 8));

		walls.add(new Rectangle(40, 40, 48, 32));
		walls.add(new Rectangle(120, 40, 64, 32));
		walls.add(new Rectangle(264, 40, 64, 32));
		walls.add(new Rectangle(360, 40, 48, 32));
		walls.add(new Rectangle(40, 104, 48, 16));
		walls.add(new Rectangle(168, 104, 112, 16));
		walls.add(new Rectangle(216, 120, 16, 48));
		walls.add(new Rectangle(360, 104, 48, 16));
		walls.add(new Rectangle(120, 104, 16, 112));
		walls.add(new Rectangle(136, 152, 48, 16));
		walls.add(new Rectangle(312, 104, 16, 112));
		walls.add(new Rectangle(264, 152, 48, 16));

		walls.add(new Rectangle(168, 200, 112, 64));

		walls.add(new Rectangle(120, 248, 16, 64));
		walls.add(new Rectangle(312, 248, 16, 64));
		walls.add(new Rectangle(168, 296, 112, 16));
		walls.add(new Rectangle(216, 312, 16, 48));
		walls.add(new Rectangle(40, 344, 48, 16));
		walls.add(new Rectangle(72, 360, 16, 48));
		walls.add(new Rectangle(360, 344, 48, 16));
		walls.add(new Rectangle(360, 360, 16, 48));
		walls.add(new Rectangle(120, 344, 64, 16));
		walls.add(new Rectangle(264, 344, 64, 16));
		walls.add(new Rectangle(168, 392, 112, 16));
		walls.add(new Rectangle(216, 408, 16, 48));
		walls.add(new Rectangle(120, 392, 16, 48));
		walls.add(new Rectangle(40, 440, 144, 16));
		walls.add(new Rectangle(312, 392, 16, 48));
		walls.add(new Rectangle(264, 440, 144, 16));

		walls.add(new Rectangle(0, 488, 448, 8));
		walls.add(new Rectangle(0, 312, 8, 176));
		walls.add(new Rectangle(440, 312, 8, 176));
		walls.add(new Rectangle(0, 304, 88, 8));
		walls.add(new Rectangle(360, 304, 88, 8));
		walls.add(new Rectangle(80, 256, 8, 48));
		walls.add(new Rectangle(360, 256, 8, 48));
		walls.add(new Rectangle(-64, 248, 152, 8));
		walls.add(new Rectangle(360, 248, 152, 8));
		walls.add(new Rectangle(8, 392, 32, 16));
		walls.add(new Rectangle(408, 392, 32, 16));*/
	}

	@Override
	protected void initPellets() {
		this.pellets = new Pellet[][]
				{{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
				{null, new Pellet(this, 1, 1, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 2, PELLET_SIZE, PELLET_SCORE), new PowerPellet(this, 1, 3, POWER_PELLET_SIZE, POWER_PELLET_SCORE), new Pellet(this, 1, 4, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 5, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 6, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 7, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 1, 20, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 21, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 22, PELLET_SIZE, PELLET_SCORE), new PowerPellet(this, 1, 23, POWER_PELLET_SIZE, POWER_PELLET_SCORE), null, null, new Pellet(this, 1, 26, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 27, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 28, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 1, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 2, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 2, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 2, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 2, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 2, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 2, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 2, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 3, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 3, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 3, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 3, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 3, 23, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 3, 24, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 3, 25, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 3, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 3, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 4, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 4, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 4, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 4, 20, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 4, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 4, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 5, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 5, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 5, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 5, 20, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 5, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 5, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 6, 1, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 2, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 3, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 4, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 5, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 6, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 7, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 8, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 9, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 10, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 11, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 12, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 13, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 14, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 15, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 16, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 17, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 18, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 19, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 20, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 21, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 22, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 23, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 24, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 25, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 6, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 6, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 7, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 7, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 7, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 7, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 7, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 8, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 8, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 8, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 8, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 8, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 9, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 9, 5, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 9, 6, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 9, 7, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 9, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 9, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 9, 23, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 9, 24, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 9, 25, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 9, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 9, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 10, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 10, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 10, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 10, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 10, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 10, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 10, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 11, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 11, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 11, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 11, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 11, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 11, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 11, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 12, 1, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 2, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 3, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 4, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 12, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 12, 20, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 21, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 22, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 12, 26, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 27, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 28, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 12, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, null, null, null, null, new Pellet(this, 13, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 13, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, null, null, null, null, new Pellet(this, 14, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 14, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 15, 1, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 2, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 3, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 4, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 15, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 15, 20, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 21, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 22, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 15, 26, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 27, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 28, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 15, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 16, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 16, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 16, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 16, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 16, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 16, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 16, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 17, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 17, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 17, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 17, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 17, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 17, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 17, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 18, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 18, 5, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 18, 6, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 18, 7, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 18, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 18, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 18, 23, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 18, 24, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 18, 25, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 18, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 18, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 19, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 19, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 19, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 19, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 19, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 20, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 20, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 20, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 20, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 20, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 21, 1, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 2, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 3, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 4, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 5, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 6, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 7, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 8, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 9, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 10, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 11, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 12, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 13, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 14, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 15, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 16, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 17, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 18, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 19, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 20, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 21, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 22, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 23, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 24, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 25, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 21, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 21, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 22, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 22, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 22, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 22, 20, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 22, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 22, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 23, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 23, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 23, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 23, 20, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, new Pellet(this, 23, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 23, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 24, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 24, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 24, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 24, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 24, 23, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 24, 24, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 24, 25, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 24, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 24, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 25, 1, PELLET_SIZE, PELLET_SCORE), null, null, null, new Pellet(this, 25, 5, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 25, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 25, 20, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 25, 23, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 25, 26, PELLET_SIZE, PELLET_SCORE), null, null, new Pellet(this, 25, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, new Pellet(this, 26, 1, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 2, PELLET_SIZE, PELLET_SCORE), new PowerPellet(this, 26, 3, POWER_PELLET_SIZE, POWER_PELLET_SCORE), new Pellet(this, 26, 4, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 5, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 6, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 7, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null, null, null, null, null, new Pellet(this, 26, 20, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 21, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 22, PELLET_SIZE, PELLET_SCORE), new PowerPellet(this, 26, 23, POWER_PELLET_SIZE, POWER_PELLET_SCORE), null, null, new Pellet(this, 26, 26, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 27, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 28, PELLET_SIZE, PELLET_SCORE), new Pellet(this, 26, 29, PELLET_SIZE, PELLET_SCORE), null},
				{null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}};
		/*int i, j;
		for (i = 0; i < 12; i++)
			pellets.add(new Pellet(this, 24+16*i, 24, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 12; i++)
			pellets.add(new Pellet(this, 248+16*i, 24, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 24, 40+32*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 424, 40+32*i, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 3; i++)
			pellets.add(new Pellet(this, 104, 40+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 3; i++)
			pellets.add(new Pellet(this, 200, 40+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 3; i++)
			pellets.add(new Pellet(this, 248, 40+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 3; i++)
			pellets.add(new Pellet(this, 344, 40+16*i, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 26; i++)
			pellets.add(new Pellet(this, 24+16*i, 88, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 24, 104+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 104, 104+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 152, 104+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 296, 104+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 344, 104+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 424, 104+16*i, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 6; i++)
			pellets.add(new Pellet(this, 24+16*i, 136, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 6; i++)
			pellets.add(new Pellet(this, 344+16*i, 136, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 4; i++)
			pellets.add(new Pellet(this, 152+16*i, 136, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 4; i++)
			pellets.add(new Pellet(this, 248+16*i, 136, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 11; i++)
			pellets.add(new Pellet(this, 104, 152+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 11; i++)
			pellets.add(new Pellet(this, 344, 152+16*i, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 12; i++)
			pellets.add(new Pellet(this, 24+16*i, 328, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 12; i++)
			pellets.add(new Pellet(this, 248+16*i, 328, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 24, 344+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 104, 344+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 200, 344+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 248, 344+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 344, 344+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 424, 344+16*i, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 40+16*i, 376, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 392+16*i, 376, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 7; i++)
			pellets.add(new Pellet(this, 104+16*i, 376, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 7; i++)
			pellets.add(new Pellet(this, 248+16*i, 376, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 2; i++)
			for (j = 0; j < 3; j++)
				pellets.add(new Pellet(this, 56+48*j, 392+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			for (j = 0; j < 3; j++)
				pellets.add(new Pellet(this, 296+48*j, 392+16*i, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 6; i++)
			pellets.add(new Pellet(this, 24+16*i, 424, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 6; i++)
			pellets.add(new Pellet(this, 344+16*i, 424, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 4; i++)
			pellets.add(new Pellet(this, 152+16*i, 424, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 4; i++)
			pellets.add(new Pellet(this, 248+16*i, 424, PELLET_SIZE, PELLET_SCORE));

		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 24, 440+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 200, 440+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 248, 440+16*i, PELLET_SIZE, PELLET_SCORE));
		for (i = 0; i < 2; i++)
			pellets.add(new Pellet(this, 424, 440+16*i, PELLET_SIZE, PELLET_SCORE));
		
		for (i = 0; i < 26; i++)
			pellets.add(new Pellet(this, 24+16*i, 472, PELLET_SIZE, PELLET_SCORE));*/
	}

	/*protected void initPowerPellets() {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				powerPellets.add(new PowerPellet(this, 24+400*i, 56+320*j, POWER_PELLET_SIZE, POWER_PELLET_SCORE));
	}*/

	@Override
	protected void initGhostZones() {
		// TODO Auto-generated method stub
		
	}
}
