package countChar3;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import countChar2.Histogram;

public class ShowFrequencyAndScanFile extends JFrame {
	private JTextArea jta;
	private JButton jbtShowHistogram = new JButton("ͳ���ı���");
	private JButton jbScan = new JButton("���");
	private JButton jbShowPathC = new JButton("ͳ���ļ�");
	private JTextField jtFilePath = new JTextField(20);

	private JFrame jfScan = new JFrame();
	private ScanFile scanpanel = new ScanFile();
	private Histogram histogram = new Histogram();
	private JFrame histogramFrame = new JFrame();

	public ShowFrequencyAndScanFile() {
		JScrollPane scrollPanel = new JScrollPane(jta = new JTextArea());
		jtFilePath.setEditable(false);
		JPanel tPanel = new JPanel();// ͳ��TextArea��ť
		JPanel fPanel = new JPanel(new BorderLayout());// �ļ�ѡ�Ų���ʾͳ��
		tPanel.add(jbtShowHistogram);
		fPanel.add(jtFilePath, BorderLayout.WEST);
		fPanel.add(jbScan, BorderLayout.CENTER);
		fPanel.add(jbShowPathC, BorderLayout.EAST);

		scrollPanel.setPreferredSize(new Dimension(300, 200));
		jta.setWrapStyleWord(true);// ��ʾ�ı����Զ�����
		jta.setLineWrap(true);// ��ʾ�ı����Ե��ʻ���
		jtFilePath.setEditable(false);
		
		add(scrollPanel, BorderLayout.NORTH);
		add(tPanel, BorderLayout.CENTER);
		add(fPanel, BorderLayout.SOUTH);
		jfScan.add(scanpanel);
		jbtShowHistogram.addActionListener(scanpanel);
		jbScan.addActionListener(scanpanel);
		jbShowPathC.addActionListener(scanpanel);
		histogramFrame.add(histogram);
		histogramFrame.pack();
		histogramFrame.setTitle("Histogram");

	}

	private int[] countLetters() {
		int[] count = new int[26];

		String text = jta.getText();

		for (int i = 0; i < text.length(); i++) {
			char character = text.charAt(i);

			if ((character >= 'A') && (character <= 'Z')) {
				count[character - 'A']++;
			} else if ((character >= 'a') && (character <= 'z')) {
				count[character - 'a']++;
			}
		}
		return count;
	}
	
	private int[] countLetter(String path) throws IOException{
		int[] count = new int[26];
		BufferedInputStream fileInput = new BufferedInputStream(
				new FileInputStream(new File(path)));
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
		return count;
	}

	public static void main(String[] args) {
		ShowFrequencyAndScanFile frame = new ShowFrequencyAndScanFile();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("MultipleWindowsDemo");
		frame.pack();
		frame.setVisible(true);
	}

	class ScanFile extends JPanel implements ActionListener, MouseListener,
			ItemListener {

		private JButton jbUp = new JButton("��һ��");
		private JButton jbSure = new JButton("ȷ��");
		private JComboBox jcbPath = new JComboBox();// ʢ��ϵͳ����
		private JTable jtFile;// �ļ����
		private MyTableModel dtmFile = new MyTableModel();
		private JTextField jtcurrentPath;// ��ǰ�ļ�λ��
		private File path;
		private String currentPath;
		private boolean ok;

		public ScanFile() {
			this.setLayout(new BorderLayout());
			JPanel panel1 = new JPanel(new BorderLayout());
			JPanel panel2 = new JPanel(new BorderLayout());

			panel1.add(jbUp, BorderLayout.WEST);
			panel1.add(jcbPath, BorderLayout.EAST);
			jbUp.addActionListener(this);
			jbSure.addActionListener(this);
			jbSure.setBackground(Color.GRAY);// ��ǰ�����ļ���ʱ���ܱ�ͳ�Ʊ�Ϊ��ɫ
			jcbPath.addItemListener(this);
			dtmFile.addColumn("�ļ���");
			dtmFile.addColumn("����");
			jtFile = new JTable(dtmFile);
			jtFile.addMouseListener(this);
			jtFile.setShowGrid(false);
			jtcurrentPath = new JTextField();
			jtFile.getTableHeader().setReorderingAllowed(false);   //���������ƶ�   
			jtFile.getTableHeader().setResizingAllowed(false);   //�����������	
		   jtcurrentPath.setEditable(false);
			panel2.add(jtcurrentPath, BorderLayout.WEST);
			panel2.add(jbSure, BorderLayout.EAST);
			this.add(panel1, BorderLayout.NORTH);
			this.add(new JScrollPane(jtFile), BorderLayout.CENTER);
			this.add(panel2, BorderLayout.SOUTH);

			File[] roots = File.listRoots();
			for (int i = 0; i < roots.length; i++)// չʾ����
			{
				String rootPath = roots[i].getAbsolutePath();
				jcbPath.addItem(rootPath + "");
			}
			path = new File(System.getProperty("user.dir"));
			fillTable(path);
		}

		public void fillTable(File path) {
			jtcurrentPath.setText(path.getAbsolutePath());// ��ʾ��ǰ����λ��
			if (path.isDirectory()) {
				jbSure.setBackground(Color.GRAY);
			}
			File[] files = path.listFiles();// ��ȡ��ǰĿ¼�������ļ���
			dtmFile.setRowCount(0);// ������ļ�Ŀ¼
			for (int i = 0; i < files.length; i++) {
				String name = files[i].getName();
				if (files[i].isDirectory()) {
					dtmFile.addRow(new String[] { name, "�ļ���" });
				} else if (name.lastIndexOf(".") != -1) {
					dtmFile.addRow(new String[] {
							name.substring(0, name.lastIndexOf(".")),
							name.substring(name.lastIndexOf(".") + 1) });
				} else {// ����Ҳ���"."
					dtmFile.addRow(new String[] { name, "" });
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {/*ͨ�����ε���б��е�ĳ���ļ�����ͨ�����ǰ��׺ת��Ϊ·����
		����fillTable ˢ�±��Ϊ��ǰ��ѡ�ļ�����*/
			if (e.getClickCount() == 2) {
				int row = ((JTable) e.getSource()).getSelectedRow();
				if (((JTable) e.getSource()).getValueAt(row, 1).toString()
						.equals("�ļ���")) {// ���ҽ������ļ����ǿ��Լ�����
					path = new File(jtcurrentPath.getText()
							+ "\\"
							+ ((JTable) e.getSource()).getValueAt(row, 0)
									.toString());
					fillTable(path);
				} else {// ��ı��ѡ�ļ���¼���ȴ��û�ȷ��
					jbSure.setBackground(new Color(238, 238, 238));// ����ɫ�ķ�ʽ��ʾ�û�����ȷ��
					currentPath = jtcurrentPath.getText()
							+ "\\"
							+ ((JTable) e.getSource()).getValueAt(row, 0)
									.toString()
							+ "."
							+ ((JTable) e.getSource()).getValueAt(row, 1)
									.toString();
					ok = true;//�����ǰ�ļ��ǲ����ļ����⵱ǰ�ļ��ɱ���ȡ
				}
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jbtShowHistogram) {
				int[] count = countLetters();
				histogram.showHistogram(count);
				histogramFrame.setSize(400, 400);
				histogramFrame.setVisible(true);
			}
			if (e.getSource() == jbScan) {
				jfScan.pack();
				jfScan.setVisible(true);
			}
			if (e.getSource() == jbUp) {
				
				jbUp.setBackground(new Color(238,238,238));
				if(path.getParentFile() != null){
					path = path.getParentFile();
					fillTable(path);
				}
				else{//����Ѿ�����ϵͳ����ײ�������ٵ���fillTable
					jbUp.setBackground(Color.gray);
				}
				
			}
			if (e.getSource() == jbShowPathC) {
				int[] count = null;
				try {
					count = countLetter(jtFilePath.getText());
				} catch (IOException e1) {//��׽���ļ���������
					e1.printStackTrace();
				}
				histogram.showHistogram(count);
				histogramFrame.setSize(400, 400);
				histogramFrame.setVisible(true);
			}
			if (e.getSource() == jbSure) {
				if (ok) {// ���;���Ѿ����㲻���ļ����û�����ȷ������
					jtFilePath.setText(currentPath);
					System.out.print(currentPath);
					((JFrame) (this.getRootPane().getParent())).dispose();
				}
			}

		}

		class MyTableModel extends DefaultTableModel {// �Զ���һ����С���ɱ仯��TableModel
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			String choicePath = (String) jcbPath.getSelectedItem();
			// System.out.print(choicePath.substring(0,choicePath.indexOf("\\")));
			path = new File(choicePath);
			fillTable(path);

		}
	}

}
