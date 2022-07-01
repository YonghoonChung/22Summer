import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NamePanel extends JPanel{

	Graphics g = getGraphics();
	Graphics2D g2 = (Graphics2D) g;
	private int noPlayer = 0;
	private int noBranch = 0;
//	JButton[] button;
	static JButton[] nameButton;
	static boolean[] drawLinesTF;
	public int getNoBranch() {
		return noBranch;
	}

	public void setNoBranch(int noBranch) {
		this.noBranch = noBranch;
	}

	public int getNoPlayer() {
		return noPlayer;
	}

	public void setNoPlayer(int noPlayer) {
		this.noPlayer = noPlayer;
	}

	public NamePanel() {
		setBounds(60,130,970,30); //900,400 pixel 크기
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(null);
		setVisible(true);
		
		

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 페인트호출
		Graphics2D g2 = (Graphics2D) g.create();
		
//		JButton[] button = new JButton[noPlayer];
		nameButton = new JButton[noPlayer];
		drawLinesTF = new boolean[noPlayer];
//		g2.setColor(Color.BLACK);
//		g2.setStroke(new BasicStroke(2));
		for (int i = 0; i < noPlayer; i++) {
			nameButton[i] = new JButton();
			nameButton[i].setBackground(Color.RED);
			nameButton[i].setHorizontalAlignment(JLabel.CENTER);			
			nameButton[i].setOpaque(true);
			nameButton[i].setBounds(i*(900/(noPlayer-1)),0,70,30);
			nameButton[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < nameButton.length; j++) {
						if(e.getSource() == nameButton[j]) {
							setInit();
							drawLinesTF[j] = true;
							Ladder.isResetOkay = false;
							Ladder.isDrawLinesExist = true;
							Ladder.mainPanel.repaint();
						}
					}
				}

				private void setInit() {
					// TODO Auto-generated method stub
					for (int j = 0; j < drawLinesTF.length; j++) {
						drawLinesTF[j] = false;
					}
				}
			});
			this.add(nameButton[i]);
		}
	}
	
}