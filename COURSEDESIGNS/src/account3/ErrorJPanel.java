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
		if(error == 0)return;//不显示任何错误
		super.paintComponent(g);
		if(error == 1)//密码或格式错误
			g.drawString("密码格式(6~10位字母或数字)错误",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 2)//账号或密码错误
			g.drawString("账号或密码错误",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 3)//存取款数值(正值且是100的倍数)错误\n或当日支出超额
			g.drawString("存取款数值错误 \n或当日支出超额",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 4)//文件读取错误
			g.drawString("文件读取错误",this.getWidth() / 5, this.getHeight() / 2);
		if(error == 5)//账户保存失败
			g.drawString("账户保存失败",this.getWidth() / 2, this.getHeight() / 2);
		if(error == 6)//成功
			g.drawString("成功",this.getWidth() / 2, this.getHeight() / 2);
		if(error == 7)//两次密码不同
			g.drawString("两次密码不同",this.getWidth() / 2, this.getHeight() / 2);
			
	}
	
	public void setError(int i){
		this.error = i;
	}
}
