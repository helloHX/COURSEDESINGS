package account2;
import java.io.Serializable;
import java.util.Date;
public class Transaction implements Serializable{

		private Date date;
		private char type;
		
		private double amount;
		private double balance;
		private  String description;
		
		public  Transaction(char type,double amount,double balance,String description){
			this.amount = amount;this.type = type;this.balance = balance;
			this.description = description; 
			date = new Date();
		}
		@Override
		public String toString(){
			return "��� =" +amount + "\n��� =" + balance + "\n���� :" + description + "\nʱ��"  + date + "\n" ; 
		}
		
		public Date getDate(){
			return this.date;
		}
		
		public double getAmount(){
			return amount;
		}
		
		public char getType(){
			return type;
		}
	
}
