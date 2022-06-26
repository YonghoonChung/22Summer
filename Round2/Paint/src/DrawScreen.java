
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

public class DrawScreen extends JPanel {
	Point start, end;
	Point a = new Point(0, 0);
	Point b = new Point(0, 0);


	static Stack<Data> memo = new Stack<Data>();

	static ArrayList<Point> sketchMemory;
	public static Stack<Data> redoMemory = new Stack<Data>();

	Line2D.Double line;
	Rectangle2D.Double rectangle;
	Ellipse2D.Double ellipse;

	public DrawScreen() {

		setBackground(Color.WHITE);
		Frame.panel2.add(this);
		MyMouseListener listener = new MyMouseListener();

		addMouseListener(listener);
		ColorChooser.color = Color.BLACK;
		addMouseMotionListener(listener);
		setBackground(Color.WHITE);
		Frame.panel2.add(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 페인트호출
		Graphics2D g2 = (Graphics2D) g.create();

//		if (shapeMemory.size() != 0) {
//			for (int i = 0; i < shapeMemory.size(); i++) {
////				g2.setColor(colorMemory.get(i));
////				g2.setStroke(new BasicStroke(strokeMemory.get(i)));
////				g2.draw((Shape) shapeMemory.get(i));
//			}
//			for (int i = 0; i < sketchMemory.size(); i++) {
//				g2.setColor((Color) sketchColorMemory.get(i));
//				g2.setStroke(new BasicStroke(sketchStrokeMemory.get(i)));
//				g2.draw(sketchMemory.get(i));
//			}
//		}
		if (!memo.isEmpty())
			for (int i = 0; i < memo.size(); i++) {
				//System.out.println(memo.get(i).shape);
				// System.out.println(i+ " "+ memo.get(i));
				g2.setColor(memo.get(i).color);
				g2.setStroke(new BasicStroke(memo.get(i).getStroke()));
				if (memo.get(i).shape == null) {
					for (int j = 0; j < memo.get(i).sketch.size()-1; j++) {
						//System.out.println(memo.get(i).sketch.get(j).x + " " + memo.get(i).sketch.get(j).y);
						g2.drawLine(memo.get(i).sketch.get(j).x, memo.get(i).sketch.get(j).y, memo.get(i).sketch.get(j+1).x, memo.get(i).sketch.get(j+1).y);
					}
				} else {
					g2.draw((Shape) memo.get(i).shape);
					
				}
					System.out.println(memo.get(i).color);
			}
		System.out.println("---------------------------");
	}

	public class MyMouseListener extends MouseInputAdapter {
		int count = 0;
		public void mousePressed(MouseEvent e) {
			
			ColorChooser.colorChange = false;
			start = e.getPoint(); // 클릭한부분을 시작점으로
			a.setLocation(0, 0);
			b.setLocation(0, 0);

			a.setLocation(e.getX(), e.getY());
			sketchMemory = new ArrayList<Point>();
		}

		public void mouseReleased(MouseEvent e) {
			Shape shape;
			end = e.getPoint(); // 드래그 한부분을 종료점으로
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(ColorChooser.color);
			float[] dash = new float[] { 10, 5, 5, 5 };
			// g2.setStroke(new BasicStroke(5,0,BasicStroke.JOIN_MITER,1.0f,dash, 0));
			g2.setStroke(new BasicStroke(StrokeChooser.stroke));
			int twoDx = Math.min(start.x, end.x);
			int twoDy = Math.min(start.y, end.y);
			int absX = Math.abs(start.x - end.x);
			int absY = Math.abs(start.y - end.y);
			if (Buttons.draw[0] == true) {
				shape = new Line2D.Double(start.x, start.y, end.x, end.y);
				//g2.draw(shape);
				memo.add(new Data(shape, ColorChooser.color, StrokeChooser.stroke));
				repaint();
			}
			if (Buttons.draw[1] == true) {
				shape = new Rectangle2D.Double(twoDx, twoDy, absX, absY);
				//g2.draw(shape);
				memo.add(new Data(shape, ColorChooser.color, StrokeChooser.stroke));
				repaint();
			}
			if (Buttons.draw[2] == true) {
				shape = new Ellipse2D.Double(twoDx, twoDy, absX, absY);
				//g2.draw(shape);
				memo.add(new Data(shape, ColorChooser.color, StrokeChooser.stroke));
				repaint();
			}
			if (Buttons.draw[3] == true) {
				memo.add(new Data(sketchMemory, ColorChooser.color, StrokeChooser.stroke));

				repaint();
				//sketchMemory.clear();
			}
			if (Buttons.erase == true) {
				memo.add(new Data(sketchMemory, Color.WHITE, 30));
				
			}

			redoMemory.clear();
		}

		public void mouseDragged(MouseEvent e) {

			end = e.getPoint();
			Graphics g = getGraphics();
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(ColorChooser.color);
			float[] dash = new float[] { 10, 5, 5, 5 };
			// g2.setStroke(new BasicStroke(5,0,BasicStroke.JOIN_MITER,1.0f,dash, 0));
			g2.setStroke(new BasicStroke(StrokeChooser.stroke));
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
				sketchMemory.add(new Point(e.getX(),e.getY()));
			}
			if (Buttons.erase == true) { // 지우개
				if (b.x != 0 && b.y != 0) {
					a.x = b.x;
					a.y = b.y;
				}
				b.setLocation(e.getX(), e.getY());
				g2.drawLine(a.x, a.y, b.x, b.y);
				sketchMemory.add(new Point(e.getX(),e.getY()));
				g2.setColor(Color.WHITE);
				g2.setStroke(new BasicStroke(30));
				g2.drawLine(a.x, a.y, b.x, b.y);
				
				sketchMemory.add(new Point(e.getX(),e.getY()));
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
