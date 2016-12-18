package geometric2;
import java.io.*;
/*����һ�������л���Triangle����
 * ʵ�ָ���ĸ��ֳ��󷽷�*/
public class Triangle implements GeometricObject{
	public double side1 = 1.0;
	public double side2 = 1.0;
	public double side3 = 1.0;

	public Triangle() {

	}

	public Triangle(double side1, double side2, double side3) {//the method maybe lead a logic error,this error well make in test program
		this.setSide1(side1);
		this.setSide1(side2);
		this.setSide1(side3);
	}

	public double getSide1() {
		return side1;
	}

	public double getSide2() {
		return side2;
	}

	public double getSide3() {
		return side3;
	}
	
	public void setSide1(double side1){//the method maybe lead a logic error,this error well make in test program
		this.side1 =(int)(side1 * 100) / 100.0;
	}
	
	public void setSide2(double side2){
		this.side2 = (int)(side2 * 100) / 100.0;//ͨ���޸�����֤������Ϊ������λС��
	}
	
	public void setSide3(double side3){
		this.side3 = (int)(side3 * 100) / 100.0;
	}

	@Override
	public double getArea() {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	@Override
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	@Override
	public String toString() {
		return "Triangle : side 1 =" + side1 + "side2 = " + side2 + "side3 = " + side3;
	}

	@Override
	public void draw() {

	}

	@Override
	public void erase() {

	}
	
	public void writeToFile(File f) throws IOException{ //storage object
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
