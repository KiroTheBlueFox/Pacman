package pacman.game.api;

public enum GhostMode {
	CHASE(true),
	SCATTER(false),
	FRIGHTENED(true),
	EATEN(false);

	private boolean rotate180;
	
	private GhostMode(boolean rotate180) {
		this.rotate180 = rotate180;
	}
	
	public boolean rotate180() {
		return this.rotate180;
	}
}
