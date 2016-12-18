package fan2;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
/*����һ����ʼ���������ɫ�ķ���*/
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
	
	//�����ɫ����
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
	
	//�����������ɫͬʱ������Ҷ���
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
		
		int x = xCenter - radius;
		int y = yCenter - radius;
		g.drawOval(x - 10, y - 10, 2 * radius + 20 , 2 * radius + 20 );//����Ȧ
		g.setColor(color);
		g.fillArc(x, y, 2 * radius, 2 * radius,0, 30);
		g.fillArc(x, y,2 * radius, 2 * radius,90, 30);
		g.fillArc(x, y,2 * radius, 2 * radius,180, 30);
		g.fillArc(x, y,2 * radius, 2 * radius,270, 30);
	}
}
	
