package geometric3;
/*�����Ĵ���һ���ܹ���GeometircObject��Ļ���
 * �ܹ�ͨ����������ֵ�ж��û��������Ƿ����
 * ��������������ʾ�û�������������������*/
import java.awt.Graphics;
import javax.swing.JPanel;
public class MyPanel3 extends JPanel {
	public GeometricObject[] geometic = new GeometricObject[2];
	public boolean[] draw = new boolean[3];

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (draw[2] == true) {
			g.drawString("IllegalArgument please enter again", getWidth() 
					/ 2, getHeight() / 2);    //���������ʾ�û����벻�Ϸ�
		} else {
			for (int i = 0; i < geometic.length; i++) {
				if (geometic[i] != null) {
					if (draw[i]) {
						//geometic[i].draw(this); //ͨ������thisֵ��Ҫͨ�����ϵ�ˢ�²��ܹ�����������ͼ��
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
