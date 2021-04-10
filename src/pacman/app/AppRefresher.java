package pacman.app;

import java.util.TimerTask;

import pacman.game.Game;

public class AppRefresher extends TimerTask {
	private long lastTime;

	public AppRefresher(int delay) {
		lastTime = System.currentTimeMillis()+delay;
	}

	@Override
	public void run() {
		Game game = Application.getGame();
		game.repaint();
		Application.getTopMenu().repaint();
		Application.getBottomMenu().repaint();
		long now = System.currentTimeMillis();
		game.act((now - lastTime) / 1000d);
		this.lastTime = now;
	}
}
