package fan3;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/*为风扇提供测试方法，并为添加按钮，滑条
 * 添加监听器调整转速，方向*/
public class FanControl extends JFrame{
	private boolean on;
	private int speed;
	private JPanel controlPanel = new JPanel(new FlowLayout());
	private FanPanel fanPanel = new FanPanel();
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	private JButton revButton = new JButton("Reverse");
	private JScrollBar speedBar = new JScrollBar(JScrollBar.HORIZONTAL);
	
    public FanControl(){
 
    	speedBar.setMaximum(50);
    	controlPanel.add(startButton);
    	controlPanel.add(stopButton);
    	controlPanel.add(revButton);
    	setLayout(new BorderLayout());
    	add(controlPanel,BorderLayout.NORTH);
    	add(fanPanel,BorderLayout.CENTER);
    	add(speedBar,BorderLayout.SOUTH);
    	
    	speedBar.addAdjustmentListener(new AdjustmentListener(){

			public void adjustmentValueChanged(AdjustmentEvent e) {
				speed = speedBar.getValue();
				if(on)
					fanPanel.setChangeDegree(speed);
			}
    		
    	});
    	startButton.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e){
    			on = true;
    			fanPanel.setChangeDegree(speed);
    		}
    	});
    	
    	stopButton.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e){
    			on = false;
    			fanPanel.setChangeDegree(0);
    		}
    	});
    	
    	revButton.addActionListener(new ActionListener(){
    		
    		public void actionPerformed(ActionEvent e){
    			if(on){
    				speed *= -1;
    			    fanPanel.setChangeDegree(speed);
    			}
    			
    		}
    	});
    		
    }
   
    public static void main(String[] args){
    	FanControl frame = new FanControl();
		frame.setSize(250,300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
}
