package pacman.game.entities;

import java.util.List;

import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class Ghost extends Entity {
	public static final int SPEED = 128;
	private int speed;
	protected boolean spawning = true, chase = true;
	
	public Ghost(String id, int x, int y, Spritesheet spritesheet, boolean spawning, Direction spawnDirection) {
		super(id, x, y, 16, 16, spritesheet);
		this.spawning = spawning;
		this.speed = SPEED;
		this.direction = spawnDirection;
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);
		
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
		}
	}
	
	 protected List<Direction> getAllPossibleWays() {
		 List<Direction> directions = game.getAllPossibleWays(this);
		 if (direction != null)
			 directions.remove(this.direction.getOpposite());
		 return directions;
	 }
}
