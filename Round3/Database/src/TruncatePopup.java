import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TruncatePopup extends JFrame{
	JFrame fr = new JFrame("Forgot");
	JPanel alertPanel = new JPanel();
	JLabel alertLabel = new JLabel();
	JButton truncateConfirmButton = new JButton("Confirm");
	JButton truncateCancelButton = new JButton("Cancel");
	RoundRect rectArea;
	QueryFunctions query = new QueryFunctions();
	
	public void doShow() {
		fr.setSize(400, 360);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.WHITE);
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		int rectAreaWidth = 365;
		int rectAreaHeight = 300;
		rectArea = new RoundRect(1,0,rectAreaWidth-2,rectAreaHeight-1);
		rectArea.setBounds(10,10,rectAreaWidth,rectAreaHeight);
		rectArea.setLayout(null);
		alertPanel.setBounds(50,75,260,130);
		alertPanel.setBackground(Color.WHITE);
		alertPanel.setOpaque(true);
		alertPanel.setLayout(null);
		alertPanel.setLayout(new GridLayout(1,0,0,0));
		alertLabel.setText("<html><body><p align='center'>Are you sure?</p><p>You cannot bring it Back!</p></body></html>");
		alertLabel.setBackground(Color.WHITE);
		alertLabel.setOpaque(true);
		alertLabel.setHorizontalAlignment(JLabel.CENTER);
		alertLabel.setFont(new Font("Arial", Font.BOLD, 20));
		alertPanel.add(alertLabel);
		
		truncateConfirmButton.setBounds(75,240,80,30);
		truncateConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				query.truncate("tb_user");
				JOptionPane.showMessageDialog(null, "Successfully Deleted All the Account", "New Password",JOptionPane.PLAIN_MESSAGE);
				AdminPanel.info2Label.setText("Total Users : " + query.countUsers());
				fr.dispose();
			}
		});
		truncateCancelButton.setBounds(210,240,80,30);
		truncateCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
			}
		});
		rectArea.add(alertPanel);
		rectArea.add(truncateConfirmButton);
		rectArea.add(truncateCancelButton);
		fr.add(rectArea);
		fr.setVisible(true);
	}
}
