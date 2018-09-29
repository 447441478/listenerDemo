package cn.hncu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AccessCountFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//这里声明成常量，因为匿名局部内部类只能访问外部类的成员变量或者外部方法域中的常量
		final ServletContext sctx = request.getServletContext();
		
		//这里 有个 知识点：多用户访问时，必须采用多线程+同步块的形式进行数据统计。
		//多线程保证效率；同步块保证多用户共享一个数据时，数据的准确性。
		new Thread() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName()+">>"+ sctx.hashCode() );
				//静态方法，锁对象为 AccessCount类模板对象
				AccessCount.add(sctx);
			};
		}.start();
				
		chain.doFilter(request, response); //放行
	}

	public void destroy() {
	}
	/* //该方式是WA的
	static class AccessCount{
		//把AccessCount类模板对象当锁
		public synchronized static void add( ServletContext sctx ) {
			Object objCount = sctx.getAttribute("count");
			Integer count = Integer.valueOf( objCount.toString() );
			count++;
			sctx.setAttribute("count", count);
		}
	}
	*/
} 
//注意 锁对象 不能是内部类 : 见测试类 TestSynchronized类
class AccessCount{
	//把AccessCount类模板对象当锁
	public synchronized static void add( ServletContext sctx ) {
		Object objCount = sctx.getAttribute("count");
		Integer count = Integer.valueOf( objCount.toString() );
		count++;
		sctx.setAttribute("count", count);
	}
}