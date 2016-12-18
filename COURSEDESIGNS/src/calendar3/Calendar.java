package calendar3;
/*根据用用户的输入年份和选择的月份
 * 输出日历*/
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class Calendar extends JFrame{
	private int month ;
	private int year = 2016;
	private JComboBox monthCombox ;//月份的组合框
	private JTextField jtfYear;
	private CalendarPanel calendarPanel;
	private String[] monthlist = {"1  ","2  ","3  ","4  ",
			"5  ","6  ","7  ","8  ","9  ","10  ","11  ","12  "};
	
	public Calendar(){
		calendarPanel = new CalendarPanel(year,month);
		JPanel titlePanel = new JPanel(new GridLayout(1,7,10,10));//日历头
		titlePanel.add(new JLabel("Sunday"));
		titlePanel.add(new JLabel("Monday"));
		titlePanel.add(new JLabel("Tuesday"));
		titlePanel.add(new JLabel("Wednesday"));
		titlePanel.add(new JLabel("Thursday"));
		titlePanel.add(new JLabel("Friday"));
		titlePanel.add(new JLabel("Saturday"));
		JPanel choicePanel = new JPanel();//用户选择面板
		
	    jtfYear = new JTextField("2016",6);//年数设定区域
		jtfYear.addActionListener(new ActionListener(){//监听用户的年份输入
			@Override
			public void actionPerformed(ActionEvent e) {
				year = Integer.parseInt(jtfYear.getText());
				changeCalendar();
			}
			
		});
		
		monthCombox = new JComboBox(monthlist);//月份选择框
		monthCombox.addItemListener(new ItemListener(){//监听用户月份输入
			@Override
			public void itemStateChanged(ItemEvent e) {
				month = monthCombox.getSelectedIndex();
				changeCalendar();
			}
		});
		choicePanel.add(jtfYear);
		choicePanel.add(monthCombox);
		
		
		add(choicePanel,BorderLayout.NORTH);
		add(titlePanel,BorderLayout.CENTER);
		add(calendarPanel,BorderLayout.SOUTH);
	}
	
	//移除旧的changeCalendar换上新的changeCalendar
	private void changeCalendar(){
		remove(calendarPanel);
		calendarPanel = new CalendarPanel(year,month);
		add(calendarPanel,BorderLayout.SOUTH);
		pack();
	}
	
	public static void main(String[] args){
		Calendar frame = new Calendar();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
