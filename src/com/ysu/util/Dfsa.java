package com.ysu.util;


import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.ysu.model.User;

public class Dfsa {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
      QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
     User user=qr.query("select * from t_user",new BeanHandler<User>(User.class));
	 System.out.println(user.getUserName());
	}

}
