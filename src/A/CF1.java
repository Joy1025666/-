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
	private static CF1 cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���

	public static synchronized CF1 GetInstance() {
		if (cframe == null) {
			cframe = new CF1();
		}
		return cframe;
	}

	private CF1() {// �����Ӵ��幹�췽��Ϊ˽�У���������ͨ��Design����
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
				CCF1 cf1=new CCF1();//ʵ�����Ӵ���
				desktopPane.add(cf1);//���Ӵ������������Ķ��������
				cf1.setVisible(true);//�����Ӵ���ɼ�
				try {
				cf1.setSelected(true);//ѡ���Ӵ���
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
