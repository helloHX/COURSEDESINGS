package geometric2;
/*����GeometricObject�ӿڱ���ԭ�е����ֳ��󷽷�
 * ����һ������ʵ�ֶ������л���ָ���ļ��ķ���*/
import java.io.*;
public interface GeometricObject extends Serializable{
    public abstract void draw();
	
	public abstract void erase();
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
	public abstract void writeToFile(File f) throws IOException;

}
