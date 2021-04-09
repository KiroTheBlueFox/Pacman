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
	
	public static Direction fromAngle(double angle) {
		if (angle < 0)
			angle += 360;
		angle += 45;
		angle /= 90;
		if (angle >= 0 && angle < 1) {
			return RIGHT;
		} else if (angle >= 1 && angle < 2) {
			return DOWN;
		} else if (angle >= 2 && angle < 3) {
			return LEFT;
		} else {
			return UP;
		}
	}

	public static Direction fromAtoB(float xA, float yA, float xB, float yB) {
		return fromAngle(Math.toDegrees(Math.atan2(yB-yA, xB-xA)));
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
