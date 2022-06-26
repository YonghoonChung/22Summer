
import java.awt.Color;
import java.util.ArrayList;
import java.awt.geom.Line2D;

public class Data {
	Object s;
	Color c;
	int stroke;
	ArrayList<Line2D.Double> sketch;
	
	public Data(Object s, Color c, int stroke) {
		this.s =s;
		this.c= c;
		this.stroke = stroke;
		this.sketch = null;
	}
	public Data(ArrayList<Line2D.Double> sketch, Color c, int stroke) {
		this.s =null;
		this.c= c;
		this.stroke = stroke;
		this.sketch = sketch;
	}
	
}
