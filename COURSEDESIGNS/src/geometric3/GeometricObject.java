package geometric3;
/*创建GeometricObject接口保留原有的四种抽象方法
 * 且具有GUI界面的系统功能*/
import java.awt.Graphics;
import java.io.*;
import javax.swing.*;
public interface GeometricObject {
   // public abstract void draw(MyPanel3 jp);//此方法穿过去的值要通过不断地刷新才能看到
	public abstract void draw(Graphics g,int width,int height);
	
	public abstract void erase(MyPanel3 jp);
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
	public abstract void writeToFile(File f) throws IOException;

}
