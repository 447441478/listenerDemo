package cn.hncu.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hncu.domain.User;
public class KickOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		//当用户是 管理员的时候才能进行删除操作，按理应该 写个过滤器进行过滤，这里略了。
		if( user.getAdmin() ) {
			String sessionId = request.getParameter("sessionId");
			@SuppressWarnings("unchecked")
			Map<String,HttpSession>  onlines = (Map<String, HttpSession>) getServletContext().getAttribute("onlines");
			if ( onlines.containsKey(sessionId) ) {
				HttpSession session = onlines.get(sessionId);
				if( session != null ) { //防护一下
					session.invalidate();
				}
			}
		}
		request.getRequestDispatcher("/servlet/ShowOnlinesServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
