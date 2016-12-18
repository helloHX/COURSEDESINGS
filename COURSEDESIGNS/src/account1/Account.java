package account1;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
public class Account implements Serializable{
	private int id;
	private double balance;
	private static double annualInterestRate;//���е��˻�����ͬһ������
	private Date dateCreated;
	public Account(){
		
	}
	
	public Account(int id,double balance){
		this.setId(id);
		this.setBalance(balance);
		this.dateCreated = new Date();
	}
	
	//id�޸���
	public void setId(int id) {
			if(id >= 1000000)
				throw new IllegalArgumentException("the id number is out of boundary");
			else
				this.id = id;
			this.dateCreated = new Date();
	}
	
	//balance���޸ķ���
	public void setBalance(double balance){
		if(balance < 0)
			throw new IllegalArgumentException("balance less than zero");
		else
		    this.balance = (int)(balance * 100) / 100.0; //��֤������Ϊ������λС��
	}
	
	//annualInterestRate���޸ķ���
	public void setAnnualInteRate(double annualInterestRate){
		this.annualInterestRate = (int)(annualInterestRate * 100) / 100.0;
	}
	
	//��ȡid�ķ���
	public int getId(){
		return id;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public double getAnnualInteRate(){
		return this.annualInterestRate;
	}
	
	//dateCreate�ķ�����
	public String getDate(){
		return this.dateCreated.toString();
	}
	
	//����������
	public double getMonthlyInterestRate(){
		return this.annualInterestRate / 12;
	}
	
	//ȡǮ
	public void withDraw(double money){
		if(money > 0)
		  this.setBalance(this.balance - money);
			
	}
	
	//��Ǯ
	public void deposit(double money){
		if(money > 0)
		  this.setBalance(this.balance + money);
			
	}
	
}
