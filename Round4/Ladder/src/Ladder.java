import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ladder extends JFrame{
	JFrame fr = new JFrame();
	static MainPanel mainPanel = new MainPanel();
	NamePanel namePanel = new NamePanel();
	TargetPanel targetPanel = new TargetPanel();
	JLabel noPlayerLabel = new JLabel();
	JPanel controlPanel = new JPanel();
	JPanel RadioPanel = new JPanel();
	JButton enterButton = new JButton("Enter");
	JButton resetButton = new JButton("Reset");
	JButton resultButton = new JButton("Result");
	JCheckBox c1 = new JCheckBox("name");
	JCheckBox c2 = new JCheckBox("target");
	JTextField controlField = new JTextField();
	ButtonGroup checkBoxGroup = new ButtonGroup();
	
	private int width = 1100;
	private int height = 700;
	private int noPlayer = 0;
	private int noBranch;
	private boolean inputStyle = true;
	static boolean isDrawLinesExist = false;
	static boolean isResetOkay = true;
	static int nameCount = 0;
	static int targetCount = 0;
	
	static ArrayList<String> name = new ArrayList<String>();
	static ArrayList<String> target = new ArrayList<String>();


	
	public void doFrame(){
		String msg =JOptionPane.showInputDialog(this, "Enter Player Number");
		noPlayer = Integer.parseInt(msg);
		String msg2 =JOptionPane.showInputDialog(this, "Enter Branch Number");
		noBranch = Integer.parseInt(msg2);
		
		
		fr.setSize(width, height);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.WHITE);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);
		fr.setVisible(true);
		fr.setLayout(null);

		
		
		noPlayerLabel.setBounds(120,25,350,60);
		noPlayerLabel.setText("Total Players : ");
		noPlayerLabel.setHorizontalAlignment(JLabel.CENTER);
		noPlayerLabel.setFont(new Font("Arial", Font.BOLD, 40));
		noPlayerLabel.setBackground(Color.WHITE);
		noPlayerLabel.setOpaque(true);
		noPlayerLabel.setVisible(true);
		
		controlPanel.setBounds(500,5,460,90);
		controlPanel.setBackground(Color.WHITE);
		controlPanel.setOpaque(true);
		controlPanel.setLayout(null);
		
		controlField.setBounds(50,17,250,50);
		controlField.setFont(new Font("Arial", Font.BOLD, 20));
		controlField.setBackground(Color.WHITE);
		controlField.setOpaque(true);
		
		c1.setBounds(300,20,60,15);
		c1.setBackground(Color.WHITE);
		c1.setOpaque(true);
		c1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				inputStyle = true;
				System.out.println("name");
			}
		});
		c2.setBounds(300,40,60,15);
		c2.setBackground(Color.WHITE);
		c2.setOpaque(true);
		c2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				inputStyle = false;
				System.out.println("target");
			}
		});

		checkBoxGroup.add(c1);
		checkBoxGroup.add(c2);
		
		enterButton.setBounds(375,0,65,90);
		enterButton.setBackground(Color.RED);
		enterButton.setOpaque(true);
		enterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(controlField.getText().isBlank()) {
					return;
				}
				if(inputStyle && name.size() < noPlayer) {
					name.add(controlField.getText());
					NamePanel.nameButton[nameCount].setText(controlField.getText());
					nameCount++;
				}else if(!inputStyle && target.size() < noPlayer) {
//					System.out.println("Å¬¸¯");
					target.add(controlField.getText());
					targetPanel.targetLabel[targetCount].setText(controlField.getText());
					targetCount++;
				}
				controlField.setText("");
			}
		});
		
		
		
		controlPanel.add(enterButton);
		controlPanel.add(c1);
		controlPanel.add(c2);
		controlPanel.add(controlField);
		
		resultButton.setBounds(950,600,90,30);
		resultButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultPanel rp = new ResultPanel();
				if(name.size() != noPlayer || target.size() != noPlayer) {
					return;
				}
				

				rp.doFrame();
			}
		});
		resetButton.setBounds(850,600,90,30);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isResetOkay = true;
				mainPanel.repaint();
			}
		});
		
		fr.add(resetButton);
		fr.add(resultButton);
		fr.add(controlPanel);
		fr.add(noPlayerLabel);
		fr.add(targetPanel);
		fr.add(namePanel);
		fr.add(mainPanel);
		
		
		MainPanel.mapping = new int[noPlayer];
		MainPanel.remapping = new int[noPlayer];
		MainPanel.branch = new int[noBranch];
		
		mainPanel.setNoPlayer(noPlayer);
		mainPanel.setNoBranch(noBranch);
		
		namePanel.setNoPlayer(noPlayer);
		namePanel.setNoBranch(noBranch);
		
		targetPanel.setNoPlayer(noPlayer);
		targetPanel.setNoBranch(noBranch);
		
		noPlayerLabel.setText("Total Players : " + noPlayer);
		mainPanel.setNoPlayer(noPlayer);
		targetPanel.repaint();
		namePanel.repaint();
		mainPanel.repaint();
		
		fr.add(mainPanel);
		fr.add(namePanel);
		fr.add(targetPanel);
		

//		mainPanel.repaint();

		
		
		
	}
}
