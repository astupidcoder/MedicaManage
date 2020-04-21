package com.ysu.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil {

	public JsonUtil() {
		// TODO Auto-generated constructor stub
	}

	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		ResultSetMetaData meta=rs.getMetaData();
		int num=meta.getColumnCount();
		JSONArray jsonArray=new JSONArray();
		while(rs.next()) {
			JSONObject jsonObject=new JSONObject();
			for(int i=1;i<=num;i++) {
				Object o=rs.getObject(i);
				if(o instanceof Date) {
					jsonObject.put(meta.getColumnName(i), DateUtil.formatDate((Date)o,"yyyy-MM-dd"));
				}else
				jsonObject.put(meta.getColumnName(i), rs.getObject(i));
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
