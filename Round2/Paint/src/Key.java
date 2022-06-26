import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//Frame.screen2.setText(e.getKeyChar());
		System.out.println("The key Typed was: " + e.getKeyChar());

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.isActionKey())
			System.exit(0);
		System.out.println("The key Pressed was: " + e.getKeyChar());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("The key Released was: " + e.getKeyChar());

	}
}

