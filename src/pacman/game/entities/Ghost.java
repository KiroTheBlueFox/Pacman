package pacman.game.entities;

import java.awt.Graphics2D;
import java.util.List;

import pacman.game.api.GhostMode;
import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Ghost extends Entity {
	public static final int SPEED = 128;
	protected int speed;
	protected boolean spawning = true;
	protected GhostMode mode = GhostMode.SCATTER;
	protected float targetX, targetY;
	protected int oldPosX, oldPosY;
	
	public Ghost(String id, float x, float y, float width, float height, Spritesheet spritesheet, boolean spawning, Direction spawnDirection) {
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
	public void act(double delta) {
		super.act(delta);
		
		/*if (!spawning) {
			int[] currentPosition = game.absolutePositionToGridPosition(getCenterX(), getCenterY());
			List<Direction> directions = getAllPossibleWays();
			if (directions.size() > 1 && (currentPosition[0] != oldPosX || currentPosition[1] != oldPosY)) {
				Direction shortestDirection = null;
				double shortestDistance = -1;
				switch (mode) {
				case CHASE:
				case SCATTER:
				case FRIGHTENED:
					for (Direction direction : directions) {
						int[] position,
							  targetPosition = game.absolutePositionToGridPosition(targetX, targetY);
						switch (direction) {
						case DOWN:
							position = game.absolutePositionToGridPosition(getCenterX(), getCenterY()+game.getCurrentMaze().getTileSize());
							break;
						case LEFT:
							position = game.absolutePositionToGridPosition(getCenterX()-game.getCurrentMaze().getTileSize(), getCenterY());
							break;
						case RIGHT:
							position = game.absolutePositionToGridPosition(getCenterX()+game.getCurrentMaze().getTileSize(), getCenterY());
							break;
						default:
						case UP:
							position = game.absolutePositionToGridPosition(getCenterX(), getCenterY()-game.getCurrentMaze().getTileSize());
							break;
						}
						double calculatedDistance = Math2D.distanceSquared(position[0], position[1], targetPosition[0], targetPosition[1]);
						if (shortestDistance == -1 || calculatedDistance < shortestDistance) {
							shortestDistance = calculatedDistance;
							shortestDirection = direction;
						}
					}
					if (shortestDirection != null) {
						direction = shortestDirection;
					}
					break;
				case EATEN:
					direction = ListUtils.getRandomInList(directions, Application.getRandom());
					break;
				}
			} else {
				if (directions.get(0) != direction) {
					direction = directions.get(0);
				}
			}
		}
		
		if (direction != null && !spawning) {
			float distance = game.isEnoughSpaceInDirection(this, direction, (float) (delta*speed*getGame().getSpeed().getSpeedFactor()), false);
			if (distance < 0) {
				int newX = (int) ((x+width/2)/(float) game.getCurrentMaze().getTileSize()),
					newY = (int) ((y+height/2)/(float) game.getCurrentMaze().getTileSize());
				switch (direction) {
				case DOWN:
					this.move(0, (float) (delta*speed*getGame().getSpeed().getSpeedFactor()));
					this.setX(newX*game.getCurrentMaze().getTileSize());
					break;
				case LEFT:
					this.move((float) (-delta*speed*getGame().getSpeed().getSpeedFactor()), 0);
					this.setY(newY*game.getCurrentMaze().getTileSize());
					break;
				case RIGHT:
					this.move((float) (delta*speed*getGame().getSpeed().getSpeedFactor()), 0);
					this.setY(newY*game.getCurrentMaze().getTileSize());
					break;
				case UP:
					this.move(0, (float) (-delta*speed*getGame().getSpeed().getSpeedFactor()));
					this.setX(newX*game.getCurrentMaze().getTileSize());
					break;
				}
			} else {
				if (distance > 0) {
					switch (direction) {
					case DOWN:
						this.move(0, distance);
						break;
					case LEFT:
						this.move(-distance, 0);
						break;
					case RIGHT:
						this.move(distance, 0);
						break;
					case UP:
						this.move(0, -distance);
						break;
					}
				} else {
					direction = null;
				}
			}
		}*/
	}
	
	 protected List<Direction> getAllPossibleWays() {
		 List<Direction> directions = game.getAllPossibleWays(this);
		 if (direction != null)
			 directions.remove(direction.getOpposite());
		 for (Direction direction : Direction.values()) {
			 int[] testingPosition;
			 switch (direction) {
			 case DOWN:
				 testingPosition = game.absolutePositionToGridPosition(getCenterX(), getCenterY()+game.getCurrentMaze().getTileSize());
				 break;
			 case LEFT:
				 testingPosition = game.absolutePositionToGridPosition(getCenterX()-game.getCurrentMaze().getTileSize(), getCenterY());
				 break;
			 case RIGHT:
				 testingPosition = game.absolutePositionToGridPosition(getCenterX()+game.getCurrentMaze().getTileSize(), getCenterY());
				 break;
			 default:
			 case UP:
				 testingPosition = game.absolutePositionToGridPosition(getCenterX(), getCenterY()-game.getCurrentMaze().getTileSize());
				 break;
			 }
			 if (testingPosition[0] != oldPosX && testingPosition[1] != oldPosY) {
				 directions.remove(direction);
			 }
		 }
		 return directions;
	 }
	 
	 @Override
	public void move(float x, float y) {
		 int[] pos = game.absolutePositionToGridPosition(getCenterX(), getCenterY());
		 this.oldPosX = pos[0];
		 this.oldPosY = pos[1];
		 super.move(x, y);
	}
}
