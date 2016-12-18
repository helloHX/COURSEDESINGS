package geometric3;
import java.awt.Color;
import java.awt.Graphics;
import java.io.*;

import javax.swing.*;

import geometric2.MyObjectOutputStream;
/*����һ�������л���Circle����
 * ʵ�ָ���ĸ��ֳ��󷽷�
 * ʵ��GUI���εĻ�ͼ����
 * ʵ�ֻ��ƺ�ɾ��*/
public class Circle /*extends JPanel*/ implements GeometricObject,Serializable{
	
	private final double PI = 3.14; 
	private double radius = 50.0;
	public Color sidesColor = new Color(111,0,0);
	
	public Circle(){

	}
	
	public Circle(double radius){
		this.radius = radius;
	}
	
	/*public void draw(MyPanel3 jp) {
		Graphics g = jp.getGraphics();
		g.drawOval((int)(jp.getWidth() / 2 - radius), (int)(jp.getHeight() / 2 - radius), (int)radius ,(int) radius);
	}*/
	public void draw(Graphics g,int width,int height) {
		g.drawOval((int)(width / 2 - radius), (int)(height / 2 - radius), (int)radius ,(int) radius);
	}
	public void setRadius(double radius){
		this.radius = radius;
	}
	
	public double getRadius(){
		return radius;
	}

	public void erase(MyPanel3 jp) {
		Graphics g = jp.getGraphics();		
		g.setColor(jp.getBackground());
		g.drawOval((int)(jp.getWidth() / 2 - radius), (int)(jp.getHeight() / 2 - radius), (int)radius ,(int) radius);
	}

	public double getArea() {
		return PI * radius * radius ;//������С�������λ
	}

	public double getPerimeter() {
		return 2 * PI * radius ;
	}
	
	@Override
	public String toString(){
		return "this circle radius is :" + radius;
	}
	
	public void writeToFile(File f) throws IOException{//storage object
		ObjectOutputStream oos = null;
		try{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f,true));
			if(f.length() < 1)
				oos = new ObjectOutputStream(bos);
			else
				oos = new MyObjectOutputStream(bos);
			oos.writeObject(this);  //�ж��ļ���С�����ò�ͬ�ķ���
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
		finally{
			oos.close();
		}
	}
}

