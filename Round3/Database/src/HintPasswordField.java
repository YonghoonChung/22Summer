import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JPasswordField;

public class HintPasswordField extends JPasswordField{
	Font gainFont = new Font("Tahoma", Font.PLAIN, 20);
	Font lostFont = new Font("Tahoma", Font.ITALIC, 20);

	public HintPasswordField(final String hint) {
		setText(hint);
		setFont(lostFont);
		setForeground(Color.GRAY);
		this.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setFont(gainFont);
				} else {
					setText(getText());
					setFont(gainFont);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint) || getText().length() == 0) {
					setText(hint);
					setFont(lostFont);
					setForeground(Color.GRAY);
				} else {
					setText(getText());
					setFont(gainFont);
					setForeground(Color.BLACK);
				}
			}
		});
	}
}
