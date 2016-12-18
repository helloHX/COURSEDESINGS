package geometric2;
import java.io.*;
/*����һ�������л���Circle����
 * ʵ�ָ���ĸ��ֳ��󷽷�*/
public class Circle implements GeometricObject{
	
	private final double PI = 3.14; 
	private double radius = 50.0;
	
	public Circle(){
		
	}
	
	public Circle(double radius){
		this.setRadius(radius);
	}
	

	public void draw() {//���ڴ˰汾û����ʾ����÷������ڰ汾��ʵ��
		
	}
	public void setRadius(double radius){
		this.radius = (int)(radius * 100) / 100.0;
	}
	
	public double getRadius(){
		return radius;
	}

	public void erase() {//���ڴ˰汾û����ʾ����÷������ڰ汾��ʵ��

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
