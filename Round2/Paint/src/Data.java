
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Data {
	Object shape;
	Color color;
	private int stroke;
	ArrayList<Point> sketch;
	boolean strokeType;

	public Data(Object shape, Color color, int stroke, boolean strokeStyle) {
		this.shape = shape;
		this.color = color;
		this.stroke = stroke;
		this.sketch = null;
		this.strokeType = strokeStyle;
	}

	public Data(ArrayList<Point> ske, Color color, int stroke, boolean strokeStyle) {
		this.shape = null;
		this.color = color;
		this.stroke = stroke;
		this.sketch = ske;
		this.strokeType = strokeStyle;
//		for (int i = 0; i < sketch.size(); i++) {
//			System.out.println(sketch.get(i));
//		}
		//
//		for (int i = 0; i < ske.size(); i++)
//			this.sketch.add(ske.get(i));
	}

	public int getStroke() {
		return stroke;
	}

	public void setStroke(int stroke) {
		this.stroke = stroke;
	}
	
}
