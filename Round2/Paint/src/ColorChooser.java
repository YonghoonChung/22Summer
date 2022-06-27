import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorChooser extends JFrame implements ChangeListener{
	JColorChooser colorChooser = new JColorChooser();
	

	static boolean colorChange;
	static Color color;
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		color = colorChooser.getColor();
		colorChange = true;
		//System.out.print("\""+Integer.toHexString(color.getRGB())+"\", ");
		System.out.println(color.WHITE);
		Buttons.buttons[4].setBackground(color);
	}
	
	public ColorChooser(){
		setTitle("색상 고르기");
		setLocation(400, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		colorChooser.getSelectionModel().addChangeListener(this);
		
		add(colorChooser);
		pack();
		setVisible(true);
	}

}
