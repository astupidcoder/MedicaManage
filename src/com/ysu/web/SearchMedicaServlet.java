package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.dao.MedicaDao;
import com.ysu.util.DbUtil;
import com.ysu.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SearchMedicaServlet extends HttpServlet{

	MedicaDao medicaDao=new MedicaDao();
	DbUtil dbUtil=new DbUtil();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchMedicaServlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String medicaName=request.getParameter("medicalName");
		Connection con=null;
		try {
			con=dbUtil.getCon();
			ResultSet rs=medicaDao.medicaList(con, medicaName,0);
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(rs);
			JSONObject result=new JSONObject();
			result.put("result",jsonArray);
			PrintWriter out=response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
}
