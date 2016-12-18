package calendar3;
/*���ݻ�õ�������ʾ�����µ�����
 * ������ʽ��JLable����*/
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class CalendarPanel extends JPanel{
	Calendar calendar;
	Border lineBorder = new LineBorder(Color.BLACK,2);
	
	public CalendarPanel(int year,int month){
		
		calendar = new GregorianCalendar(year,month,1);
		setLayout(new GridLayout(0,7));
		for(int i = 0; i < getStartDay(); i++)
			add(new JLabel());
		for(int i = 1; i <= getMaximumDay(); i++){
			JLabel jlb = new JLabel(i + "");
			jlb.setHorizontalAlignment(SwingConstants.RIGHT);//�������λ��
			jlb.setVerticalAlignment(SwingConstants.TOP);
			add(jlb);
			jlb.setBorder(lineBorder);
		}
	}
	//��ȡ���µ�һ���λ��
	public int getStartDay(){
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}
	//��ȡ���µ�����
	public int getMaximumDay(){
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
