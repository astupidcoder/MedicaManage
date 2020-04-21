package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.dao.MedicaDao;
import com.ysu.model.MedicaInfo;
import com.ysu.util.DateUtil;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;

public class ModifyMedicaServlet extends HttpServlet{
DbUtil dbUtil=new DbUtil();
MedicaDao medicaDao=new MedicaDao();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModifyMedicaServlet() {
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
		PrintWriter out=response.getWriter();
		int medicaId=Integer.parseInt(request.getParameter("medicaId"));
		String name=request.getParameter("name");
		int classId=Integer.parseInt(request.getParameter("className"));
		double price=Double.parseDouble(request.getParameter("price"));
		float volume=Float.parseFloat(request.getParameter("volume"));
		String pdate=request.getParameter("pdate");
		String producer=request.getParameter("producer");
		MedicaInfo medicaInfo=null;
		try {
			medicaInfo = new MedicaInfo(medicaId,name,classId,price,volume,DateUtil.formatString(pdate, "yyyy-MM-dd"),producer);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			int result=medicaDao.medicaUpdate(con,medicaInfo);
			JSONObject jsonObject=new JSONObject();
			if(result>0) {
				jsonObject.put("success", true);
				//out.println("<script>alert('修改成功');</script>");
				//response.setHeader("refresh","1;url=medica.jsp");
				//response.sendRedirect("medica.jsp");
			}else {
				jsonObject.put("success", false);
				//out.println("<script>alert('修改失败');</script>");
				//response.setHeader("refresh","3;url=modifyMedica.jsp");
			}
			out.println(jsonObject);
			out.flush();
			out.close();
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
	}

