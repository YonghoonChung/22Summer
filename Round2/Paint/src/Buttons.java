import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.util.Timer; 
import java.util.TimerTask;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.plaf.metal.MetalButtonUI;


public class Buttons extends JButton implements MouseListener{
	public static JButton[] buttons = new JButton[7];
	static boolean[] draw = new boolean[4]; // ��������,
	static boolean erase = false;
	//static boolean lastButton = false; //false -> shape, true -> sketch
	static boolean undoFlag = false;
	private boolean allClearFlag = false;
	Timer timer;
	TimerTask task;
	
	int count = 0;

	String buttonNames[] = { "Shapes", "Sketch", "Color", "Stroke", "Erase", "Undo", "Redo" };

	public Buttons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonNames[i]);
			
			buttons[i].setVisible(true);
			buttons[i].addMouseListener(this);
			buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
			buttons[i].setUI (new MetalButtonUI () {//��ư ������ �ȳ����� �ϱ�
		    protected void paintButtonPressed (Graphics g, AbstractButton b) { }
		});
			buttons[i].setBackground(Color.GRAY);
			buttons[i].setForeground(Color.WHITE);
			// decorate();
		}
		setInit();
	}
	@Override
	public void mouseClicked(MouseEvent e) {

		JButton button = (JButton) e.getSource();
		String str = button.getText();
		if (button == buttons[0]) { // line
			DrawScreen.showTextPanel1 *= -1; //��ư�� ������ ���� ��ȭ
			if(DrawScreen.showTextPanel1 == 1) DrawScreen.buttonPanel1.setVisible(true); //������ panel�� ���̰�
			else if(DrawScreen.showTextPanel1 == -1) DrawScreen.buttonPanel1.setVisible(false); //������ panel�� �Ⱥ��̰�
		}
		if (button == buttons[1]) { // Sketch
			setInit();
			draw[3] = true;
			buttons[3].setBorderPainted(true);
			undoFlag = false;
		}
		if (button == buttons[2]) { // Color
			new ColorChooser();
		}
		if (button == buttons[3]) { // Stroke
			DrawScreen.showTextPanel2 *= -1; //��ư�� ������ ���� ��ȭ
			if(DrawScreen.showTextPanel2 == 1) DrawScreen.buttonPanel2.setVisible(true); //������ panel�� ���̰�
			else if(DrawScreen.showTextPanel2 == -1) DrawScreen.buttonPanel2.setVisible(false); //������ panel�� �Ⱥ��̰�
			
		}
		if (button == buttons[4]) {  // Erase
			setInit();
			erase = true;
			
		}
		if (button == buttons[5]) {  // Undo
			undo();

		}
		if (button == buttons[6]) {// redo
			redo();
			
		}
		Frame.screen2.setText(str);
	}
	private void undo() {
		// TODO Auto-generated method stub
		undoFlag = true;
		if (allClearFlag && DrawScreen.memo.isEmpty()) {
			DrawScreen.memo = DrawScreen.allClearMemory;
			allClearFlag = !allClearFlag;
			Frame.panel2.repaint();
			return;
		}
		if (!DrawScreen.memo.isEmpty()) {
			DrawScreen.redoMemory.add(DrawScreen.memo.pop());
			Frame.panel2.repaint();
		}
	}
	private void redo() {
		if(!undoFlag)
			return;
		if(DrawScreen.redoMemory.isEmpty()) 
			return;
			//System.out.println("hello");
		DrawScreen.memo.add(DrawScreen.redoMemory.pop());
		Frame.panel2.repaint();
	}
	public static void setInit() {
		// TODO Auto-generated method stub
		for (int i = 0; i < draw.length; i++) {
			draw[i] = false;
			erase = false;
		}
	}
	protected void allClear() {
		
//		DrawScreen.allClearMemory = DrawScreen.memo;
		for (int i = 0; i < DrawScreen.memo.size(); i++) {
			DrawScreen.allClearMemory.add(DrawScreen.memo.get(i));
		}
		DrawScreen.memo.clear();
		Frame.panel2.repaint();
		allClearFlag = true;
		return;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if (button == buttons[4]) {
			System.out.println("����");
			count = 1;
			timer = new Timer();
			task = new TimerTask() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					if (count == 74) {
						allClear();
						System.out.println("��Ŭ���� �߻�");
						timer.cancel();
					}
					//System.out.println("[ī��Ʈ�ٿ� : " + count + "]");
					double colorForeground = 255-count*255.0/75+1;
					double colorBackground = 128+count*127.0/75+1;
					 Color color1 = new Color((int)colorForeground,(int)colorForeground,(int)colorForeground);
					 Color color2 = new Color((int)colorBackground,(int)colorBackground,(int)colorBackground);
					buttons[4].setBackground(color2);
					buttons[4].setForeground(color1);
					count++; // ����Ƚ�� ����
				}
			};
			timer.schedule(task, 100, 10); // ���� Task, 1�ʵ� ����, 1�ʸ��� �ݺ�
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		JButton button = (JButton) e.getSource();
		if (button == buttons[4]) {
			timer.cancel();
			buttons[4].setBackground(Color.GRAY);
			buttons[4].setForeground(Color.WHITE);
			 // Ÿ�̸� ����
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}
}