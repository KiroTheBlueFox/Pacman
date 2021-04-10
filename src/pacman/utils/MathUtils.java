package pacman.utils;

public class MathUtils {
	public static double distance(double xA, double yA, double xB, double yB) {
		return Math.sqrt(Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2));
	}

	public static double distanceSquared(double xA, double yA, double xB, double yB) {
		return Math.pow(xB - xA, 2) + Math.pow(yB - yA, 2);
	}

	public static int distanceSquared(int xA, int yA, int xB, int yB) {
		return (xB - xA) * (xB - xA) + (yB - yA) * (yB - yA);
	}

	public static double normalize(double value, double max) {
		return value / max;
	}
	
	public static int clamp(int value, int min, int max) {
		if (value < min)
			return min;
		else if (value > max)
			return max;
		else
			return value;
	}
}
