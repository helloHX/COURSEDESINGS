package account3;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import account2.ATMMachine2;
import account2.Account1;

public class ATM extends JFrame {
	private JFrame errorframe = new JFrame("Error");
	private ErrorJPanel errorPanel = new ErrorJPanel();
	private KeyPanel keyPanel = new KeyPanel();
	private Panels panels = new Panels();
	private JTextArea jtadetail = new JTextArea(10,50);
	private Register registerPanel = new Register();
	private MenuPanel menuPanel = new MenuPanel();
	private CreatPanel creatPanel = new CreatPanel();
	private WithDrawPanel withdrawPanel = new WithDrawPanel();
	private ArrayList<Account1> accounts = new ArrayList<>();
	private DepoistPanel depoistPanel = new DepoistPanel();
	private DetailPanel detailPanel = new DetailPanel();
	private ChangpassPanel changpassPanel = new ChangpassPanel();
	private Account1 account;
	private CardLayout card = new CardLayout();

	public ATM() {
		errorframe.add(errorPanel);
		errorframe.setSize(300, 100);
		errorframe.setLocationRelativeTo(null);
		panels.setLayout(card);
		add(panels);
		panels.add(registerPanel, "0");
		panels.add(creatPanel, "0.0");
		panels.add(menuPanel, "1");
		panels.add(withdrawPanel, "1.0");
		panels.add(depoistPanel, "1.1");
		panels.add(detailPanel, "1.2");
		panels.add(changpassPanel, "1.3");
		try {
			accounts = ATMMachine2.read();
		} catch (FileNotFoundException e) {
			System.out.print(e.getMessage());
		} catch (ClassNotFoundException e) {
			errorPanel.setError(4);
			errorframe.setVisible(true);
		} catch (IOException e) {
			errorPanel.setError(4);
			errorframe.setVisible(true);
		}
	}

	public static void main(String[] args) {// main
		ATM frame = new ATM();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// 登录界面
	class Register extends JPanel {
		private JPanel enterPanel = new JPanel(new GridLayout(2, 2, 0, 10));
		private JPanel chiocePane = new JPanel(new GridLayout(1, 2, 10, 0));
		private JTextField nameField = new JTextField(10);
		private JPasswordField passwField = new JPasswordField(10);
		private JButton registerButton = new JButton("登录");
		private JButton creatButton = new JButton("注册");

		public Register() {
			this.setLayout(new BorderLayout());
			enterPanel.add(new JLabel("用户ID:"));
			enterPanel.add(nameField);
			enterPanel.add(new JLabel("密码:"));
			enterPanel.add(passwField);
			chiocePane.add(registerButton);
			chiocePane.add(creatButton);
			ButtonListener buttonmoniter = new ButtonListener();
			creatButton.addActionListener(buttonmoniter);
			registerButton.addActionListener(buttonmoniter);
			this.add(enterPanel, BorderLayout.NORTH);
			this.add(chiocePane, BorderLayout.CENTER);
			this.add(keyPanel, BorderLayout.SOUTH);
		}

		class ButtonListener implements ActionListener {
			Account1 account = null;
			int id;

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == registerButton) {
					try {
						id = Integer.parseInt(nameField.getText());
					} catch (NumberFormatException n) {
						errorPanel.setError(1);
						errorframe.setVisible(true);
					}
					boolean sure = register(id, passwField.getText());
					if (sure) {
						card.show(panels, "1");
					}
					else{
						errorPanel.setError(2);
						errorframe.setVisible(true);
					}
				}
				if (e.getSource() == creatButton) {
					card.show(panels, "0.0");
				}
			}
		}

		public boolean register(int id, String password) {
			int i = -1;

			for (i = 0; i < accounts.size(); i++) {// 检验输入账户的有效性
				if (accounts.get(i).getId() == id)
					account = accounts.get(i);
			}
			if (account != null) {
				if (account.getPassword().equals(password)) {
					return true;
				}
			} else {
				errorPanel.setError(2);
				errorframe.setVisible(true);

			}
			return false;
		}
	}

	// 菜单
	class MenuPanel extends JPanel {
		private JPanel funcPanel1 = new JPanel(new GridLayout(3, 0, 0, 50));
		private JPanel funcPanel2 = new JPanel(new GridLayout(2, 0, 0, 50));
		private JButton withDrawB = new JButton("取款");
		private JButton changePasswB = new JButton("修改密码");
		private JButton depositB = new JButton("存款");
		private JButton detailB = new JButton("明细");
		private JButton exitB = new JButton("退出");

		public MenuPanel() {
			this.setOpaque(false);
			funcPanel1.setOpaque(false);
			funcPanel2.setOpaque(false);
			this.setLayout(new BorderLayout());
			funcPanel1.add(withDrawB);
			funcPanel1.add(changePasswB);
			funcPanel1.add(depositB);
			funcPanel2.add(detailB);
			funcPanel2.add(exitB);
			
			this.add(funcPanel1, BorderLayout.WEST);
			this.add(funcPanel2, BorderLayout.EAST);
			

			ButtonListener buttonmoniter = new ButtonListener();
			withDrawB.addActionListener(buttonmoniter);// 显示存款面
			depositB.addActionListener(buttonmoniter);//
			changePasswB.addActionListener(buttonmoniter);//
			detailB.addActionListener(buttonmoniter);//
			exitB.addActionListener(buttonmoniter);
			exitB.setBackground(Color.PINK);
		}

		// 按键监听器
		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == withDrawB) {
					card.show(panels, "1.0");
				}
				if (e.getSource() == changePasswB) {
					card.show(panels, "1.3");
				}
				if (e.getSource() == detailB) {
					String string = "";
					for(int i = 0 ; i < account.getdetails().size() ;i++){
						string +=account.getdetails().get(i).toString();
						string +="\n";
					  }
					jtadetail.append(string);
					card.show(panels, "1.2");
				}
				if (e.getSource() == depositB)
					card.show(panels, "1.1");
				if (e.getSource() == exitB) {
					card.show(panels, "0");
					try {
						ATMMachine2.save(accounts);
					} catch (IOException i) {
						errorPanel.setError(5);
						errorframe.setVisible(true);
					}
				}
			}
		}
	}

	// 注册
	class CreatPanel extends JPanel {
		private JTextField nameField = new JTextField(10);
		private JTextField passwField = new JTextField(10);
		private JTextField withdrawField = new JTextField(10);
		private JTextField IDField = new JTextField(6);
		private JPanel panel1 = new JPanel(new GridLayout(4, 2, 0, 10));
		private JPanel panel2 = new JPanel();
		private JButton sureButton = new JButton("确定");
		private JButton exitButton = new JButton("退出");

		// 创建对象
		public CreatPanel() {

			panel1.add(new JLabel("用户名*："));
			panel1.add(nameField);
			panel1.add(new JLabel("密码*："));
			panel1.add(passwField);
			panel1.add(new JLabel("存款*："));
			panel1.add(withdrawField);
			panel1.add(new JLabel("ID:"));
			panel1.add(IDField);
			panel2.add(sureButton);
			panel2.add(exitButton);
			IDField.setEditable(false);
			this.add(panel1, BorderLayout.CENTER);
			this.add(panel2, BorderLayout.SOUTH);
			ButtonListener buttonmoniter = new ButtonListener();
			sureButton.addActionListener(buttonmoniter);
			exitButton.addActionListener(buttonmoniter);

		}

		class ButtonListener implements ActionListener {
			Account1 account = null;
			double balance;

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exitButton) {
					card.show(panels, "0.0");
				}
				if (e.getSource() == sureButton) {
					try {
						balance = Double.parseDouble(withdrawField.getText());
						account = new Account1(nameField.getText(), balance,
								ATMMachine2.creatRadomId(accounts),
								passwField.getText());
						accounts.add(account);
						IDField.setText(account.getId() + "");
						ATMMachine2.save(accounts);
						errorPanel.setError(6);
						errorframe.setVisible(true);
					} catch (IllegalArgumentException i) {
						if (i.getMessage().equals("abnomal fromat")) {
							errorPanel.setError(1);
							errorframe.setVisible(true);
						}
						if (i.getMessage().equals("balance less than zero")) {
							errorPanel.setError(3);
							errorframe.setVisible(true);
						}
					} catch (IOException i) {
						errorPanel.setError(5);
						errorframe.setVisible(true);
					}
				}
				if (e.getSource() == exitButton) {
					card.show(panels, "0");
				}
			}
		}

	}
   //qu款
	class WithDrawPanel extends JPanel {
		private JButton hButton = new JButton("100");
		private JButton thButton = new JButton("200");
		private JButton tButton = new JButton("1000");
		private JButton ttButton = new JButton("2000");
		private JButton exitButton = new JButton("退出");
		private JButton sureButton = new JButton("确定");
		private JPanel panel1 = new JPanel(new GridLayout(2, 1, 0, 50));
		private JPanel panel2 = new JPanel(new GridLayout(3, 1, 0, 30));
		private JLabel rankMassage = new JLabel("取款量");
		private JPanel panel3 = new JPanel();
		private JTextField amountField = new JTextField("0", 10);
		private ImageIcon image = new ImageIcon("黄金1.png");// ??

		public WithDrawPanel() {
			this.setOpaque(false);
			panel3.setOpaque(false);
			this.setLayout(new BorderLayout());
			textFieldListener textmoniter = new textFieldListener();
			panel1.add(hButton);
			panel1.add(thButton);
			panel2.add(tButton);
			panel2.add(ttButton);
			panel2.add(exitButton);
			hButton.addActionListener(textmoniter);
			thButton.addActionListener(textmoniter);
			tButton.addActionListener(textmoniter);
			ttButton.addActionListener(textmoniter);
			exitButton.addActionListener(textmoniter);
			sureButton.addActionListener(textmoniter);
			amountField.setHorizontalAlignment(SwingConstants.RIGHT);
			panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 100));
			panel3.add(rankMassage);
			panel3.add(amountField);
			panel3.add(sureButton);

			this.add(panel3, BorderLayout.CENTER);
			this.add(panel1, BorderLayout.WEST);
			this.add(panel2, BorderLayout.EAST);
			this.add(keyPanel, BorderLayout.SOUTH);

		}

		class textFieldListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == hButton)
					amountField.setText("100");
				if (e.getSource() == thButton)
					amountField.setText("200");
				if (e.getSource() == tButton)
					amountField.setText("1000");
				if (e.getSource() == exitButton)
					card.show(panels, "1");
				if (e.getSource() == ttButton)
					amountField.setText("2000");
				if (e.getSource() == sureButton) {// 未完成

					double amount = Double.parseDouble(amountField.getText());
					if (amount < 0) {
						errorPanel.setError(3);
						errorframe.setVisible(true);
					} else {
						try {
							account.withDraw(amount);
							errorPanel.setError(6);
							errorframe.setVisible(true);
						} catch (IllegalArgumentException i) {
							if (i.getMessage().equals(
									"money must is some times of hundred")
									|| i.getMessage().equals(
											"Excessive withdraw")) {
								errorPanel.setError(3);
								errorframe.setVisible(true);
							}
						}
					}
				}
			}

		}

	}

	// 存款
	class DepoistPanel extends JPanel {
		private JTextField amountField = new JTextField("0", 10);
		private JButton exitButton = new JButton("退出");
		private JButton sureButton = new JButton("确定");
		private JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER,
				10, 100));

		public DepoistPanel() {
			this.setOpaque(false);
			panel3.setOpaque(false);
			amountField.setHorizontalAlignment(SwingConstants.RIGHT);
			panel3.add(amountField);
			panel3.add(sureButton);
			panel3.add(exitButton);
			ButtonListener moniter = new ButtonListener();
			exitButton.addActionListener(moniter);
			sureButton.addActionListener(moniter);
			this.add(panel3);
		}

		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				double amount = Double.parseDouble(amountField.getText());
				if (e.getSource() == sureButton)
					if (Double.parseDouble(amountField.getText()) < 0) {
						errorPanel.setError(3);
						errorframe.setVisible(true);
					} else {
						account.deposit(Double.parseDouble(amountField
								.getText()));
						errorPanel.setError(6);
						errorframe.setVisible(true);
					}
				if (e.getSource() == exitButton) {
					card.show(panels, "1");
				}
			}

		}
	}

	class DetailPanel extends JPanel {
		private JScrollPane scollBar = new JScrollPane(jtadetail);
		private JButton exitB = new JButton("退出");
		private JPanel panel1 = new JPanel();
		private JPanel panel2 = new JPanel();
		
		public DetailPanel(){
			this.setOpaque(false);
			panel2.setOpaque(false);
			panel1.setOpaque(false);
			this.setLayout(new BorderLayout());
			ButtonListener moniter = new ButtonListener();
			panel1.add(scollBar);
			panel2.add(exitB);
			exitB.addActionListener(moniter);
			this.add(panel1,BorderLayout.CENTER);
			this.add(panel2,BorderLayout.SOUTH);
		}
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				card.show(panels, "1");
				jtadetail.setText("");
	      }
		}
}
	
	class ChangpassPanel extends JPanel {
		private JPanel panel1 = new JPanel(new GridLayout(3, 2,0, 5));
		private JPanel panel2 = new JPanel();
		private JTextField oldwordField = new JTextField(10);
		private JTextField new1Field = new JTextField(10);
		private JTextField new2Field = new JTextField(10);
		private JButton sureButton = new JButton("确定");
		private JButton exitButton = new JButton("退出");

		public ChangpassPanel() {
			this.setOpaque(false);
			panel1.setOpaque(false);
			panel2.setOpaque(false);
			this.setLayout(new BorderLayout());
			panel1.add(new JLabel("旧密码*："));
			panel1.add(oldwordField);
			panel1.add(new JLabel("新密码*："));
			panel1.add(new1Field);
			panel1.add(new JLabel("确认新密码*："));
			panel1.add(new2Field);
			panel2.add(sureButton);
			panel2.add(exitButton);
			ButtonListener buttonmoniter = new ButtonListener();
			sureButton.addActionListener(buttonmoniter);
			exitButton.addActionListener(buttonmoniter);
			this.add(panel1,BorderLayout.CENTER);
			this.add(panel2,BorderLayout.SOUTH);
		}

		class ButtonListener implements ActionListener {
			double balance;

			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == exitButton) {
					card.show(panels, "1");
				}
				if (e.getSource() == sureButton) {
					if (account.getPassword().equals(oldwordField.getText())){
						if (new1Field.getText().equals(new2Field.getText()))
							if (Account1.isFromat(new1Field.getText())) {
								account.setPassword(new1Field.getText());
								errorPanel.setError(6);
								errorframe.setVisible(true);
							} else{
								errorPanel.setError(2);
								errorframe.setVisible(true);
							}
						else{
							errorPanel.setError(7);
						    errorframe.setVisible(true);
						}
					}
					else {
						errorPanel.setError(1);
						errorframe.setVisible(true);
					}
				
				}

			}
		}
	}
}