package pacman.game.api;

import pacman.app.Clips;

public enum GameSpeed {
	NORMAL(Clips.move1, 1), FAST(Clips.move2, 1.1f), FASTER(Clips.move3, 1.2f), FASTEST(Clips.move4, 1.3f), MAX(Clips.move5, 1.4f);

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
