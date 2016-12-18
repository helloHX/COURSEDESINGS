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

	// ȡ��
	public void withDraw(double money) {
		double dayWithdraw = money;// 24Сʱ�ڵ�ȡ����
		System.out.print(dayWithdraw);
		// ��֤��֤ÿ���֧ȡ�������5000
		// �������5000���߲���1000�ı����׳��쳣
		// �쳣�ɲ��Գ��򲶻񲢴���
		if (money % 100 != 0)
			throw new IllegalArgumentException(
					"money must is some times of hundred");
		for (int i = 0; i < transactions.size(); i++) {
			// ѡ���׼�¼�е�ÿһ��ȡ��������ʱ�����С��һ���ÿ��ȡ�����

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

	// ���
	public void deposit(double money) {
		super.deposit(money);
		transactions.add(new Transaction('D', money, super.getBalance(),
				"deposit"));
	}

	// ��ʼ������
	// ͨ����֤����ĳ��Ⱥ����������ֺ���ĸ��ɵ�����
	// �����ǹ涨�ĸ�ʽ�׳��쳣�쳣�ɲ��Գ���׽�ʹ���
	public void setPassword(String newPassword){
		
		if(isFromat(newPassword))
		  this.password = newPassword;
		else
	      throw new IllegalArgumentException("abnomal fromat");//��ʽ����
	}
	
	//��������
	public void changePassword(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter the old password :");
		if(this.password.equals(input.next()));
		else{//�������������
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
			    
			else{//�������벻ͬ
				System.out.println("twice times enter are not equal" + 
			" changeing fail");
				return;
			}
		}
		else{//���������ʽ�����޸�ʧ��
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
			return false;//���������ʽ����
		for(int i = 0 ; i < passwordArray.length; i++){
			if(Character.isLetterOrDigit(passwordArray[i]));
			else
			return false;//���������ʽ����
			
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
