package geometric2;
/*测试GeometricObject类中的各种方法
 * 测试GeometricObject文件的读写*/
import java.io.*;
import java.util.*;
public class TestGeometricObject2 {
	public static void main(String[] args) throws IOException {
		
		//create random geometric object and display
         System.out.println("File storage success " + RandomCreateGeometricObject());
         ArrayList<Object> geometricObjectList = readObjectFromFile();
         for(int i = 0; i < geometricObjectList.size(); i++){
        	 if(geometricObjectList.get(i) instanceof GeometricObject){
        	 System.out.println(geometricObjectList.get(i).toString());
        	 System.out.printf("Area is : %.2f\n",(( GeometricObject)geometricObjectList.get(i)).getArea());
        	 System.out.printf("perimeter is : %.2f\n\n" , (( GeometricObject)geometricObjectList.get(i)).getPerimeter());
        	 }
        	 else
        		 continue;
         }
         
         System.out.println("nest text others method:\n");
         testTriangleSetSides();//test triangle object method
 		 testCricleSetRadius();	//test circle object method
 		  testRectangleSetSides();//test Rectangle object method
        
	}
	//随机产生GeometricObject到文件中
	public static boolean RandomCreateGeometricObject(){
		File f = new File("GeometricObjects.dat");
		GeometricObject[] geometricObjects = new GeometricObject[3];
		
		int n ; 
		for(int i = 0; i < 3 ; i++){
			n = (int)(Math.random()*3);
			switch(n){
			case 0: geometricObjects[i] = new Circle();break;
			case 1: geometricObjects[i] = new Triangle();break;
			case 2: geometricObjects[i] = new Rectangle();break;
			}
		}
		
		try{
			for(int i = 0 ; i < geometricObjects.length; i++){
				geometricObjects[i].writeToFile(f);// come true writeToFile method
			}
		}
		
		catch(IOException ex){
			return false;
		}
		
		return true;
	}
	 // 从文件中读出随机产生的GeometricObject
	public static ArrayList<Object> readObjectFromFile() {
		ArrayList<Object> temp = new ArrayList<Object>();
		
		try{
			try(ObjectInputStream input =
					new ObjectInputStream (new BufferedInputStream(new FileInputStream("GeometricObjects.dat")))
					){
				while(true){
					
				  temp.add(input.readObject());
				  
				}
			}
		}
		
		catch(EOFException ex){
			System.out.println("All data were read\n");
		}
		
		catch(ClassNotFoundException ex){
			System.out.println("ClassNotFoundException" + ex.getMessage());
		}
		
		catch(IOException e){
			System.out.println("IOException" + e.getMessage());
		}
		
		return temp;
	}
	
	//test circle others method
	public static void testCricleSetRadius(){ 
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
			catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				System.out.println("Enter radius again");
				
			}
		}
		 System.out.printf(testCircle.toString() + "\n" );
		 System.out.printf("Area is : %.2f\n",testCircle.getArea());
    	 System.out.printf("perimeter is : %.2f\n\n" , testCircle.getPerimeter());
	}
	
	
	 // test Triangle others method
	public static void testTriangleSetSides(){
		Scanner input = new Scanner(System.in);
		Triangle testTriangle = new Triangle();
		
		System.out.println("Enter three sides for a triangle:");
		
		while(true){
		    testTriangle.setSide1(input.nextDouble());
			testTriangle.setSide2(input.nextDouble());
			testTriangle.setSide3(input.nextDouble());
			try{
				if(testTriangle.side1 + testTriangle.side2 <= testTriangle.side3 ||
						testTriangle.side1 + testTriangle.side3 <= testTriangle.side2 ||
						testTriangle.side2 + testTriangle.side3 <= testTriangle.side1) 
					throw new IllegalArgumentException("can't develope a triangle  that twn side is small that other side ");
				else
					break;
			}
			
			catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				System.out.println("again set sides");
			}
		}
		 System.out.printf(testTriangle.toString() + "\n" );
		 System.out.printf("Area is : %.2f\n",testTriangle.getArea());
    	 System.out.printf("perimeter is : %.2f\n\n" , testTriangle.getPerimeter());
	}
	
	
	//测试rectangle的各种方法
	public static void testRectangleSetSides(){
		Scanner input = new Scanner(System.in);
		Rectangle testRectangle = new Rectangle();
		
		System.out.println("Enter two sides for a Rectangle");
		
		while(true){
			testRectangle.setHigh(input.nextDouble());
			testRectangle.setWidth(input.nextDouble());
			try{
				if(testRectangle.getHigh() <= 0 || testRectangle.getWidth() <= 0)
					throw new IllegalArgumentException("the rectangle's side's should't is negative");
				else
					break;
			}
			
			catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
				System.out.println("again set sides");
			}
		}
		System.out.println(testRectangle.toString());
	}
}