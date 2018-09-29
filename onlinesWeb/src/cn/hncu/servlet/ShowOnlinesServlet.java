package cn.hncu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hncu.domain.User;

public class ShowOnlinesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//把 在线用户信息封装成 一个map,把所有用户封装成一个list
		
		// 1 获取onlines在线用户池
		@SuppressWarnings("unchecked")
		Map<String,HttpSession> onlines = (Map<String, HttpSession>) getServletContext().getAttribute("onlines");
		if( onlines == null ) {
			request.getSession().invalidate();
			return;
		}
		
		// 2 创建List<Map<String,Object>> 集合
		List<Map<String,Object>> onlineInfos = new ArrayList<Map<String,Object>>();
		
		// 3 遍历onlines
		for (Entry<String, HttpSession> entry : onlines.entrySet()) {
			HashMap<String, Object> onlineInfo = new HashMap<String,Object>();
			
			//封装用户
			HttpSession session = entry.getValue();
			User user = (User) session.getAttribute("user");
			onlineInfo.put("user", user);
			
			//封装第一次访问时间
			Date creationDate = new Date( session.getCreationTime() );
			onlineInfo.put("creationDate", creationDate);
			
			//封装上一次访问时间
			Date AccessedDate = new Date( session.getLastAccessedTime() );
			onlineInfo.put("AccessedDate", AccessedDate);
			
			//封装用户ip
			onlineInfo.put("IP", session.getAttribute("IP"));
			
			//封装sessionId以便踢出操作
			onlineInfo.put( "sessionId", entry.getKey() ); 
			
			//把 onlineInfo 加入到 onlineInfos
			onlineInfos.add(onlineInfo);
		}
		//收集完毕后，把信息传递给前端页面
		request.setAttribute("onlineInfos", onlineInfos);
		request.getRequestDispatcher("/jsps/show.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
