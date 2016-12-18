package geometric2;
/*新增一个Rectangle类
 * 也实现geometicObject类*/
import java.io.*;
public class Rectangle implements GeometricObject{
	private double high = 1.0;
	private double width = 1.0;
	
	public Rectangle(){
		
	}
	
	public Rectangle(double high,double width){
		this.high = high;
		this.width = width;
	}
	public double getHigh(){
		return this.high;
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public void setHigh(double high){
		this.high = high;
	}
	
	public void setWidth(double width){
		this.width = width;
	}
	
	
	@Override
	public void draw() {//由于此版本没有显示界面该方法将在版本三实现
		
	}

	@Override
	public void erase() {//由于此版本没有显示界面该方法将在版本三实现
		
	}

	@Override
	public double getArea() {
		return width * high;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return (width + high) * 2;
	}
	
	@Override
	public String toString(){
		return "Rectangle :" + "high " + high + " width " + width;
	}
	@Override
	public void writeToFile(File f) throws IOException {
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