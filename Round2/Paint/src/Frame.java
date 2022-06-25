import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Canvas;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

@SuppressWarnings("serial")
public class Frame extends JFrame{
	//class private variable
	private static String name;
	static JFrame fr = new JFrame(name);
	JPanel panel = new JPanel();
	static JPanel panel2 = new JPanel();
	Buttons buttons = new Buttons();
	
	public Frame(String name) { // variable 불러온 후 class variable과 연동
		this.name = name;
	}
	
	public void doFrame() {
		//fr.setTitle(name);
		fr.setSize(1440, 800);
		fr.setLocationRelativeTo(null);
		//fr.setLayout(new GridLayout(2, 1,5,5));
		fr.setLayout(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//DrawScreen drawing =new DrawScreen();
		panel.setLayout(null);
		panel.setBounds(0,0,1440,100);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 2,10,10));
		panel2.setBounds(0,100,1440,700);
		panel2.setLayout(new GridLayout(1,0,5,0));		
		panel2.setBackground(Color.BLACK);
		
		for (int i = 0; i < 9; i++) {
			panel.add(buttons.buttons[i]);
		}
		
		fr.add(panel2);
		fr.add(panel);
		
		fr.setVisible(true);
	}
}
