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
	private JButton jbtShowHistogram = new JButton("统计文本域");
	private JButton jbScan = new JButton("浏览");
	private JButton jbShowPathC = new JButton("统计文件");
	private JTextField jtFilePath = new JTextField(20);

	private JFrame jfScan = new JFrame();
	private ScanFile scanpanel = new ScanFile();
	private Histogram histogram = new Histogram();
	private JFrame histogramFrame = new JFrame();

	public ShowFrequencyAndScanFile() {
		JScrollPane scrollPanel = new JScrollPane(jta = new JTextArea());
		jtFilePath.setEditable(false);
		JPanel tPanel = new JPanel();// 统计TextArea按钮
		JPanel fPanel = new JPanel(new BorderLayout());// 文件选着并显示统计
		tPanel.add(jbtShowHistogram);
		fPanel.add(jtFilePath, BorderLayout.WEST);
		fPanel.add(jbScan, BorderLayout.CENTER);
		fPanel.add(jbShowPathC, BorderLayout.EAST);

		scrollPanel.setPreferredSize(new Dimension(300, 200));
		jta.setWrapStyleWord(true);// 表示文本不自动换行
		jta.setLineWrap(true);// 表示文本不对单词换行
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

		private JButton jbUp = new JButton("上一层");
		private JButton jbSure = new JButton("确定");
		private JComboBox jcbPath = new JComboBox();// 盛放系统分区
		private JTable jtFile;// 文件表格
		private MyTableModel dtmFile = new MyTableModel();
		private JTextField jtcurrentPath;// 当前文件位置
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
			jbSure.setBackground(Color.GRAY);// 当前件是文件夹时不能被统计变为灰色
			jcbPath.addItemListener(this);
			dtmFile.addColumn("文件名");
			dtmFile.addColumn("类型");
			jtFile = new JTable(dtmFile);
			jtFile.addMouseListener(this);
			jtFile.setShowGrid(false);
			jtcurrentPath = new JTextField();
			jtFile.getTableHeader().setReorderingAllowed(false);   //不可整列移动   
			jtFile.getTableHeader().setResizingAllowed(false);   //不可拉动表格	
		   jtcurrentPath.setEditable(false);
			panel2.add(jtcurrentPath, BorderLayout.WEST);
			panel2.add(jbSure, BorderLayout.EAST);
			this.add(panel1, BorderLayout.NORTH);
			this.add(new JScrollPane(jtFile), BorderLayout.CENTER);
			this.add(panel2, BorderLayout.SOUTH);

			File[] roots = File.listRoots();
			for (int i = 0; i < roots.length; i++)// 展示分区
			{
				String rootPath = roots[i].getAbsolutePath();
				jcbPath.addItem(rootPath + "");
			}
			path = new File(System.getProperty("user.dir"));
			fillTable(path);
		}

		public void fillTable(File path) {
			jtcurrentPath.setText(path.getAbsolutePath());// 显示当前工作位置
			if (path.isDirectory()) {
				jbSure.setBackground(Color.GRAY);
			}
			File[] files = path.listFiles();// 获取当前目录下所有文件名
			dtmFile.setRowCount(0);// 清除旧文件目录
			for (int i = 0; i < files.length; i++) {
				String name = files[i].getName();
				if (files[i].isDirectory()) {
					dtmFile.addRow(new String[] { name, "文件夹" });
				} else if (name.lastIndexOf(".") != -1) {
					dtmFile.addRow(new String[] {
							name.substring(0, name.lastIndexOf(".")),
							name.substring(name.lastIndexOf(".") + 1) });
				} else {// 如果找不到"."
					dtmFile.addRow(new String[] { name, "" });
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {/*通过两次点击列表中的某个文件将其通过添加前后缀转换为路径并
		调用fillTable 刷新表格为当前所选文件夹内*/
			if (e.getClickCount() == 2) {
				int row = ((JTable) e.getSource()).getSelectedRow();
				if (((JTable) e.getSource()).getValueAt(row, 1).toString()
						.equals("文件夹")) {// 当且仅当是文件夹是可以继续打开
					path = new File(jtcurrentPath.getText()
							+ "\\"
							+ ((JTable) e.getSource()).getValueAt(row, 0)
									.toString());
					fillTable(path);
				} else {// 则改变别选文件记录器等待用户确认
					jbSure.setBackground(new Color(238, 238, 238));// 以颜色的方式提示用户可以确定
					currentPath = jtcurrentPath.getText()
							+ "\\"
							+ ((JTable) e.getSource()).getValueAt(row, 0)
									.toString()
							+ "."
							+ ((JTable) e.getSource()).getValueAt(row, 1)
									.toString();
					ok = true;//如果当前文件是不是文件夹这当前文件可被读取
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
				else{//如果已经是在系统的最底层分区就再调用fillTable
					jbUp.setBackground(Color.gray);
				}
				
			}
			if (e.getSource() == jbShowPathC) {
				int[] count = null;
				try {
					count = countLetter(jtFilePath.getText());
				} catch (IOException e1) {//捕捉打开文件遇到问题
					e1.printStackTrace();
				}
				histogram.showHistogram(count);
				histogramFrame.setSize(400, 400);
				histogramFrame.setVisible(true);
			}
			if (e.getSource() == jbSure) {
				if (ok) {// 如果途径已经满足不是文件且用户给出确定邀请
					jtFilePath.setText(currentPath);
					System.out.print(currentPath);
					((JFrame) (this.getRootPane().getParent())).dispose();
				}
			}

		}

		class MyTableModel extends DefaultTableModel {// 自定义一个大小不可变化的TableModel
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
