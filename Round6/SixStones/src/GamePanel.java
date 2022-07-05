import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	Queries query = new Queries();
	WinnerDetection winDetection = new WinnerDetection();
	Ellipse2D.Double ellipse[][] = new Ellipse2D.Double[20][20];
	static int[][] bwMatrix = new int[19][19];
	Stones stone;
	static int result = 0;

	Graphics2D g2;

	public void drawPanel() {
		for (int i = 0; i < bwMatrix.length; i++) { // --------> 이거 reset누르면 초기화되는지 확인 필요
			for (int j = 0; j < bwMatrix[0].length; j++) {
				bwMatrix[i][j] = -1;
			}
		}
		setBounds(290, 80, 620, 620);
		setBackground(new Color(0xFFCC66));
		setOpaque(true);
		addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Frame.finish) {
					return;
				}
				Frame.swapButton.setEnabled(false);
				if (Frame.blockCount == 0) {
					Frame.blockTextField.setText("0");
				}
				if (Memory.points.size() == 0) {
					Frame.blockButton.setEnabled(false);
				}
				for (int i = 0; i < 20; i++) {
					for (int j = 0; j < 20; j++) {
						if (ellipse[i][j].contains(e.getPoint())) {
							for (int j2 = 0; j2 < Memory.points.size(); j2++) { // 중복 판단
								if (Memory.points.get(j2).i == i && Memory.points.get(j2).j == j) {
									return;
								}
							}
							Color color;
							if (Memory.points.size() - Frame.blockCount == 0) { // 바둑돌 색상 설정
//								color = Color.BLACK;
//								bwMatrix[i][j] = 1;
								if(Frame.swap) {
									color = Color.WHITE;
									bwMatrix[i][j] = 2;
								}else {
									color = Color.BLACK;
									bwMatrix[i][j] = 1;
								}
							} else if (((Memory.points.size() + 1 - Frame.blockCount) / 2) % 2 == 0) {
//								color = Color.BLACK;
//								bwMatrix[i][j] = 1;
								if(Frame.swap) {
									color = Color.WHITE;
									bwMatrix[i][j] = 2;
								}else {
									color = Color.BLACK;
									bwMatrix[i][j] = 1;
								}
							} else {
//								color = Color.WHITE;
//								bwMatrix[i][j] = 2;
								if(Frame.swap) {
									color = Color.BLACK;
									bwMatrix[i][j] = 1;
								}else {
									color = Color.WHITE;
									bwMatrix[i][j] = 2;
								}
							}

							if (Memory.points.size() < Frame.blockCount) {// 스택에 넣기
								Memory.points.add(new Stones(i, j, color, false));
								bwMatrix[i][j] = 0;
							} else {
								Memory.points.add(new Stones(i, j, color, true));
							}

							result = winDetection.detectWinner(i, j);// 판정하기

							if (Memory.points.size() < Frame.blockCount) {
								Frame.bwLabel.setText("Setting Blocks");
							} else if (Memory.points.size() - Frame.blockCount == 0) {
								if(Frame.swap) {
									Frame.bwLabel.setText("WHITE's TURN");
								}
								else {
									Frame.bwLabel.setText("BLACK's TURN");
								}
							} else if (Memory.points.size() - Frame.blockCount == 1) {
								if(Frame.swap) {
									Frame.bwLabel.setText("BLACK's TURN");
								}
								else {
									Frame.bwLabel.setText("WHITE's TURN");
								}
							} else if (((Memory.points.size() + 1 - Frame.blockCount) / 2) % 2 == 0) {
								if(Frame.swap) {
									Frame.bwLabel.setText("WHITE's TURN");
								}
								else {
									Frame.bwLabel.setText("BLACK's TURN");
								}
							} else {
								if(Frame.swap) {
									Frame.bwLabel.setText("BLACK's TURN");
								}
								else {
									Frame.bwLabel.setText("WHITE's TURN");
								}
							}
						}
					}
				}

				repaint();
				if (result == 1) {
					System.out.println("Black Win");
					JLabel text = new JLabel(
							"<html><body><p align = 'center'>Black Won</p><p align = 'center'>Press Reset to Start Again</p></body></html>",
							JLabel.CENTER);
					text.setFont(new Font("Arial", Font.BOLD, 40));
					JOptionPane optionPane = new JOptionPane(text);
					JDialog dialog = optionPane.createDialog("");
					dialog.setModal(true);
					dialog.setVisible(true);
					Frame.finish = true;
				} else if (result == 2) {
					System.out.println("White Win");
					JLabel text = new JLabel(
							"<html><body><p align = 'center'>White Won</p><p align = 'center'>Press Reset to Start Again</p></body></html>",
							JLabel.CENTER);
					text.setFont(new Font("Arial", Font.BOLD, 40));
					JOptionPane optionPane = new JOptionPane(text);
					JDialog dialog = optionPane.createDialog("");
					dialog.setModal(true);
					dialog.setVisible(true);
					Frame.finish = true;
				}
				query.insert(result);
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int[] index = { 3, 9, 15 };
		g2 = (Graphics2D) g;
		for (int i = 0; i < 20; i++) {// 값 이니샬 라이징 + 세로줄 세팅
			for (int j = 0; j < 20; j++) {
				ellipse[i][j] = new Ellipse2D.Double(i * 600 / 18 - 25 / 2 + 10, j * 600 / 18 - 25 / 2 + 10, 25, 25);
			}
			g2.setColor(Color.BLACK);
			g2.drawLine(i * 600 / 18 + 10, 10, i * 600 / 18 + 10, 610);
			g2.drawLine(10, i * 600 / 18 + 10, 610, i * 600 / 18 + 10);
		}
		for (int i : index) {
			for (int j : index) {
				g2.fill(new Ellipse2D.Double(i * 600 / 18 - 10 / 2 + 10, j * 600 / 18 - 10 / 2 + 10, 10, 10));
			}
		}
		for (int i = 0; i < Memory.points.size(); i++) {
			int x = Memory.points.get(i).i;
			int y = Memory.points.get(i).j;
			if (i < Frame.blockCount) {
				g2.setColor(Color.RED);
				g2.fill(new Rectangle2D.Double(x * 600 / 18 - 25 / 2 + 10, y * 600 / 18 - 25 / 2 + 10, 25, 25));
			} else {
				g2.setColor(Color.BLACK);
				g2.draw(ellipse[x][y]);
				g2.setColor(Memory.points.get(i).color);
				g2.fill(ellipse[x][y]);
			}
		}

	}
}
