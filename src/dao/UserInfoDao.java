package dao;

import entity.UserInfo;

public interface UserInfoDao {
	int insert(UserInfo info);
	
	UserInfo query(String username,String password);
}
