package cn.itcast.chapter05.session.example02;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
 public void doGet(HttpServletRequest request,HttpServletResponse response)
 throws ServletException,IOException{
		 response.setContentType("text/html;Charset=utf-8");
		 String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 String checkCode = request.getParameter("check_code");
		 String savedCode = (String) request.getSession().getAttribute("check_code");
		 PrintWriter pW = response.getWriter();
		 if(("itcat").equals(username)&&("123").equals(password)&&checkCode.equals(savedCode)) {
			 User user = new User();
			 user.setUsername(username);
			 user.setPassword(password);
			 request.getSession().setAttribute("uesr", user);
			 response.sendRedirect("/chapter05/IndexServlet");
			 
		 }else if (checkCode.equals(savedCode)) {
			pW.write("´íÎó");
		}else {
			pW.write("´íÎó");
		}
}
    public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
		doGet(request, response);
	}
    }