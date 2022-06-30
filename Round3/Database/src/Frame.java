import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.metal.MetalButtonUI;

import java.awt.Color;
import java.awt.Font;

public class Frame extends JFrame{
	private int width = 1100;
	private int height = 700;
	private boolean Id;
	public void setId(boolean id) {
		Id = id;
	}
	public boolean isId() {
		return Id;
	}
	private String name;
	
	JFrame fr = new JFrame("Login");
	JPanel mainPanel = new JPanel();
	JPanel subPanel1 = new JPanel();
	JLabel logo = new JLabel();
	
	JPanel subPanel2 = new JPanel();
	JPanel idPwPanel = new JPanel();
	JPanel idPanel = new JPanel();
	JPanel pwPanel = new JPanel();
	static JTextField userId = new HintTextField(" ID");
	static JPasswordField userPw = new HintPasswordField(" PW");
	JPanel loginButtonPanel = new JPanel();
	JButton loginButton = new JButton("Login");
	
	JPanel subPanel3 = new JPanel();
	JButton idSearchButton = new JButton("Forgot ID");
	JButton pwSearchButton = new JButton("Forgot PW");
	JButton createAccountButton = new JButton("Create New Account");
	static RoundRect rectArea;
	static UserPanel userPanel;
	static AdminPanel adminPanel;
	
	
	QueryFunctions query = new QueryFunctions();
	
	public void doFrame() {
		// 메인프레임
		fr.setSize(width, height);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.WHITE);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//메인 패널
		rectArea = new RoundRect(1,0,699,449);
		rectArea.setBounds(200,100,700,450);
		rectArea.setLayout(null);
//		rectArea.setVisible(false);
		
		userPanel = new UserPanel(1,0,699,449);
		userPanel.setBounds(200,100, 700, 450);
		userPanel.setVisible(false);
		adminPanel= new AdminPanel(1,0,699,449);
		adminPanel.setBounds(200,100, 700, 450);
		adminPanel.setVisible(false);	

		
		//서브 패널 1
		subPanel1 = new RoundRect(3,3,695,110,false);
		subPanel1.setBounds(0,0,700,113);
		subPanel1.setLayout(null);
		logo.setHorizontalAlignment(JLabel.CENTER);
		logo.setText("YOUNG~~~~~");
		logo.setBounds(0,0,700,100);
		logo.setFont(new Font("Arial", Font.BOLD, 40));
		subPanel1.add(logo);
		
		//서브패널2
		subPanel2 = new RoundRect(3,3,699,224,false);
		subPanel2.setBounds(0,113,700,225);
		subPanel2.setLayout(null);
		idPwPanel.setBounds(0,0,520,224); // 너비 520 , 높이 224
		idPwPanel.setLayout(null);
		idPanel.setBounds(0,0,520,112);
		idPanel.setLayout(new GridLayout(1,0,0,0));
		idPanel.add(userId);
		userId.setColumns(10);
		userId.setHorizontalAlignment(JTextField.CENTER);
		
		pwPanel.setBounds(0,112,520,112);
		pwPanel.setBackground(Color.RED);
		pwPanel.setLayout(new GridLayout(1,0,0,0));
		pwPanel.add(userPw);
		userPw.setHorizontalAlignment(JTextField.CENTER);
		
		idPwPanel.add(idPanel);
		idPwPanel.add(pwPanel);
		
		loginButtonPanel.setBounds(520,0,180,224);
		loginButtonPanel.setLayout(new GridLayout(1,0,0,0));
		loginButtonPanel.add(loginButton);
		
		loginButton.setFont(new Font("Arial", Font.BOLD, 40));
		loginButton.setBackground(Color.LIGHT_GRAY);
		loginButton.setUI (new MetalButtonUI () {//버튼 누를때 안나오게 하기
		    protected void paintButtonPressed (Graphics g, AbstractButton b) { }
		});
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userId.getText().equals("admin") && userPw.getText().equals("1234")) {
					JOptionPane.showMessageDialog(null, "HELLO ADMIN", "Y2K Project", JOptionPane.PLAIN_MESSAGE);
					rectArea.setVisible(false);
					adminPanel.setVisible(true);
				}else {
					String arr[];
					arr = query.selectLogin(userId.getText(),userPw.getText());
					if(arr[0] == null) {
						JOptionPane.showMessageDialog(null, "Please Enter the Correct ID/PW", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(QueryFunctions.loginSuccess == 1){
						JOptionPane.showMessageDialog(null, "HELLO "+userId.getText() , "Y2K Project", JOptionPane.PLAIN_MESSAGE);
						userPanel.setName(arr[0]);
						userPanel.setGender(arr[1]);
						userPanel.setEmail(arr[2]);
						userPanel.setId(arr[3]);
						query.insertLoginLog(userId.getText());
						rectArea.setVisible(false);//user보이기
						userPanel.setVisible(true);
					
					}else{
						JOptionPane.showMessageDialog(null, "Please Enter the Correct ID/PW", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		loginButtonPanel.setOpaque(true);
		
		subPanel2.add(idPwPanel);
		subPanel2.add(loginButtonPanel);
		
		//서브패널3
		subPanel3 = new RoundRect(3,0,699,112,false);
		subPanel3.setBounds(0,338,700,113);
		
		idSearchButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				// 준비됨
				System.out.println("ID 찾기 클릭");
				setId(true);
				System.out.println(Id);
				Forgot fg = new Forgot();
				fg.changeIsId(Id);
				fg.doShow();
//				rectArea.setVisible(false);//user보이기
//				userPanel1.setVisible(true);
			}
		});
		pwSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 준비됨
				System.out.println("PW 찾기 클릭");
				setId(false);
				System.out.println(Id);
				Forgot fg = new Forgot();
				fg.doShow();
//				rectArea.setVisible(true);
//				userPanel1.setVisible(false);
				
			}
		});
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 준비됨
				System.out.println("계정 생성 클릭");
				NewAccount na = new NewAccount();
				na.doShow();
			}
		});
		subPanel3.add(idSearchButton);
		subPanel3.add(pwSearchButton);
		subPanel3.add(createAccountButton);
		rectArea.add(subPanel1);
		rectArea.add(subPanel2);
		rectArea.add(subPanel3);
		//------------------------userPanel
		userPanel.drawPanel();
		//------------------------adminPanel
		adminPanel.drawPanel();
		
		fr.add(adminPanel);
		fr.add(userPanel);
		fr.add(rectArea);
		fr.setVisible(true);
		
	}
}
