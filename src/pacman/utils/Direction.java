package pacman.utils;

public enum Direction {
	UP, DOWN, LEFT, RIGHT;
	
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
}
