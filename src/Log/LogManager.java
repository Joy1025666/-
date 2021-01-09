package Log;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogManager extends JInternalFrame {

	private JTextField usernametext;
	private JPasswordField passwordtext;
	private static LogManager cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���

	public static synchronized LogManager GetInstance() {
		if (cframe == null) {
			cframe = new LogManager();
		}
		return cframe;
	}

	private LogManager() {
		try {
			setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setTitle("��¼");
		setIconifiable(true);
		setClosable(true);
		try {
			setSelected(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// �����Ӵ��幹�췽��Ϊ˽�У���������ͨ��Design����
		setResizable(true);
		setBounds(100, 100, 692, 487);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		JLabel username = new JLabel("�û���");
		username.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		username.setBounds(61, 87, 99, 48);
		desktopPane.add(username);

		JLabel password = new JLabel("����");
		password.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		password.setBounds(61, 178, 99, 48);
		desktopPane.add(password);

		// �û�����ʾ��ǩ
		JLabel userMsg = new JLabel("");
		userMsg.setForeground(Color.RED);
		userMsg.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		userMsg.setBounds(502, 87, 174, 48);
		desktopPane.add(userMsg);

		usernametext = new JTextField();
		usernametext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (usernametext.getText().length() == 0) {
					userMsg.setText("�û�����Ϊ��");
				} else if (!usernametext.getText().matches("[a-zA-Z0-9_]{3,15}")) { // �û���(3-15λ,��ĸ,�����»���)
					userMsg.setText("�û������Ϸ�");
				} else
					userMsg.setText("");
			}
		});
		usernametext.setBounds(175, 94, 297, 42);
		desktopPane.add(usernametext);
		usernametext.setColumns(10);

		// ������ʾ��ǩ
		JLabel passMsg = new JLabel("");
		passMsg.setForeground(Color.RED);
		passMsg.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		passMsg.setBounds(502, 178, 174, 48);
		desktopPane.add(passMsg);
		passwordtext = new JPasswordField();
		passwordtext.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (passwordtext.getText().length() == 0) {
					passMsg.setText("���벻Ϊ��");
				} else if (!passwordtext.getText().matches("[a-zA-Z0-9_]{3,15}")) {
					passMsg.setText("���벻�Ϸ�");
				} else
					passMsg.setText("");
			}
		});
		passwordtext.setBounds(175, 186, 297, 40);
		desktopPane.add(passwordtext);

		JButton surebtn = new JButton("ȷ��");
		surebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DatabaseConnectionSql dcu = new DatabaseConnectionSql();
				String sql = "select *from manager";
				try (Connection conn = dcu.getConnection(); // ��ȡ���ݿ��
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����
					ResultSet rs = pstmt.executeQuery();
					String user = null;
					String pass = null;
					Map<String, String> data = new HashMap<>();
					while (rs.next()) {
						user = rs.getString(1);
						pass = rs.getString(2);
						data.put(user, pass);
					}
					Set<String> u = data.keySet();
					boolean flag = false;
					for (String s : u) {
						if (s.equals(usernametext.getText()) && data.get(s).equals(passwordtext.getText())&&userMsg.getText()!=""&&passMsg.getText()!="") {
							flag = true;
							JOptionPane.showMessageDialog(null, "��¼�ɹ�����ǰ����������");
							break;
						}
//						System.out.println(s.equals(usernametext.getText())+","+data.get(s).equals(passwordtext.getText()));
					}
					if (flag == false) {
						JOptionPane.showMessageDialog(null, "��¼ʧ�ܣ�������");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		surebtn.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		surebtn.setBounds(334, 285, 102, 45);
		desktopPane.add(surebtn);

		JButton cancelbtn = new JButton("ȡ��");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usernametext.setText("");
				passwordtext.setText("");
				userMsg.setText("");
				passMsg.setText("");
			}
		});
		cancelbtn.setHorizontalAlignment(SwingConstants.LEADING);
		cancelbtn.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		cancelbtn.setBounds(115, 285, 92, 45);
		desktopPane.add(cancelbtn);

	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogManager frame = new LogManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
