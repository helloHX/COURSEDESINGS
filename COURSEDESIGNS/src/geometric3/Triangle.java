package geometric3;
/*创建一个可序列化的Triangle对象
 * 实现父类的各种抽象方法
 * 实现GUI几何的绘图功能
 * 实现绘制和删除*/
import geometric2.MyObjectOutputStream;

import java.awt.Color;
import java.awt.Graphics;
import java.io.*;

import javax.swing.JPanel;
public class Triangle/* extends JPanel*/ implements GeometricObject,Serializable{
	public double side1 = 1.0;
	public double side2 = 1.0;
	public double side3 = 1.0;
	public Color sidesColor;
	public int[] ypoints;
	public int[] xpoints;

	public Triangle() {

	}

	public Triangle(double side1, double side2, double side3) {//the method maybe lead a logic error,this error well make in test program
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}

	public double getSide1() {
		return side1;
	}

	public double getSide2() {
		return side2;
	}

	public double getSide3() {
		return side3;
	}
	
	public void setSide1(double side1){//the method maybe lead a logic error,this error well make in test program
		this.side1 = side1;
	}
	
	public void setSide2(double side2){
		this.side2 = side2;
	}
	
	public void setSide3(double side3){
		this.side3 = side3;
	}

	@Override
	public double getArea() {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	@Override
	public double getPerimeter() {
		return side1 + side2 + side3;//让返回值保留两位小数
	}

	@Override
	public String toString() {
		return "Triangle : side 1 =" + side1 + "side2 = " + side2 + "side3 = " + side3;
	}

	@Override
	/*public void draw(MyPanel3 jp) {
		Graphics g = jp.getGraphics();
		xpoints = new int[3];
		ypoints = new int[3];
		double[] sides = rank(side1, side2, side3);
		double cosMinDegree = (sides[2] * sides[2] + sides[1] * sides[1] - sides[0]
				* sides[0])
				/ (2 * sides[2] * sides[1]);// cos is degree for the minmum length side
											
		double sinMinDegree = Math.sqrt(1 - Math.pow(cosMinDegree, 2));

		xpoints[0] = jp.getWidth() / 2;
		ypoints[0] = jp.getHeight() / 2;
		xpoints[1] = xpoints[0] + (int) sides[2];
		ypoints[1] = ypoints[0];
		xpoints[2] = xpoints[0] + (int) (sides[1] * cosMinDegree);
		ypoints[2] = ypoints[0] + (int) (sides[1] * sinMinDegree);

		g.drawPolygon(xpoints, ypoints, 3);
	}*/
	public void draw(Graphics g,int width,int height) {
		xpoints = new int[3];
		ypoints = new int[3];
		double[] sides = rank(side1, side2, side3);
		double cosMinDegree = (sides[2] * sides[2] + sides[1] * sides[1] - sides[0]
				* sides[0])
				/ (2 * sides[2] * sides[1]);// cos is degree for the minmum length side
											
		double sinMinDegree = Math.sqrt(1 - Math.pow(cosMinDegree, 2));

		xpoints[0] = width / 2;
		ypoints[0] = height / 2;
		xpoints[1] = xpoints[0] + (int) sides[2];
		ypoints[1] = ypoints[0];
		xpoints[2] = xpoints[0] + (int) (sides[1] * cosMinDegree);
		ypoints[2] = ypoints[0] + (int) (sides[1] * sinMinDegree);

		g.drawPolygon(xpoints, ypoints, 3);
	}

	@Override
	public void erase(MyPanel3 jp) {
		Graphics g = jp.getGraphics();
		g.setColor(jp.getBackground());
		g.drawPolygon(xpoints, ypoints, 3);
	}
	
	public static double[] rank(double side1, double side2, double side3) {
		double[] sides = { side1, side2, side3 };
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2 - i; j++) {
				if (sides[j] > sides[j + 1]) {
					double temp = sides[j];
					sides[j] = sides[j + 1];
					sides[j] = temp;
				}
			}

		}
		return sides;
	}
	
	public void writeToFile(File f) throws IOException{ //storage object
		ObjectOutputStream oos = null;
		try{
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f,true));
			if(f.length() < 1)
				oos = new ObjectOutputStream(bos);
			else
				oos = new MyObjectOutputStream(bos);
			oos.writeObject(this);  //判断文件大小并调用不同的方法
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
		finally{
			oos.close();
		}
	}
}
