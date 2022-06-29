import java.awt.Color;
import javax.swing.*;

import java.awt.event.*;

public class Combo extends JComboBox<String> implements ActionListener{
	public JComboBox<String> combo;
	private String toolcombo[] = {"gmail.com", "naver.com", "hotmail.com","yahoo.com","nate.com"};
	public static String comboButtonStr;
	
	public Combo() {
		combo = new JComboBox<>();
		
		for(int i = 0; i<5; i++) {
			combo.addItem(toolcombo[i]);
		}
//		combo.setOpaque(true);
		
		combo.setBackground(Color.WHITE);
		combo.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		comboButtonStr = combo.getSelectedItem().toString();
		System.out.println(comboButtonStr);
	}
}
