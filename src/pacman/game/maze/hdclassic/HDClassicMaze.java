package pacman.game.maze.hdclassic;

import pacman.game.maze.classic.ClassicMaze;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.game.maze.hdclassic.pellets.HDPellet;
import pacman.game.maze.hdclassic.pellets.HDPowerPellet;

public class HDClassicMaze extends ClassicMaze {
	public HDClassicMaze() {
		super("assets/hdclassicmaze/");
		this.texture = loadTextureFromFolder("mazehd.png");
	}

	@Override
	protected void initPellets() {
		this.pellets = new Pellet[][] {
				{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null },
				{ null, new HDPellet(this, 1, 1, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 2, PELLET_SIZE, PELLET_SCORE),
						new HDPowerPellet(this, 1, 3, POWER_PELLET_SIZE, POWER_PELLET_SCORE),
						new HDPellet(this, 1, 4, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 5, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 6, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 7, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 1, 20, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 21, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 22, PELLET_SIZE, PELLET_SCORE),
						new HDPowerPellet(this, 1, 23, POWER_PELLET_SIZE, POWER_PELLET_SCORE), null, null,
						new HDPellet(this, 1, 26, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 27, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 28, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 1, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 2, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 2, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 2, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 2, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 2, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 2, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 2, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 3, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 3, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 3, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 3, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 3, 23, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 3, 24, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 3, 25, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 3, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 3, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 4, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 4, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 4, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 4, 20, PELLET_SIZE, PELLET_SCORE), null, null, null,
						null, null, new HDPellet(this, 4, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 4, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 5, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 5, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 5, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 5, 20, PELLET_SIZE, PELLET_SCORE), null, null, null,
						null, null, new HDPellet(this, 5, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 5, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 6, 1, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 2, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 3, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 4, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 5, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 6, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 7, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 8, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 9, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 10, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 11, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 12, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 13, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 14, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 15, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 16, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 17, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 18, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 19, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 20, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 21, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 22, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 23, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 24, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 25, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 6, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 6, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 7, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 7, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, new HDPellet(this, 7, 20, PELLET_SIZE, PELLET_SCORE),
						null, null, new HDPellet(this, 7, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null,
						new HDPellet(this, 7, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 8, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 8, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, new HDPellet(this, 8, 20, PELLET_SIZE, PELLET_SCORE),
						null, null, new HDPellet(this, 8, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null,
						new HDPellet(this, 8, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 9, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 9, 5, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 9, 6, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 9, 7, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 9, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 9, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 9, 23, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 9, 24, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 9, 25, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 9, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 9, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 10, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 10, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 10, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 10, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 10, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 10, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 10, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 11, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 11, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 11, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 11, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 11, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 11, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 11, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 12, 1, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 2, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 3, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 4, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 12, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 12, 20, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 21, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 22, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 12, 26, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 27, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 28, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 12, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, null, null, null, null, new HDPellet(this, 13, 5, PELLET_SIZE, PELLET_SCORE), null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 13, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, null, null, null, null, new HDPellet(this, 14, 5, PELLET_SIZE, PELLET_SCORE), null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 14, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 15, 1, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 2, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 3, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 4, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 15, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 15, 20, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 21, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 22, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 15, 26, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 27, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 28, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 15, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 16, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 16, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 16, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 16, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 16, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 16, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 16, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 17, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 17, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 17, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 17, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 17, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 17, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 17, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 18, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 18, 5, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 18, 6, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 18, 7, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 18, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 18, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 18, 23, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 18, 24, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 18, 25, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 18, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 18, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 19, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 19, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, new HDPellet(this, 19, 20, PELLET_SIZE, PELLET_SCORE),
						null, null, new HDPellet(this, 19, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null,
						new HDPellet(this, 19, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 20, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 20, 5, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, new HDPellet(this, 20, 20, PELLET_SIZE, PELLET_SCORE),
						null, null, new HDPellet(this, 20, 23, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null,
						new HDPellet(this, 20, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 21, 1, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 2, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 3, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 4, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 5, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 6, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 7, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 8, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 9, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 10, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 11, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 12, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 13, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 14, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 15, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 16, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 17, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 18, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 19, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 20, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 21, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 22, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 23, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 24, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 25, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 21, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 21, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 22, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 22, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 22, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 22, 20, PELLET_SIZE, PELLET_SCORE), null, null, null,
						null, null, new HDPellet(this, 22, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 22, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 23, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 23, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 23, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 23, 20, PELLET_SIZE, PELLET_SCORE), null, null, null,
						null, null, new HDPellet(this, 23, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 23, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 24, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 24, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 24, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 24, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 24, 23, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 24, 24, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 24, 25, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 24, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 24, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 25, 1, PELLET_SIZE, PELLET_SCORE), null, null, null,
						new HDPellet(this, 25, 5, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 25, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 25, 20, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 25, 23, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 25, 26, PELLET_SIZE, PELLET_SCORE), null, null,
						new HDPellet(this, 25, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, new HDPellet(this, 26, 1, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 2, PELLET_SIZE, PELLET_SCORE),
						new HDPowerPellet(this, 26, 3, POWER_PELLET_SIZE, POWER_PELLET_SCORE),
						new HDPellet(this, 26, 4, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 5, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 6, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 7, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 8, PELLET_SIZE, PELLET_SCORE), null, null, null, null, null, null, null,
						null, null, null, null, new HDPellet(this, 26, 20, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 21, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 22, PELLET_SIZE, PELLET_SCORE),
						new HDPowerPellet(this, 26, 23, POWER_PELLET_SIZE, POWER_PELLET_SCORE), null, null,
						new HDPellet(this, 26, 26, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 27, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 28, PELLET_SIZE, PELLET_SCORE),
						new HDPellet(this, 26, 29, PELLET_SIZE, PELLET_SCORE), null },
				{ null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
						null, null, null, null, null, null, null, null, null, null, null, null, null, null } };
	}
}
