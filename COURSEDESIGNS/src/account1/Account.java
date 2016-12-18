package account1;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
public class Account implements Serializable{
	private int id;
	private double balance;
	private static double annualInterestRate;//所有的账户都有同一个利率
	private Date dateCreated;
	public Account(){
		
	}
	
	public Account(int id,double balance){
		this.setId(id);
		this.setBalance(balance);
		this.dateCreated = new Date();
	}
	
	//id修改器
	public void setId(int id) {
			if(id >= 1000000)
				throw new IllegalArgumentException("the id number is out of boundary");
			else
				this.id = id;
			this.dateCreated = new Date();
	}
	
	//balance的修改方法
	public void setBalance(double balance){
		if(balance < 0)
			throw new IllegalArgumentException("balance less than zero");
		else
		    this.balance = (int)(balance * 100) / 100.0; //保证数据域为保留两位小数
	}
	
	//annualInterestRate的修改方法
	public void setAnnualInteRate(double annualInterestRate){
		this.annualInterestRate = (int)(annualInterestRate * 100) / 100.0;
	}
	
	//获取id的方法
	public int getId(){
		return id;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public double getAnnualInteRate(){
		return this.annualInterestRate;
	}
	
	//dateCreate的访问器
	public String getDate(){
		return this.dateCreated.toString();
	}
	
	//返回月利率
	public double getMonthlyInterestRate(){
		return this.annualInterestRate / 12;
	}
	
	//取钱
	public void withDraw(double money){
		if(money > 0)
		  this.setBalance(this.balance - money);
			
	}
	
	//存钱
	public void deposit(double money){
		if(money > 0)
		  this.setBalance(this.balance + money);
			
	}
	
}
