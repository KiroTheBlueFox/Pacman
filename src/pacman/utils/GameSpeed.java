package pacman.utils;

import pacman.app.Clips;

public enum GameSpeed {
	NORMAL(Clips.move1, 1),
	FAST(Clips.move2, 1.25f),
	FASTER(Clips.move3, 1.5f);
	
	private Clips movementClip;
	private float speedFactor;
	
	private GameSpeed(Clips movementClip, float speedFactor) {
		this.movementClip = movementClip;
		this.speedFactor = speedFactor;
	}
	
	public Clips getMovementClip() {
		return movementClip;
	}
	
	public float getSpeedFactor() {
		return speedFactor;
	}
}
