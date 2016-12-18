package countChar3;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScanFile extends JPanel implements ActionListener, MouseListener,ItemListener {

	private JButton jbUp = new JButton("上一层");
	private JButton jbSure = new JButton("确定");
	private JComboBox jcbPath = new JComboBox();
	private JTable jtFile;
	private MyTableModel dtmFile = new MyTableModel();
	private JTextField jtcurrentPath;
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
		jbSure.setBackground(Color.GRAY);
		jcbPath.addItemListener(this);
		dtmFile.addColumn("文件名");
		dtmFile.addColumn("类型");
		jtFile = new JTable(dtmFile);
		jtFile.addMouseListener(this);
		jtFile.setShowGrid(false);
		jtcurrentPath = new JTextField();
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
			jcbPath.addItem(rootPath + "         ");
		}
		path = new File(System.getProperty("user.dir"));
		fillTable(path);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("测试");
		ScanFile panel = new ScanFile();
		jf.setSize(300, 400);
		jf.add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	public void fillTable(File path) {
		jtcurrentPath.setText(path.getAbsolutePath());// 显示当前工作位置
		if(path.isDirectory()){
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
			} else {//如果找不到"."
				dtmFile.addRow(new String[] { name, "" });
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
		int row = ((JTable)e.getSource()).getSelectedRow();
		if(((JTable)e.getSource()).getValueAt(row, 1).toString().equals("文件夹")){//当且仅当是文件夹是可以继续打开
			path = new File(jtcurrentPath.getText() + "\\" + ((JTable)e.getSource()).getValueAt(row,0).toString());
			fillTable(path);
		}
		else{//则改变别选文件记录器等待用户确认
			jbSure.setBackground(new Color(238,238,238));//以颜色的方式提示用户可以确定
			ok = true;
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
		if (e.getSource() == jbUp) {

			path = path.getParentFile();
			fillTable(path);
		}
		if (e.getSource() == jbSure) {
			if(ok)
				((JFrame)  ( this.getRootPane().getParent() )  ).dispose();
			  
		}

	}

	class MyTableModel extends DefaultTableModel {// 自定义一个大小不可变化的TableModel
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public String getCurrentPath() {// 返回当前所选文件路径
		return currentPath;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		String choicePath = (String) jcbPath.getSelectedItem();
		// System.out.print(choicePath.substring(0,choicePath.indexOf("\\")));
		path = new File(choicePath);
		fillTable(path);

	}
}
