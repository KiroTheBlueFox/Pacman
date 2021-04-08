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
import pacman.game.entities.Entity;
import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Direction;
import pacman.utils.GameSpeed;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int MARGIN = 8;
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
			double scale = Math.min((getWidth()-MARGIN*2)/(float) currentMaze.getWidth(), (getHeight()-MARGIN*2)/(float) currentMaze.getHeight());
			brush.translate((getWidth()-(currentMaze.getWidth()*scale))/2, (getHeight()-(currentMaze.getHeight()*scale))/2);
			brush.scale(scale, scale);
			
			brush.setColor(Color.blue);
			currentMaze.draw(brush);
			brush.setStroke(defaultStroke);
			if (Application.debug) {
				brush.setRenderingHints(noAntialiasingRH);
				currentMaze.drawDebug(brush);
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
				if (Application.debug) {
					brush.setRenderingHints(noAntialiasingRH);
					actor.drawDebug(brush);
					brush.setRenderingHints(antialiasingRH);
					brush.setStroke(defaultStroke);
				}
			});
			
			brush.setColor(Color.black);
			brush.fillRect(-getWidth(), 0, getWidth(), getHeight());
			brush.fillRect(currentMaze.getWidth(), 0, getWidth(), getHeight());
			brush.fillRect(0, getHeight(), getWidth(), getHeight());
			brush.fillRect(0, currentMaze.getHeight(), getWidth(), getHeight());
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
	 * @param entity The entity from which to detect collisions
	 * @param direction Direction to go
	 * @param distance Distance to go
	 * @param force If you don't want in-between calculations
	 * @return Something below 0 if there is enough space<br>The distance otherwise
	 */
	public float isEnoughSpaceInDirection(Entity entity, Direction direction, float distance, boolean force) {
		if (distance > currentMaze.getTileSize() && !force) {
			for (int i = 0; i < distance/currentMaze.getTileSize(); i++) {
				float calculatedDistance = isEnoughSpaceInDirection(entity, direction, currentMaze.getTileSize()*i, true);
				if (calculatedDistance >= 0)
					return calculatedDistance;
			}
			return isEnoughSpaceInDirection(entity, direction, distance, true);
		} else {
			float playerX = entity.getX()+entity.getWidth()/2,
					playerY = entity.getY()+entity.getHeight()/2,
					x = playerX, y = playerY;
			switch (direction) {
			case DOWN:
				y += distance+entity.getHeight()/2;
				break;
			case LEFT:
				x -= distance+entity.getWidth()/2;
				break;
			case RIGHT:
				x += distance+entity.getWidth()/2;
				break;
			case UP:
				y -= distance+entity.getHeight()/2;
				break;
			default:
				break;
			}
			int newX = (int) (x/(float) currentMaze.getTileSize()), newY = (int) (y/(float) currentMaze.getTileSize());
			if (newX >= 0 && newX < currentMaze.getWalls().length &&
					newY >= 0 && newY < currentMaze.getWalls()[newX].length) {
				if (currentMaze.getWalls()[newX][newY]) {
					switch (direction) {
					case UP:
						return entity.getY()-(newY+1)*currentMaze.getTileSize();
					case DOWN:
						return newY*currentMaze.getTileSize()-(entity.getY()+entity.getHeight());
					case LEFT:
						return entity.getX()-(newX+1)*currentMaze.getTileSize();
					case RIGHT:
						return newX*currentMaze.getTileSize()-(entity.getX()+entity.getWidth());
					}
					return 0;
				} else {
					if (entity.getDirection() != null && entity.getDirection() != direction) {
						switch (direction) {
						case DOWN:
							entity.setX(newX*currentMaze.getTileSize());
							break;
						case LEFT:
							entity.setY(newY*currentMaze.getTileSize());
							break;
						case RIGHT:
							entity.setY(newY*currentMaze.getTileSize());
							break;
						case UP:
							entity.setX(newX*currentMaze.getTileSize());
							break;
						}
					}
				}
			} else {
				if (entity.getDirection() != null && entity.getDirection() != direction) {
					return 0;
				}
			}
			return -1;
		}
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