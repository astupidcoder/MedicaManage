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

/**
 * Servlet implementation class AddMedicaServlet
 */
public class AddMedicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DbUtil dbUtil=new DbUtil();
       MedicaDao medicaDao=new MedicaDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMedicaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out=response.getWriter();
				String name=request.getParameter("name");
				int classId=Integer.parseInt(request.getParameter("className"));
				String price=request.getParameter("price");
				String volume=request.getParameter("volume");
				String pdate=request.getParameter("pdate");
				String producer=request.getParameter("producer");
				Connection con=null;
				try {
					con=dbUtil.getCon();
 MedicaInfo medicaInfo=new MedicaInfo(0,name,classId,Double.parseDouble(price),Float.parseFloat(volume),DateUtil.formatString(pdate, "yyyy-MM-dd"),producer);
               JSONObject jsonObject=new JSONObject();
					if(medicaDao.medicaAdd(con, medicaInfo)) {
						jsonObject.put("success",true);
						//out.println("<script>alert('添加成功');</script>");
						//response.setHeader("refresh","1;url=addMedica.jsp");
					}else{
						jsonObject.put("success",false);
						//out.println("<script>alert('添加失败')</script>");
					};
					
					out.println(jsonObject);
					
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
