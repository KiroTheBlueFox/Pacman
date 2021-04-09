package pacman.utils;

import java.util.List;
import java.util.Random;

public class ListUtils {
	public static <T> T getRandomInList(List<T> list, Random random) {
		return list.get(random.nextInt(list.size()));
	}
	
	public static <T> T getRandomInList(List<T> list) {
		return getRandomInList(list, new Random());
	}
}
