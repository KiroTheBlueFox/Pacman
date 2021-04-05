package pacman.game.maze;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import pacman.game.maze.pellets.Pellet;
import pacman.game.maze.pellets.PowerPellet;

public class ClassicMaze extends Maze {
	private static final int PELLET_SIZE = 4, PELLET_SCORE = 1, POWER_PELLET_SIZE = 16, POWER_PELLET_SCORE = 10;

	public ClassicMaze() {
		super(448, 496, 208, 360);
	}
	
	@Override
	public void draw(Graphics2D brush) {
		try {
			brush.drawImage(ImageIO.read(new File("assets/maze.png")), 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initWalls() {
		walls.add(new Rectangle(0, 0, 448, 8));
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
		walls.add(new Rectangle(408, 392, 32, 16));
	}

	@Override
	protected void initPellets() {
		int i, j;
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
			pellets.add(new Pellet(this, 24+16*i, 472, PELLET_SIZE, PELLET_SCORE));
	}

	@Override
	protected void initPowerPellets() {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				powerPellets.add(new PowerPellet(this, 24+400*i, 56+320*j, POWER_PELLET_SIZE, POWER_PELLET_SCORE));
	}

	@Override
	protected void initGhostZone() {
		
	}
}
