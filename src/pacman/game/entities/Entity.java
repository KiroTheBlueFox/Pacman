package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.game.Game;

public abstract class Entity {
	protected String id;
	protected int x, y, width, height;
	protected double lifeTime;
	protected Game game;
	
	public Entity(String id, int x, int y, int width, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lifeTime = 0;
	}
	
	public abstract void draw(Graphics2D brush);
	
	public void drawDebug(Graphics2D brush) {
		brush.setColor(Color.red);
		brush.drawRect(x, y, width, height);
	}
	
	public void act(double delta) {
		this.lifeTime += delta;
	};
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public String getId() {
		return id;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void move(int x, int y) {
		this.x += x;
		this.y += y;
		if (this.x > game.getCurrentMaze().getWidth()) {
			this.x = -this.width;
		} else if (this.x < -this.width) {
			this.x = game.getCurrentMaze().getWidth();
		}
		if (this.y > game.getCurrentMaze().getHeight()) {
			this.y = -this.height;
		} else if (this.y < -this.height) {
			this.y = game.getCurrentMaze().getHeight();
		}
	}
}
