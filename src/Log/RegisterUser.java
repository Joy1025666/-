package Log;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterUser extends JInternalFrame {
	private JTextField registerName;
	private JTextField registerPass;
	private JTextField registerAgain;
	private static RegisterUser cframe = null;// �����Ӵ���Ϊ˽��
	// ��̬����������ֻ����һ������synchronized��֤�̰߳���

	public static synchronized RegisterUser GetInstance() {
		if (cframe == null) {
			cframe = new RegisterUser();
		}
		return cframe;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private RegisterUser() {
		try {
			setClosed(true);
		} catch (PropertyVetoException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		setTitle("ע��");
		setIconifiable(true);
		setClosable(true);
		try {
			setSelected(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setBounds(100, 100, 725, 445);
		getContentPane().setLayout(null);

		JLabel username = new JLabel("�û���");
		username.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		username.setBounds(94, 55, 99, 48);
		getContentPane().add(username);

		JLabel password = new JLabel("����");
		password.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		password.setBounds(94, 138, 99, 48);
		getContentPane().add(password);

		JLabel again = new JLabel("ȷ������");
		again.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		again.setBounds(94, 214, 121, 48);
		getContentPane().add(again);

		// �û�����ʾ��ǩ
		JLabel userMsg = new JLabel("");
		userMsg.setForeground(Color.RED);
		userMsg.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		userMsg.setBounds(537, 55, 174, 48);
		getContentPane().add(userMsg);

		registerName = new JTextField();
		registerName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				DatabaseConnectionSql dcu = new DatabaseConnectionSql();
				String sql = "select *from user";
				try (Connection conn = dcu.getConnection(); // ��ȡ���ݿ��
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����
					ResultSet rs = pstmt.executeQuery();
					String user = null;
					String pass = null;
					List<String> data = new ArrayList<>();
					while (rs.next()) {
						user = rs.getString(1);
						pass = rs.getString(2);
						data.add(user);
					}
					for (String s : data) {
						if (s.equals(registerName.getText())) {
							userMsg.setText("�û����Ѵ���");
						} else if (registerName.getText().length() == 0) {
							userMsg.setText("�û�����Ϊ��");
						} else if (!registerName.getText().matches("[a-zA-Z0-9_]{3,15}")) { // �û���(6-20λ,��ĸ,�����»���)
							userMsg.setText("�û������Ϸ�");
						} else
							userMsg.setText("");
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		registerName.setColumns(10);
		registerName.setBounds(225, 61, 297, 42);
		getContentPane().add(registerName);

		// ������ʾ��ǩ
		JLabel passMsg = new JLabel("");
		passMsg.setForeground(Color.RED);
		passMsg.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		passMsg.setBounds(537, 132, 174, 48);
		getContentPane().add(passMsg);
		registerPass = new JTextField();
		registerPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (registerPass.getText().length() == 0) {
					passMsg.setText("���벻Ϊ��");
				} else if (!registerPass.getText().matches("[a-zA-Z0-9_]{6,20}")) {
					passMsg.setText("���벻�Ϸ�");
				} else
					passMsg.setText("");
			}
		});
		registerPass.setColumns(10);
		registerPass.setBounds(225, 138, 297, 42);
		getContentPane().add(registerPass);

		// ȷ�������ǩ
		JLabel againMsg = new JLabel("");
		againMsg.setForeground(Color.RED);
		againMsg.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		againMsg.setBounds(537, 214, 174, 48);
		getContentPane().add(againMsg);
		registerAgain = new JTextField();
		registerAgain.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!registerPass.getText().equals(registerAgain.getText())) {
					againMsg.setText("���벻һ��");
				} else
					againMsg.setText("");
			}
		});
		registerAgain.setColumns(10);
		registerAgain.setBounds(225, 220, 297, 42);
		getContentPane().add(registerAgain);

		JButton cancelbtn = new JButton("ȡ��");
		cancelbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registerName.setText("");
				registerPass.setText("");
				registerAgain.setText("");
				userMsg.setText("");
				passMsg.setText("");
				againMsg.setText("");
			}
		});
		cancelbtn.setHorizontalAlignment(SwingConstants.LEADING);
		cancelbtn.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		cancelbtn.setBounds(101, 309, 92, 45);
		getContentPane().add(cancelbtn);

		JButton surebtn = new JButton("ȷ��");
		surebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userMsg.getText()==""&&passMsg.getText()==""&&againMsg.getText()=="") {
					DatabaseConnectionSql dbcm = new DatabaseConnectionSql();
				String name = registerName.getText();
				String pass = registerPass.getText();
				String sql = "insert into user(username,password) values(?,?)";// ʹ��ռλ������������
				try (Connection conn = dbcm.getConnection(); // ��ȡ���ݿ��
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����
					pstmt.setString(1, name);// �����1��ռλ��������
					pstmt.setString(2, pass);// �����2��ռλ��������
//						pstmt.setString(3, xb);// �����3��ռλ��������
//						pstmt.setInt(4, cj);// �����4��ռλ��������
					pstmt.executeUpdate();// ִ�в������
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		surebtn.setFont(new Font("΢���ź�", Font.PLAIN, 25));
		surebtn.setBounds(390, 309, 102, 45);
		getContentPane().add(surebtn);

	}
}
