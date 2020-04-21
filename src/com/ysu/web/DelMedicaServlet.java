package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ysu.dao.MedicaDao;
import com.ysu.util.DbUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class AddMedicaServlet
 */
public class DelMedicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       DbUtil dbUtil=new DbUtil();
       MedicaDao medicaDao=new MedicaDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelMedicaServlet() {
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
				String delIds=request.getParameter("delIds");
				System.out.println(delIds);
				Connection con=null;
				try {
					con=dbUtil.getCon();
					int delNums=medicaDao.medicaDel(con, delIds);
					JSONObject result=new JSONObject();
					if(delNums>0) {
						result.put("success", true);
					}else {
						result.put("success",false);
					}
					out.println(result);
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
