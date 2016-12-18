package geometric3;
/*单独的创建一个能够画GeometircObject类的画板
 * 能够通过所传来的值判断用户的输入是否错误
 * 并且在桌面上提示用户输入有问题重新输入*/
import java.awt.Graphics;
import javax.swing.JPanel;
public class MyPanel3 extends JPanel {
	public GeometricObject[] geometic = new GeometricObject[2];
	public boolean[] draw = new boolean[3];

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (draw[2] == true) {
			g.drawString("IllegalArgument please enter again", getWidth() 
					/ 2, getHeight() / 2);    //在面板上提示用户输入不合法
		} else {
			for (int i = 0; i < geometic.length; i++) {
				if (geometic[i] != null) {
					if (draw[i]) {
						//geometic[i].draw(this); //通过传递this值需要通过不断地刷新才能够看到画出的图像
						geometic[i].draw(g, getWidth(), getHeight());
						
					} else {
						geometic[i].erase(this);
					}
					System.out.println(i);
				}			
			}
		}
	}
}
