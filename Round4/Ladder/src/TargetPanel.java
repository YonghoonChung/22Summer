import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TargetPanel extends JPanel{

	Graphics g = getGraphics();
	Graphics2D g2 = (Graphics2D) g;
	private int noPlayer = 0;
	private int noBranch = 0;
	static JLabel[] targetLabel;
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

	public TargetPanel() {
		setBounds(75,560,940,20); //900,400 pixel 크기
		setBackground(Color.WHITE);
		setOpaque(true);
		setLayout(null);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 페인트호출
		Graphics2D g2 = (Graphics2D) g.create();
//		System.out.println(Ladder.targetCount);
		targetLabel = new JLabel[noPlayer];
		for (int i = 0; i < noPlayer; i++) {
			targetLabel[i] = new JLabel();
			targetLabel[i].setBackground(Color.RED);
			targetLabel[i].setHorizontalAlignment(JLabel.CENTER);
			targetLabel[i].setOpaque(true);
			targetLabel[i].setBounds(i*(900/(noPlayer-1)),0,40,20);
			this.add(targetLabel[i]);	
		}
	}
	
}