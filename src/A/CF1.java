package A;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class CF1 extends JInternalFrame {
	private static CF1 cframe = null;// 定义子窗体为私有
	// 静态公开方法，只产生一个对象，synchronized保证线程案例

	public static synchronized CF1 GetInstance() {
		if (cframe == null) {
			cframe = new CF1();
		}
		return cframe;
	}

	private CF1() {// 设置子窗体构造方法为私有，下续代码通过Design生成
		setClosable(true);
//	setFrameIcon(new ImageIcon(ChildFrame.class.getResource("/icon/j11.png")));
		setResizable(true);
		setTitle("\u7EC4\u4EF6\u6807\u7B7E\u4F7F\u7528");
		setBounds(100, 100, 770, 513);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CCF1 cf1=new CCF1();//实例化子窗体
				desktopPane.add(cf1);//将子窗体加入主窗体的顶层面板中
				cf1.setVisible(true);//设置子窗体可见
				try {
				cf1.setSelected(true);//选中子窗体
				} catch (PropertyVetoException e1) {
				e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(293, 189, 123, 29);
		desktopPane.add(btnNewButton);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CF1 frame = new CF1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
