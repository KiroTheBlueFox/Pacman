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
import pacman.game.entities.Entity;
import pacman.game.maze.Maze;
import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Direction;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int MARGIN = 8;
	private List<Entity> actors;
	private Maze currentMaze;
	private RenderingHints rh;

	public Game() {
		this.actors = new ArrayList<Entity>(); 
		this.setBackground(Color.black);
		
		rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g.create();
		Stroke defaultStroke = brush.getStroke();
		brush.setRenderingHints(rh);
		
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
				currentMaze.drawDebug(brush);
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
					actor.drawDebug(brush);
					brush.setStroke(defaultStroke);
				}
			});
			
			brush.setColor(Color.black);
			brush.fillRect((int) (-(getWidth()-(currentMaze.getWidth()))/2), 0, (int) ((getWidth()-(currentMaze.getWidth()))/2), getHeight());
			brush.fillRect((int) (currentMaze.getWidth()), 0, (int) ((getWidth()-(currentMaze.getWidth()*scale))/2), getHeight());
			brush.fillRect(0, (int) (-(getHeight()-(currentMaze.getHeight()))/2), getWidth(), (int) ((getHeight()-(currentMaze.getHeight()))/2));
			brush.fillRect(0, (int) (currentMaze.getHeight()), getWidth(), (int) ((getHeight()-(currentMaze.getHeight()*scale))/2));
		}
	}
	
	public void act(double delta) {
		this.actors.forEach((actor) -> {
			actor.act(delta);
		});
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
		this.setSize(currentMaze.getWidth(), currentMaze.getHeight());
	}

	/**
	 * Detects if the Entity can move in said direction of said distance
	 * @param entity The entity from which to detect collisions
	 * @param direction Direction to go
	 * @param distance Distance to go
	 * @return Something below 0 if there is enough space<br>The distance otherwise
	 */
	public float isEnoughSpaceInDirection(Entity entity, Direction direction, float distance) {
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
		}
		return -1;
	}

	public void close() {
		currentMaze.close();
	}
}