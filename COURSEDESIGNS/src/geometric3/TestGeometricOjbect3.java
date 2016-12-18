package geometric3;
/*展示GUI界面
 * 添加相应的组件
 * 添加相应监听器
 * 计算几何图像的周长面积并展示与界面
 * 可通过界面按钮绘制删除相应图像
 * 通过界面设置图像的属性*/
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class TestGeometricOjbect3 extends JFrame {
	private JPanel controlPanel = new JPanel(new GridLayout(6,1));
	private  MyPanel3 showPanel = new MyPanel3();
	private Triangle showTriangle = new Triangle(110,110,110);
	private Circle showCircle = new Circle();
	private JPanel setSidesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,1,1));
	private JPanel setRadiusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,1,1));
	private JPanel showConclusion = new JPanel(new GridLayout(1,2));
	private JButton showTriangleB = new JButton("ShowTriangle");
	private JButton eraseTrianglB = new JButton("EraseTriangle");
	private JButton showCircleB = new JButton("ShowCircle");
	private JButton eraseCircleB = new JButton("EraseCircle");
	private JTextField side1Field = new JTextField("50.0" ,4);
	private JTextField side2Field = new JTextField("50.0" ,4);
	private JTextField side3Field = new JTextField("50.0" ,4);
	private JTextField radiusField = new JTextField("50.0" , 4);
	private JTextField showAreaField = new JTextField("0" ,4);
	private JTextField showPerField = new JTextField("0" ,4);
	
	public static void main(String[] args){
		
		TestGeometricOjbect3 frame = new TestGeometricOjbect3();
		frame.setSize(500,500);
		frame.setTitle("GeometricShow");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setVisible(true);
		
	}
	
	public TestGeometricOjbect3(){
		
		setSidesPanel.add(new JLabel("Side1"));
		setSidesPanel.add(side1Field); 
		setSidesPanel.add(new JLabel("Side2"));
		setSidesPanel.add(side2Field); 
		setSidesPanel.add(new JLabel("Side3"));
		setSidesPanel.add(side3Field);
		setRadiusPanel.add( new JLabel("Radius"));
		setRadiusPanel.add(radiusField);
		controlPanel.add(showTriangleB);
		controlPanel.add(setSidesPanel);
		controlPanel.add(eraseTrianglB);
		controlPanel.add(showCircleB);
		controlPanel.add(setRadiusPanel);
		controlPanel.add(eraseCircleB);//对输入控制台进行添加组件
		
		showConclusion.add(new JLabel("Area"));
		showAreaField.setEditable(false);
		showConclusion.add(showAreaField);
		showConclusion.add(new JLabel("Perimeter"));
		showPerField.setEditable(false);
		showConclusion.add(showPerField);//对结果显示台进行显示
		
		add(showPanel,BorderLayout.CENTER);
		add(showConclusion,BorderLayout.SOUTH);
		add(controlPanel,BorderLayout.WEST);//放置各种显示面板
		
		ButtonListener buttonMoniter = new ButtonListener();
		showTriangleB.addActionListener(buttonMoniter);
		eraseTrianglB.addActionListener(buttonMoniter);
		showCircleB.addActionListener(buttonMoniter);
		eraseCircleB.addActionListener(buttonMoniter);//将输入台上的原数上添加监听器
	}
	
	
	class ButtonListener implements ActionListener{
		
		 public void actionPerformed(ActionEvent e) {
			if(e.getSource() == showTriangleB){
				showPanel.geometic[0] = showTriangle;
				double side1 = Double.parseDouble(side1Field.getText());
				double side2 = Double.parseDouble(side2Field.getText());
				double side3 = Double.parseDouble(side3Field.getText());
				//判断用户输入是否符合数学几何关系
				if(side1 + side2 <= side3 ||side1 + side3 <= side2 ||
						side2 + side3 <= side1)
					showPanel.draw[2] = true;
				else
					showPanel.draw[2] = false;
				
				showTriangle.setSide1(side1);
				showTriangle.setSide2(side2);
				showTriangle.setSide3(side3);
				//将显示图像设为true
				showPanel.draw[0] = true;
				showAreaField.setText(String.format("%.2f", showTriangle.getArea()) );
				showPerField.setText(String.format("%.2f", showTriangle.getPerimeter()) );
			}
			if(e.getSource() == eraseTrianglB){
				//将显示图像设为false
				showPanel.draw[0] = false;
				showAreaField.setText(""+ 0);
				showPerField.setText(""+ 0);
			}
			if(e.getSource() == showCircleB){
				showPanel.geometic[1] = showCircle;
				double radius = Double.parseDouble(radiusField.getText());
				//判断用户输入是否符合数学几何关系
				if(radius <= 0)
				       showPanel.draw[2] = true; 
				else
						showPanel.draw[2] = false;
				
				showCircle.setRadius(radius);
				//将显示图像设为true
				showPanel.draw[1] = true;
				showAreaField.setText(String.format("%.2f", showCircle.getArea()));
				showPerField.setText(String.format("%.2f", showCircle.getPerimeter()));
			}
			if(e.getSource() == eraseCircleB){
				//将显示图像设为false
				showPanel.draw[1] = false;
				showAreaField.setText(""+ 0);
				showPerField.setText(""+ 0);
			}
			showPanel.repaint();
		}
	}
	
}
