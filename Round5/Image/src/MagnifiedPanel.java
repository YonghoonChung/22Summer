import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MagnifiedPanel extends JPanel {
	DeepCopy dCopy = new DeepCopy();

	private int mouseX = 0;
	private int mouseY = 0;
	private boolean scope;

	BufferedImage temp;
	BufferedImage croppedTemp;

	public void draw() {
//		setBounds(0, 0, 100, 100);
//		setBackground(Color.ORANGE);
		setOpaque(false);
		setVisible(false);
	}

	public void setScope(boolean scope) {
		this.scope = scope;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(3));
		g2.draw(new Ellipse2D.Double(0, 0, 30, 30));
	}

	
}
