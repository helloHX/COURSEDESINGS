package account3;
import java.awt.Graphics;
import java.awt.Image;


/*������*/
import javax.swing.*;
public class Panels extends JPanel{
	private ImageIcon ImageIcon = new ImageIcon("�ƽ�3.png");
	private Image image = ImageIcon.getImage();
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image != null){
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
	