package clock2;
/*根据系统时间绘制出以当前时间为基准的时钟
 *并且以数值的形式在TextFiled上显示当前时间*/
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
public class StillClock extends JFrame implements ActionListener{
	private int hour;
	private int minute;
	private int second;
	private JLabel jlHour = new JLabel("Hour");
	private JLabel jlMinute = new JLabel("Minute");
	private JLabel jlSecond = new JLabel("Second");
	private JTextField jtHour = new JTextField(4);
	private JTextField jtMinute = new JTextField(4);
	private JTextField jtSecond = new JTextField(4);
	private JPanel jpEnter = new JPanel();
	private JpClock jpClock = new JpClock();
	public StillClock(){
		setCurrentTime();
		drawClock();
	}
	
	public StillClock(int hour,int minute,int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		drawClock();
	}
	
	public int getHour(){
		return this.hour;
	}
	
	public int getMinute(){
		return this.minute;
	}
	
	public int getSecond(){
		return this.second;
	}
	
	public void setHour(int hour){
		this.hour = hour;
		jpClock.repaint();
	}
	
	public void setMinute(){
		this.minute = minute;
		jpClock.repaint();
	}
	
	public void setSecond(){
		this.second = second;
		jpClock.repaint();
	}
	
	public void drawClock(){//画出图像
		jtHour.setEditable(false);
		jtMinute.setEditable(false);
		jtSecond.setEditable(false);
		jpEnter.add(jlHour);
		jpEnter.add(jtHour);
		jpEnter.add(jlMinute);
		jpEnter.add(jtMinute);
		jpEnter.add(jlSecond);
		jpEnter.add(jtSecond);
		Timer timer = new Timer(1000,this);
		timer.start();
		this.add(jpClock,BorderLayout.CENTER);
		this.add(jpEnter,BorderLayout.SOUTH);
		this.setSize(300,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public Dimension getSize(){//frame尺寸访问器
		return new Dimension(this.getWidth(),this.getHeight());
	}
	
	public void setCurrentTime(){//获取当前时间
		Calendar calendar = new GregorianCalendar();//获取系统当前时间
		this.hour = calendar.get(Calendar.HOUR);
		this.second = calendar.get(Calendar.SECOND);
		this.minute =  calendar.get(Calendar.MINUTE);
	}
	
	class JpClock extends JPanel{//绘制时钟
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			
			int clockRadius = 
					(int)Math.min(getWidth(), getHeight() * 0.8 * 0.5);
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;
			
			g.setColor(Color.black);
			g.drawOval(xCenter - clockRadius, yCenter - clockRadius, 2 * clockRadius, 2 * clockRadius);
			g.drawString("12",xCenter - 5, yCenter - clockRadius + 12);
			g.drawString("9",xCenter - clockRadius + 3,yCenter + 5);
			g.drawString("3",xCenter + clockRadius - 10,yCenter + 3);
			g.drawString("6",xCenter - 3,yCenter + clockRadius - 3);
			
			int sLength = (int)(clockRadius * 0.8);//画秒钟
			int xSecond = (int)(xCenter + sLength *
					Math.sin(second * (2 * Math.PI / 60)));
			int ySecond = (int)(yCenter - sLength * 
					Math.cos(second * (2 * Math.PI / 60)));
			g.setColor(Color.red);
			g.drawLine(xCenter, yCenter, xSecond, ySecond);
			
			int mLength =  (int)(clockRadius * 0.65);//画分钟
			int xMinute = (int)(xCenter + mLength *
					Math.sin(minute * (2 * Math.PI / 60)));
			int yMinute = (int)(yCenter - mLength *
					Math.cos(minute * (2 * Math.PI / 60)));
			g.setColor(Color.blue);
			g.drawLine(xCenter, yCenter, xMinute, yMinute);
			
			int hLength =  (int)(clockRadius * 0.5);//画时钟
			int xHour = (int)(xCenter + mLength *
					Math.sin(hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
			int yHour = (int)(yCenter - mLength *
					Math.cos(hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
			g.setColor(Color.green);
			g.drawLine(xCenter, yCenter, xHour, yHour);
			
		}
		
	}
	
	public static void main(String[] args){
		StillClock frame = new StillClock();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setCurrentTime();
		this.repaint();
		this.jtHour.setText(this.hour + "");
		this.jtMinute.setText(this.minute + "");
		this.jtSecond.setText(this.second + "");
	}
}
