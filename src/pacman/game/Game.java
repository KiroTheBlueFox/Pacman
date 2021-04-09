package pacman.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.api.GameSpeed;
import pacman.game.entities.Entity;
import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Direction;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int MARGIN = 8;
	private List<Entity> actors;
	private Maze currentMaze;
	public final RenderingHints antialiasingRH, noAntialiasingRH;
	private GameSpeed speed;

	public Game() {
		this.actors = new ArrayList<Entity>();
		this.setBackground(Color.black);

		speed = GameSpeed.NORMAL;
		antialiasingRH = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		antialiasingRH.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		noAntialiasingRH = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g.create();
		Stroke defaultStroke = brush.getStroke();
		brush.setRenderingHints(antialiasingRH);

//		brush.setColor(Color.black);
//		brush.fillRect(0, 0, (int) getWidth(), (int) getHeight());

		if (currentMaze != null) {
			double scale = Math.min((getWidth() - MARGIN * 2) / (float) currentMaze.getWidth(),
					(getHeight() - MARGIN * 2) / (float) currentMaze.getHeight());
			brush.translate((getWidth() - (currentMaze.getWidth() * scale)) / 2,
					(getHeight() - (currentMaze.getHeight() * scale)) / 2);
			brush.scale(scale, scale);

			currentMaze.draw(brush);
			brush.setStroke(defaultStroke);
			if (Application.debug) {
				brush.setRenderingHints(noAntialiasingRH);
				brush.setColor(Color.blue);
				currentMaze.drawDebug(brush);
				brush.setColor(Color.darkGray);
				currentMaze.drawGrid(brush);
				brush.setRenderingHints(antialiasingRH);
				brush.setStroke(defaultStroke);
			}

			brush.setColor(Color.white);
			for (Pellet[] line : currentMaze.getPellets()) {
				for (Pellet pellet : line) {
					if (pellet != null)
						pellet.draw(brush);
				}
			}
			brush.setStroke(defaultStroke);

			brush.setColor(Color.white);
			this.actors.forEach((actor) -> {
				actor.draw(brush);
				brush.setStroke(defaultStroke);
			});

			brush.setColor(Color.black);
			brush.fillRect(-getWidth(), 0, getWidth(), getHeight());
			brush.fillRect(currentMaze.getWidth(), 0, getWidth(), getHeight());
			brush.fillRect(0, getHeight(), getWidth(), getHeight());
			brush.fillRect(0, currentMaze.getHeight(), getWidth(), getHeight());
			
			if (Application.debug) {
				brush.setRenderingHints(noAntialiasingRH);
				
				brush.setColor(Color.blue);
				currentMaze.drawDebug(brush);
				brush.setColor(Color.darkGray);
				currentMaze.drawGrid(brush);
				brush.setStroke(defaultStroke);
				
				this.actors.forEach((actor) -> {
					actor.drawDebug(brush);
					brush.setStroke(defaultStroke);
				});
				
				brush.setRenderingHints(antialiasingRH);
			}
		}
	}

	public void act(double delta) {
		boolean moving = false;
		for (Entity actor : this.actors) {
			actor.act(delta);
			if (actor.getDirection() != null) {
				moving = true;
			}
		}
		if (!moving) {
			Application.stopSounds(Clips.getMoveClips());
		}
	}

	public void addActor(Entity actor) {
		this.actors.add(actor);
		actor.setGame(this);
	}

	public void removeActor(Entity actor) {
		this.actors.remove(actor);
	}

	public List<Entity> getActors() {
		return actors;
	}

	public Maze getCurrentMaze() {
		return currentMaze;
	}

	public void setCurrentMaze(Maze currentMaze) {
		this.currentMaze = currentMaze;
		this.currentMaze.setGame(this);
		this.setSize(currentMaze.getWidth(), currentMaze.getHeight());
	}

	/**
	 * Detects if the Entity can move in said direction of said distance
	 * 
	 * @param entity    The entity from which to detect collisions
	 * @param direction Direction to go
	 * @param distance  Distance to go
	 * @param force     If you don't want in-between calculations
	 * @return Something below 0 if there is enough space<br>
	 *         The distance otherwise
	 */
	public boolean isEnoughSpaceInDirection(Entity entity, Direction direction, int distance, boolean force) {
		if (distance > 1 && !force) {
			for (int i = 0; i < distance / currentMaze.getTileSize(); i++) {
				if (!isEnoughSpaceInDirection(entity, direction, i, true))
					return false;
			}
			return isEnoughSpaceInDirection(entity, direction, distance, true);
		} else {
			int x = entity.getGridX(), y = entity.getGridY();
			switch (direction) {
			case DOWN:
				y += distance;
				break;
			case LEFT:
				x -= distance;
				break;
			case RIGHT:
				x += distance;
				break;
			case UP:
				y -= distance;
				break;
			default:
				break;
			}
			if (x >= 0 && x < currentMaze.getWalls().length && y >= 0 && y < currentMaze.getWalls()[x].length) {
				if (currentMaze.getWalls()[x][y]) {
					return false;
				}
			} else {
				if (entity.getDirection() != null && entity.getDirection() != direction) {
					return false;
				}
			}
			return true;
		}
	}

	public List<Direction> getAllPossibleWays(Entity entity) {
		List<Direction> directions = new ArrayList<Direction>();
		for (Direction direction : Direction.values()) {
			if (isEnoughSpaceInDirection(entity, direction, 1, false)) {
				directions.add(direction);
			}
		}
		return directions;
	}

	public void close() {
		currentMaze.close();
	}

	public void setSpeed(GameSpeed speed) {
		this.speed = speed;
		for (GameSpeed speed1 : GameSpeed.values()) {
			if (speed1 != speed) {
				Application.stopSound(speed1.getMovementClip());
			}
		}
	}

	public GameSpeed getSpeed() {
		return this.speed;
	}
}