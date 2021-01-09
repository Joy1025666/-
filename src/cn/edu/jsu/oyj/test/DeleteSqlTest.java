package cn.edu.jsu.oyj.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;

public class DeleteSqlTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String s = "02505";
	
		DatabaseConnectionSql dbcs = new DatabaseConnectionSql();
		String sql = "delete from pets where num=?";
		System.out.println("s:" + s);
		try (Connection conn = dbcs.getConnection(); // ��ȡ���ݿ�����
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// ʵ����
			pstmt.setString(1, s); // ͨ��
			pstmt.executeUpdate();// ִ��ɾ��
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	

}
