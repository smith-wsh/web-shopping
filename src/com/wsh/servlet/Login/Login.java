package com.wsh.servlet.Login;

import java.io.IOException;
import java.io.PrintWriter;

import com.wsh.Implements.UserImplement;
import com.wsh.bean.User;
import com.wsh.factory.DBImplements;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String uname = request.getParameter("uname");
		String passwd = request.getParameter("passwd");
		User user = null;
		String message = "";
		String path = "jsp/login.jsp";
		try {
			UserImplement dao = DBImplements.getUserServiceInstance();
			if ((user = dao.queryByName(uname)) != null) {
				if (user.getPasswd().equals(passwd)) {
					String lastLoginTime = user.getLastLogin();
					dao.editLoginTime(user.getUid());
					request.getSession().setAttribute("uname", uname);
					request.getSession().setAttribute("uid",
							String.valueOf(user.getUid()));
					request.getSession().setAttribute("lastLoginTime",
							lastLoginTime);
					path = "index.jsp";
				} else {
					message = "�����������������";
				}
			} else {
				message = "�����ڸ��û�������������";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String truePath = request.getContextPath() + "/" + path;
		if ("".equals(message)) {
			response.sendRedirect(truePath);
		} else {
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
			out.println("<HTML>");
			out.println("  <HEAD><TITLE>��¼����</TITLE>");
			out.println("<meta http-equiv=\"refresh\" content=\"5;url="
					+ truePath + "\">");
			out.println("</HEAD>");
			out.println("  <BODY>");
			out.print("<div align=\"center\">");
			out.print(message);
			out.print("<br/>");
			out.print("���Զ���ת����¼ҳ��");
			out.print("<br/>");
			out.print("�������ﷵ�أ�");
			out.print("<a href=\"" + truePath+"\"" +">��¼"+"</a>");
			out.print("</div>");
			out.println("  </BODY>");
			out.println("</HTML>");
			out.flush();
			out.close();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
