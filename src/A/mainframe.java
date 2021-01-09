package A;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;

public class mainframe {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe window = new mainframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JDesktopPane desktopPane = new JDesktopPane();
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("123");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				CF1 cf1=new CF1();//实例化子窗体
				CF1 cf1=CF1.GetInstance();
				desktopPane.add(cf1);//将子窗体加入主窗体的顶层面板中
				cf1.setVisible(true);//设置子窗体可见
				try {
				cf1.setSelected(true);//选中子窗体
				} catch (PropertyVetoException e1) {
				e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(151, 96, 123, 29);
		desktopPane.add(btnNewButton);
	}

}
