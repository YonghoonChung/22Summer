import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultPanel extends JFrame {
	JFrame fr = new JFrame("Result");
	JLabel resultLabel = new JLabel();
	JLabel titleLabel = new JLabel();
	
	int tempNoPlayer = 5;
	String tempA[] = {"하나","둘","삼","넷","오",};
	String temptarget[] = {"보상1","보상2","보상3","보상4","보상5"};
	String printString = "<html><body>";
	
	public void doFrame() {
		fr.setSize(400, 700);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.WHITE);
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
//		for (int i = 0; i < MainPanel.mapping.length; i++) {
//			System.out.println(MainPanel.mapping[i]);
////			System.out.println(Ladder.name.get(i));
//		}
		
		for (int i = 0; i < MainPanel.mapping.length; i++) {
			printString += "<p align = 'center'>"+Ladder.name.get(i)+" - "+Ladder.target.get(MainPanel.remapping[i])+"</p>";
		}
		printString += "</body></html>";
		
		titleLabel.setBounds(0,0,400,100);
		titleLabel.setBackground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setText("Final Result");
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
		
		resultLabel.setBounds(0,100,400,600);
		resultLabel.setBackground(Color.RED);
		resultLabel.setOpaque(true);
		resultLabel.setHorizontalAlignment(JLabel.CENTER);
		if(tempA.length<10) {
			resultLabel.setFont(new Font("Arial", Font.BOLD, 40));			
		}else if(tempA.length<30) {
			resultLabel.setFont(new Font("Arial", Font.BOLD, 30));						
		}
		resultLabel.setText(printString);
		
		
		fr.add(titleLabel);
		fr.add(resultLabel);
		fr.setVisible(true);
	}
}
