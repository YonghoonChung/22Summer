import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Canvas;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class Frame extends JFrame implements KeyListener{
	// class private variable
	private static String name;
	static JFrame fr = new JFrame(name);
	Buttons buttons = new Buttons();
	JPanel panel = new JPanel();
	static JPanel panel2 = new JPanel();
	static JPanel controlScreenPanel = new JPanel();
	static JLabel screen1 = new JLabel();
	static JLabel screen2 = new JLabel();

	public Frame(String name) { // variable 불러온 후 class variable과 연동
		this.name = name;
	}

	public void doFrame() {
		// fr.setTitle(name);
		fr.setSize(1440, 800);
		fr.setLocationRelativeTo(null);
		// fr.setLayout(new GridLayout(2, 1,5,5));
		fr.setLayout(null);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		// DrawScreen drawing =new DrawScreen();
		panel.setLayout(null);
		panel.setBounds(0, 0, 1440, 100);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(1, 2, 10, 10));
		panel2.setBounds(0, 100, 1440, 700);
		panel2.setLayout(new GridLayout(1, 0, 5, 0));
		panel2.setBackground(Color.BLACK);

		screen1.setText("Yonghoon");
		screen1.setOpaque(true);
		screen1.setBackground(Color.RED);
		screen1.setHorizontalAlignment(JLabel.CENTER);
		screen2.setOpaque(true);
		screen2.setText("asdfasdf");
		screen2.setHorizontalAlignment(JLabel.CENTER);
		
		
		controlScreenPanel.setLayout(new GridLayout(2, 1));
		controlScreenPanel.setBorder(new TitledBorder(new LineBorder(Color.RED, 6), "Mode Status"));
		controlScreenPanel.setBackground(Color.WHITE);
		controlScreenPanel.add(screen1);
		controlScreenPanel.add(screen2);

		for (int i = 0; i < 7; i++) {
			panel.add(buttons.buttons[i]);
			if (i == 3)
				panel.add(controlScreenPanel);
		}
		fr.add(panel2);
		fr.add(panel);
		

		fr.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("123");
		// TODO Auto-generated method stub
		System.out.println(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("123");
		// TODO Auto-generated method stub
		System.out.println(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("123");
		// TODO Auto-generated method stub
		System.out.println(e);
	}
}
