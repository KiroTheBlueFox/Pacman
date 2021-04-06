package pacman.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Timer;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

import pacman.game.BottomMenu;
import pacman.game.Game;
import pacman.game.GameKeyListener;
import pacman.game.TopMenu;
import pacman.game.entities.PacMan;
import pacman.game.maze.Maze;
import pacman.game.maze.hdclassic.HDClassicMaze;

public class Application {
	private static Game game;
	private static TopMenu topMenu;
	private static BottomMenu bottomMenu;
	private static PacMan player;
	private static JFrame window;
	private static Timer timer;
	public final static int FPS = 60;
	public static boolean debug = false;
	
	public static void main(String[] args) {
		Clips.initClips();
		
		window = new JFrame();
		
		window.setMinimumSize(new Dimension(480, 663));

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
		
		startGame();
		
		window.setVisible(true);
		
		window.addKeyListener(new GameKeyListener());
		
		timer = new Timer();
		timer.schedule(new AppRefresher(), 0, 1000/FPS);
	}
	
	private static void startGame() {
		Maze maze = new HDClassicMaze();
		game.setCurrentMaze(maze);
		
		player = new PacMan(maze.getPlayerSpawnX()-8, maze.getPlayerSpawnY()-8);
		game.addActor(player);
	}
	
	public static synchronized boolean playSound(Clip clip, int times, boolean force) {
		if (force || !clip.isActive()) {
			clip.stop();
			FloatControl gainControl = ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN));
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * 0.8f) + gainControl.getMinimum();
			gainControl.setValue(gain);
			clip.loop(times);
			return true;
		}
		return false;
	}
	
	public static void stopSound(Clip clip) {
		clip.stop();
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
	
	public static void close() {
		timer.cancel();
		game.close();
		game = null;
		window.dispose();
		Clips.close();
		System.exit(0);
	}
}
