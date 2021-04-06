package pacman.game.entities;

import java.awt.Color;
import java.awt.Graphics2D;

import pacman.app.Application;
import pacman.app.Clips;
import pacman.game.maze.pellets.Pellet;
import pacman.utils.Direction;

public class PacMan extends Entity {
	private static int MIN_ANGLE = 270, MAX_ANGLE = 360, ANIM_SPEED = 1000, SPEED = 96;
	private static float SIZE = 1.5f;
	private int angle, score;
	private boolean animationForward;
	private Direction nextDirection;
	
	public PacMan(int x, int y) {
		super("pacman", x, y, 16, 16);
		this.angle = MIN_ANGLE;
		this.score = 0;
	}

	@Override
	public void draw(Graphics2D brush) {
		brush.setColor(Color.yellow);
		float widthDifference = width-width*SIZE,
				heightDifference = height-height*SIZE;
		int resizedX = Math.round(x+widthDifference/2f),
			resizedY = Math.round(y+heightDifference/2f);
		if (direction != null) {
			int angle = Math.round(direction.toDegrees());
			int difference = 360-this.angle;
			brush.fillArc(resizedX, resizedY, Math.round(width*SIZE), Math.round(height*SIZE), angle+difference/2, this.angle);
		} else {
			brush.fillOval(resizedX, resizedY, Math.round(width*SIZE), Math.round(height*SIZE));
		}
	}
	
	@Override
	public void act(double delta) {
		super.act(delta);

		if (nextDirection != null) {
			if (nextDirection != direction && game.isEnoughSpaceInDirection(this, nextDirection, (float) (delta*SPEED)) < 0) {
				direction = nextDirection;
			}
		} else {
			direction = nextDirection;
		}
		
		if (direction != null) {
			float distance = game.isEnoughSpaceInDirection(this, direction, (float) (delta*SPEED));
			if (distance < 0) {
				switch (direction) {
				case DOWN:
					this.move(0, (float) (delta*SPEED));
					break;
				case LEFT:
					this.move((float) (-delta*SPEED), 0);
					break;
				case RIGHT:
					this.move((float) (delta*SPEED), 0);
					break;
				case UP:
					this.move(0, (float) (-delta*SPEED));
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
					Application.stopSound(Clips.move1);
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
			/*List<Pellet> tmpPellets = new ArrayList<Pellet>(game.getCurrentMaze().getPellets());
			for (Pellet pellet : tmpPellets) {
				if (Math.abs(this.x+this.width/2-pellet.getX()) < RANGE_OF_EATING &&
						Math.abs(this.y+this.height/2-pellet.getY()) < RANGE_OF_EATING) {
					pellet.act(this, delta);
					game.getCurrentMaze().getPellets().remove(pellet);
				}
			}
			
			List<PowerPellet> tmpPowerPellets = new ArrayList<PowerPellet>(game.getCurrentMaze().getPowerPellets());
			for (PowerPellet powerPellet : tmpPowerPellets) {
				if (Math.abs(this.x+this.width/2-powerPellet.getX()) < RANGE_OF_EATING &&
						Math.abs(this.y+this.height/2-powerPellet.getY()) < RANGE_OF_EATING) {
					powerPellet.act(this, delta);
					game.getCurrentMaze().getPowerPellets().remove(powerPellet);
				}
			}*/
		}
		
		if (animationForward) {
			this.angle += delta*ANIM_SPEED;
			if (this.angle >= MAX_ANGLE)
				this.animationForward = false;
		} else {
			this.angle -= delta*ANIM_SPEED;
			if (this.angle <= MIN_ANGLE)
				this.animationForward = true;
		}
	}
	
	@Override
	public void move(float x, float y) {
		super.move(x, y);
		if (!Clips.powerPellet.isActive()) {
			Application.playSound(Clips.move1, 1, false);
		}
	}
	
	@Override
	public void setDirection(Direction direction) {
		this.nextDirection = direction;
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
}
