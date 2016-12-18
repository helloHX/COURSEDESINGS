package account2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import account1.Account;

public class Account1 extends Account implements Serializable {
	private String password;
	private String name;
	private ArrayList<Transaction> transactions = new ArrayList<>();

	public Account1(String name, double balance, int id, String password) {
		super(id, balance);
		this.name = name;
		this.setPassword(password);
	}

	// 取款
	public void withDraw(double money) {
		double dayWithdraw = money;// 24小时内的取款量
		System.out.print(dayWithdraw);
		// 保证保证每天的支取数额不大于5000
		// 如果大于5000或者不是1000的倍数抛出异常
		// 异常由测试程序捕获并处理
		if (money % 100 != 0)
			throw new IllegalArgumentException(
					"money must is some times of hundred");
		for (int i = 0; i < transactions.size(); i++) {
			// 选择交易记录中的每一次取款于现在时间相距小于一天的每次取款叠加

			if ((System.currentTimeMillis() - transactions.get(i).getDate().getTime()) < 86400000
					&& transactions.get(i).getType() == 'W')
				dayWithdraw += transactions.get(i).getAmount();
		}
		if (dayWithdraw > 5000){
			System.out.print(dayWithdraw);
			throw new IllegalArgumentException("Excessive withdraw");
		}
		else
			super.withDraw(money);
		transactions.add(new Transaction('W', money, super.getBalance(),
				"WithDraw"));
	}

	// 存款
	public void deposit(double money) {
		super.deposit(money);
		transactions.add(new Transaction('D', money, super.getBalance(),
				"deposit"));
	}

	// 初始化密码
	// 通过保证密码的长度和密码是数字和字母组成的条件
	// 若不是规定的格式抛出异常异常由测试程序捕捉和处理
	public void setPassword(String newPassword){
		
		if(isFromat(newPassword))
		  this.password = newPassword;
		else
	      throw new IllegalArgumentException("abnomal fromat");//格式错误
	}
	
	//更改密码
	public void changePassword(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the old password :");
		if(this.password.equals(input.next()));
		else{//旧密码输入错误
			System.out.println("the old password is not correct" + 
		"changeing fail");
			return;
		}
		
		System.out.print("Enter the new password :");
		String newPassword = input.next();
		
		if(isFromat(newPassword)){
			System.out.print("Enter the new password again :");
			if(newPassword.equals(input.next())){
				this.password = newPassword;
				System.out.println("changed over!!");
			}
			    
			else{//两次输入不同
				System.out.println("twice times enter are not equal" + 
			" changeing fail");
				return;
			}
		}
		else{//若新密码格式错误修改失败
			System.out.println("abnormal fromat" + 
			" changeing fail");
			return;
		}
	}
	
	public void detailTransation(){
		for(int i = 0;i < transactions.size();i++){
			System.out.println();
			System.out.println(transactions.get(i).toString());
			
		}
	}
	
	public static boolean isFromat(String newPassword){
		char[] passwordArray = newPassword.toCharArray();
		if(passwordArray.length < 6 || passwordArray.length > 10)
			return false;//密码输入格式不对
		for(int i = 0 ; i < passwordArray.length; i++){
			if(Character.isLetterOrDigit(passwordArray[i]));
			else
			return false;//密码输入格式不对
			
		}
		return true;
	}

	public String getPassword(){
		return this.password;
	}
	
	public ArrayList<Transaction> getdetails(){
		return this.transactions;
	}
}
