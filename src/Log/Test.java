package Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.jsu.oyj.dao.PetsDAO;
import cn.edu.jsu.oyj.dbc.DatabaseConnectionSql;
import cn.edu.jsu.oyj.vo.Pets;

public class Test{
	public static void main(String args[]) {
//		List<Pets> p=new PetsDAO().getAllPetData();
//		for(Pets s:p) {
//			System.out.println(s.getNum());
//		}
//		DatabaseConnectionSql dbc=new DatabaseConnectionSql();
//		Connection con=dbc.getConnection();
//		String sql="update test set ?=? where id=1";
//		try {
//			PreparedStatement pst=con.prepareStatement(sql);
//			pst.setString(1,"id");
//			pst.setInt(2,100);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		
////		
//		long time = new Date().getTime();
//		SimpleDateFormat f= new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
//		String dateTime = f.format(time);
//		System.out.println(dateTime);
//		Date currentTime = new Date();
//		System.out.println(currentTime);
//		String s="编号";
//		System.out.println("编号重量热度价格".contains(s));
		List<Pets> p = new PetsDAO().getAllPetData();
		for (Pets s : p) {
//			if (!s.getNum().equals((idtext.getText()))) {
//			} 
			System.out.println(s.getNum().equals("00203"));
		}
	}
}