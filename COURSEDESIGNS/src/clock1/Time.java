package clock1;
import java.util.*;
public class Time {
	private int minute;
	private int hour;
	private int second;
	
	public Time(){//无参构造
		Calendar calendar = new GregorianCalendar();
		this.hour = calendar.get(Calendar.HOUR);
		this.second = calendar.get(Calendar.SECOND);
		this.minute =  calendar.get(Calendar.MINUTE);
		
	}
	
	public Time(long elapseTime){//用逝去时间构造
		setTime(elapseTime);
	}
	
	public Time(int hour,int minute,int second){//指定时间构造方法
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	public int getHour(){//小时访问器
		return this.hour;
	}
	
	public int getMinute(){//分钟访问器
		return this.minute;
	}
	
	public int getSecond(){//秒访问器
		return this.second;
	}
	
	public String toString(){//toString方法重写
		return "Hour " + hour + "\nMinute " + minute + 
				"Second" + second;
	}
	
	public void setHour(int hour){//小时修改器
		this.hour = hour;
	}
	
	public void setMinute(int minute){//分钟修改器
		this.minute = minute;
	}
	
	public void setSecond(int second){//秒钟修改器
		this.second = second;
	}
	
	public void setTime(long elapseTime){//逝去时间修改器
		Calendar calendar = new GregorianCalendar(2002,1,1);
		int[] addDate = changeUnit(elapseTime);
		calendar.add(getSecond(), addDate[0]);
		calendar.add(getMinute(), addDate[1]);
		calendar.add(getHour(), addDate[2]);
		this.hour = calendar.get(Calendar.HOUR);
		this.second = calendar.get(Calendar.SECOND);
		this.minute =  calendar.get(Calendar.MINUTE);
	}
	
	public static int[] changeUnit(long elapseTime){//计算逝去时间小时、分钟、秒
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
