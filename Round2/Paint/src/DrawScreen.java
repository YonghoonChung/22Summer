
import java.util.ArrayList;
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
	Point firstPointer = new Point(0, 0);
	Point secondPointer = new Point(0, 0);

	static Stack<Object> shapeMemory = new Stack<Object>();
	static Stack<Color> colorMemory = new Stack<Color>();
	static Stack<Integer> strokeMemory = new Stack<Integer>();
	static Stack<Object> shapeRedoMemory = new Stack<Object>();
	static Stack<Color> colorRedoMemory = new Stack<Color>();
	static Stack<Integer> strokeRedoMemory = new Stack<Integer>();
	Stack <Data> data= new Stack<Data>();
	
	

	static ArrayList<Line2D> sketchMemory = new ArrayList<Line2D>();
	static ArrayList<Object> sketchColorMemory = new ArrayList<Object>();
	static ArrayList<Integer> sketchStrokeMemory = new ArrayList<Integer>();



	Line2D.Double line;
	Rectangle2D.Double rectangle;
	Ellipse2D.Double ellipse;

	public DrawScreen() {

		setBackground(Color.WHITE);
		Frame.panel2.add(this);
		MyMouseListener listener = new MyMouseListener();

		addMouseListener(listener);
		addMouseMotionListener(listener);
		setBackground(Color.WHITE);
		Frame.panel2.add(this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 페인트호출
		Graphics2D g2 = (Graphics2D) g.create();

		if (shapeMemory.size() != 0) {
			for (int i = 0; i < shapeMemory.size(); i++) {
				g2.setColor(colorMemory.get(i));
				g2.setStroke(new BasicStroke(strokeMemory.get(i)));
				g2.draw((Shape) shapeMemory.get(i));
				data.add();
			}
			for (int i = 0; i < sketchMemory.size(); i++) {
				g2.setColor((Color) sketchColorMemory.get(i));
				System.out.println(sketchColorMemory.get(i));
				g2.setStroke(new BasicStroke(sketchStrokeMemory.get(i)));
				g2.draw(sketchMemory.get(i));
			}
		}
	}

	public class MyMouseListener extends MouseInputAdapter {
		public void mousePressed(MouseEvent e) {
			ColorChooser.colorChange = false;
			start = e.getPoint(); // 클릭한부분을 시작점으로
			firstPointer.setLocation(0, 0);
			secondPointer.setLocation(0, 0);

			firstPointer.setLocation(e.getX(), e.getY());
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
				shapeMemory.add(shape);
				g2.draw(shape);
				colorMemory.add(ColorChooser.color);
				strokeMemory.add(StrokeChooser.stroke);
				// memoryPrint();
			}
			if (Buttons.draw[1] == true) {
				shape = new Rectangle2D.Double(twoDx, twoDy, absX, absY);
				g2.draw(shape);
				shapeMemory.add(shape);
				colorMemory.add(ColorChooser.color);
				strokeMemory.add(StrokeChooser.stroke);
				// memoryPrint();
			}
			if (Buttons.draw[2] == true) {
				shape = new Ellipse2D.Double(twoDx, twoDy, absX, absY);
				g2.draw(shape);
				shapeMemory.add(shape);
				colorMemory.add(ColorChooser.color);
				strokeMemory.add(StrokeChooser.stroke);
				// memoryPrint();
			}
			
			shapeRedoMemory.clear();
			colorRedoMemory.clear();
			strokeRedoMemory.clear();
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
			}
			if (Buttons.draw[1] == true) {
				repaint();
				g2.drawRect(twoDx, twoDy, absX, absY);
			}
			if (Buttons.draw[2] == true) {
				repaint();
				g2.drawOval(twoDx, twoDy, absX, absY);
			}
			if (Buttons.draw[3] == true) {// sketch
				if (secondPointer.x != 0 && secondPointer.y != 0) {
					firstPointer.x = secondPointer.x;
					firstPointer.y = secondPointer.y;
				}
				secondPointer.setLocation(e.getX(), e.getY());
				// updatePaint();
				g2.setColor(ColorChooser.color);
				g2.setStroke(new BasicStroke(StrokeChooser.stroke));
				line = new Line2D.Double(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
				g2.draw(line);
				sketchMemory.add(line);
				sketchColorMemory.add(ColorChooser.color);
				sketchStrokeMemory.add(StrokeChooser.stroke);
				// memory.add(sketchMemory);
				// color.add(sketchColorMemory);
				// stroke.add(sketchStrokeMemory);
				// sketchMemory.clear();
				// sketchColorMemory.clear();
				// sketchStrokeMemory.clear();
			}
			if(Buttons.erase == true) {
				System.out.println("Hello");
				if (secondPointer.x != 0 && secondPointer.y != 0) {
					firstPointer.x = secondPointer.x;
					firstPointer.y = secondPointer.y;
				}
				secondPointer.setLocation(e.getX(), e.getY());
				g2.setColor(Color.WHITE);
				g2.setStroke(new BasicStroke(30));
				line = new Line2D.Double(firstPointer.x, firstPointer.y, secondPointer.x, secondPointer.y);
				g2.draw(line);
				sketchMemory.add(line);
				sketchColorMemory.add(Color.WHITE);
				sketchStrokeMemory.add(30);
			}
		}

		public void mouseMoved(MouseEvent e) {
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
