package pacman.app;

import java.util.TimerTask;

import pacman.game.Game;

public class AppRefresher extends TimerTask {
	private long lastTime;
	
	public AppRefresher() {
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	public void run() {
		Game game = Application.getGame();
		game.repaint();
		long now = System.currentTimeMillis();
		game.act((now-lastTime)/1000d);
		this.lastTime = now;
	}
}
