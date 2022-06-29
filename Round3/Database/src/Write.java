import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Write extends JLabel{
	public JLabel label;
	
	public Write(String s, int x, int y, int w, int h) {
		label = new JLabel(s);
		label.setBounds(x, y, w, h);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setFont(new Font("Dialog", Font.PLAIN, 20));
		if(s.length() > 15) label.setHorizontalAlignment(JLabel.CENTER);
		else label.setHorizontalAlignment(JLabel.LEFT);
		label.setVerticalAlignment(JLabel.CENTER);
//		label.setBackground(Color.RED);
	}
}
