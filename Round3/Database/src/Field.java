import java.awt.Font;

import javax.swing.JTextField;

public class Field extends JTextField{
	public JTextField field;
	String str="";
	public Field(int x, int y, int w, int h) {
		field = new JTextField("");
		field.setFont(new Font("Dialog", Font.PLAIN, 20));
		field.setBounds(x, y, w, h);
		
	}
	public Field(int x, int y, int w, int h,String s) {
		field = new JTextField("");
		field.setText(s);
		field.setFont(new Font("Dialog", Font.PLAIN, 20));
		field.setBounds(x, y, w, h);
		
	}
	public void setText(String s){
		field.setText(s);
	}
}
