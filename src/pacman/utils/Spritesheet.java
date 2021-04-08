package pacman.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import javax.imageio.ImageIO;

public class Spritesheet {
	private BufferedImage fullSheet;
	private int spriteWidth, spriteHeight, rowCount;
	private int[] frames;
	
	public Spritesheet(String file, int rowCount, int... frames) {
		if (rowCount == 0) {
			new InvalidParameterException("rows (second parameter) has to be 1 or higher").printStackTrace();
			return;
		}
		if (frames.length < rowCount) {
			new InvalidParameterException("frames (third parameter) need a minimum of rows (second parameter) elements").printStackTrace();
		}
		
		int maximum = 0;
		for (int i : frames) {
			if (i == 0) {
				new InvalidParameterException("All integers in frames have to be 1 or higher").printStackTrace();
				return;
			} else if (i > maximum) {
				maximum = i;
			}
		}
		
		try {
			fullSheet = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		float tempSpriteHeight = (fullSheet.getHeight()/(float) rowCount),
				tempSpriteWidth = (fullSheet.getWidth()/(float) maximum);
		if (tempSpriteWidth+tempSpriteHeight != (int) (tempSpriteWidth+tempSpriteHeight)) {
			new InvalidParameterException("The width and height of the loaded image have to be divisible by the maximum amount of rows and frames").printStackTrace();
			return;
		}
		spriteWidth = (int) tempSpriteWidth;
		spriteHeight = (int) tempSpriteHeight;
		this.rowCount = rowCount;
		this.frames = frames;
	}
	
	public int getSpriteWidth() {
		return spriteWidth;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
	}
	
	public int getRowCount() {
		return rowCount;
	}
	
	public int getFrameCount(int index) {
		return frames[index];
	}
	
	public BufferedImage getFullSheet() {
		return fullSheet;
	}

	public void drawSprite(Graphics2D brush, int x, int y, int animationIndex, int animationFrame) {
		brush.drawImage(fullSheet,
						x-spriteWidth/2, y-spriteHeight/2, x+spriteWidth/2, y+spriteHeight/2,
						animationFrame*spriteWidth, animationIndex*spriteHeight, (animationFrame+1)*spriteWidth, (animationIndex+1)*spriteHeight,
						null);
	}
}
