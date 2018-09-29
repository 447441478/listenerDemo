package cn.hncu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//如果用户登录了的话 session 中的 属性 user 肯定不为null
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("user") != null ) {
			//用户登录了直接放行
			chain.doFilter(request, response); //放行
		} else {
			//用户没有登录，踢到登录页面
			((HttpServletResponse)response).sendRedirect( req.getContextPath() );
		}
	}

	@Override
	public void destroy() {
	}

}
