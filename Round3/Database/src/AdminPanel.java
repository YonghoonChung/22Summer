import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AdminPanel extends RoundRect {
	
	JPanel displayPanel = new JPanel();
	JPanel titlePanel = new JPanel();
	JPanel infoPanel = new JPanel();
	JLabel titleLabel = new JLabel();
	static JLabel info1Label = new JLabel();
	static JLabel info2Label = new JLabel();
	JButton truncateButton = new JButton("Delete All Accounts");
	JButton logoutButton = new JButton("Logout");
	
	QueryFunctions query = new QueryFunctions();
	
	public AdminPanel(int x,int y,int width,int height) {
		super(x, y, width, height);
	}
	
	public void drawPanel() {
		this.setLayout(null);
		displayPanel.setBounds(20,20,660,300);
		displayPanel.setBackground(Color.WHITE);
		displayPanel.setOpaque(true);
		displayPanel.setLayout(null);
		
		titlePanel.setBounds(20,20,620,80);
		titlePanel.setLayout(new GridLayout(1,0,0,0));
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setText("ADMIN PAGE");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
		titleLabel.setOpaque(true);
		
		infoPanel.setBounds(20,100,620,178);
//		infoPanel.setBackground(Color.RED);
//		infoPanel.setOpaque(true);
		infoPanel.setLayout(new GridLayout(2,0,0,0));
		
		info1Label.setText("Daily Login Counts : "+ query.selectLoginLog());
		info1Label.setHorizontalAlignment(JLabel.CENTER);
		info1Label.setFont(new Font("Arial", Font.BOLD, 40));
		info2Label.setText("Total Users : " + query.countUsers());
		info2Label.setHorizontalAlignment(JLabel.CENTER);
		info2Label.setFont(new Font("Arial", Font.BOLD, 40));
//		infoPanel.setLayout(new GridLayout(2,1,0,0));
		
		infoPanel.add(info2Label);
		infoPanel.add(info1Label);
		titlePanel.add(titleLabel);
		
		truncateButton.setBounds(270,320,150,30);
		truncateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TruncatePopup tp = new TruncatePopup();
				tp.doShow();
			}
		});
		
		logoutButton.setBounds(270,360,150,30);
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.adminPanel.setVisible(false);
				Frame.rectArea.setVisible(true);
				Frame.userId.setText(null);
				Frame.userPw.setText(null);
			}
		});
		
		displayPanel.add(titlePanel);
		displayPanel.add(infoPanel);
		this.add(displayPanel);
		this.add(truncateButton);
		this.add(logoutButton);
	}
}
