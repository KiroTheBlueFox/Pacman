package pacman.game.api;

public enum GhostMode {
	CHASE(true, true),
	SCATTER(true, true),
	FRIGHTENED(true, false),
	EATEN(false, false);

	private boolean turnAround, nextCanTurnAround;
	
	private GhostMode(boolean turnAround, boolean nextCanTurnAround) {
		this.turnAround = turnAround;
		this.nextCanTurnAround = nextCanTurnAround;
	}
	
	public boolean turnAround() {
		return this.turnAround;
	}
	
	public boolean nextCanTurnAround() {
		return nextCanTurnAround;
	}
}
