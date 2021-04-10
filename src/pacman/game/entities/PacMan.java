package pacman.game.entities;

import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Direction;
import pacman.utils.MathUtils;
import pacman.utils.Spritesheet;

public class PacMan extends Entity {
	protected static final int DEATH_ANIMATION_INDEX = 5;
	protected static final float SPEED = 0.132f, TIME_BEFORE_DEATH_ANIMATION = 0.5f;
	private double timeSinceDead = 0;
	private int score, lives, ghostCombo;
	private boolean dead = false;
	private Direction nextDirection;

	public PacMan(int x, int y, int width, int height, Direction spawnDirection) {
		super("pacman", x, y, width, height, spawnDirection,
				new Spritesheet("assets/classicmaze/pacman.png", 0.04, 6, 1, 4, 4, 4, 4, 12));
		this.score = 0;
		this.lives = 3;
		spritesheet.setFrameTime(DEATH_ANIMATION_INDEX, 0.1);
		spritesheet.setLoop(DEATH_ANIMATION_INDEX, false);
		this.ghostCombo = 0;
		this.speed = SPEED;
	}

	@Override
	public void draw(Graphics2D brush) {
		float centerX = getCenterX(), centerY = getCenterY();
		brush.setRenderingHints(getGame().noAntialiasingRH);
		if (dead) {
			int index = IDLE_ANIMATION_INDEX;
			if (timeSinceDead >= TIME_BEFORE_DEATH_ANIMATION) {
				index = DEATH_ANIMATION_INDEX;
			}
			if (timeSinceLastFrame >= spritesheet.getFrameTime(index)) {
				timeSinceLastFrame = 0;
				if (spritesheet.doesLoop(index)) {
					animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
				} else {
					animationFrame = MathUtils.clamp(animationFrame+1, 0, spritesheet.getFrameCount(index)-1);
				}
			}
			spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index, animationFrame);
		} else {
			float offset = (float) (MathUtils.normalize(lastTime, speed)-0.5)*game.getCurrentMaze().getTileSize();
			int index = IDLE_ANIMATION_INDEX;
			if (direction == null) {
				if (timeSinceLastFrame >= spritesheet.getFrameTime(index)) {
					timeSinceLastFrame = 0;
					animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
				}
				spritesheet.drawSprite(brush, (int) centerX, (int) centerY, IDLE_ANIMATION_INDEX,
						animationFrame % spritesheet.getFrameCount(IDLE_ANIMATION_INDEX));
			} else {
				Direction offsetDirection = direction;
				if (offset > 0 &&
						!game.isEnoughSpaceInDirection(this, direction, 1, false) &&
						!game.isEnoughSpaceInDirection(this, nextDirection, 1, false)) {
					offset = 0;
					offsetDirection = null;
				}
				if (offset < 0) {
					offsetDirection = Direction.fromAtoB(oldX, oldY, x, y);
				} else if (game.isEnoughSpaceInDirection(this, nextDirection, 1, false)) {
					offsetDirection = nextDirection;
				}
				if (offsetDirection == null) {
					index = IDLE_ANIMATION_INDEX;
				} else {
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
				if (timeSinceLastFrame >= spritesheet.getFrameTime(index)) {
					timeSinceLastFrame = 0;
					if (spritesheet.doesLoop(index)) {
						animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
					} else {
						animationFrame = MathUtils.clamp(animationFrame+1, 0, spritesheet.getFrameCount(index)-1);
					}
				}
				spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index, animationFrame);
			}
		}
		brush.setRenderingHints(getGame().antialiasingRH);
	}

	@Override
	public void act(double delta) {
		super.act(delta);

		if (dead) {
			timeSinceDead += delta;
			if (timeSinceDead >= TIME_BEFORE_DEATH_ANIMATION) {
				Application.playSound(Clips.death1, 1, false);
				if (timeSinceDead > spritesheet.getFrameCount(DEATH_ANIMATION_INDEX)
						* spritesheet.getFrameTime(DEATH_ANIMATION_INDEX) + TIME_BEFORE_DEATH_ANIMATION) {
					Application.stopSound(Clips.death1);
					Application.playSound(Clips.death2, 1, false);
					if (timeSinceDead > spritesheet.getFrameCount(DEATH_ANIMATION_INDEX)
							* spritesheet.getFrameTime(DEATH_ANIMATION_INDEX) + TIME_BEFORE_DEATH_ANIMATION * 2) {
						Application.stopSound(Clips.death2);
						dead = false;
						game.restart();
					}
				}
			}
		} else {
			if (lastTime >= getSpeed()) {
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
			try {
				if (!game.getCurrentMaze().getSpeedRestrictionZones()[x][y]) {
					for (Entity entity : game.getEntities()) {
						if (entity instanceof Ghost && entity.getX() == x && entity.getY() == y) {
							Ghost ghost = (Ghost) entity;
							switch (ghost.getMode()) {
							case CHASE:
							case SCATTER:
								die();
								break;
							case FRIGHTENED:
								ghost.eat(this);
								break;
							default:
								break;
							}
						}
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
	}
	
	public void die() {
		this.dead = true;
		this.timeSinceDead = 0;
		this.removeLife();
		game.clear();
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void respawn() {
		this.lastTime = 0;
		x = game.getCurrentMaze().getPlayerSpawnX();
		oldX = x+1;
		y = game.getCurrentMaze().getPlayerSpawnY();
		oldY = y;
		direction = Direction.LEFT;
		nextDirection = Direction.LEFT;
	}

	@Override
	public void setDirection(Direction direction) {
		if (game == null) {
			this.direction = direction;
			this.nextDirection = direction;
		} else {
			if (direction != nextDirection && direction != this.direction) {
				if (this.direction == null) {
					this.lastTime = getSpeed();
				}
				this.animationFrame = 0;
				this.nextDirection = direction;
			}
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
	
	public int getGhostCombo() {
		return ghostCombo;
	}
	
	public void setGhostCombo(int ghostCombo) {
		this.ghostCombo = ghostCombo;
	}
	
	public void addGhostCombo() {
		this.ghostCombo++;
	}
	
	public void subGhostCombo() {
		this.ghostCombo--;
	}
	
	public void resetGhostCombo() {
		this.ghostCombo = 0;
	}

	public Direction getLastDirection() {
		if (direction == null) {
			return Direction.fromAtoB(oldX, oldY, x, y);
		} else {
			return direction;
		}
	}
}
