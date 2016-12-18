package fan2;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
/*创建一个开始就有随机颜色的风扇*/
public class Fan extends JPanel{

	private double radius = 40.0;
	private Color color = Color.red ;
	
	public Fan(){
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public static void main(String[] args){
		JFrame frame = new JFrame("Drawing a Fan");
		Fan fan = new Fan();
		fan.setColor(radomColor());
		frame.add(fan);
		frame.setSize(250,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//随机颜色产生
	public static Color radomColor(){
		int colorRank = (int)(Math.random() * 5);
		switch(colorRank){
		case 0: return Color.red;
		case 1: return Color.blue;
		case 2: return Color.yellow;
		case 3: return Color.green;
		case 4: return Color.orange;
		default : return Color.red;
		}
	}
	
	//产生随机的颜色同时画出扇叶外框
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
		
		int x = xCenter - radius;
		int y = yCenter - radius;
		g.drawOval(x - 10, y - 10, 2 * radius + 20 , 2 * radius + 20 );//画外圈
		g.setColor(color);
		g.fillArc(x, y, 2 * radius, 2 * radius,0, 30);
		g.fillArc(x, y,2 * radius, 2 * radius,90, 30);
		g.fillArc(x, y,2 * radius, 2 * radius,180, 30);
		g.fillArc(x, y,2 * radius, 2 * radius,270, 30);
	}
}
	
