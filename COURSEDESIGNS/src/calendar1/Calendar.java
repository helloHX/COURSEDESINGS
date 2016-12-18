package calendar1;
/*ͨ���û�������ĳ��ĳ���������ڼ���ͷ
 * �������ÿһ���µ�����*/
import java.util.Scanner;

public class Calendar {
	public static  void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter year");
		int year = input.nextInt();
		
		System.out.println("Enter month between 1 and 12");
		int month = input.nextInt();
		while( month  < 1&& month > 12)
			System.out.println("illegal month please enter again");
		
		System.out.println("Enter the first day index 1~7");
		int day = input.nextInt();
		
		makeMonth(year,month,day);
	}
	
	//�������������
	public static void makeMonth(int year,int month,int day){
		int StartDay = getStartDay(year,month,day);
		for(int i = 1; i <= 12; i++)
		StartDay = printMonth(StartDay,year,i);
	}
	
	//�����������
	public static int printMonth(int StartDay,int year,int month){
		printTitle(year,month);
		return printMonthBody(StartDay,year,month);
	}
	
	//���������ͷ
	public static void printTitle(int year,int month){
		System.out.println("         " + getMonthName(month)
				+ " " + year);
		System.out.println("-----------------------------");
		System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
	}
	
	
	//����·���
	public static int printMonthBody(int startDay,int year,int month){
		int NumberOfDaysInMonth = getNumberOfDaysInMonth(year,month);
		
		int i = 0; 
		for(;i < startDay;i++)
			System.out.print("    ");
		for(i = 1;i <= NumberOfDaysInMonth;i++){
			System.out.printf("%4d",i);
			
			if((i + startDay) % 7 == 0)
				System.out.println();
		}
		System.out.println();
		return (startDay + NumberOfDaysInMonth) % 7;//������һ���µĿ�ʼ����
		
	}
	
	//get the English name for the month
	public static String getMonthName(int month){
		String monthName = "";
		switch(month){
		case 1: monthName = "January";break;
		case 2: monthName = "February";break;
		case 3: monthName = "March"; break;
		case 4: monthName = "April";break;
		case 5: monthName = "May";break;
		case 6: monthName = "June";break;
		case 7: monthName = "July";break;
		case 8: monthName = "August";break;
		case 9: monthName = "September";break;
		case 10: monthName = "October";break;
		case 11: monthName = "November";break;
		case 12: monthName = "December";
		}
		
		return monthName;
	}
	
	//�õ���ǰ�µ�����
	public static int getNumberOfDaysInMonth(int year,int month){
		if(month == 1 || month == 3 || month == 5 || month == 7
				|| month == 8 || month == 10 || month == 12)
			return 31;
		
		if(month == 4 || month == 6 || month == 9 || month == 11)
			return 30;
		
		if(month == 2)return isLeapYear(year)? 29: 28;
		
		return 0;
	}
	
	 //�ж��Ƿ�������
	public static boolean isLeapYear(int year){
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}
	
	//�ĵ������һ���λ��
	public static int getStartDay(int year,int month,int day){
		int frontDays = 0;
		for(int i = 0 ; i < month ; i++){//�صڸ��µ��ӵ���ǰ��
			frontDays += getNumberOfDaysInMonth(year,i);
		}
		return (7 - Math.abs((frontDays - day ) % 7)) % 7;//������㵱��ĵ�һ��
	}
}
