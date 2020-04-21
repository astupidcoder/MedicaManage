package com.ysu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
		// TODO Auto-generated constructor stub
	}
  public static String formatDate(Date date,String format)throws Exception{
	  
	  String result="";
	  SimpleDateFormat sdf=new SimpleDateFormat(format);
	  result=sdf.format(date);
	  return result;
  }
  
public static Date formatString(String str,String format)throws Exception{
	  
	  SimpleDateFormat sdf=new SimpleDateFormat(format);
	  return sdf.parse(str);
  }
}
