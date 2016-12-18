package countChar1;

import java.util.Scanner;

public class Textcount {
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string:");
		System.out.println("there are " + countLetters(input.next()) + " letters");
		
		}
	
	public static int countLetters(String s){
		int account = 0;
		char[] letters = s.toCharArray();
		for(int i = 0;i < s.length();i++){
			if((65 <= letters[i] && letters[i] <= 90)||(97 <= letters[i] && letters[i] <= 122))
				account++;
		}
		return account;
	}
}
