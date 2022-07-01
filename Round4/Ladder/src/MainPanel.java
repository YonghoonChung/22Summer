import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.BasicStroke;

public class MainPanel extends JPanel{
	Graphics g = getGraphics();
	Graphics2D g2 = (Graphics2D) g;
	private int noPlayer = 0;
	private int noBranch = 0;
	
	static int[] mapping;
	static int[] remapping;
	static int[] branch;
//	int d = 900/(noPlayer -1);
//	int h = 400/(noBranch + 1);
	int d;
	int h;
	
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

	public MainPanel() {
		setBounds(95,160,900,400); //900,400 pixel 크기
		setBackground(Color.PINK);
		setOpaque(true);
		setLayout(null);
		setVisible(true);
		
//		for (int j = 0; j < branch.length; j++) {
//			branch[j]=(int)(Math.random()*(noPlayer-1));
//			int temp = mapping[branch[j]];
//			mapping[branch[j]] = mapping[branch[j]+1];
//			mapping[branch[j]+1] = temp;			
//		}
	    
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // 부모 페인트호출
		Graphics2D g2 = (Graphics2D) g.create();
		
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(2));
		d = 900/(noPlayer -1);
		h = 400/(noBranch + 1);

		for (int i = 0; i < noPlayer; i++) {//값 이니샬 라이징 + 세로줄 세팅
			g2.setColor(Color.BLACK);
			g2.drawLine(i*(900/(noPlayer-1)),0,i*(900/(noPlayer-1)),400);	
			if(Ladder.isResetOkay) {
				mapping[i]=i;				
			}
		}
		
		for (int i = 0; i < noBranch; i++) {//랜덤으로 선 긋기 준비 + 선 긋기
//			setRandomBranch();
			if (Ladder.isResetOkay) {
				branch[i] = (int) (Math.random() * (noPlayer - 1));
				int temp = mapping[branch[i]];
				mapping[branch[i]] = mapping[branch[i] + 1];
				mapping[branch[i] + 1] = temp;				
			}
			g2.setColor(Color.BLACK);
			g2.drawLine(branch[i]*d, i*h+h,branch[i]*d+d,i*h+h);
			
		}
		Ladder.isResetOkay = false;
		for (int i = 0; i < noPlayer; i++) {
			remapping[mapping[i]]=i;
		}
//		for (int i = 0; i < branch.length; i++) {
//			System.out.println(branch[i]);
//		}
		if (Ladder.isDrawLinesExist == true) {
//			System.out.println("hello");
			for (int i = 0; i < noPlayer; i++) {
				int startIndex = i;
				if (NamePanel.drawLinesTF[i] == true) {
					System.out.println(startIndex);
					g2.setStroke(new BasicStroke(10));
					g2.setColor(Color.RED);
					for (int j = 0; j < branch.length; j++) {
						g2.drawLine(startIndex*(900/(noPlayer-1)),h*j,startIndex*(900/(noPlayer-1)),h+j*h);
						if(branch[j] == startIndex) {
							g2.drawLine(startIndex*(900/(noPlayer-1)),j*h+h,(startIndex+1)*(900/(noPlayer-1)),j*h+h);
							startIndex++;
						}
						else if(branch[j] == startIndex -1) {
							g2.drawLine((startIndex-1)*(900/(noPlayer-1)),j*h+h,(startIndex)*(900/(noPlayer-1)),j*h+h);
							startIndex--;
						}
					}
						g2.drawLine(startIndex*(900/(noPlayer-1)),400-h,startIndex*(900/(noPlayer-1)),400);
				}
			}
		}

		for (int i = 0; i < noPlayer; i++) {
			System.out.print(remapping[i]+" ");
		}System.out.println("\n--------------");
	}
}
