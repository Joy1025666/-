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
		try (Connection conn = dbcs.getConnection(); // 获取数据库连接
				PreparedStatement pstmt = conn.prepareStatement(sql);) {// 实例化
			pstmt.setString(1, s); // 通配
			pstmt.executeUpdate();// 执行删除
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} 
	

}
