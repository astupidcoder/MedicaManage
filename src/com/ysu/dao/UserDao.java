package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ysu.model.User;

public class UserDao {

	public UserDao() {
		// TODO Auto-generated constructor userb
	}

	public User userLogin(Connection con,User user)throws Exception{
		
			String sql="select * from t_user where userName=? and password=?";
			User u=null;
					
				PreparedStatement pstmt=con.prepareStatement(sql);
				pstmt.setString(1,user.getUserName());
				pstmt.setString(2,user.getPwd());
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					u=new User();
					u.setUserId(rs.getInt("userId"));
					u.setUserName(rs.getString("userName"));
					u.setPwd(rs.getString("password"));
					u.setTel(rs.getString("tel"));
					u.setEmail(rs.getString("email"));
				}
				return u;
	}
	
	public boolean pwdUpdate(Connection con,String pwd,int userId)throws Exception{
		
		String sql="update t_user set password=?where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,pwd);
		pstmt.setInt(2,userId);
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
