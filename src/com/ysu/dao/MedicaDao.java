package com.ysu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.dbutils.QueryRunner;

import com.ysu.model.MedicaInfo;
import com.ysu.util.DataSourceUtils;
import com.ysu.util.DateUtil;

public class MedicaDao {

	
	public MedicaDao() {
		// TODO Auto-generated constructor stub
	}
  
	public ResultSet medicaList(Connection con,String medicalName,int medicaId)throws Exception{
		StringBuffer sql=new StringBuffer("select * from t_medicainfo a,t_medicaclass b where a.classId=b.classId");
		if(medicalName!=null) {
			sql.append(" and name like '%"+medicalName+"%'");
		}
		if(medicaId != 0) {
			sql.append(" and medicaId="+medicaId);
		}
		PreparedStatement pstmt=con.prepareStatement(sql.toString());
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	public boolean medicaAdd(Connection con,MedicaInfo medicaInfo)throws Exception{
		String sql="insert into t_medicainfo values (null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,medicaInfo.getName());
        pstmt.setInt(2,medicaInfo.getClassId());
        pstmt.setDouble(3,medicaInfo.getPrice());
        pstmt.setFloat(4,medicaInfo.getVolume());
        pstmt.setString(5,DateUtil.formatDate(medicaInfo.getPdate(), "yyyy-MM-dd"));
        pstmt.setString(6,medicaInfo.getProducer());
		int result=pstmt.executeUpdate();
		if(result==1) {
			return true;
		}else
		return false;
	}
	
	public int medicaDel(Connection con,String delIds)throws Exception{
		String sql="delete from t_medicainfo where medicaId in ("+delIds+")";
		PreparedStatement pstmt=con.prepareStatement(sql);
		int result=pstmt.executeUpdate();
		return result;
	}
	
	public int medicaUpdate(Connection con,MedicaInfo md)throws Exception{
		QueryRunner qr=new QueryRunner(DataSourceUtils.getDataSource());
		return qr.update("update t_medicainfo set name=?,classId=?,price=?,volume=?,pdate=?,producer=? where medicaId=?",md.getName(),md.getClassId(),md.getPrice(),md.getVolume(),md.getPdate(),md.getProducer(),md.getMedicaId());
	}
	
}
	
	

