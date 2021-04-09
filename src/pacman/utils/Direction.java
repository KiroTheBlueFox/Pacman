package pacman.utils;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	UP(1), DOWN(3), LEFT(2), RIGHT(4);
	
	private int priority;
	
	private Direction(int priority) {
		this.priority = priority;
	}
	
	public Direction getOpposite() {
		switch (this) {
		case UP:
			return DOWN;
		case DOWN:
			return UP;
		case LEFT:
			return RIGHT;
		case RIGHT:
			return LEFT;
		default:
			return this;
		}
	}
	
	public Direction rotateClockwise() {
		switch (this) {
		case UP:
			return RIGHT;
		case RIGHT:
			return DOWN;
		case DOWN:
			return LEFT;
		case LEFT:
			return UP;
		default:
			return this;
		}
	}
	
	public Direction rotateCounterClockwise() {
		switch (this) {
		case UP:
			return LEFT;
		case LEFT:
			return DOWN;
		case DOWN:
			return RIGHT;
		case RIGHT:
			return UP;
		default:
			return this;
		}
	}
	
	public int toDegrees() {
		switch (this) {
		case UP:
			return 90;
		case LEFT:
			return 180;
		case DOWN:
			return -90;
		case RIGHT:
		default:
			return 0;
		}
	}
	
	public static Direction[] fromAtoB(float x, float y, float f, float g) {
		Direction[] directions = new Direction[2];
		if (x > f) {
			directions[0] = RIGHT;
		} else {
			directions[0] = LEFT;
		}
		if (y > g) {
			directions[1] = DOWN;
		} else {
			directions[1] = UP;
		}
		return directions;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public static Direction fromPriority(int priority) {
		switch (priority) {
		default:
		case 1:
			return UP;
		case 2:
			return LEFT;
		case 3:
			return DOWN;
		case 4:
			return RIGHT;
		}
	}
	
	public static Direction getHighestPriority(Direction... directions) {
		return getHighestPriority(Arrays.asList(directions));
	}
	
	public static Direction getHighestPriority(List<Direction> directions) {
		int highestPriority = -1;
		for (Direction direction : directions) {
			if (direction.getPriority() < highestPriority || highestPriority == -1) {
				highestPriority = direction.getPriority();
			}
		}
		return fromPriority(highestPriority);
	}
}
