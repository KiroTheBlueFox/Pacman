package pacman.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import java.util.Timer;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;

import pacman.game.BottomMenu;
import pacman.game.Game;
import pacman.game.GameKeyListener;
import pacman.game.TopMenu;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;
import pacman.game.maze.classic.ClassicMaze;

public class Application {
	private static Game game;
	private static TopMenu topMenu;
	private static BottomMenu bottomMenu;
	private static PacMan player;
	private static JFrame window;
	private static Random random;
	private static Timer timer;
	public static final int FPS = 60;
	public static final long DEFAULT_SEED = 0;
	public static final float DEFAULT_VOLUME = 0.8f;
	public static boolean debug = false;

	public static void main(String[] args) {
		window = new JFrame();

		window.setMinimumSize(new Dimension(480, 679));

		window.setTitle("Pac-Man");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
//		window.setResizable(false);
		window.setBackground(Color.black);

		game = new Game();
//		game.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		window.add(game);
		topMenu = new TopMenu();
		window.add(topMenu, BorderLayout.NORTH);
		bottomMenu = new BottomMenu();
		window.add(bottomMenu, BorderLayout.SOUTH);

		random = new Random(DEFAULT_SEED);

		startGame();

		window.setVisible(true);

		window.addKeyListener(new GameKeyListener());

		timer = new Timer();
		timer.schedule(new AppRefresher(), 0, 1000 / FPS);
	}

	private static void startGame() {
		Maze maze = new ClassicMaze();
		game.setCurrentMaze(maze);

//		player = new PacMan((maze.getPlayerSpawnX()-maze.getTileSize()/2f)/(float) maze.getTileSize(), (maze.getPlayerSpawnY()-maze.getTileSize()/2f)/(float) maze.getTileSize(), maze.getTileSize(), maze.getTileSize());
		player = new PacMan(maze.getPlayerSpawnX(), maze.getPlayerSpawnY(), maze.getTileSize(), maze.getTileSize());
		game.addActor(player);
	}

	public static synchronized boolean playSound(Clips clip1, int times, boolean force) {
		Clip clip = clip1.getClip();
		if (force || !clip.isActive()) {
			clip.stop();
			clip.loop(times);
			return true;
		}
		return false;
	}

	public static void stopSound(Clips clip) {
		clip.getClip().stop();
		clip.getClip().flush();
	}

	public static void stopSounds(Clips[] clips) {
		for (Clips clip : clips) {
			clip.getClip().stop();
			clip.getClip().flush();
		}
	}

	public static Game getGame() {
		return game;
	}

	public static TopMenu getTopMenu() {
		return topMenu;
	}

	public static BottomMenu getBottomMenu() {
		return bottomMenu;
	}

	public static PacMan getPlayer() {
		return player;
	}

	public static Random getRandom() {
		return random;
	}

	public static void close() {
		Clips.closeClips();
		timer.cancel();
		game.close();
		game = null;
		window.dispose();
		System.exit(0);
	}
}
