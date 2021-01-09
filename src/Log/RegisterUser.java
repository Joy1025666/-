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
	private static RegisterUser cframe = null;// 定义子窗体为私有
	// 静态公开方法，只产生一个对象，synchronized保证线程案例

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
		setTitle("注册");
		setIconifiable(true);
		setClosable(true);
		try {
			setSelected(true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		setBounds(100, 100, 725, 445);
		getContentPane().setLayout(null);

		JLabel username = new JLabel("用户名");
		username.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		username.setBounds(94, 55, 99, 48);
		getContentPane().add(username);

		JLabel password = new JLabel("密码");
		password.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		password.setBounds(94, 138, 99, 48);
		getContentPane().add(password);

		JLabel again = new JLabel("确认密码");
		again.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		again.setBounds(94, 214, 121, 48);
		getContentPane().add(again);

		// 用户名提示标签
		JLabel userMsg = new JLabel("");
		userMsg.setForeground(Color.RED);
		userMsg.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		userMsg.setBounds(537, 55, 174, 48);
		getContentPane().add(userMsg);

		registerName = new JTextField();
		registerName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				DatabaseConnectionSql dcu = new DatabaseConnectionSql();
				String sql = "select *from user";
				try (Connection conn = dcu.getConnection(); // 获取数据库接
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化
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
							userMsg.setText("用户名已存在");
						} else if (registerName.getText().length() == 0) {
							userMsg.setText("用户名不为空");
						} else if (!registerName.getText().matches("[a-zA-Z0-9_]{3,15}")) { // 用户名(6-20位,字母,数字下划线)
							userMsg.setText("用户名不合法");
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

		// 密码提示标签
		JLabel passMsg = new JLabel("");
		passMsg.setForeground(Color.RED);
		passMsg.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		passMsg.setBounds(537, 132, 174, 48);
		getContentPane().add(passMsg);
		registerPass = new JTextField();
		registerPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (registerPass.getText().length() == 0) {
					passMsg.setText("密码不为空");
				} else if (!registerPass.getText().matches("[a-zA-Z0-9_]{6,20}")) {
					passMsg.setText("密码不合法");
				} else
					passMsg.setText("");
			}
		});
		registerPass.setColumns(10);
		registerPass.setBounds(225, 138, 297, 42);
		getContentPane().add(registerPass);

		// 确认密码标签
		JLabel againMsg = new JLabel("");
		againMsg.setForeground(Color.RED);
		againMsg.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		againMsg.setBounds(537, 214, 174, 48);
		getContentPane().add(againMsg);
		registerAgain = new JTextField();
		registerAgain.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!registerPass.getText().equals(registerAgain.getText())) {
					againMsg.setText("密码不一致");
				} else
					againMsg.setText("");
			}
		});
		registerAgain.setColumns(10);
		registerAgain.setBounds(225, 220, 297, 42);
		getContentPane().add(registerAgain);

		JButton cancelbtn = new JButton("取消");
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
		cancelbtn.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		cancelbtn.setBounds(101, 309, 92, 45);
		getContentPane().add(cancelbtn);

		JButton surebtn = new JButton("确认");
		surebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userMsg.getText()==""&&passMsg.getText()==""&&againMsg.getText()=="") {
					DatabaseConnectionSql dbcm = new DatabaseConnectionSql();
				String name = registerName.getText();
				String pass = registerPass.getText();
				String sql = "insert into user(username,password) values(?,?)";// 使用占位符定义插入语句
				try (Connection conn = dbcm.getConnection(); // 获取数据库接
						PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化
					pstmt.setString(1, name);// 定义第1个占位符的内容
					pstmt.setString(2, pass);// 定义第2个占位符的内容
//						pstmt.setString(3, xb);// 定义第3个占位符的内容
//						pstmt.setInt(4, cj);// 定义第4个占位符的内容
					pstmt.executeUpdate();// 执行插入语句
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				}
			}
		});
		surebtn.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		surebtn.setBounds(390, 309, 102, 45);
		getContentPane().add(surebtn);

	}
}
