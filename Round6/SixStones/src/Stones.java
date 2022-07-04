import java.awt.Color;

public class Stones {
	int i;
	int j;
	Color color;
	boolean valid;
	int count = 0;
	
	public Stones(int x, int y, Color color) {
		this.i = x;
		this.j = y;
		this.color = color;
	}
	public Stones(int x, int y, Color color, boolean valid) {
		this.i = x;
		this.j = y;
		this.color = color;
		this.valid = valid;
		if(valid) {
			this.count++;
		}
	}
}


/*
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
//	for (int i = 0; i < sketch.size(); i++) {
//		System.out.println(sketch.get(i));
//	}
	//
//	for (int i = 0; i < ske.size(); i++)
//		this.sketch.add(ske.get(i));
}
*/