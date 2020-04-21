package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MedicaClassDao {

	public MedicaClassDao() {
		// TODO Auto-generated constructor stub
	}

	public ResultSet medicaClassList(Connection con)throws Exception{
		String sql="select * from t_medicaclass";
		PreparedStatement pstmt=con.prepareStatement(sql);
		ResultSet result=pstmt.executeQuery();
		return result;
	}
}
