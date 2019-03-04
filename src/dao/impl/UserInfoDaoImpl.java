package dao.impl;

import java.util.List;

import dao.BookBase;
import dao.UserInfoDao;
import entity.UserInfo;

public class UserInfoDaoImpl extends BookBase implements UserInfoDao {

	@Override
	public int insert(UserInfo info) {
		String sql ="insert into userinfo(username,password) values(?,?)";
		Object[] params = {info.getUsername(),info.getPassword()};
		return executeUpdate(sql, params);
	}

	@Override
	public UserInfo query(String username, String password) {
		String sql = "select * from userinfo where username = ? and password = ?";
		Object[] params = {username,password};
		List<UserInfo> users = executeQuery(sql, params, UserInfo.class);
		if(users.isEmpty()){
			return null;
		}else{
			return users.get(0);
		}
	}
	
}
