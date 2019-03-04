package utiles;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
	static String url = null;
	static String user = null;
	static String password = null;
	static{
		Properties pros = new Properties();
		try {
			InputStream is = JdbcUtils.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
			pros.load(is);
			is.close();
			//1.加载驱动
			Class.forName(pros.getProperty("driver"));
			url = pros.getProperty("url");
			user = pros.getProperty("user");
			password = pros.getProperty("password");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//获取连接
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new RuntimeException("获取连接失败");
		}
	}
	
	//释放资源
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		try {
			if(rs != null){
				rs.close();
			}
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	

	
		
	}
}
