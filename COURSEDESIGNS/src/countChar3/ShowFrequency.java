package countChar3;
import javax.swing.*;

import java.io.*;
import java.util.Scanner;

import countChar2.Histogram;

public class ShowFrequency {
	
	public static void main(String[] args) throws Exception{
		 Histogram histogram = new Histogram();
	    JFrame frame = new JFrame("Show Histogram");
		int[] count = new int[26];
		Scanner input = new Scanner(System.in);
		System.out.println("enter file path");
		String filename = input.next();
		BufferedInputStream fileInput = new BufferedInputStream(
				new FileInputStream(new File(filename)));
	   int letter;
		while((letter = fileInput.read()) != -1){
			char character = (char)letter;
				if((character >= 'A') && (character <= 'Z')){
					count[character - 'A']++;
				}
				else if((character >= 'a') && (character <= 'z')){
					count[character - 'a']++;
				}
		}
			frame.add(histogram);
			histogram.showHistogram(count);
			frame.setSize(400,400);
			frame.setVisible(true);
		
	}
}
