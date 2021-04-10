package pacman.app;

import java.util.TimerTask;

public class ActRefresher extends TimerTask {
	private long lastTime;

	public ActRefresher(int delay) {
		lastTime = System.currentTimeMillis()+delay;
	}

	@Override
	public void run() {
		long now = System.currentTimeMillis();
		Application.getGame().act((now - lastTime) / 1000d);
		this.lastTime = now;
	}
}
