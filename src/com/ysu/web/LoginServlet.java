package com.ysu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ysu.dao.UserDao;
import com.ysu.model.User;
import com.ysu.util.DbUtil;
import com.ysu.util.StringUtil;

public class LoginServlet extends HttpServlet{

	
	public LoginServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String hd=request.getParameter("hd");
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)) {
			User user=new User(userName,password);
			request.setAttribute("user", user);
			request.setAttribute("error", "用户名或密码不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		if(hd.equals("0")) {
			out.println("<script>alert('滑块验证失败！！');</script>");
             response.setHeader("refresh","0;url=login.jsp");
			return;
		}
		if(hd.equals("")) {
			out.println("<script>alert('请先完成滑块验证！！');</script>");
            response.setHeader("refresh","0;url=login.jsp");
			return;
		}
		DbUtil dbUtil=new DbUtil();
		Connection con=null;
		
		
		User user=new User(userName,password);
		UserDao stuDao=new UserDao();
		
		try {
			con=dbUtil.getCon();
			User currentUser=null;
			currentUser=stuDao.userLogin(con, user);
			if(currentUser!=null) {
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentUser);
				
				Cookie cookie=new Cookie("user","student"+"-"+userName+"-"+password);
				cookie.setMaxAge(60*60*24*30);
				response.addCookie(cookie);
				
				response.sendRedirect("main.jsp");
				
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("user",user);
		       request.setAttribute("error", "用户名或密码错误");
		       request.getRequestDispatcher("login.jsp").forward(request, response);
			   }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
