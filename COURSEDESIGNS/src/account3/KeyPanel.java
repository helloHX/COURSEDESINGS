package account3;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class KeyPanel extends JPanel{
	private JButton[] b = {new JButton("1"),new JButton("2"),new JButton("3"),
	new JButton("4"),new JButton("5"),new JButton("6"),new JButton("7"),
	new JButton("8"),new JButton("9"),new JButton("0"), new JButton("00"),new JButton("000")};
	private String s = "";
	public KeyPanel(){
		setLayout(new GridLayout(0,3));
		ButtonListener buttonmoniter = new ButtonListener();
		for(int i = 0; i <= 11 ; i++){
			this.add(b[i]);
			b[i].addActionListener(buttonmoniter);
		}
	}
	
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			s += e.getSource();
		}
		
	}
	
	public String getText(){
		return this.s;
	}
}
