import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NewAccount extends JFrame {
	QueryFunctions query = new QueryFunctions();
	JFrame fr = new JFrame("Create New Account");
	Combo emailStr;
	RoundRect rectArea;
	int rectAreaWidth = 340;
	int rectAreaHeight = 450;
	boolean idValid = false;
	boolean passwordValid = false;
	ButtonGroup checkBoxGroup;

	
	
	public void doShow() {
		fr.setSize(400, 530);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.WHITE);
		
		rectArea = new RoundRect(1,0,rectAreaWidth-1,rectAreaHeight-1);
		rectArea.setBounds(20,20,rectAreaWidth,rectAreaHeight);
		rectArea.setLayout(null);
		
		//----------------ID
		rectArea.add(new Write("ID",55,20,57,17).label);
		Field idStr = new Field(130,14,200,30); 
		rectArea.add(idStr.field);
		
		Buttons dupCheckButton = new Buttons("check",10,15,50,70,30);
		Write dupCheck = new Write("", 100, 50, 200, 30);
		dupCheckButton.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String dC = idStr.field.getText();
				if(dC.isEmpty()) {
					return;
				}
				query.select(dC);
				if(QueryFunctions.count == 0) {					
					dupCheck.label.setText("Valid ID");
					idValid = true;
				}else {
					dupCheck.label.setText("Invalid ID");					
				}
			}
		});
		rectArea.add(dupCheckButton.button);
		rectArea.add(dupCheck.label);
		
		//----------------- password
		rectArea.add(new Write("Password",20,107,90,17).label);
		Field password = new Field(130,100,200,30); 
		
		rectArea.add(password.field);
		
		rectArea.add(new Write("Re-Password",5,155,122,17).label);
		Field rePassword = new Field(130,150,200,30); 
		rectArea.add(rePassword.field);
		
		Buttons checkPasswordButton = new Buttons("check",10,15,200,70,30);
		Write passCheck = new Write("", 100, 200, 200, 30);
		checkPasswordButton.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw1 = password.field.getText();
				String pw2 = rePassword.field.getText();
				
				if(pw1.isEmpty()||pw2.isEmpty()) {
					return;
				}
				if(pw1.equals(pw2)) {					
					passCheck.label.setText("Same Passwords");
					passwordValid = true;
				}else {
					passCheck.label.setText("Different Passwords");					
				}
			}
		});
		rectArea.add(checkPasswordButton.button);
		rectArea.add(passCheck.label);
		
		//---------------Name
		rectArea.add(new Write("Name",30,255,60,17).label);
		Field name = new Field(130,250,200,30);
		rectArea.add(name.field);
		
		//--------------Gender
		rectArea.add(new Write("Gender",23,305,70,17).label);		
		JCheckBox c1 = new JCheckBox("Male");
		c1.setBounds(130,300,60,20);
		c1.setBackground(Color.WHITE);
		JCheckBox c2 = new JCheckBox("Female");
		c2.setBounds(200,300,80,20);
		c2.setBackground(Color.WHITE);
		checkBoxGroup = new ButtonGroup();
		checkBoxGroup.add(c1);
		checkBoxGroup.add(c2);
		rectArea.add(c1);
		rectArea.add(c2);
		
		//---------------Email
		rectArea.add(new Write("Email",30,355,60,17).label);
		Field email = new Field(90,350,100,30);
		rectArea.add(email.field);
		rectArea.add(new Write("@",190,352,20,23).label);
		emailStr = new Combo();
		
		emailStr.combo.setBounds(212,350,120,30);
		rectArea.add(emailStr.combo);
		
		
		
		Buttons confirmButton = new Buttons("Confirm",10,260,400,70,30);
		confirmButton.button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validateChecker(idStr.field.getText(), password.field.getText(), name.field.getText(), email.field.getText()+"@"+emailStr.combo.getSelectedItem(), "Female")) {					
					query.insert(idStr.field.getText(), password.field.getText(), name.field.getText(), email.field.getText()+"@"+emailStr.combo.getSelectedItem(), "Female");
					JOptionPane.showMessageDialog(null, "Welcome.", "Success", JOptionPane.PLAIN_MESSAGE);
					fr.dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Not Access", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		rectArea.add(confirmButton.button);

		
		fr.add(rectArea);
		fr.setVisible(true);
	}
	
	boolean validateChecker(String id, String pw, String name, String email, String gender) {
		if(!passwordValid) {
			return false;
		}
		if(!idValid) {
			return false;
		}
		if(id.length()<=6) {
			return false;
		}
		if(!id.matches("[+-]?\\d*(\\.\\d+)?")&&!id.matches("^[a-zA-Z]*$")) {
			return false;
		}
		if(pw.length()<=6) {
			return false;
		}
		if(!pw.matches("[+-]?\\d*(\\.\\d+)?")&&!pw.matches("^[a-zA-Z]*$")) {
			return false;
		}
		if(!email.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$")) {
			return false;
		}

		return true;
		
	}
}
