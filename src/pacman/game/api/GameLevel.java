package pacman.game.api;

public class GameLevel {
	private int level;
	private double speedFactor;
	
	public GameLevel(int startingLevel) {
		setLevel(startingLevel);
	}
	
	private void refreshSpeedFactor() {
		speedFactor = 1+Math.sqrt(level)/100d;
	}
	
	private void setLevel(int level) {
		this.level = level;
		refreshSpeedFactor();
	}
	
	public void nextLevel() {
		setLevel(level+1);
	}
	
	public void previousLevel() {
		setLevel(level-1);
	}
	
	public int getLevel() {
		return level;
	}
	
	public double getSpeedFactor() {
		return speedFactor;
	}
	
	public GameDifficulty getDifficulty() {
		if (this.level == 0) {
			return GameDifficulty.EASY;
		} else if (this.level >= 1 && this.level < 5) {
			return GameDifficulty.NORMAL;
		} else {
			return GameDifficulty.HARD;
		}
	}
}
