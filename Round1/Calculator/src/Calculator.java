import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.ArrayList;
import java.util.Stack;

public class Calculator extends JFrame {
	String infix = "";
	boolean reset = false;
	boolean plusminus = false;
	boolean dotFlag = false;
	private JTextField screen;
	String ANS = "0";
	String prevClickedOperation = "";

	public Calculator(String title) {
		setLayout(null);

		screen = new JTextField();
		screen.setEditable(false);
		screen.setBackground(Color.WHITE);
		screen.setHorizontalAlignment(JTextField.RIGHT);
		screen.setFont(new Font("Arial", Font.BOLD, 50));
		screen.setBounds(8, 10, 305, 140);

		JPanel panels = new JPanel();
		panels.setLayout(new GridLayout(5, 10, 5, 5));
		panels.setBounds(8, 155, 305, 345);

		String buttonKeys[] = { "C", "←", "ANS", "÷", "7", "8", "9", "×", "4", "5", "6", "-", "1", "2", "3", "+", "±",
				"0", ".", "=" }; // ±
		JButton buttons[] = new JButton[buttonKeys.length];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonKeys[i]);
			if (buttonKeys[i] != "ANS")
				buttons[i].setFont(new Font("Arial", Font.BOLD, 25));
			else
				buttons[i].setFont(new Font("Arial", Font.BOLD, 15));
			buttons[i].setBackground(Color.GRAY);
			buttons[i].setForeground(Color.WHITE);
			if (buttonKeys[i] == "=")
				buttons[i].setBackground(Color.RED);
			buttons[i].addActionListener(new buttonActions());
			panels.add(buttons[i]);
		}

		add(screen);// 이걸로 값을 넣어준다
		add(panels);

		setTitle(title);
		setVisible(true);
		setSize(335, 550);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// X 누르면 종료
	}

	class buttonActions implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String value = e.getActionCommand();
			if (screen.getText().equals("Error, 0 divide")||screen.getText().equals("Syntax Error")) {
				if (!value.equals("C")) {
					return;
				} else {
					screen.setText("");
				}
			}
			if (value.equals("C")) { // C를 누름
				screen.setText("");
			} else if (value.equals("=")) { // =을 누름
				String temp = screen.getText();
				temp = temp.replaceAll("ANS", ANS);
				calc(temp);
				reset = true;
				ANS = screen.getText();
			} else if (value.equals("±")) {// +-를 누름
				if (screen.getText().equals("")) {
					screen.setText("");
					return;
				}
				String str = screen.getText();
				plusminus = true;
				stringChange(str);
			} else if (value.equals("←")) { // backspace누름
				String s = screen.getText();
				int len = s.length();
				if (len == 0) return;
				screen.setText(s.substring(0, len - 1));
			} else if (value.equals("+") || value.equals("-") || 
					value.equals("×") || value.equals("÷")|| value.equals(".")) { // 사측연산 누름
				if (reset)
					reset = false;
				if (screen.getText().equals("") && (value.equals("-") || value.equals("."))) {
					screen.setText(value);
					return;
				}
				if(value.equals("-")&&(prevClickedOperation.equals("-")||prevClickedOperation.equals("+")||
						prevClickedOperation.equals("×")||prevClickedOperation.equals("÷"))) {
					prevClickedOperation = "";
				}
				if (!prevClickedOperation.equals("+") 
						&& !prevClickedOperation.equals("×") && !prevClickedOperation.equals("÷")
						&& !prevClickedOperation.equals(".") && !screen.getText().equals("")) {
					screen.setText(screen.getText() + value);
				}
			} else if (value.equals("ANS")) {
				screen.setText(screen.getText() + "ANS");
			} else { // 숫자누름
				if ((value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4")
						|| value.equals("5") || value.equals("6") || value.equals("7") || value.equals("8")
						|| value.equals("9") || value.equals("0") || value.equals(".")) && reset) {
					reset = false;
					screen.setText("");
					screen.setText(screen.getText() + value);
					return;
				}
				screen.setText(screen.getText() + value);
			}
			prevClickedOperation = value;
			int leng = screen.getText().length();
			if (leng < 10)
				screen.setFont(new Font("Arial", Font.BOLD, 50));
			else if (leng < 13)
				screen.setFont(new Font("Arial", Font.BOLD, 40));
			else if (leng < 17)
				screen.setFont(new Font("Arial", Font.BOLD, 30));
			else if (leng < 24)
				screen.setFont(new Font("Arial", Font.BOLD, 20));
			else if (leng < 52)
				screen.setFont(new Font("Arial", Font.BOLD, 10));
			else
				screen.setFont(new Font("Arial", Font.BOLD, 5));
		}
	}

	public ArrayList<String> stringChange(String s) {
		ArrayList<String> array = new ArrayList<String>();
		array.clear();
		for (int i = 0; i < s.length(); i++) {// arraylist로 만들기
			char ch = s.charAt(i);
			if (ch == '+' || ch == '-' || ch == '×' || ch == '÷') {
				array.add(infix);
				infix = "";
				array.add(ch + "");
			} else {
				infix = infix + ch;
			}
		}
		array.add(infix);
		infix = "";
		
		array.remove("");
		if(array.get(0).equals("-")) {
			array.set(0, array.get(0).concat(array.get(1)));
			array.remove(array.get(1));
		}
		for (int i = 0; i < array.size(); i++)
			if(array.get(i).equals("")) array.remove("");

		for(int i = 1; i < array.size(); i++) {
			String prev = array.get(i-1);
			String curr = array.get(i);
			if(curr.equals("-")&&(prev.equals("+")||prev.equals("-")||prev.equals("×")||prev.equals("÷"))) {
				array.set(i, array.get(i).concat(array.get(i+1)));
				array.remove(array.get(i+1));
			}
		}
		if(plusminus) {
			plusminus = false;
			String temp = array.get(array.size()-1);
			if(temp.charAt(0) == '-') {
				temp = temp.substring(1, temp.length());
			}else {
				temp = "-"+temp;
			}
			array.set(array.size()-1, temp);
			temp ="";
			for(String str : array) temp += str;
			
			screen.setText(temp);
			return array;//dummy value
		}
		return array;
	}

	public void calc(String s) {
		Stack<String> stack = new Stack<String>();
		ArrayList<String> array = stringChange(s);
		ArrayList<String> calc = new ArrayList<String>();
		double val1 = 0.0;
		double val2 = 0.0;
		String output = "";
		String temp = "";
		if(!is_operand(array.get(array.size()-1))) {
			screen.setText("Syntax Error");
			return;
		}
		for (int i = 0; i < array.size(); i++) {// postfix
			String op = array.get(i);
			if (is_operand(op)) {
				calc.add(op);
			} else {
				while (!stack.isEmpty() && get_precedence(op) <= get_precedence(stack.peek())) {
					calc.add(stack.peek());
					stack.pop();
				}
				stack.push(op);
			}
		}
		while (!stack.isEmpty()) {
			calc.add(stack.peek());
			stack.pop();
		}
		for (int i = 0; i < calc.size(); i++) {
			String op = calc.get(i);
			int dotCount = 0;
			for (int j = 0; j < op.length(); j++) {
				if(op.charAt(j) == '.') {
					dotCount++;
					if(dotCount >=2) {
						screen.setText("Syntax Error");
						return;
					}
				}
			}
			if (is_operand(op))
				stack.push(op);
			else {
				val2 = Double.parseDouble(stack.pop());
				val1 = Double.parseDouble(stack.pop());
				if (op.equals("+")) {
					temp = Double.toString(val1 + val2);
					stack.push(temp);
				}
				if (op.equals("-")) {
					temp = Double.toString(val1 - val2);
					stack.push(temp);
				}
				if (op.equals("×")) {
					temp = Double.toString(val1 * val2);
					stack.push(temp);
				}
				if (op.equals("÷")) {
					if (val2 == 0.0) {
						screen.setText("Error, 0 divide");
						return;
					}
					temp = Double.toString(val1 / val2);
					stack.push(temp);
				}
			}
		}
		output = stack.pop();
		if(output.contains("E")) {
			screen.setText(output);
			return;
		}
		int num;
		double temp1 = Double.parseDouble(output);
		if (temp1 % 1 < 0.000003333333334) {
			num = (int) temp1;
			screen.setText(Integer.toString(Math.round(num)));
			return;
		}
		String result = Double.toString(Math.round(Double.parseDouble(output) * 100000) / 100000.0);
		screen.setText(result);
	}

	public boolean is_operand(String op) {
		if (op.equals("+") || op.equals("-") || op.equals("×") || op.equals("÷"))
			return false;
		else
			return true; // 숫자라면
	}

	public int get_precedence(String op) {
		if (op.equals("+") || op.equals("-")) {
			return 1;
		} else if (op.equals("×") || op.equals("÷")) {
			return 2;
		}
		return 0;
	}

	public static void main(String[] args) {
		new Calculator("계산기");
	}
}
