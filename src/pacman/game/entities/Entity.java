package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.game.Game;
import pacman.utils.Direction;
import pacman.utils.MathUtils;
import pacman.utils.Spritesheet;

public abstract class Entity {
	protected static final int IDLE_ANIMATION_INDEX = 0, DOWN_ANIMATION_INDEX = 1, LEFT_ANIMATION_INDEX = 2,
			RIGHT_ANIMATION_INDEX = 3, UP_ANIMATION_INDEX = 4;
	protected String id;
	protected int x, y, oldX, oldY, width, height, animationFrame = 0;
	protected float speed;
	protected double lifeTime, lastTime, timeSinceLastFrame;
	protected Direction direction;
	protected Spritesheet spritesheet;
	protected Game game;

	public Entity(String id, int x, int y, int width, int height, Direction spawnDirection, Spritesheet defaultSpritesheet) {
		this.id = id;
		this.x = x;
		this.oldX = x;
		this.y = y;
		this.oldY = y;
		this.width = width;
		this.height = height;
		this.direction = spawnDirection;
		switch (this.direction) {
		case DOWN:
			this.oldY--;
			break;
		case LEFT:
			this.oldX++;
			break;
		case RIGHT:
			this.oldX--;
			break;
		case UP:
			this.oldY++;
			break;
		}
		this.lifeTime = 0;
		this.lastTime = 0;
		this.timeSinceLastFrame = 0;
		spritesheet = defaultSpritesheet;
	}

	public void draw(Graphics2D brush) {
		float centerX = getCenterX(), centerY = getCenterY(),
				offset = (float) (MathUtils.normalize(lastTime, speed)-0.5)*game.getCurrentMaze().getTileSize();
		int index = IDLE_ANIMATION_INDEX;
		if (direction != null) {
			if (offset > 0 && !game.isEnoughSpaceInDirection(this, direction, 1, false)) {
				offset = 0;
			}
			Direction offsetDirection = direction;
			if (offset < 0) {
				offsetDirection = Direction.fromAtoB(oldX, oldY, x, y);
			}
			switch (offsetDirection) {
			case DOWN:
				index = DOWN_ANIMATION_INDEX;
				centerY += offset;
				break;
			case LEFT:
				index = LEFT_ANIMATION_INDEX;
				centerX -= offset;
				break;
			case RIGHT:
				index = RIGHT_ANIMATION_INDEX;
				centerX += offset;
				break;
			case UP:
				index = UP_ANIMATION_INDEX;
				centerY -= offset;
				break;
			}
		}
		if (timeSinceLastFrame >= spritesheet.getFrameTime()) {
			timeSinceLastFrame = 0;
			if (spritesheet.doesLoop(index)) {
				animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
			} else {
				animationFrame = MathUtils.clamp(animationFrame+1, 0, spritesheet.getFrameCount(index)-1);
			}
		}
		spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
				animationFrame % spritesheet.getFrameCount(index));
	}

	public void drawDebug(Graphics2D brush) {
		brush.setColor(Color.black);
		brush.setRenderingHints(game.antialiasingRH);
		brush.fillOval((int) getCenterX() - 1, (int) getCenterY() - 1, 2, 2);
		brush.setRenderingHints(game.noAntialiasingRH);
	}

	public void act(double delta) {
		this.lifeTime += delta;
		this.lastTime += delta;
		this.timeSinceLastFrame += delta;
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

	public float getCenterX() {
		return (x * game.getCurrentMaze().getTileSize()) + width / 2f;
	}

	public int getY() {
		return y;
	}

	public float getCenterY() {
		return (y * game.getCurrentMaze().getTileSize()) + width / 2f;
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
	
	public float getSpeed() {
		return (float) ((speed/getGame().getSpeed().getSpeedFactor())/getGame().getGameLevel().getSpeedFactor());
	}

	public Direction getDirection() {
		return direction;
	}

	public double getLifeTime() {
		return lifeTime;
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
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setDirection(Direction direction) {
		if (direction != this.direction) {
			this.timeSinceLastFrame = 0;
			this.animationFrame = 0;
			this.direction = direction;
		}
	}

	public void move(int x, int y) {
		this.oldX = this.x;
		this.oldY = this.y;
		this.x += x;
		this.y += y;
		int maxWidth = game.getCurrentMaze().getWidth()/game.getCurrentMaze().getTileSize();
		int maxHeight = game.getCurrentMaze().getHeight()/game.getCurrentMaze().getTileSize();
		if (this.x >= maxWidth) {
			this.x = -1;
		} else if (this.x < 0) {
			this.x = maxWidth;
		}
		if (this.y >= maxHeight) {
			this.y = -1;
		} else if (this.y < 0) {
			this.y = maxHeight;
		}
		Application.playSound(getGame().getSpeed().getMovementClip(), 1, false);
	}
}
