package geometric1;
import geometric3.MyPanel3;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
/*创建一个抽象的类
 * 拥有颜色，填充，Date的使用域
 * 相关数据的访问器修改器
 * draw erase的抽象方法
 * getArea 和 getPerimeter的抽象方法*/
public abstract class GeometricObject  {
	private Color color ;//what the geometric color
	boolean filled;//is filled
	Date dateCreated;
	
	protected  GeometricObject(){
		dateCreated = new Date();
	}
	
	protected GeometricObject(Color color ,boolean filled){
		this.setColor(color);//through the class set method set color
		this.setFilled(filled);
		dateCreated = new Date();
		
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void setFilled(boolean filled){
		this.filled = filled;
	}
	
	public Color getColor(){
		return color;
	}
	
	public boolean getFilled(){
		return filled;
	}

    public abstract void draw(Graphics g,int width,int height);
	
	public abstract void erase(MyPanel3 jp);
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	@Override
	public String toString(){
		return "created on " + dateCreated + "\ncolor: " + color +
				"and filled: " + filled;
	}
}
