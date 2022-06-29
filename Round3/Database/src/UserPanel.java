import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends RoundRect {
	JPanel info = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel changePwPanel = new JPanel();
	JPanel removePanel = new JPanel();
	JLabel nameLabel = new JLabel();
	JLabel genderLabel = new JLabel();
	JLabel emailLabel = new JLabel();
	JLabel removeLabel = new JLabel();
	JButton updateButton = new JButton("Change Password");
	JButton signOutButton = new JButton("Sign Out");
	JButton removeButton = new JButton("Remove Account");
	JButton removeConfirmButton = new JButton("Confirm");
	JButton changeButton = new JButton("Confirm");
	HintTextField newPw = new HintTextField("New Password");
	HintTextField newRePw = new HintTextField("New Re-Password");
	private boolean changePW = false;
	private boolean remove = false;
	
	public UserPanel(int x,int y,int width,int height) {
		super(x, y, width, height);
//		this.setBackground(Color.PINK);
//		this.setOpaque(true);
	}
	public void drawPanel() {
		
		
		this.setLayout(null);
		info.setBounds(20,20,660,300);
		info.setBackground(Color.RED);
		info.setOpaque(true);
		info.setLayout(new GridLayout(0,1,0,0));
		
		nameLabel.setBackground(Color.PINK);
		nameLabel.setOpaque(true);
		nameLabel.setText("Name : ");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 40));
		genderLabel.setBackground(Color.BLUE);
		genderLabel.setOpaque(true);
		genderLabel.setText("Gender : ");
		genderLabel.setHorizontalAlignment(JLabel.CENTER);
		genderLabel.setFont(new Font("Arial", Font.BOLD, 40));
		emailLabel.setBackground(Color.GREEN);
		emailLabel.setOpaque(true);
		emailLabel.setText("Email : ");
		emailLabel.setHorizontalAlignment(JLabel.CENTER);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 40));
		info.add(nameLabel);
		info.add(genderLabel);
		info.add(emailLabel);
		
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(20,320,660,110);
		buttonPanel.setOpaque(true);
		changePwPanel.setBounds(5,33,282,73);
//		changePwPanel.setBackground(Color.RED);
//		changePwPanel.setOpaque(true);
		changePwPanel.setLayout(null);
		newPw.setBounds(3,3,200,35);
		newRePw.setBounds(3,38,200,34);
		newPw.setFont(new Font("Arial", Font.BOLD, 20));
		newRePw.setFont(new Font("Arial", Font.BOLD, 20));
		changeButton.setBounds(205,3,74,68);
		changeButton.setFont(new Font("Arial", Font.BOLD, 10));
		
		changePwPanel.add(newPw);
		changePwPanel.add(newRePw);
		changePwPanel.add(changeButton);
		changePwPanel.setVisible(false);
		buttonPanel.add(changePwPanel);
		
		
		removePanel.setBounds(378,33,276,73);
		removePanel.setLayout(null);
		removeLabel.setBounds(3,12,170,49);
		removeLabel.setText("Are you sure?");
		removeLabel.setHorizontalAlignment(JLabel.CENTER);
		removeLabel.setFont(new Font("Arial", Font.BOLD, 20));
		removeConfirmButton.setBounds(180,30,90,17);
		removePanel.add(removeLabel);
		removePanel.add(removeConfirmButton);
		buttonPanel.add(removePanel);
		
		updateButton.setBounds(150,8,137,25);
		signOutButton.setBounds(290,8,85,25);
		removeButton.setBounds(378,8,133,25);
		
		updateButton.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!changePW) {
					changePwPanel.setVisible(true);
				}else {
					changePwPanel.setVisible(false);					
				}
			}
		});
		signOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.userPanel1.setVisible(false);
				Frame.rectArea.setVisible(true);
			}
		});
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		buttonPanel.add(updateButton);
		buttonPanel.add(signOutButton);
		buttonPanel.add(removeButton);
		this.add(buttonPanel);
		this.add(info);
	}
}