package geometric2;
/*创建GeometricObject接口保留原有的四种抽象方法
 * 申明一个可以实现对象序列化到指定文件的方法*/
import java.io.*;
public interface GeometricObject extends Serializable{
    public abstract void draw();
	
	public abstract void erase();
	
	public abstract double getArea();
	
	public abstract double getPerimeter();
	
	public abstract void writeToFile(File f) throws IOException;

}
