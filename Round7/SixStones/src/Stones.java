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
