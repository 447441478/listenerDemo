package cn.hncu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hncu.domain.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect( request.getContextPath() );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1收集参数
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		
		//2组织参数
		User user = new User();
		user.setName(name);
		user.setPwd(pwd);
		
		//3访问 service+dao 这里 模拟了 假设用户名和密码一致 就可以登录
		if( name != null && name.trim().length() > 0 && name.equals(pwd) ) {
			//这里 假设 用户名 hncu开头的就是 管理员
			if( name.startsWith("hncu") ) {
				user.setAdmin(true);
			}else {
				user.setAdmin(false);
			}
			//用户登录成功把 user信息存到session中
			request.getSession().setAttribute("user", user);
		}
		//4 导向结果页面
		//重定向到主页
		response.sendRedirect(request.getContextPath());
	}

}
