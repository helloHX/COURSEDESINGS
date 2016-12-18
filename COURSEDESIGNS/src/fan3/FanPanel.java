package fan3;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*����һ���ܹ��������ȵĻ���
 * �ܹ�ͨ�������������Զ�ת�ٸı�*/
public class FanPanel extends JPanel{
	    private int degree ;//change the fan to run
	    private int changeDegree;
		private Color color = Color.red ;//current fan color
		
	public FanPanel(){
		color = radomColor();
		Timer timer = new Timer(100,new ActionListener(){ //ͨ������������ˢ��

			@Override
			public void actionPerformed(ActionEvent e) {
				degree += changeDegree;
				if(degree % 360 == 0) //��֤degree��Ϊint���Ͳ�������Χ
					degree = 0;
				repaint();
				
			}
    		
    	});
    	timer.start();
	}
	
	public void setChangeDegree(int degree){
		this.changeDegree = degree;
	}
	
	public int getChangeDegree(){
		return this.changeDegree;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	 
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(color);
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		int radius = (int)(Math.min(getWidth(), getHeight()) * 0.4);
		
		int x = xCenter - radius;
		int y = yCenter - radius;
		g.drawOval(x - 10, y - 10, 2 * radius + 20 , 2 * radius + 20 );
		
		g.fillArc(x, y, 2 * radius, 2 * radius, degree , 30);
		g.fillArc(x, y,2 * radius, 2 * radius, degree + 90, 30);
		g.fillArc(x, y,2 * radius, 2 * radius, degree + 180, 30);
		g.fillArc(x, y,2 * radius, 2 * radius, degree + 270, 30);
	}
	
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
}
