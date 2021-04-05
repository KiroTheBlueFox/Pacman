package pacman.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import pacman.app.Application;
import pacman.game.entities.Entity;
import pacman.game.maze.Maze;
import pacman.utils.Direction;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Entity> actors;
	private Maze currentMaze;
	private RenderingHints rh;

	public Game() {
    	this.actors = new ArrayList<Entity>(); 
    	
        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    }
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g.create();
        brush.setRenderingHints(rh);
        
        brush.setColor(Color.black);
        brush.fillRect(0, 0, (int) getWidth(), (int) getHeight());

        brush.setColor(Color.blue);
        if (currentMaze != null) {
        	currentMaze.draw(brush);
        	if (Application.debug)
        		currentMaze.drawDebug(brush);
        	
    		brush.setColor(Color.white);
        	currentMaze.getPellets().forEach((pellet) -> {
        		pellet.draw(brush);
        	});
        	
        	currentMaze.getPowerPellets().forEach((powerPellet) -> {
        		powerPellet.draw(brush);
        	});
        }

        brush.setColor(Color.white);
        this.actors.forEach((actor) -> {
        	actor.draw(brush);
        	if (Application.debug)
        		actor.drawDebug(brush);
        });
        
        brush.setFont(new Font("arial", Font.BOLD, 16));
        brush.drawString("Score : "+Application.getPlayer().getScore(), 8, getHeight()-8);
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
	}

	/**
	 * Detects if the Entity can move in said direction of said distance
	 * @param entity The entity from which to detect collisions
	 * @param direction Direction to go
	 * @param distance Distance to go
	 * @return Something below 0 if there is enough space<br>The distance otherwise
	 */
	public int isEnoughSpaceInDirection(Entity entity, Direction direction, int distance) {
		int playerLeft = entity.getX(),
			playerRight = entity.getX()+entity.getWidth(),
			playerUp = entity.getY(),
			playerDown = entity.getY()+entity.getHeight();
		for (Rectangle wall : currentMaze.getWalls()) {
			switch (direction) {
			case DOWN:
				if (wall.y < playerDown+distance && wall.y > playerUp+distance &&
						((playerLeft > wall.x && playerLeft < wall.x+wall.width) ||
						(playerRight > wall.x && playerRight < wall.x+wall.width) ||
						(playerRight >= wall.x+wall.width && playerLeft <= wall.x)))
					return wall.y-playerDown;
				break;
			case LEFT:
				if (wall.x+wall.width > playerLeft-distance && wall.x+wall.width < playerRight-distance &&
						((playerUp > wall.y && playerUp < wall.y+wall.height) ||
						(playerDown > wall.y && playerDown < wall.y+wall.height) ||
						(playerDown >= wall.y+wall.height && playerUp <= wall.y)))
					return playerLeft-(wall.x+wall.width);
				break;
			case RIGHT:
				if (wall.x < playerRight+distance && wall.x > playerLeft+distance &&
						((playerUp > wall.y && playerUp < wall.y+wall.height) ||
						(playerDown > wall.y && playerDown < wall.y+wall.height) ||
						(playerDown >= wall.y+wall.height && playerUp <= wall.y)))
					return wall.x-playerRight;
				break;
			case UP:
				if (wall.y+wall.height > playerUp-distance && wall.y+wall.height < playerDown-distance &&
						((playerLeft > wall.x && playerLeft < wall.x+wall.width) ||
						(playerRight > wall.x && playerRight < wall.x+wall.width) ||
						(playerRight >= wall.x+wall.width && playerLeft <= wall.x)))
					return playerUp-(wall.y+wall.height);
				break;
			default:
				break;
			}
		}
		return -1;
	}
}