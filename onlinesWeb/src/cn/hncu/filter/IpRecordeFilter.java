package cn.hncu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class IpRecordeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//记录IP的过滤器
		HttpServletRequest req = (HttpServletRequest) request;
		String ip = req.getRemoteAddr();
		// 把IP 存储到 session中，因为游客(没登录的用户) 也是在线用户
		req.getSession().setAttribute("IP", ip);
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
