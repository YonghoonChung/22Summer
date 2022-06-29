import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Buttons extends JButton{
	public JButton button;
	
	public Buttons(String s, int size, int x, int y, int w, int h) {
		button = new JButton(s);
		button.setBounds(x, y, w, h);
		button.setFont(new Font("Dialog", Font.PLAIN, size));
		button.setHorizontalAlignment(JButton.CENTER);
		button.setVerticalAlignment(JLabel.CENTER);
	}
}