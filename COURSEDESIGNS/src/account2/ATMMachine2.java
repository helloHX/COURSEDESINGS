package account2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ATMMachine2 {

	public static void main(String[] args) {
		ArrayList<Account1> accounts = new ArrayList<>();
		try {
			accounts = read();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found or file is empty");
		} catch (FileNotFoundException e) {
			System.out.println("File not found need creat new account");
		} catch (IOException e) {
			e.printStackTrace();
		}

		menu(accounts);

		try {
			save(accounts);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//菜单
	public static void menu(ArrayList<Account1> accounts) {
		Scanner input = new Scanner(System.in);
		int choice;
		Account1 account;
		do {
			System.out.println();
			System.out.println("Main menu");
			System.out
					.printf("0:create a account \n1 : check balance \n2: withdraw"
							+ " \n3: deposit \n4: detail of the transation \n5: change password \n6: exit\n");
			System.out.print("Enter a choice :");

			choice = input.nextInt();
			switch (choice) {
			case 0:
				creatAccount(accounts);
				break;
			case 2:
				account = register(accounts);// 登录
				if (account != null)
					withdraw(account);
				break;
			case 1:
				account = register(accounts);// 登录
				if (account != null)
					System.out
							.printf("the balance is %.2f\n",account.getBalance());
				break;
			case 3:
				account = register(accounts);// 登录
				if (account != null)
					deposit(account);
				break;
			case 4:
				account = register(accounts);
				if (account != null)
					detailTransation(account);
				break;
			case 5:
				account = register(accounts);// 登录
				if (account != null)
					changePassword(account);
				break;
			}
			account = null;
		} while (choice != 6);

	}

	// 取款
	public static void withdraw(Account1 account) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an amount to withdraw :");

		while (true) {// 保证取款操作后余额大于零
			try {
				double withdraw = input.nextDouble();

				if (withdraw < 0) {// 保证取得数量大于0
					System.out.println("money must more than zero");
					continue;
				}
				account.withDraw(withdraw);// 如果存款余额小于零重新输入取款数
				break;
			} catch (IllegalArgumentException e) {
				if (e.getMessage().equals("balance less than zero"))
					System.out.print("Enter again(more than zero):");
				if (e.getMessage()
						.equals("money must is some times of hundred"))
					System.out.print("Enter again(is some times of hundred):");
				if (e.getMessage().equals("Excessive withdraw"))
					System.out.print("daliy withdraw out of 5000");
				System.out.print("Enter the withdraw again:");
			}

		}
	}

	// 存款
	public static void deposit(Account1 account) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter an amount to deposit :");

		while (true) {
			double deposit = input.nextDouble();
			if (deposit < 0) {
				System.out.println("money must more than zero");
				continue;
			} else {
				account.deposit(deposit);
				break;
			}
		}
	}
	
	//新建
	public static void creatAccount(ArrayList<Account1> accounts) {
		Scanner input = new Scanner(System.in);
		Account1 account = null;
		System.out.printf("Enter your name:");
		
		String name = input.next();
		System.out.printf("Enter your balance:");
		double balance = input.nextDouble();
		System.out.printf("set your password(6~10)");
		String password = input.next();
		int id = creatRadomId(accounts);
		System.out.print("your id is :" + id);
		while (true) {
			try {
				account = new Account1(name, balance, id, password);
				break;
			} catch (IllegalArgumentException e) {
				if (e.getMessage().equals("abnomal fromat")){// 验证密码是否符合格
				System.out.println("password error fromat");
				System.out.print("set the password again:");
				password = input.next();
				}
				if(e.getMessage().equals("balance less than zero")){
					System.out.println("balance must more than zero");
					System.out.print("enter balance angain");
					balance = input.nextDouble();
				}
			}
		}
		accounts.add(account);
		System.out.println("creat over!!!");
	}

	public static void detailTransation(Account1 account) {
		account.detailTransation();
	}

	public static void changePassword(Account1 account) {
		account.changePassword();
	}

	// 保存账户
	public static void save(ArrayList<Account1> accounts) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("account.dat")));) {
			oos.writeObject(accounts);
		}
	}

	// 读取已经存入内存的账户
	public static ArrayList<Account1> read() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream("account.dat")));
		return (ArrayList<Account1>) ois.readObject();
	}

	// 登录
	public static Account1 register(ArrayList<Account1> accounts) {
		Scanner input = new Scanner(System.in);

		Account1 account = null;
		while (true) {
			System.out.print("Enter your account id(int):");
			int id = input.nextInt();

			for (int i = 0; i < accounts.size(); i++) {// 检验输入账户的有效性
				if (accounts.get(i).getId() == id)
					account = accounts.get(i);
			}
			if (account != null)
				break;
			else
				System.out.println("account not find");
			return null;
		}
		System.out.print("Enter yout account password:");
		input.nextLine();//消除分隔符
		String password = input.next();
		if (account.getPassword().equals(password))// 检验输入密码的正确性
			return account;
		else {
			System.out.println("error Password");
			return null;
		}
	}

	// 产生随机id
	public static int creatRadomId(ArrayList<Account1> accounts) {
		int id = 0;
		boolean reptition = true;// 监听是否有重复
		while (reptition) {
			reptition = false;
			id = (int) (Math.random() * 1000000);
			for (int i = 0; i < accounts.size(); i++)
				if (accounts.get(i).getId() == id)
					reptition = true;
		}
		return id;
	}
}
