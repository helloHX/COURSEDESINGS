package countChar2;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class MultipleWindowsDemo extends JFrame{
	private JTextArea jta;
	private JButton jbtShowHistogram = new JButton("Show Histogram");
	private Histogram histogram = new Histogram();
	private JFrame histogramFrame = new JFrame();
	
	public MultipleWindowsDemo(){
		JScrollPane scrollPanel = new JScrollPane(jta = new JTextArea());
		scrollPanel.setPreferredSize(new Dimension(300,200));
		jta.setWrapStyleWord(true);
		jta.setLineWrap(true);
		
		add(scrollPanel,BorderLayout.CENTER);
		add(jbtShowHistogram,BorderLayout.SOUTH);
		
		jbtShowHistogram.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] count = countLetters();
				histogram.showHistogram(count);
				histogramFrame.setSize(400,400);
				histogramFrame.setVisible(true);
			}
		});
	
		histogramFrame.add(histogram);
		histogramFrame.pack();
		histogramFrame.setTitle("Histogram");
			
	}
	
	private int[] countLetters(){
		int[] count = new int[26];
		
		String text = jta.getText();
		
		for(int i = 0; i < text.length(); i++){
			char character = text.charAt(i);
			
			if((character >= 'A') && (character <= 'Z')){
				count[character - 'A']++;
			}
			else if((character >= 'a') && (character <= 'z')){
				count[character - 'a']++;
			}
		}
		return count;
	}
	public static void main(String[] args){
		MultipleWindowsDemo frame = new MultipleWindowsDemo();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MultipleWindowsDemo");
		frame.pack();
		frame.setVisible(true);
	}
}
