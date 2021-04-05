package pacman.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pacman.app.Application;
import pacman.utils.Direction;

public class GameKeyListener implements KeyListener {
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			Application.getPlayer().setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_UP:
			Application.getPlayer().setDirection(Direction.UP);
			break;
		case KeyEvent.VK_LEFT:
			Application.getPlayer().setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			Application.getPlayer().setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_F1:
			Application.debug = !Application.debug;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
