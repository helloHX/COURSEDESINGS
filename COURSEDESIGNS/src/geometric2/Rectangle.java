package geometric2;
/*����һ��Rectangle��
 * Ҳʵ��geometicObject��*/
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
	public void draw() {//���ڴ˰汾û����ʾ����÷������ڰ汾��ʵ��
		
	}

	@Override
	public void erase() {//���ڴ˰汾û����ʾ����÷������ڰ汾��ʵ��
		
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
			oos.writeObject(this);  //�ж��ļ���С�����ò�ͬ�ķ���
		}
		catch (Exception ex){
			ex.printStackTrace(); 
		}
		finally{
			oos.close();
		}
		
	}
}