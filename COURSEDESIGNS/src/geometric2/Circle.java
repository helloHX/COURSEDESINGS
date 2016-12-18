package geometric2;
import java.io.*;
/*创建一个可序列化的Circle对象
 * 实现父类的各种抽象方法*/
public class Circle implements GeometricObject{
	
	private final double PI = 3.14; 
	private double radius = 50.0;
	
	public Circle(){
		
	}
	
	public Circle(double radius){
		this.setRadius(radius);
	}
	

	public void draw() {//由于此版本没有显示界面该方法将在版本三实现
		
	}
	public void setRadius(double radius){
		this.radius = (int)(radius * 100) / 100.0;
	}
	
	public double getRadius(){
		return radius;
	}

	public void erase() {//由于此版本没有显示界面该方法将在版本三实现

	}


	public double getArea() {
		return PI * radius * radius;
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
