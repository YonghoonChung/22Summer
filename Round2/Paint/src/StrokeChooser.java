import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Font;

public class StrokeChooser extends JFrame{
	public static int stroke = 5;
	JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 8);
	JLabel l = new JLabel();
	
	public StrokeChooser(){
		this.setTitle("굵기 고르기");
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		pack();
		setVisible(true);
		slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        stroke = slider.getValue();
        slider.addChangeListener(new MyChangeListener());
        l.setText("슬라이더를 움직이세요 : " + stroke);
        l.setBounds(20,20,150,40);
        this.add(l, BorderLayout.NORTH);
        this.add(slider, BorderLayout.CENTER);
        this.setSize(300,300);
        this.setVisible(true);
	}
    class MyChangeListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			stroke = slider.getValue();
	        l.setText(""+stroke);
	        if(stroke >9) {
	        	Buttons.buttons[5].setFont(new Font("Arial", Font.BOLD, 18));
	        }
	        Buttons.buttons[5].setText("Stroke : " + StrokeChooser.stroke);
		}
    }
}