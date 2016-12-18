package clock3;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.URL;
import javax.swing.*;
public class alarm extends JFrame implements ActionListener{
	private int currentAngle;
	private Sclock sp;
	private int minute;
	private int second;
	private int mLength = 2;
	private final static String picture = "����.png";
	private JTextField jtTimes = new JTextField("0",10);//�û����뵹��ʱ����
	private JButton jbBegain = new JButton("��ʼ");
    private Timer timer = new Timer(1000,this);
	private AudioClip audioClip;
	
	public alarm(){
		java.net.URL urlForAudio = getClass().getResource("1.wav");
		audioClip = Applet.newAudioClip(urlForAudio);//��ȡ����
		sp = new Sclock();
		ImageIcon imageIcon = new  ImageIcon(picture);
		Image  iamge= imageIcon.getImage();
		jbBegain.addActionListener(this);
		JPanel jpShow = new JPanel(new BorderLayout());
		jpShow.setOpaque(false);
		JPanel jpEnter = new JPanel(new BorderLayout());
		FlagAnthemPanel faImage = new FlagAnthemPanel(iamge);
		faImage.setLayout(new BorderLayout());
		jpShow.add (sp,BorderLayout.CENTER);
		jpEnter.add(jtTimes,BorderLayout.WEST);
		jpEnter.add(jbBegain,BorderLayout.CENTER);
		faImage.add(jpShow,BorderLayout.CENTER);
		faImage.add(jpEnter,BorderLayout.SOUTH);
		this.add(faImage,BorderLayout.CENTER);
		
	}
	
	 class FlagAnthemPanel extends JPanel {
		private Image image;
		public FlagAnthemPanel(Image image) {
			this.image = image;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0,getWidth(), getHeight(), this);
		}
	
	 }
	 
	 class Sclock extends JPanel{//�滭�����ֺ�ʱ�ӵ���ʱ
			
			public Sclock(){
				this.setOpaque(false);
			}
			
			public void paintComponent(Graphics g) {
				int clockRadius =Math.min(this.getWidth(), this.getHeight()) * 3/ 8 ;//���ݿ�Ⱥ͸߶����ð뾶
				int xCenter = (getWidth() * 5 / 8) ;
			    int yCenter =(getHeight() / 2) - clockRadius;
				super.paintComponent(g);
				g.setFont(new Font("SanSerif",Font.BOLD,120));//��������
				g.setColor(Color.red);
				g.drawString(minute + "",xCenter - 60 * (mLength + 1),yCenter + 120);
				g.setFont(new Font("SanSerif",Font.BOLD,20));
				g.drawString("m",xCenter - 30,yCenter +120);//��ӡm
				g.setColor(Color.white);
				g.drawOval(xCenter, yCenter, 2 * clockRadius,2 * clockRadius );
				g.setColor(Color.CYAN);
				g.fillOval(xCenter, yCenter,  2 * clockRadius, 2 * clockRadius);
				g.setColor(Color.green);
				g.fillArc(xCenter, yCenter,  2 * clockRadius,  2 * clockRadius, 90,currentAngle);
				g.setColor(Color.red);
				g.setFont(new Font("SanSerif",Font.BOLD,60));
				g.drawString(second + "",xCenter + clockRadius,yCenter + clockRadius + 30);
				
			}
		}
	 
		public void actionPerformed(ActionEvent e) {	
			if(minute <= 0){//������Ϊ��
				if(second == 0){//��ʼ��Ϊ��ʱ������ֹͣ��ת
					timer.stop();
					audioClip.loop();
				}
				
			} 
			else if(second == 0 && minute != 0){//�������Ϊ����Ӳ�Ϊ�������Ӽ�һ���Ӽ�60
					minute--;
					second +=60;
					currentAngle = -360;	
				}
			if(second != 0){//��ÿ�����Ӳ�Ϊ��ʱ������6
				currentAngle += 6;
				second--;
			    sp.repaint();
			}
			
			if(e.getSource() == jbBegain){
				double time= Double.parseDouble(jtTimes.getText());
				minute = (int)time;//���������ָ�����
				mLength = (minute + "").length();
				time = time - minute;
				second = (int)(time * 60);
				currentAngle = second * -6;
				audioClip.stop();
				timer.start();
			}
		}
		
		public static void main(String[] args){
			alarm frame = new alarm();
			frame.setSize(600,300);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
}