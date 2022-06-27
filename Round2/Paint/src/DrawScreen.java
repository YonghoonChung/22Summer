
import java.util.ArrayList;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawScreen extends JPanel {
	Point start, end;
	Point a = new Point(0, 0);
	Point b = new Point(0, 0);

	static Stack<Data> memo = new Stack<Data>();

	static ArrayList<Point> sketchMemory;
	public static Stack<Data> redoMemory = new Stack<Data>();
	public static Stack<Data> allClearMemory = new Stack<Data>();

	Line2D.Double line;
	Rectangle2D.Double rectangle;
	Ellipse2D.Double ellipse;

	static JPanel buttonPanel1 = new JPanel();
	static JPanel buttonPanel2 = new JPanel();
	static JButton strokeButton[] = new JButton[2];
	static JButton shapeButton[] = new JButton[3];
	float[] dash = new float[] { 10, 5, 5, 5 };

	Timer timer;
	TimerTask task;
	static int showTextPanel = -1;
	static boolean strokeStyleFlag = true;
	static String strokeStyleName = "Line";

	public DrawScreen() {
		String strokeButtonNames[] = { "Stroke Size : " + StrokeChooser.stroke, "Stroke Style - 1" };

		setBackground(Color.WHITE);
		Frame.panel2.add(this);
		MyMouseListener listener = new MyMouseListener();

		addMouseListener(listener);
		ColorChooser.color = Color.BLACK;
		addMouseMotionListener(listener);
		setBackground(Color.WHITE);
		this.setLayout(null);
		buttonPanel2.setBounds(775, 0, 325, 60);

		buttonPanel2.setBounds(775, 0, 325, 60);
		buttonPanel2.setLayout(null);
		buttonPanel2.setLayout(new GridLayout(1, 2, 10, 10));
		buttonPanel2.setOpaque(true);
		buttonPanel2.setVisible(false);
//		buttonPanel.setBackground(Color.BLACK);
		for (int i = 0; i < strokeButton.length; i++) {
			strokeButton[i] = new JButton();
			strokeButton[i].setBackground(Color.GRAY);
			strokeButton[i].setText(strokeButtonNames[i]);
			strokeButton[i].setFont(new Font("Arial", Font.BOLD, 16));
			strokeButton[i].setForeground(Color.WHITE);
			strokeButton[i].setUI (new MetalButtonUI () {//버튼 누를때 안나오게 하기
			    protected void paintButtonPressed (Graphics g, AbstractButton b) { }
			});
			strokeButton[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource().equals(strokeButton[0])) {
						new StrokeChooser();
					} else {
						strokeStyleFlag = !strokeStyleFlag;
						if (strokeStyleFlag) {
							strokeButton[1].setText("Stroke Style - 1");
							strokeStyleName = "Line";
							Buttons.buttons[5].setText(DrawScreen.strokeStyleName + " : " + StrokeChooser.stroke);
						} else {
							strokeButton[1].setText("Stroke Style - 2");
							strokeStyleName = "Dotted";
							Buttons.buttons[5].setText(DrawScreen.strokeStyleName + " : " + StrokeChooser.stroke);

						}
					}
				}
			});
			buttonPanel2.add(strokeButton[i]);
		}

		this.add(buttonPanel2);
		this.add(buttonPanel1);
		Frame.panel2.add(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 페인트호출
		Graphics2D g2 = (Graphics2D) g.create();
		if (!memo.isEmpty())
			for (int i = 0; i < memo.size(); i++) {
				g2.setColor(memo.get(i).color);
				if (memo.get(i).strokeType) {
					g2.setStroke(new BasicStroke(memo.get(i).getStroke(), BasicStroke.CAP_ROUND, 0));
				} else {
					g2.setStroke(new BasicStroke(memo.get(i).getStroke(), 0, BasicStroke.JOIN_MITER, 1.0f, dash, 0));
				}
				if (memo.get(i).shape == null) {
					for (int j = 0; j < memo.get(i).sketch.size() - 1; j++) {
						g2.drawLine(memo.get(i).sketch.get(j).x, memo.get(i).sketch.get(j).y,
								memo.get(i).sketch.get(j + 1).x, memo.get(i).sketch.get(j + 1).y);
					}
				} else {
					g2.draw((Shape) memo.get(i).shape);

				}
			}
	}

	public class MyMouseListener extends MouseInputAdapter {
		int count = 0;

		public void mousePressed(MouseEvent e) {
			if (Buttons.draw[0] || Buttons.draw[1] || Buttons.draw[2] || Buttons.draw[3]) {
				allClearMemory.clear();
			}
			ColorChooser.colorChange = false;
			start = e.getPoint(); // 클릭한부분을 시작점으로
			a.setLocation(0, 0);
			b.setLocation(0, 0);

			a.setLocation(e.getX(), e.getY());
			sketchMemory = new ArrayList<Point>();
			repaint();

		}

		public void mouseReleased(MouseEvent e) {
			Shape shape;
			end = e.getPoint(); // 드래그 한부분을 종료점으로
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(ColorChooser.color);
//			g2.setStroke(new BasicStroke(StrokeChooser.stroke));
			if (strokeStyleFlag) {
				g2.setStroke(new BasicStroke(StrokeChooser.stroke, BasicStroke.CAP_ROUND, 0));
			} else {
				g2.setStroke(new BasicStroke(StrokeChooser.stroke, 0, BasicStroke.JOIN_MITER, 1.0f, dash, 0));
			}
			int twoDx = Math.min(start.x, end.x);
			int twoDy = Math.min(start.y, end.y);
			int absX = Math.abs(start.x - end.x);
			int absY = Math.abs(start.y - end.y);
			if (Buttons.draw[0]) {
				shape = new Line2D.Double(start.x, start.y, end.x, end.y);
				// g2.draw(shape);
				memo.add(new Data(shape, ColorChooser.color, StrokeChooser.stroke, strokeStyleFlag));
				repaint();
			}
			if (Buttons.draw[1]) {
				shape = new Rectangle2D.Double(twoDx, twoDy, absX, absY);
				// g2.draw(shape);
				memo.add(new Data(shape, ColorChooser.color, StrokeChooser.stroke, strokeStyleFlag));
				repaint();
			}
			if (Buttons.draw[2]) {
				shape = new Ellipse2D.Double(twoDx, twoDy, absX, absY);
				// g2.draw(shape);
				memo.add(new Data(shape, ColorChooser.color, StrokeChooser.stroke, strokeStyleFlag));
				repaint();
			}
			if (Buttons.draw[3]) {
				memo.add(new Data(sketchMemory, ColorChooser.color, StrokeChooser.stroke, strokeStyleFlag));

				repaint();
				// sketchMemory.clear();
			}
			if (Buttons.erase) {
				memo.add(new Data(sketchMemory, Color.WHITE, 30, true));
			}

			redoMemory.clear();
		}

		public void mouseDragged(MouseEvent e) {

			end = e.getPoint();
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(ColorChooser.color);

			if (strokeStyleFlag) {
				g2.setStroke(new BasicStroke(StrokeChooser.stroke));
			} else {
				g2.setStroke(new BasicStroke(5, 0, BasicStroke.JOIN_MITER, 1.0f, dash, 0));
			}
			int twoDx = Math.min(start.x, end.x);
			int twoDy = Math.min(start.y, end.y);
			int absX = Math.abs(start.x - end.x);
			int absY = Math.abs(start.y - end.y);
			if (Buttons.draw[0] == true) {
				repaint();
				g2.drawLine(start.x, start.y, end.x, end.y);
				return;
			}
			if (Buttons.draw[1] == true) {
				repaint();
				g2.drawRect(twoDx, twoDy, absX, absY);
				return;
			}
			if (Buttons.draw[2] == true) {
				repaint();
				g2.drawOval(twoDx, twoDy, absX, absY);
				return;
			}
			if (Buttons.draw[3] == true) {// sketch
				if (b.x != 0 && b.y != 0) {
					a.x = b.x;
					a.y = b.y;
				}
				b.setLocation(e.getX(), e.getY());
				g2.drawLine(a.x, a.y, b.x, b.y);
				sketchMemory.add(new Point(e.getX(), e.getY()));
			}
			if (Buttons.erase == true) { // 지우개
				if (b.x != 0 && b.y != 0) {
					a.x = b.x;
					a.y = b.y;
				}
				b.setLocation(e.getX(), e.getY());
				g2.drawLine(a.x, a.y, b.x, b.y);
				sketchMemory.add(new Point(e.getX(), e.getY()));
				// g2.setBorder(new TitledBorder(new LineBorder(Color.RED, 6), "Mode Status"));
				g2.setColor(Color.WHITE);
				g2.setStroke(new BasicStroke(30));
				g2.drawLine(a.x, a.y, b.x, b.y);

				sketchMemory.add(new Point(e.getX(), e.getY()));
			}
		}

		public void mouseMoved(MouseEvent e) { // 지우개
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			if (Buttons.erase == true) {
				repaint();
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke(30));
				line = new Line2D.Double(e.getPoint().x, e.getPoint().y, e.getPoint().x, e.getPoint().y);
				g2.draw(line);

			}
		}
	}
}
