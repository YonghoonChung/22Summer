import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class Buttons extends JButton implements MouseListener {
	public static JButton[] buttons = new JButton[9];
	static boolean[] draw = new boolean[4]; // 라인인지,
	static boolean erase = false;
	static boolean lastButton = false; //false -> shape, true -> sketch
	boolean flag = false;

	String buttonNames[] = { "Line", "Rectangle", "Circle", "Sketch", "Color", "Stroke", "Erase", "Undo", "Redo" };

	public Buttons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			if (i == 5) {
				buttons[i].setText("Stroke : " + StrokeChooser.stroke);
			}
			buttons[i].setVisible(true);
			buttons[i].addMouseListener(this);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			buttons[i].setBackground(Color.GRAY);
			buttons[i].setForeground(Color.WHITE);
			// decorate();
		}
		setInit();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JButton button = (JButton) e.getSource();
		String str = button.getText();
		if (button == buttons[0]) { // line
			setInit();
			draw[0] = true;
			buttons[0].setBorderPainted(true);

		}
		if (button == buttons[1]) { // Rectangle
			setInit();
			draw[1] = true;
			buttons[1].setBorderPainted(true);
			// print();
		}
		if (button == buttons[2]) { // Circle
			setInit();
			draw[2] = true;
			buttons[2].setBorderPainted(true);
		}
		if (button == buttons[3]) { // Sketch
			setInit();
			draw[3] = true;
			buttons[3].setBorderPainted(true);
		}
		if (button == buttons[4]) { // Color
			new ColorChooser();
		}
		if (button == buttons[5]) { // Stroke
			new StrokeChooser();
		}
		if (button == buttons[6]) { // Erase
			setInit();
			erase = true;
		}
		if (button == buttons[7]) { // Undo
//			setInit();
			undo();
		}
		if (button == buttons[8]) { // redo
			redo();
		}
		Frame.screen2.setText(str);

	}
	private void redo() {
		if(!lastButton) {
			if(!DrawScreen.memo.isEmpty()) {
			DrawScreen.memo.add(DrawScreen.redoMemory.pop());
			Frame.panel2.repaint();
			}
		}
	}

	private void undo() {
		// TODO Auto-generated method stub
		if(!lastButton) {
			if(!DrawScreen.memo.isEmpty()) {
				DrawScreen.redoMemory.add(DrawScreen.memo.pop());
				Frame.panel2.repaint();
			}
		}
	}

	public void setInit() {
		// TODO Auto-generated method stub
		for (int i = 0; i < draw.length; i++) {
			draw[i] = false;
			erase = false;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}
}
