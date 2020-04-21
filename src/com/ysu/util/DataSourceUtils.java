package com.ysu.util;



import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSourceUtils {
	private static ComboPooledDataSource ds=new ComboPooledDataSource();
	
	/**
	 * 获取数据源
	 * @return 连接池
	 */
	public static DataSource getDataSource(){
		return ds;
	}
	
	
}
