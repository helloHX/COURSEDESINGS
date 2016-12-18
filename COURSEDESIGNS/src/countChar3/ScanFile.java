package countChar3;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ScanFile extends JPanel implements ActionListener, MouseListener,ItemListener {

	private JButton jbUp = new JButton("��һ��");
	private JButton jbSure = new JButton("ȷ��");
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
		dtmFile.addColumn("�ļ���");
		dtmFile.addColumn("����");
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
		for (int i = 0; i < roots.length; i++)// չʾ����
		{
			String rootPath = roots[i].getAbsolutePath();
			jcbPath.addItem(rootPath + "         ");
		}
		path = new File(System.getProperty("user.dir"));
		fillTable(path);
	}

	public static void main(String[] args) {
		JFrame jf = new JFrame("����");
		ScanFile panel = new ScanFile();
		jf.setSize(300, 400);
		jf.add(panel);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	public void fillTable(File path) {
		jtcurrentPath.setText(path.getAbsolutePath());// ��ʾ��ǰ����λ��
		if(path.isDirectory()){
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
			} else {//����Ҳ���"."
				dtmFile.addRow(new String[] { name, "" });
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
		int row = ((JTable)e.getSource()).getSelectedRow();
		if(((JTable)e.getSource()).getValueAt(row, 1).toString().equals("�ļ���")){//���ҽ������ļ����ǿ��Լ�����
			path = new File(jtcurrentPath.getText() + "\\" + ((JTable)e.getSource()).getValueAt(row,0).toString());
			fillTable(path);
		}
		else{//��ı��ѡ�ļ���¼���ȴ��û�ȷ��
			jbSure.setBackground(new Color(238,238,238));//����ɫ�ķ�ʽ��ʾ�û�����ȷ��
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

	class MyTableModel extends DefaultTableModel {// �Զ���һ����С���ɱ仯��TableModel
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public String getCurrentPath() {// ���ص�ǰ��ѡ�ļ�·��
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
