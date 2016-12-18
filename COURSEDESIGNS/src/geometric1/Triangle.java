package geometric1;

import geometric3.MyPanel3;
import java.awt.Graphics;
import java.util.Scanner;
/*创建一个三角形类并实现父类的抽象方法
 * 创建不同属性的访问器和修改器
*/
public class Triangle extends GeometricObject {
	public double side1 = 1.0;
	public double side2 = 1.0;
	public double side3 = 1.0;
	public int[] ypoints;
	public int[] xpoints;
	

	public Triangle() {

	}

	public Triangle(double side1, double side2, double side3) {//the method maybe lead a logic error,this error well make in test program
		this.setSide1(side1);
		this.setSide1(side2);
		this.setSide1(side3);
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
		this.side1 =(int)(side1 * 100) / 100.0;
	}
	
	public void setSide2(double side2){//通过修改器保证数据域为保留两位小数
		this.side2 = (int)(side2 * 100) / 100.0;
	}
	
	public void setSide3(double side3){
		this.side3 = (int)(side3 * 100) / 100.0;
	}

	@Override
	public double getArea() {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	@Override
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	@Override
	public String toString() {
		return "Triangle : side 1 =" + side1 + "side2 = " + side2 + "side3 = " + side3
				+ "\nfilled? : " + getFilled() + 
				 "\nColor : " + getColor() + "\ncreate time: " + dateCreated.toString();
	}

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
	
	@Override
	public void erase(MyPanel3 jp) {
		Graphics g = jp.getGraphics();
		g.setColor(jp.getBackground());
		g.drawPolygon(xpoints, ypoints, 3);
	}
	
}
