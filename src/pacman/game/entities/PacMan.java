package pacman.game.entities;

import java.awt.Graphics2D;

import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Direction;
import pacman.utils.MathUtils;
import pacman.utils.Spritesheet;

public class PacMan extends Entity {
	protected static final int DEATH_ANIMATION_INDEX = 5;
	public static final float SPEED = 0.15f;
	private int score, lives;
	private boolean dead;
	private Direction nextDirection;

	public PacMan(int x, int y, int width, int height) {
		super("pacman", x, y, width, height,
				new Spritesheet("assets/classicmaze/pacman.png", 0.05, 6, 1, 4, 4, 4, 4, 11));
		this.score = 0;
		this.lives = 3;
		this.speed = SPEED;
	}

	@Override
	public void draw(Graphics2D brush) {
		if (dead) {

		} else {
			brush.setRenderingHints(getGame().noAntialiasingRH);
			float centerX = getCenterX(), centerY = getCenterY(),
					offset = (float) (MathUtils.normalize(lastTime, speed)-0.5)*game.getCurrentMaze().getTileSize();
			int index = IDLE_ANIMATION_INDEX;
			if (direction == null) {
				spritesheet.drawSprite(brush, (int) centerX, (int) centerY, IDLE_ANIMATION_INDEX,
						animationFrame % spritesheet.getFrameCount(IDLE_ANIMATION_INDEX));
			} else {
				if (offset > 0 &&
						!game.isEnoughSpaceInDirection(this, direction, 1, false) &&
						!game.isEnoughSpaceInDirection(this, nextDirection, 1, false)) {
					offset = 0;
				}
				Direction offsetDirection = direction;
				if (offset < 0) {
					offsetDirection = Direction.fromAtoB(oldX, oldY, x, y);
				} else if (game.isEnoughSpaceInDirection(this, nextDirection, 1, false)) {
					offsetDirection = nextDirection;
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
				switch (direction) {
				case DOWN:
					spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
							animationFrame % spritesheet.getFrameCount(index));
					break;
				case LEFT:
					spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
							animationFrame % spritesheet.getFrameCount(index));
					break;
				case RIGHT:
					spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
							animationFrame % spritesheet.getFrameCount(index));
					break;
				case UP:
					spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
							animationFrame % spritesheet.getFrameCount(index));
					break;
				default:
					break;
				}
			}
			if (timeSinceLastFrame >= spritesheet.getFrameTime()) {
				timeSinceLastFrame = 0;
				animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
			}
			brush.setRenderingHints(getGame().antialiasingRH);
		}
	}

	@Override
	public void act(double delta) {
		super.act(delta);

		if (lastTime >= speed / getGame().getSpeed().getSpeedFactor()) {
			lastTime = 0;
			if (nextDirection != null) {
				if (nextDirection != direction && game.isEnoughSpaceInDirection(this, nextDirection, 1, false)) {
					direction = nextDirection;
				}
			} else {
				direction = nextDirection;
			}

			if (direction != null) {
				if (game.isEnoughSpaceInDirection(this, direction, 1, false)) {
					switch (direction) {
					case DOWN:
						this.move(0, 1);
						break;
					case LEFT:
						this.move(-1, 0);
						break;
					case RIGHT:
						this.move(1, 0);
						break;
					case UP:
						this.move(0, -1);
						break;
					}
				} else {
					direction = null;
				}
				try {
					Pellet pellet = game.getCurrentMaze().getPellets()[(int) x][(int) y];
					if (pellet != null) {
						pellet.act(this, delta);
						game.getCurrentMaze().getPellets()[(int) x][(int) y] = null;
						game.getCurrentMaze().removePellet();
					}
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}

	@Override
	public void setDirection(Direction direction) {
		if (direction != nextDirection && direction != this.direction) {
			if (this.direction == null) {
				this.lastTime = speed/getGame().getSpeed().getSpeedFactor();
			}
			this.animationFrame = 0;
			this.nextDirection = direction;
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int value) {
		this.score += value;
	}

	public void subScore(int value) {
		this.score -= value;
	}

	public void addLife() {
		this.lives++;
	}

	public void removeLife() {
		this.lives--;
	}

	public int getLives() {
		return lives;
	}
}
