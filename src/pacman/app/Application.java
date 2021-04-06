package pacman.app;

import java.util.Timer;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JFrame;

import pacman.game.Game;
import pacman.game.GameKeyListener;
import pacman.game.entities.PacMan;
import pacman.game.maze.ClassicMaze;
import pacman.game.maze.Maze;

public class Application {
	private static Game game;
	private static PacMan player;
	private static JFrame window;
	public static int FPS = 60;
	public static boolean debug = false;
	
	public static void main(String[] args) {
		Clips.initClips();
		
		window = new JFrame();
		
		game = new Game();
		window.add(game);

		window.setSize(464, 568);

		window.setTitle("Pac-Man");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		
		window.setVisible(true);

		startGame();
		
		window.addKeyListener(new GameKeyListener());
		
		Timer timer = new Timer();
		timer.schedule(new AppRefresher(), 0, 1000/FPS);
	}
	
	private static void startGame() {
		Maze maze = new ClassicMaze();
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
	
	public static PacMan getPlayer() {
		return player;
	}
	
	public static void close() {
		window.dispose();
		System.exit(0);
	}
}
