package calendar3;
/*�������û���������ݺ�ѡ����·�
 * �������*/
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
public class Calendar extends JFrame{
	private int month ;
	private int year = 2016;
	private JComboBox monthCombox ;//�·ݵ���Ͽ�
	private JTextField jtfYear;
	private CalendarPanel calendarPanel;
	private String[] monthlist = {"1  ","2  ","3  ","4  ",
			"5  ","6  ","7  ","8  ","9  ","10  ","11  ","12  "};
	
	public Calendar(){
		calendarPanel = new CalendarPanel(year,month);
		JPanel titlePanel = new JPanel(new GridLayout(1,7,10,10));//����ͷ
		titlePanel.add(new JLabel("Sunday"));
		titlePanel.add(new JLabel("Monday"));
		titlePanel.add(new JLabel("Tuesday"));
		titlePanel.add(new JLabel("Wednesday"));
		titlePanel.add(new JLabel("Thursday"));
		titlePanel.add(new JLabel("Friday"));
		titlePanel.add(new JLabel("Saturday"));
		JPanel choicePanel = new JPanel();//�û�ѡ�����
		
	    jtfYear = new JTextField("2016",6);//�����趨����
		jtfYear.addActionListener(new ActionListener(){//�����û����������
			@Override
			public void actionPerformed(ActionEvent e) {
				year = Integer.parseInt(jtfYear.getText());
				changeCalendar();
			}
			
		});
		
		monthCombox = new JComboBox(monthlist);//�·�ѡ���
		monthCombox.addItemListener(new ItemListener(){//�����û��·�����
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
	
	//�Ƴ��ɵ�changeCalendar�����µ�changeCalendar
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
