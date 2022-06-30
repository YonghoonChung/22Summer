import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalButtonUI;

public class Forgot extends JFrame{
	QueryFunctions query = new QueryFunctions();
	JFrame fr = new JFrame("Forgot");
	RoundRect rectArea;
	private boolean isId = false;
	
	public boolean isID() {
		return isId;
	}
	
	public void changeIsId(boolean isId) {
		this.isId = isId;
	}
	
	public void doShow() {
		fr.setSize(400, 480);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.WHITE);
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		int rectAreaWidth = 365;
		int rectAreaHeight = 420;
		rectArea = new RoundRect(1,0,rectAreaWidth-2,rectAreaHeight-1);
		rectArea.setBounds(10,10,rectAreaWidth,rectAreaHeight);
		rectArea.setLayout(null);
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(55,60,160,40);
		buttonPanel.setLayout(new GridLayout(1,2,0,0));
		
		JButton idButton = new JButton("ID");
		JButton pwButton = new JButton("PW");
		idButton.setUI (new MetalButtonUI () {//버튼 누를때 안나오게 하기
		    protected void paintButtonPressed (Graphics g, AbstractButton b) { }
		});
		pwButton.setUI (new MetalButtonUI () {//버튼 누를때 안나오게 하기
		    protected void paintButtonPressed (Graphics g, AbstractButton b) { }
		});
		if (isId) {
			idButton.setBackground(Color.LIGHT_GRAY);	
			pwButton.setBackground(Color.WHITE);

		} else {
			idButton.setBackground(Color.WHITE);
			pwButton.setBackground(Color.LIGHT_GRAY);	

		}
		
		idButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {	
		    	isId = !isId;
		    	idButton.setBackground(Color.LIGHT_GRAY);
		    	pwButton.setBackground(Color.WHITE);
//		    	JOptionPane.showMessageDialog(null, "Welcome.", "Success", JOptionPane.PLAIN_MESSAGE);
//		    	fr.dispose();
		    }
		});
		pwButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {	
		    	isId = !isId;
		    	
		    	idButton.setBackground(Color.WHITE);
		    	pwButton.setBackground(Color.LIGHT_GRAY);
//		    	JOptionPane.showMessageDialog(null, "Welcome.", "Success", JOptionPane.PLAIN_MESSAGE);
//		    	fr.dispose();
		    }
		});
		buttonPanel.add(idButton);
		buttonPanel.add(pwButton);
		
		rectArea.add(buttonPanel);
		HintTextField nameInput = new HintTextField(55,137,250,25,"Name"); 
		rectArea.add(nameInput);
		HintTextField emailInput = new HintTextField(55,187,250,25,"Email"); 
		rectArea.add(emailInput);
		
		Buttons confirmButton = new Buttons("Confirmed", 10, 225, 250, 80, 30);
		confirmButton.button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {	
		    	String userName = nameInput.getText();
		    	String userEmail = emailInput.getText();
		    	System.out.println(userName);
		    	System.out.println(userName.isEmpty());
				if (userName.equals("Name") || userEmail.equals("Email")) {
					if (isId) {
						JOptionPane.showMessageDialog(null, "Please Enter Your Name and Email", "ID Finding", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Please Enter Your Name and Email", "PW Finding", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
		    	
		    	query.select(userName, userEmail);
//		    	System.out.println(QueryFunctions.str);
		    	String str = QueryFunctions.str;
		    	System.out.println(str.isBlank());
		    	if(str.isBlank()) {
		    		if(isId) {
		    			JOptionPane.showMessageDialog(null, "Your Account Not Found" , "ID Finding", JOptionPane.ERROR_MESSAGE);		    			
		    		}
		    		else {
		    			JOptionPane.showMessageDialog(null, "Your Account Not Found" , "PW Finding", JOptionPane.ERROR_MESSAGE);		    			
		    		}
		    		return;
		    	}
				String[] ArraysStr = str.split(" ");
		    	if(isId) {
		    		JOptionPane.showMessageDialog(null, "Your Account ID is " + ArraysStr[0] , "ID Finding", JOptionPane.PLAIN_MESSAGE);
		    		fr.dispose();
		    		Frame.userId.setText(ArraysStr[0]);
		    	}else {
		    		JOptionPane.showMessageDialog(null, "Your Account PW is " + ArraysStr[1] , "PW Finding", JOptionPane.PLAIN_MESSAGE);
		    		fr.dispose();
		    		Frame.userPw.setText(ArraysStr[1]);
		    	}
		    }
		});
		rectArea.add(confirmButton.button);

		fr.add(rectArea);
		fr.setVisible(true);
	}
	


}
