package clock1;
import java.util.*;
public class Time {
	private int minute;
	private int hour;
	private int second;
	
	public Time(){//�޲ι���
		Calendar calendar = new GregorianCalendar();
		this.hour = calendar.get(Calendar.HOUR);
		this.second = calendar.get(Calendar.SECOND);
		this.minute =  calendar.get(Calendar.MINUTE);
		
	}
	
	public Time(long elapseTime){//����ȥʱ�乹��
		setTime(elapseTime);
	}
	
	public Time(int hour,int minute,int second){//ָ��ʱ�乹�췽��
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getHour(){//Сʱ������
		return this.hour;
	}
	
	public int getMinute(){//���ӷ�����
		return this.minute;
	}
	
	public int getSecond(){//�������
		return this.second;
	}
	
	public String toString(){//toString������д
		return "Hour " + hour + "\nMinute " + minute + 
				"Second" + second;
	}
	
	public void setHour(int hour){//Сʱ�޸���
		this.hour = hour;
	}
	
	public void setMinute(int minute){//�����޸���
		this.minute = minute;
	}
	
	public void setSecond(int second){//�����޸���
		this.second = second;
	}
	
	public void setTime(long elapseTime){//��ȥʱ���޸���
		Calendar calendar = new GregorianCalendar(2002,1,1);
		int[] addDate = changeUnit(elapseTime);
		calendar.add(getSecond(), addDate[0]);
		calendar.add(getMinute(), addDate[1]);
		calendar.add(getHour(), addDate[2]);
		this.hour = calendar.get(Calendar.HOUR);
		this.second = calendar.get(Calendar.SECOND);
		this.minute =  calendar.get(Calendar.MINUTE);
	}
	
	public static int[] changeUnit(long elapseTime){//������ȥʱ��Сʱ�����ӡ���
		int[] date = new int[3];
		elapseTime = elapseTime % 86400000;
		elapseTime = elapseTime / 1000;
		date[0] = (int) (elapseTime % 60);
		elapseTime = elapseTime / 60;
		date[1] = (int) (elapseTime % 60);
		elapseTime = elapseTime / 60;
		date[2] = (int) (elapseTime % 60);
		return date;
	}
	
	public static void main(String[] args){
		Time time = new Time();
		System.out.println("hour " + time.getHour() +" minute " + time.getMinute() +" Second " +time.getSecond());
	}
	
}
