package account1;

import java.util.Scanner;

public class ATMMatchinel {
	
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		
		Account[] accounts = creatAccounts();
		while(true){
		System.out.println("Enter your id 0~99");
		int id = input.nextInt();
		  if( 0 <= id && id <= 99)
		  menu(accounts[id]);
		}
		
	}
	
	public static Account[] creatAccounts(){
		Account[] accounts = new Account[100];
		for(int i = 0 ; i < accounts.length; i++){
			accounts[i] = new Account(i,1000);
		}
		return accounts;
	}
	
	public static void menu(Account account){
		Scanner input = new Scanner(System.in);
		int choice;
		
		do{
		System.out.println("Main menu");
		System.out.printf("1 : check balance \n2: withdraw \n3: deposit \n4: exit\n");
		System.out.print("Enter a choice :");
		
		choice = input.nextInt();
		
		switch(choice){
		case 2: withdraw(account);break;
		case 1: System.out.println("the balance is " + account.getBalance());break;
		case 3: deposit(account);break;
		}
	 }while(choice != 4);
		
	}
	
	//取钱
	public static void withdraw(Account account){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an amount to withdraw :");
        while(true){//保证取款操作后余额大于零
          try{
            double  withdraw = input.nextDouble();
            
            if(withdraw < 0){
            	System.out.println("money must more than zero");
            	continue;
            }
            account.withDraw(withdraw);//如果存款余额小于零重新输入取款数
            break;
          }
          catch(IllegalArgumentException e){
        	if(e.getMessage().equals("balance less than zero"));
        	System.out.print("Enter again:");
          }
        }
	}
	
	//存钱
	public static void deposit(Account account){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an amount to deposit :");
		
		while(true){
			double deposit= input.nextDouble();
			if(deposit < 0){
	        	System.out.println("money must more than zero");
	        	continue;
	        }
			else{
				account.deposit(deposit);
				break;
			}	
		}	
	}
}
