package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.game.Game;
import pacman.utils.Direction;

public abstract class Entity {
	protected String id;
	protected float x, y, width, height;
	protected double lifeTime;
	protected Direction direction;
	protected Game game;
	
	public Entity(String id, int x, int y, int width, int height) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.direction = null;
		this.lifeTime = 0;
	}
	
	public abstract void draw(Graphics2D brush);
	
	public void drawDebug(Graphics2D brush) {
		brush.setColor(Color.red);
		brush.drawRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
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
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public double getLifeTime() {
		return lifeTime;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public void move(float x, float y) {
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
