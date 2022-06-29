import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundRect extends JPanel {
	int w;
	int h;
	int s;
	int e;
	boolean fill = true;

	public RoundRect(int x, int y) {
		super();
		decorate();
		w = x;
		h = y;
		s = 0;
		e = 0;
	}

	public RoundRect(int s, int e, int x, int y) {
		super();
		decorate();
		w = x;
		h = y;
		this.s = s;
		this.e = e;
	}

	public RoundRect(int s, int e, int x, int y, boolean b) {
		super();
		decorate();
		w = x;
		h = y;
		this.s = s;
		this.e = e;
		this.fill = b;
		// TODO Auto-generated constructor stub
	}

	protected void decorate() {
		setOpaque(false);

	}

	protected void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (fill) {
			graphics.setColor(new Color(200, 200, 200));
		} else {
			graphics.setColor(new Color(255, 255, 255));
		}
		graphics.drawRoundRect(s, e, w, h, 10, 10);

		super.paintComponent(g);
	}
}