import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends RoundRect {
	private String id = "";
	private String name = "";
	private String gender = "";
	private String email = "";
	
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
	QueryFunctions query = new QueryFunctions();
	
	public UserPanel(int x,int y,int width,int height) {
		super(x, y, width, height);
//		this.setBackground(Color.PINK);
//		this.setOpaque(true);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
		nameLabel.setText("User Name : " + name);
	}

	public void setGender(String gender) {
		this.gender = gender;
		genderLabel.setText("User Gender : " + gender);
	}

	public void setEmail(String email) {
		this.email = email;
		emailLabel.setText("User email : " + email);
	}

	public void drawPanel() {
		
		
		this.setLayout(null);
		info.setBounds(20,20,660,300);
		info.setBackground(Color.WHITE);
		info.setOpaque(true);
		info.setLayout(new GridLayout(0,1,0,0));
		
		nameLabel.setBackground(Color.WHITE);
//		nameLabel.setOpaque(true);
		nameLabel.setText(name);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 40));
		genderLabel.setBackground(Color.WHITE);
//		genderLabel.setOpaque(true);
		genderLabel.setText(gender);
		genderLabel.setHorizontalAlignment(JLabel.CENTER);
		genderLabel.setFont(new Font("Arial", Font.BOLD, 40));
		emailLabel.setBackground(Color.WHITE);
//		emailLabel.setOpaque(true);
		emailLabel.setText(email);
		emailLabel.setHorizontalAlignment(JLabel.CENTER);
		emailLabel.setFont(new Font("Arial", Font.BOLD, 30));
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
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(newPw.getText().isBlank()||newRePw.getText().isBlank()) {
					return;
				}
				if(!newPw.getText().equals(newRePw.getText())) {
					JOptionPane.showMessageDialog(null, "Type Same New Passwords", "Error", JOptionPane.ERROR_MESSAGE);	
					return;
				}
				if(newPw.getText().length()<6) {
					JOptionPane.showMessageDialog(null, "New PW should be longer than 6 letters", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(newPw.getText().matches("[+-]?\\d*(\\.\\d+)?")||newPw.getText().matches("^[a-zA-Z]*$")) {
					JOptionPane.showMessageDialog(null, "New PW should be contain at least 1 letter and 1 number", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(newPw.getText().equals(newRePw.getText())) {
					query.update(id, newPw.getText());
					JOptionPane.showMessageDialog(null, "Successfully Changed Password", "New Password", JOptionPane.PLAIN_MESSAGE);
					changePwPanel.setVisible(false);					
					changePW = false;
				}else {
					System.out.println("Æ²¸²");
				}
			}
		});
		
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
		removeConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				query.update(id, newPw.getText());
				query.delete(id);
				JOptionPane.showMessageDialog(null, "Successfully Delete Account", "New Password",
						JOptionPane.PLAIN_MESSAGE);
				removePanel.setVisible(false);
				remove = false;
				Frame.userPanel.setVisible(false);
				Frame.rectArea.setVisible(true);
				Frame.userId.setText(null);
				Frame.userPw.setText(null);
			}
		});
		removePanel.add(removeLabel);
		removePanel.add(removeConfirmButton);
		removePanel.setVisible(false);
		buttonPanel.add(removePanel);
		
		updateButton.setBounds(150,8,137,25);
		signOutButton.setBounds(290,8,85,25);
		removeButton.setBounds(378,8,133,25);
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!changePW) {
					changePwPanel.setVisible(true);
					changePW = true;
				}else {
					changePwPanel.setVisible(false);					
					changePW = false;
				}
			}
		});
		signOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.userPanel.setVisible(false);
				Frame.rectArea.setVisible(true);
				Frame.userPw.setText(null);
			}
		});
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!remove) {
					removePanel.setVisible(true);
					remove = true;
				}else {
					removePanel.setVisible(false);
					remove = false;
				}
			}
		});
		
		buttonPanel.add(updateButton);
		buttonPanel.add(signOutButton);
		buttonPanel.add(removeButton);
		this.add(buttonPanel);
		this.add(info);
	}
}