package pacman.game.entities;

import java.awt.Graphics2D;

import pacman.game.maze.classic.pellets.Pellet;
import pacman.utils.Direction;
import pacman.utils.Spritesheet;

public class PacMan extends Entity {
	protected static int DEATH_ANIMATION_INDEX = 5;
	public static final int SPEED = 96;
	private int speed, score, lives;
	private boolean dead;
	private Direction nextDirection;
	
	public PacMan(int x, int y) {
		super("pacman", x, y, 16, 16, new Spritesheet("assets/classicmaze/pacman.png", 6, 1, 4, 4, 4, 4, 11));
		this.score = 0;
		this.lives = 3;
		this.speed = SPEED;
	}

	@Override
	public void draw(Graphics2D brush) {
		if (dead) {
			
		} else {
			brush.setRenderingHints(getGame().noAntialiasingRH);
			super.draw(brush);
			brush.setRenderingHints(getGame().antialiasingRH);
		}
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);

		if (nextDirection != null) {
			if (nextDirection != direction && game.isEnoughSpaceInDirection(this, nextDirection, (float) (delta*speed*getGame().getSpeed().getSpeedFactor()), false) < 0) {
				direction = nextDirection;
			}
		} else {
			direction = nextDirection;
		}
		
		if (direction != null) {
			float distance = game.isEnoughSpaceInDirection(this, direction, (float) (delta*speed*getGame().getSpeed().getSpeedFactor()), false);
			if (distance < 0) {
				switch (direction) {
				case DOWN:
					this.move(0, (float) (delta*speed*getGame().getSpeed().getSpeedFactor()));
					break;
				case LEFT:
					this.move((float) (-delta*speed*getGame().getSpeed().getSpeedFactor()), 0);
					break;
				case RIGHT:
					this.move((float) (delta*speed*getGame().getSpeed().getSpeedFactor()), 0);
					break;
				case UP:
					this.move(0, (float) (-delta*speed*getGame().getSpeed().getSpeedFactor()));
					break;
				}
			} else {
				if (distance > 0) {
					switch (direction) {
					case DOWN:
						this.move(0, distance);
						break;
					case LEFT:
						this.move(-distance, 0);
						break;
					case RIGHT:
						this.move(distance, 0);
						break;
					case UP:
						this.move(0, -distance);
						break;
					}
				} else {
					direction = null;
				}
			}
			try {
				Pellet pellet = game.getCurrentMaze().getPellets()[(int) ((x+width/2)/(float) game.getCurrentMaze().getTileSize())][(int) ((y+height/2)/(float) game.getCurrentMaze().getTileSize())];
				if (pellet != null) {
					pellet.act(this, delta);
					game.getCurrentMaze().getPellets()[(int) ((x+width/2)/(float) game.getCurrentMaze().getTileSize())][(int) ((y+height/2)/(float) game.getCurrentMaze().getTileSize())] = null;
					game.getCurrentMaze().removePellet();
				}
			} catch (ArrayIndexOutOfBoundsException e) {}
		}
	}
	
	@Override
	public void move(float x, float y) {
		super.move(x, y);
	}
	
	@Override
	public void setDirection(Direction direction) {
		if (direction != nextDirection && direction != this.direction) {
			this.animationFrame = 0;
			this.nextDirection = direction;
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int value) {
		this.score += value;
	}
	
	public void subScore(int value) {
		this.score -= value;
	}
	
	public void addLife() {
		this.lives++;
	}
	
	public void removeLife() {
		this.lives--;
	}
	
	public int getLives() {
		return lives;
	}
}
