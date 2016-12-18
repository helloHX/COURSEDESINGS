package account3;

import javax.swing.*;
import java.awt.*;
public class ErrorJPanel extends JPanel{
	private int error;
	
	public void showError(int error){
		this.error = error;
		repaint();
	}
	
	protected void paintComponent(Graphics g){
		g.setColor(Color.RED);
		if(error == 0)return;//����ʾ�κδ���
		super.paintComponent(g);
		if(error == 1)//������ʽ����
			g.drawString("�����ʽ(6~10λ��ĸ������)����",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 2)//�˺Ż��������
			g.drawString("�˺Ż��������",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 3)//��ȡ����ֵ(��ֵ����100�ı���)����\n����֧������
			g.drawString("��ȡ����ֵ���� \n����֧������",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 4)//�ļ���ȡ����
			g.drawString("�ļ���ȡ����",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 5)//�˻�����ʧ��
			g.drawString("�˻�����ʧ��",this.getWidth() / 2, this.getHeight() / 2);
		if(error == 6)//�ɹ�
			g.drawString("�ɹ�",this.getWidth() / 2, this.getHeight() / 2);
		if(error == 7)//�������벻ͬ
			g.drawString("�������벻ͬ",this.getWidth() / 2, this.getHeight() / 2);
			
	}
	
	public void setError(int i){
		this.error = i;
	}
}
