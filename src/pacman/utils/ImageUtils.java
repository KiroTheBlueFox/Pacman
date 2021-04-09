package pacman.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {
	public static int[][] imageToArray(String path) {
		try {
			return imageToArray(ImageIO.read(new File(path)));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int[][] imageToArray(BufferedImage image) {
		int[][] arrayToReturn = new int[image.getWidth()][image.getHeight()];
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				arrayToReturn[x][y] = image.getRGB(x, y);
			}
		}
		return arrayToReturn;
	}
}
