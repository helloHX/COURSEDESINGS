package fan1;
/*创建一个拥有速度，开关情况，半径，颜色的Fan类
 *且拥有各种属性的访问器，Fan类的equal方法*/
public class Fan {
	final static int SLOW = 10;
	final static int DEDIUM = 20;
	final static int FAST = 30;
	
	int speed = SLOW;
	boolean on;
	double radius = 40.0;
	String color = "blue";
	
	public static void main(String[] args){
		Fan fan1 = new Fan();
		fan1.setSpeed(Fan.FAST);
		fan1.setRadius(10);
		fan1.setColor("yellow");
		fan1.setOn(true);
		System.out.println(fan1.toString());
	}
	public Fan(){
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
	public void setOn(boolean isOpen){
		this.on = isOpen;
	}
	
	public void setRadius(double radius){
		this.radius = radius;
	}
	
	public void setColor(String color){
		this.color = color;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public boolean getOn(){
		return on;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public String getColor(){
		return color;
	}
	
	@Override
	public String toString(){
		return "this fan radius is open?" + on + "\ncolor is " + color + "\nradius is " + radius + "\nspeed is " + speed;
		
	}
	
	@Override
	public boolean equals(Object obj){
		Fan fan;
		if(obj instanceof Fan)
			fan = ((Fan)obj);
		else
			return false;
		if((this.color == fan.color) && (this.radius == radius) && (this.speed == fan.speed))
			return true;
		else
			return false;
	}
}
