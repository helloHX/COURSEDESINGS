package calendar2;

import java.util.*;

public class DisplayCalendar {
	
	static Calendar calendar;
	
	public static void main(String[] args){
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the year");//�������
		int year = input.nextInt();
		System.out.println("Enter the month of the year");//�����·�
		int month = input.nextInt();
		calendar = new GregorianCalendar(year,month - 1,1);
		printMonth(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
		
		//���û�����Ҫ�����ĵ����ĳ��
		while(true){
			System.out.println("Enter month for the current year");
			calendar.set(Calendar.MONTH, input.nextInt() - 1);
			printMonth(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH));
		}
		
	}
	
	//��ӡ����
	public static void printMonth(int year,int month){
		int StartDay = getStartDay();
		printMonthTitle(year,month + 1);
		printMonthBody(StartDay,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	//��ȡ������һ���������е�λ��
	public static int getStartDay(){
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	//��ӡ������
	public static void printMonthBody(int startDay,int numOfDaysInMonth){
		
		int i = 0; 
		for(;i < startDay;i++)
			System.out.print("    ");
		for(i = 1;i <= numOfDaysInMonth;i++){
			System.out.printf("%4d",i);
			
			if((i + startDay ) % 7 == 0)
				System.out.println();
		}
		System.out.println();
		
	}
	
	//��ӡ����ͷ
	public static void printMonthTitle(int year,int month){
		System.out.println("         " + month 
				+ " " + year);
		System.out.println("-----------------------------");
		System.out.println(" Sun Mon Tue Wed Thu Fri Sat");
	}
}
