package cn.hncu.listener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("ServletContextListener,初始化...");
		//应该从本地磁盘读取出访问数量，继续记录
		ServletContext sctx = sce.getServletContext();
		String path = sctx.getRealPath("/WEB-INF/count.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader( new FileReader(path));
			String strCount = br.readLine();
			Integer count =0;
			try {
				count = Integer.valueOf(strCount);
			} catch (NumberFormatException e) {
				System.err.println("谁改了/WEB-INF/count.txt文件！！！");
				e.printStackTrace();
			}
			sctx.setAttribute("count", count);
		} catch (FileNotFoundException e) {
			//e.printStackTrace();
			//如果出现异常说明项目是第一次初始化，即直接给ServletContext中放一个count等于0
			sctx.setAttribute("count", 0);
		} catch (IOException e) {
			System.err.println("项目初始化异常...请联系维护人员！！！");
			e.printStackTrace();
		} finally {
			if( br != null ) { //关流防止内存泄漏
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("项目初始化异常...请联系维护人员！！！");
					e.printStackTrace();
				}
			}
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyServletContextListener,销毁了...");
		//项目关闭时应该 把 count 存到  '/WEB-INF/count.txt' 中
		ServletContext sctx = sce.getServletContext();
		String path = sctx.getRealPath("/WEB-INF/count.txt");
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( path );
			writer.println("" + sctx.getAttribute("count") );
		} catch (FileNotFoundException e) {
			System.err.println("项目停止时存储'/WEB-INF/count.txt'数据出现异常...请联系维护人员！！！");
			e.printStackTrace();
		} finally {
			if( writer != null ) { //关流防止内存泄漏
				writer.close();
			}
		}
	}

}
