package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.game.Game;
import pacman.utils.Direction;
import pacman.utils.MathUtils;
import pacman.utils.Spritesheet;

public abstract class Entity {
	protected static final int IDLE_ANIMATION_INDEX = 0,
			DOWN_ANIMATION_INDEX = 1,
			LEFT_ANIMATION_INDEX = 2,
			RIGHT_ANIMATION_INDEX = 3,
			UP_ANIMATION_INDEX = 4;
	protected double timeSinceLastFrame = 0;
	protected String id;
	protected float x, y, oldX, oldY, width, height, speed;
	protected double lifeTime, lastTime = 0;
	protected Direction direction;
	protected Spritesheet spritesheet;
	protected int animationFrame = 0;
	protected Game game;
	
	public Entity(String id, float x, float y, float width, float height, Spritesheet defaultSpritesheet) {
		this.id = id;
		this.x = x;
		this.oldX = x;
		this.y = y;
		this.oldY = y;
		this.width = width;
		this.height = height;
		this.direction = null;
		this.lifeTime = 0;
		spritesheet = defaultSpritesheet;
	}
	
	public void draw(Graphics2D brush) {
		float centerX = getCenterX(),
				centerY = getCenterY(),
				offset = (float) (MathUtils.normalize(lastTime, speed)-0.5)*game.getCurrentMaze().getTileSize();
		int index = IDLE_ANIMATION_INDEX;
		if (direction == null) {
			spritesheet.drawSprite(brush, (int) centerX, (int) centerY, IDLE_ANIMATION_INDEX, animationFrame%spritesheet.getFrameCount(IDLE_ANIMATION_INDEX));
		} else {
			switch (direction) {
			case DOWN:
				index = DOWN_ANIMATION_INDEX;
				spritesheet.drawSprite(brush, (int) (centerX), (int) (centerY+offset), DOWN_ANIMATION_INDEX, animationFrame%spritesheet.getFrameCount(DOWN_ANIMATION_INDEX));
				break;
			case LEFT:
				index = LEFT_ANIMATION_INDEX;
				spritesheet.drawSprite(brush, (int) (centerX-offset), (int) (centerY), LEFT_ANIMATION_INDEX, animationFrame%spritesheet.getFrameCount(LEFT_ANIMATION_INDEX));
				break;
			case RIGHT:
				index = RIGHT_ANIMATION_INDEX;
				spritesheet.drawSprite(brush, (int) (centerX+offset), (int) (centerY), RIGHT_ANIMATION_INDEX, animationFrame%spritesheet.getFrameCount(RIGHT_ANIMATION_INDEX));
				break;
			case UP:
				index = UP_ANIMATION_INDEX;
				spritesheet.drawSprite(brush, (int) (centerX), (int) (centerY-offset), UP_ANIMATION_INDEX, animationFrame%spritesheet.getFrameCount(UP_ANIMATION_INDEX));
				break;
			default:
				break;
			}
		}
		if (timeSinceLastFrame >= spritesheet.getFrameTime()) {
			timeSinceLastFrame = 0;
			animationFrame = (animationFrame+1)%spritesheet.getFrameCount(index);
		}
	}
	
	public void drawDebug(Graphics2D brush) {
		brush.setColor(Color.black);
		brush.drawRect(Math.round(x*game.getCurrentMaze().getTileSize()), Math.round(y*game.getCurrentMaze().getTileSize()), Math.round(width), Math.round(height));
		brush.setRenderingHints(game.antialiasingRH);
		brush.fillOval((int) getCenterX()-1, (int) getCenterY()-1, 2, 2);
		brush.setRenderingHints(game.noAntialiasingRH);
	}
	
	public void act(double delta) {
		this.lifeTime += delta;
		this.timeSinceLastFrame += delta;
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
	
	public int getGridX() {
		return (int) x;
	}
	
	public float getCenterX() {
		return (x*game.getCurrentMaze().getTileSize())+width/2f;
	}
	
	public float getY() {
		return y;
	}
	
	public int getGridY() {
		return (int) y;
	}
	
	public float getCenterY() {
		return (y*game.getCurrentMaze().getTileSize())+width/2f;
	}
	
	public Game getGame() {
		return game;
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
		if (direction != this.direction) {
			this.animationFrame = 0;
			this.direction = direction;
		}
	}
	
	public void move(float x, float y) {
		this.oldX = this.x;
		this.oldY = this.y;
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
		Application.playSound(getGame().getSpeed().getMovementClip(), 1, false);
	}
}
