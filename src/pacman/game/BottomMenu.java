package pacman.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import pacman.app.Application;

public class BottomMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private RenderingHints rh;
	
	public BottomMenu() {
		setPreferredSize(new Dimension(WIDTH, 64));
		setBackground(Color.black);
		
		rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g.create();
		brush.setRenderingHints(rh);
		
		brush.translate((getWidth()-Application.getGame().getCurrentMaze().getWidth())/2, 0);
		
		brush.setColor(Color.yellow);
		brush.fillArc(8, 8, 48, 48, 45, 270);
		
		brush.setColor(Color.white);
		brush.setFont(new Font("arial", Font.BOLD, 48));
		brush.drawString("\u00D7"+Application.getPlayer().getLives(), 56, 48);
	}
}
