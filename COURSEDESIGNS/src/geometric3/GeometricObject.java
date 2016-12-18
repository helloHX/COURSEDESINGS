package geometric3;
/*����GeometricObject�ӿڱ���ԭ�е����ֳ��󷽷�
 * �Ҿ���GUI�����ϵͳ����*/
import java.awt.Graphics;
import java.io.*;
import javax.swing.*;
public interface GeometricObject {
   // public abstract void draw(MyPanel3 jp);//�˷�������ȥ��ֵҪͨ�����ϵ�ˢ�²��ܿ���
	public abstract void draw(Graphics g,int width,int height);
	
	public abstract void erase(MyPanel3 jp);
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
	public abstract void writeToFile(File f) throws IOException;

}
