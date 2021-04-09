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
	public static final float SPEED = PacMan.SPEED/1.25f;
	protected boolean spawning = true;
	protected GhostMode mode = GhostMode.CHASE;
	protected int targetX, targetY;

	public Ghost(String id, int x, int y, int width, int height, Spritesheet spritesheet, boolean spawning,
			Direction spawnDirection) {
		super(id, x, y, width, height, spritesheet);
		this.spawning = spawning;
		this.speed = SPEED;
		this.direction = spawnDirection;
	}

	@Override
	public void draw(Graphics2D brush) {
		brush.setRenderingHints(game.noAntialiasingRH);
		super.draw(brush);
		brush.setRenderingHints(game.antialiasingRH);
	}
	
	@Override
	public void drawDebug(Graphics2D brush) {
		super.drawDebug(brush);
		brush.setRenderingHints(game.antialiasingRH);
		brush.setColor(Color.magenta);
		brush.fillOval(targetX * game.getCurrentMaze().getTileSize() - 1,
				targetY * game.getCurrentMaze().getTileSize() - 1, 2, 2);
		brush.setRenderingHints(game.noAntialiasingRH);
	}

	@Override
	public void act(double delta) {
		super.act(delta);
		
		if (lastTime >= speed / getGame().getSpeed().getSpeedFactor()) {
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
		return directions;
	}
}
