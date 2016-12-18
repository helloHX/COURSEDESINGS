package geometric1;

import java.awt.Color;
import java.util.Scanner;
/*���������ͬ��Geometric��
 * �����Լ���Geometric��ĸ��ַ���*/
public class TestGeometricObject1 {

	public static void main(String[] args) {

		GeometricObject[] geometricObjects = RandomCreateGeometricObject();//create random geometric object and display
		for(int i = 0 ; i < geometricObjects.length;i++){
			System.out.println(geometricObjects[i].toString());
			System.out.printf("Area is : %.2f\n", geometricObjects[i].getArea());
			System.out.printf("perimeter is : %.2f\n\n" , geometricObjects[i].getPerimeter());
			
		}
		
		testTriangleSetSides();//test triangle object method
		testCricleSetRadius();	//test circle object method
	}
	
	public static GeometricObject[] RandomCreateGeometricObject(){//������ɶ�������
		GeometricObject[] geometricObjects = new GeometricObject[3];
		int n ; 
		for(int i = 0; i < 3 ; i++){
			n = (int)(Math.random()*2);
			switch(n){
			case 0: geometricObjects[i] = new Circle();break;
			case 1: geometricObjects[i] = new Triangle();break;
			}
		}
		return geometricObjects;
	}
	
	public static void testTriangleSetSides(){//����triangle�ĸ��ַ���
		Scanner input = new Scanner(System.in);
		Triangle testTriangle = new Triangle();
		
		System.out.println("Enter three sides for a triangle:");
		
		while(true){
		    testTriangle.setSide1(input.nextDouble());
			testTriangle.setSide2(input.nextDouble());
			testTriangle.setSide3(input.nextDouble());
			try{
				if(testTriangle.side1 + testTriangle.side2 < testTriangle.side3 ||
						testTriangle.side1 + testTriangle.side3 < testTriangle.side2 ||
						testTriangle.side2 + testTriangle.side3 < testTriangle.side1) 
					throw new IllegalArgumentException("can't develope a triangle  that twn side is small that other side ");
				else
					break;
			}
			
			catch(IllegalArgumentException ex){//��׽���ڲ���ȷ���������ֵ��µĴ���
				System.out.println(ex.getMessage());
				System.out.println("again set sides");
			}
		}
		
		
		testTriangle.setColor(Color.yellow);
		testTriangle.setFilled(true);
		System.out.println(testTriangle.toString()+ "\n");
		
		
	}
	
	public static void testCricleSetRadius(){//����circle�ĸ��ַ���
		Scanner input = new Scanner(System.in);
		Circle testCircle = new Circle();
		
		System.out.println("Enter a double value as radius for circle :");
		
		while(true){
			testCircle.setRadius(input.nextDouble());
			try{
				if(testCircle.getRadius() < 0)
					throw new IllegalArgumentException("radius must big than zero");
				else
					break;
			}
			catch(IllegalArgumentException ex){  //���������û�����Բ��С�Ĵ���
				System.out.println(ex.getMessage());
				System.out.println("Enter radius again");
				
			}
		}
		testCircle.setColor(Color.yellow);
		testCircle.setFilled(true);
		System.out.println(testCircle.toString() + "\n");
	}
}
