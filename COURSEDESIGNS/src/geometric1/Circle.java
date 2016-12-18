package geometric1;

import geometric3.MyPanel3;

import java.awt.Graphics;

/*创建一个圆形类并实现父类的抽象方法
 * 创建不同属性的访问器和修改器
*/
public class Circle extends GeometricObject{
	
	private final double PI = 3.14; 
	private double radius = 50.0;
	
	public Circle(){
		
	}
	
	public Circle(double radius){
		this.setRadius(radius);
	}
	
	public void draw(Graphics g,int width,int height) {
		g.drawOval((int)(width / 2 - radius), (int)(height / 2 - radius), (int)radius ,(int) radius);
	}
	
	public void setRadius(double radius){
		this.radius = (int)(radius * 100) / 100.0;
	}
	
	public double getRadius(){
		return radius;
	}

	public void erase(MyPanel3 jp) {
		Graphics g = jp.getGraphics();		
		g.setColor(jp.getBackground());
		g.drawOval((int)(jp.getWidth() / 2 - radius), (int)(jp.getHeight() / 2 - radius), (int)radius ,(int) radius);
	}

	@Override
	public double getArea() {
		return PI * radius * radius;
	}

	@Override
	public double getPerimeter() {
		return 2 * PI * radius ;
	}
	
	
	@Override
	public String toString(){
		return "this circle radius is :" + radius + "\nfilled? : " + getFilled() + 
				 "\nColor : " + getColor() + "\ncreate time: " + dateCreated.toString();
	}
	
}
