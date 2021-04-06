package pacman.game.maze;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HDClassicMaze extends ClassicMaze {
	public HDClassicMaze() {
		try {
			this.texture = ImageIO.read(new File("assets/classicmaze/mazehd.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
