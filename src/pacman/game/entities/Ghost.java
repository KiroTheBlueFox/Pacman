package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import pacman.game.api.GhostMode;
import pacman.utils.Direction;
import pacman.utils.MathUtils;
import pacman.utils.Spritesheet;

public class Ghost extends Entity {
	public static final float SPEED = PacMan.SPEED/1.1f, SLOW_FACTOR = 2;
	public static final int FRIGHTENED_NORMAL_INDEX = 0, FRIGHTENED_BLINKING_INDEX = 1;
	protected boolean spawning = true;
	protected Color debugColor = Color.magenta;
	protected GhostMode mode = GhostMode.CHASE;
	protected Spritesheet frightenedSpritesheet;
	protected boolean frightenedCooldown;
	protected int targetX, targetY;

	public Ghost(String id, int x, int y, int width, int height, Spritesheet spritesheet, boolean spawning,
			Direction spawnDirection) {
		super(id, x, y, width, height, spawnDirection, spritesheet);
		this.spawning = spawning;
		this.speed = SPEED;
		if (spawning) {
			this.speed *= 2f; 
			this.lastTime = this.speed/2d; 
		}
		this.frightenedSpritesheet = new Spritesheet("assets/classicmaze/frightenedGhost.png", 0.1, 2, 2, 4);
		this.frightenedCooldown = false;
	}

	public void setMode(GhostMode mode, boolean force) {
		if (this.mode != mode && (this.mode != GhostMode.FRIGHTENED || force)) { 
			this.mode = mode;
			if (mode.rotate180()) {
				this.direction = Direction.fromAtoB(x, y, oldX, oldY);
			}
		}
	}
	
	public GhostMode getMode() {
		return mode;
	}
	
	public void setFrightenedCooldown(boolean frightenedCooldown) {
		this.frightenedCooldown = frightenedCooldown;
	}

	@Override
	public void draw(Graphics2D brush) {
		brush.setRenderingHints(game.noAntialiasingRH);
		if (spawning) {
			float centerX = getCenterX()+0.5f*game.getCurrentMaze().getTileSize(), centerY = getCenterY(),
					offset = (float) (MathUtils.normalize(lastTime, speed)-0.5)*game.getCurrentMaze().getTileSize();
			int index = IDLE_ANIMATION_INDEX;
			if (direction == null) {
				spritesheet.drawSprite(brush, (int) centerX, (int) centerY, IDLE_ANIMATION_INDEX,
						animationFrame % spritesheet.getFrameCount(IDLE_ANIMATION_INDEX));
			} else {
				switch (direction) {
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
				default:
					break;
				}
				spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
						animationFrame % spritesheet.getFrameCount(index));
			}
			if (timeSinceLastFrame >= spritesheet.getFrameTime()) {
				timeSinceLastFrame = 0;
				animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
			}
		} else {
			float centerX = getCenterX(), centerY = getCenterY(),
					offset = (float) (MathUtils.normalize(lastTime, speed)-0.5)*game.getCurrentMaze().getTileSize();
			int index = IDLE_ANIMATION_INDEX;
			if (direction == null) {
				if (mode != GhostMode.FRIGHTENED) {
					spritesheet.drawSprite(brush, (int) centerX, (int) centerY, IDLE_ANIMATION_INDEX,
							animationFrame % spritesheet.getFrameCount(IDLE_ANIMATION_INDEX));
				} else {
					if (frightenedCooldown)
						frightenedSpritesheet.drawSprite(brush, (int) centerX, (int) centerY, FRIGHTENED_BLINKING_INDEX,
								animationFrame % frightenedSpritesheet.getFrameCount(FRIGHTENED_BLINKING_INDEX));
					else
						frightenedSpritesheet.drawSprite(brush, (int) centerX, (int) centerY, FRIGHTENED_NORMAL_INDEX,
								animationFrame % frightenedSpritesheet.getFrameCount(FRIGHTENED_NORMAL_INDEX));
				}
			} else {
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
				if (mode != GhostMode.FRIGHTENED) {
					spritesheet.drawSprite(brush, (int) centerX, (int) centerY, index,
							animationFrame % spritesheet.getFrameCount(index));
				} else {
					if (frightenedCooldown)
						frightenedSpritesheet.drawSprite(brush, (int) centerX, (int) centerY, FRIGHTENED_BLINKING_INDEX,
								animationFrame % frightenedSpritesheet.getFrameCount(FRIGHTENED_BLINKING_INDEX));
					else
						frightenedSpritesheet.drawSprite(brush, (int) centerX, (int) centerY, FRIGHTENED_NORMAL_INDEX,
								animationFrame % frightenedSpritesheet.getFrameCount(FRIGHTENED_NORMAL_INDEX));
				}
			}
			if (timeSinceLastFrame >= spritesheet.getFrameTime()) {
				timeSinceLastFrame = 0;
				if (mode != GhostMode.FRIGHTENED) {
					animationFrame = (animationFrame + 1) % spritesheet.getFrameCount(index);
				} else {
					if (frightenedCooldown)
						animationFrame = (animationFrame + 1) % frightenedSpritesheet.getFrameCount(FRIGHTENED_BLINKING_INDEX);
					else
						animationFrame = (animationFrame + 1) % frightenedSpritesheet.getFrameCount(FRIGHTENED_NORMAL_INDEX);
				}
			}
		}
		brush.setRenderingHints(game.antialiasingRH);
	}
	
	@Override
	public void drawDebug(Graphics2D brush) {
		super.drawDebug(brush);
		brush.setRenderingHints(game.antialiasingRH);
		brush.setColor(debugColor);
		brush.fillOval((int) ((targetX+0.5f) * game.getCurrentMaze().getTileSize()) - 1,
				(int) ((targetY+0.5f) * game.getCurrentMaze().getTileSize()) - 1, 2, 2);
		brush.drawRect(targetX * game.getCurrentMaze().getTileSize(), targetY * game.getCurrentMaze().getTileSize(),
				game.getCurrentMaze().getTileSize(), game.getCurrentMaze().getTileSize());
		brush.setRenderingHints(game.noAntialiasingRH);
	}

	@Override
	public void act(double delta) {
		super.act(delta);
		this.setMode(game.getGameLevel().getDifficulty().getModeForTime(lifeTime), false);
		
		try {
			if (game.getCurrentMaze().getSpeedRestrictionZones()[x][y]) {
				speed = SPEED * SLOW_FACTOR;
			} else {
				speed = SPEED;
			}
		} catch (ArrayIndexOutOfBoundsException e) {};
		if (lastTime >= getSpeed()) {
			lastTime = 0;
			
			if (direction != null && !spawning) {
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
			}
			if (spawning) {
				direction = direction.getOpposite();
			}
		}
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);
		List<Direction> directions = getAllPossibleWays();
		if (!(direction == null)) {
			List<Direction> shortestDirections = null;
			int shortestDistance = -1;
			for (Direction direction : directions) {
				int testingX = this.x,
					testingY = this.y;
				switch (direction) {
				case DOWN:
					testingY++;
					break;
				case LEFT:
					testingX--;
					break;
				case RIGHT:
					testingX++;
					break;
				case UP:
					testingY--;
					break;
				}
				int distance = MathUtils.distanceSquared(testingX, testingY, this.targetX, this.targetY);
				if (shortestDistance == -1 || distance < shortestDistance) {
					shortestDirections = new ArrayList<Direction>();
					shortestDistance = distance;
					shortestDirections.add(direction);
				} else if (distance == shortestDistance) {
					shortestDirections.add(direction);
				}
			}
			if (shortestDirections != null) {
				if (!shortestDirections.isEmpty()) {
					direction = Direction.getHighestPriority(shortestDirections);
				} else {
					direction = direction.getOpposite();
				}
			} else {
				direction = direction.getOpposite();
			}
		} else {
			direction = direction.getOpposite();
		}
	}

	protected List<Direction> getAllPossibleWays() {
		List<Direction> directions = game.getAllPossibleWays(this);
		if (direction != null)
			directions.remove(direction.getOpposite());
		for (Direction direction : Direction.values()) {
			int[] testingPosition = new int[] {x, y};
			switch (direction) {
			case DOWN:
				testingPosition[1]++;
				break;
			case LEFT:
				testingPosition[0]--;
				break;
			case RIGHT:
				testingPosition[0]++;
				break;
			default:
			case UP:
				testingPosition[1]--;
				break;
			}
			if (testingPosition[0] == oldX && testingPosition[1] == oldY) {
				directions.remove(direction);
			}
		}
		try {
			if (game.getCurrentMaze().getMovementRestrictionZones()[x][y]) {
				directions.remove(Direction.UP);
			}
		} catch(ArrayIndexOutOfBoundsException e) {};
		return directions;
	}
}
