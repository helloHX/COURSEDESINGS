package clock2;

/*根据系统时间绘制出以当前时间为基准的时钟
 *并且以数值的形式在TextFiled上显示当前时间*/
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class StillClock2 extends JFrame implements ActionListener {
	private int hour;
	private int minute;
	private int second;
	private boolean change;// 记录是否根据当前时间显示
	private JButton jbSure = new JButton("确定");
	private JButton jbCancle = new JButton("取消");
	private JLabel jlHour = new JLabel("Hour");
	private JLabel jlMinute = new JLabel("Minute");
	private JLabel jlSecond = new JLabel("Second");
	private JTextField jtHour = new JTextField(4);
	private JTextField jtMinute = new JTextField(4);
	private JTextField jtSecond = new JTextField(4);
	private JPanel jpEnter = new JPanel();
	private JpClock jpClock = new JpClock();

	public StillClock2() {
		setCurrentTime();
		drawClock();
	}

	public StillClock2(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		drawClock();
	}

	public int getHour() {
		return this.hour;
	}

	public int getMinute() {
		return this.minute;
	}

	public int getSecond() {
		return this.second;
	}

	public void setHour(int hour) {
		this.hour = hour;
		jpClock.repaint();
	}

	public void setMinute() {
		this.minute = minute;
		jpClock.repaint();
	}

	public void setSecond() {
		this.second = second;
		jpClock.repaint();
	}

	public void drawClock() {// 画出图像
		jpEnter.add(jlHour);
		jpEnter.add(jtHour);
		jpEnter.add(jlMinute);
		jpEnter.add(jtMinute);
		jpEnter.add(jlSecond);
		jpEnter.add(jtSecond);
		jpEnter.add(jbSure);
		jpEnter.add(jbCancle);
		Timer timer = new Timer(1000, this);
		timer.start();
		jbSure.addActionListener(this);
		jbCancle.addActionListener(this);

		this.add(jpClock, BorderLayout.CENTER);
		this.add(jpEnter, BorderLayout.SOUTH);
		this.setSize(450, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public Dimension getSize() {// frame尺寸访问器
		return new Dimension(this.getWidth(), this.getHeight());
	}

	public void setCurrentTime() {// 获取当前时间
		Calendar calendar = new GregorianCalendar();// 获取系统当前时间
		this.hour = calendar.get(Calendar.HOUR);
		this.second = calendar.get(Calendar.SECOND);
		this.minute = calendar.get(Calendar.MINUTE);
	}

	class JpClock extends JPanel {// 绘制时钟
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			int clockRadius = (int) Math.min(getWidth(),
					getHeight() * 0.8 * 0.5);
			int xCenter = getWidth() / 2;
			int yCenter = getHeight() / 2;

			g.setColor(Color.black);
			g.drawOval(xCenter - clockRadius, yCenter - clockRadius,
					2 * clockRadius, 2 * clockRadius);
			g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
			g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
			g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
			g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

			int sLength = (int) (clockRadius * 0.8);// 画秒钟
			int xSecond = (int) (xCenter + sLength
					* Math.sin(second * (2 * Math.PI / 60)));
			int ySecond = (int) (yCenter - sLength
					* Math.cos(second * (2 * Math.PI / 60)));
			g.setColor(Color.red);
			g.drawLine(xCenter, yCenter, xSecond, ySecond);

			int mLength = (int) (clockRadius * 0.65);// 画分钟
			int xMinute = (int) (xCenter + mLength
					* Math.sin(minute * (2 * Math.PI / 60)));
			int yMinute = (int) (yCenter - mLength
					* Math.cos(minute * (2 * Math.PI / 60)));
			g.setColor(Color.blue);
			g.drawLine(xCenter, yCenter, xMinute, yMinute);

			int hLength = (int) (clockRadius * 0.5);// 画时钟
			int xHour = (int) (xCenter + hLength
					* Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
			int yHour = (int) (yCenter - hLength
					* Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
			g.setColor(Color.green);
			g.drawLine(xCenter, yCenter, xHour, yHour);

		}

	}

	public static void main(String[] args) {
		StillClock2 frame = new StillClock2();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (change == false) {
			this.setCurrentTime();
		} else {//改变新建时钟的时间
			this.second++;
			if (this.second == 60) {
				this.minute++;
				second = 0;
				if (this.minute == 60) {
					this.hour++;
					this.minute = 0;
					if (this.hour == 12)
						hour = 0;
				}
			}
		}
		if (e.getSource() == jbSure) {
			change = true;
			this.hour = Integer.parseInt(jtHour.getText());
			this.minute = Integer.parseInt(jtMinute.getText());
			this.second = Integer.parseInt(jtSecond.getText());
		}
		if(e.getSource() == jbCancle){
			change = false;
		}
		this.repaint();
	}
}
