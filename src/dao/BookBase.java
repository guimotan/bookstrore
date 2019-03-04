package dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import utiles.JdbcUtils;

public class BookBase {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//通用增删改
	public int executeUpdate(String sql,Object[] params){
		int rows = 0;
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			rows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(conn, ps, rs);
		}
		return rows;
	}
	
	//通用查询
	public<T> List<T> executeQuery(String sql,Object[] params,Class<T> clz){
		List<T> lists = new ArrayList<>();
		try {
			conn = JdbcUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			//获取元数据
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			while(rs.next()){
				T t = clz.newInstance();
				for(int i=0;i<count;i++){//遍历每一列
					String name = md.getColumnName(i+1);
					Object value = rs.getObject(name);
					Field field = clz.getDeclaredField(name);
					field.setAccessible(true);
					if(value != null){
						field.set(t, value);
						
					}
				}
				lists.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtils.close(conn, ps, rs);
		}
		return lists;
		
	}
}
