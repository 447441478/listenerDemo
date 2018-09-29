package cn.hncu.listener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {


    @SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent se)  { 
    	//先从 ServletContext 中拿出 onlines 属性值
    	HttpSession session = se.getSession();
    	ServletContext sctx = session.getServletContext();
		Map<String,HttpSession> onlines = (Map<String, HttpSession>) sctx.getAttribute("onlines");
    	//判断 onlines 是否为null
    	if( onlines == null ) { //如果为null，则进行创建一个Map<String,HttpSession> 
    		/* 因为 onlines 是多用户共享的，存在线程安全问题 
    		 * 所以采用 ConcurrentHashMap 线程安全的 Map,
    		 * 它采用 segment分段锁技术 理论上效率是 HashTable 的  16倍，16是分段锁的个数。默认是16个，可以更改。
    		 */
    		/* 可想而知 这里的 onlines 是单例，而且是懒汉模式 
    		 * 所以需要预防:两个或者更多 session 同时初始化时 从 ServletContext 
    		 * 中获取到的 onlines 都为 null 时，这时就要进行加锁，只能一个 session 来创建 
    		 * 并且加入到 ServletContext 中，其他的就 用 '第一个用户' 创建的  onlines 
    		 */
    		//拿锁
    		synchronized (MySessionListener.class) {
    			//从新获取一下
    			onlines = (Map<String, HttpSession>)sctx.getAttribute("onlines");
    			//如果还是为null 那么 当前这个 session用户将是 被誉为'第一个用户'
				if( onlines == null ) {
					// '第一个用户' 进行创建 Map 容器
					onlines = new ConcurrentHashMap<String,HttpSession>();
					//放入 sctx 中
					sctx.setAttribute("onlines", onlines);
				}
			}//释放锁
    	}
    	//把 sessionId 和 session 对象放入 现在用户池中
    	onlines.put(session.getId(), session);
    }
    //当 session调用  invalidate() 方法时 就会触发该方法
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// 1.获取 sessionId
    	String sessionId = se.getSession().getId();
    	// 2.获取 sctx
    	ServletContext sctx = se.getSession().getServletContext();
    	// 3.获取 onlines 在线用户池
    	@SuppressWarnings("unchecked")
		Map<String,HttpSession> onlines = (Map<String, HttpSession>) sctx.getAttribute("onlines");
    	// 4.判断 onlines 中是否含有 sessionId 的key值
    	if( onlines != null && onlines.containsKey(sessionId) ) { //防护一下
    		// 5.含有就从在线用户池中移除掉
    		onlines.remove(sessionId);
    	}
    }
	
}
