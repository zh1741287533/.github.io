package cn.itcast.chapter05.session.example02;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class IndexServlet extends HttpServlet{
	
public void doGet(HttpServletRequest request,HttpServletResponse response)throws  ServletException,IOException{
	response.setContentType("text/html;Character=utf-8");
	HttpSession session = request.getSession();
	User user = (User) session.getAttribute("user");
	if (user == null) {
		response.getWriter().print(
		"��û�е�¼����<a href = '/chapter05/login.html'>��¼</a>");		
	}else {
		response.getWriter().print("��ӭ��"+user.getUsername()+"!"
			
				);
		response.getWriter().print("<a href = '/chapter05/LogoutServlet'>�˳�</a>");
		Cookie cookie = new Cookie("JSESSIONID",session.getId());
		cookie.setMaxAge(60*30);
		cookie.setPath("/chapter05");
		response.addCookie(cookie);
	}
		
}
public void doPost (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{
	doGet(request, response);
}
}

