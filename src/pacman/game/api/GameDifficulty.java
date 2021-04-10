package pacman.game.api;

public enum GameDifficulty {
	EASY(7, 20, 7, 20, 5, 20, 5),
	NORMAL(7, 20, 7, 20, 5, 1034.14, 0.01),
	HARD(5, 20, 5, 20, 5, 1037.14, 0.01);
	
	private double[] modes;
	
	private GameDifficulty(double scatter1, double chase1, double scatter2, double chase2, double scatter3, double chase3, double scatter4) {
		modes = new double[] {
				scatter1,
				scatter1+chase1,
				scatter1+chase1+scatter2,
				scatter1+chase1+scatter2+chase2,
				scatter1+chase1+scatter2+chase2+scatter3,
				scatter1+chase1+scatter2+chase2+scatter3+chase3,
				scatter1+chase1+scatter2+chase2+scatter3+chase3+scatter4};
	}
	
	public double[] getModes() {
		return modes;
	}
	
	public double getTimeForMode(int index) {
		try {
			return modes[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public GhostMode getModeForTime(double d) {
		if (d < modes[0] ||
				(d >= modes[1] && d < modes[2]) ||
				(d >= modes[3] && d < modes[4]) ||
				(d >= modes[5] && d < modes[6])) {
			return GhostMode.SCATTER;
		} else {
			return GhostMode.CHASE;
		}
	}
}
