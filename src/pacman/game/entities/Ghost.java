package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ghost extends Entity {
	public static final float SIZE = 1.5f;
	private Color color;
	private boolean frame = false;
	
	public Ghost(String id, int x, int y, Color color) {
		super(id, x, y, 16, 16, null);
		this.color = color;
	}

	@Override
	public void draw(Graphics2D brush) {
		brush.setColor(color);
		float widthDifference = width-width*SIZE,
				heightDifference = height-height*SIZE;
		int resizedX = Math.round(x+widthDifference/2f),
			resizedY = Math.round(y+heightDifference/2f);
		brush.fillArc(resizedX, resizedY, Math.round(width*SIZE), Math.round(height*SIZE), 0, 180);
		brush.fillRect(resizedX, resizedY+Math.round(height*0.5f*SIZE), Math.round(width*SIZE), Math.round(height*0.375f*SIZE));
		if (frame) {
			for (int i = 0; i < 2; i++) {
				brush.fillArc(resizedX+Math.round(width*(i/3f)*SIZE),
						resizedY+Math.round(height*SIZE-height*SIZE*(1/3f)),
						Math.round(width*(1/3f)*SIZE),
						Math.round(height*(1/3f)*SIZE),
						180, 180);
			}
		} else {
			for (int i = 0; i < 3; i++) {
				brush.fillArc(resizedX+Math.round(width*(i/3f)*SIZE),
						resizedY+Math.round(height*SIZE-height*SIZE*(1/3f)),
						Math.round(width*(1/3f)*SIZE),
						Math.round(height*(1/3f)*SIZE),
						180, 180);
			}
		}
		if (getDirection() != null) {
			switch (getDirection()) {
			case DOWN:
				break;
			case LEFT:
				break;
			case RIGHT:
				break;
			case UP:
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
	}
}
