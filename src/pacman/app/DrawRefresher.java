package pacman.app;

import java.util.TimerTask;

public class DrawRefresher extends TimerTask {
	@Override
	public void run() {
		Application.getGame().repaint();
		Application.getTopMenu().repaint();
		Application.getBottomMenu().repaint();
	}

}
