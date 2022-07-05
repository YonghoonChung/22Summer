import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
//\*

public class Frame extends JFrame{
	JFrame fr = new JFrame();
	JPanel buttonPanel = new JPanel();
	JPanel blockPanel = new JPanel();
	GamePanel gamePanel = new GamePanel();
	static JLabel bwLabel = new JLabel();
	JLabel blockLabel = new JLabel();
	static JTextField blockTextField = new JTextField();
	static JButton blockButton = new JButton("Confirm");
	JButton resetButton = new JButton("Reset");
	static JButton swapButton = new JButton("Swap");
	JButton undoButton = new JButton("Undo");
	
	static int blockCount = 0;
	static boolean finish = false;
	static boolean swap = false;
	
	public void doFrame() {
		fr.setSize(1200,750);
		fr.setLocationRelativeTo(null);
		fr.setLayout(null);
		fr.getContentPane().setBackground(Color.LIGHT_GRAY);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);
		
		fr.setLayout(null);
		buttonPanel.setBounds(100,25,1000,50);
		buttonPanel.setBackground(Color.RED);
		buttonPanel.setLayout(null);
		resetButton.setBounds(5,5,70,40);
		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Memory.points.clear();
				bwLabel.setText("BLACK's TURN");
				blockButton.setEnabled(false);
				swapButton.setEnabled(true);
				gamePanel.repaint();
				finish = false;
				blockCount = 0;
				blockTextField.setText("");
				for (int i = 0; i < GamePanel.bwMatrix.length; i++) { // --------> 이거 reset누르면 초기화되는지 확인 필요
					for (int j = 0; j < GamePanel.bwMatrix[0].length; j++) {
						GamePanel.bwMatrix[i][j] = -1;
					}
				}
			}
		});
		
		bwLabel.setBounds(80,5,840,40);
		bwLabel.setBackground(Color.PINK);
		bwLabel.setOpaque(true);
		bwLabel.setHorizontalAlignment(JLabel.CENTER);
		bwLabel.setFont(new Font("Arial", Font.BOLD, 55));
		bwLabel.setText("BLACK's TURN");

		swapButton.setBounds(925,5,70,40); //swap 버튼
		swapButton.setBackground(Color.BLACK); // 처음에는 흑으로 시작 -> 흑 false
		swapButton.setForeground(Color.WHITE);
		swapButton.setEnabled(true);
		swapButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(swap) { 
					swapButton.setBackground(Color.BLACK);
					swapButton.setForeground(Color.WHITE);
					swap = false;
				}else {
					swapButton.setBackground(Color.WHITE);
					swapButton.setForeground(Color.BLACK);
					swap = true;
				}
			}
		});

		gamePanel.drawPanel();
		
		blockPanel.setBounds(930,80,200,185);
		blockPanel.setBackground(Color.GREEN);
		blockPanel.setOpaque(true);
		blockPanel.setLayout(null);
		
		blockLabel.setBounds(5,5,190,40);
		blockLabel.setBackground(Color.RED);
		blockLabel.setOpaque(true);
		blockLabel.setText("Block Counts (0~5)");
		blockLabel.setFont(new Font("Arial", Font.BOLD, 20));
		blockLabel.setHorizontalAlignment(JLabel.CENTER);
		
		blockTextField.setBounds(5,50,190,40);
		blockTextField.setHorizontalAlignment(JTextField.CENTER);
		blockTextField.setFont(new Font("Arial", Font.BOLD, 25));
		blockTextField.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				try {
					blockCount = Integer.parseInt(blockTextField.getText());
					if(blockCount < 0 || blockCount > 5) {
						blockButton.setEnabled(false);
						return;
					}
					blockButton.setEnabled(true);
					
				} catch (Exception e2) {
					blockButton.setEnabled(false);
				}
				
			}
			
		});
		
		blockButton.setBounds(5,95,190,40);
		blockButton.setEnabled(false);
		blockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				blockButton.setEnabled(false);
				System.out.println(blockCount);
			}
			
		});
		
		undoButton.setBounds(5, 140, 190, 40);
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
//					System.out.println(Memory.points.si);
					GamePanel.bwMatrix[Memory.points.get(Memory.points.size()-1).i][Memory.points.get(Memory.points.size()-1).j] = -1;
					Memory.undoPoints.add(Memory.points.pop());
					if (Memory.points.size() < Frame.blockCount) {
						Frame.bwLabel.setText("Setting Blocks");
					} else if (Memory.points.size() - Frame.blockCount == 1) {
						Frame.bwLabel.setText("WHITE's TURN");
					} else if (((Memory.points.size() + 1 - Frame.blockCount) / 2) % 2 == 0) {
						Frame.bwLabel.setText("BLACK's TURN");
					} else {
						Frame.bwLabel.setText("WHITE's TURN");
					}
					gamePanel.repaint();	
					finish = false;
				}catch(RuntimeException e1) {
					
				}

			}
		});
		
		
		blockPanel.add(undoButton);
		blockPanel.add(blockLabel);
		blockPanel.add(blockButton);
		blockPanel.add(blockTextField);
		buttonPanel.add(resetButton);
		buttonPanel.add(bwLabel);
		buttonPanel.add(swapButton);
		fr.add(blockPanel);
		fr.add(buttonPanel);
		fr.add(gamePanel);
		
		fr.setVisible(true);
	}
}
