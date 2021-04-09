package pacman.utils;

public class MathUtils {
	public static double distance(double xA, double yA, double xB, double yB) {
		return Math.sqrt(Math.pow(xB-xA, 2)+Math.pow(yB-yA, 2));
	}
	
	public static double distanceSquared(double xA, double yA, double xB, double yB) {
		return Math.pow(xB-xA, 2)+Math.pow(yB-yA, 2);
	}

	public static double normalize(double value, double max) {
		return value/max;
	}
}
