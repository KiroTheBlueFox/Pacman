package pacman.game.entities;

import java.awt.Graphics2D;

public class Ghost extends Entity {
	public Ghost(String id, int x, int y, int width, int height) {
		super(id, x, y, width, height);
	}

	@Override
	public void draw(Graphics2D brush) {
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
	}
}
