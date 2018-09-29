package cn.hncu.domain;

import java.io.Serializable;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * &emsp;&emsp;
 * <br/><br/><b>CreateTime:</b><br/>&emsp;&emsp;&emsp; 2018年9月28日 下午10:57:56	
 * @author 宋进宇&emsp;<a href='mailto:447441478@qq.com'>447441478@qq.com</a>
 */
@WebListener
public class User implements HttpSessionActivationListener, Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String pwd;
	
	public User() {
    }

    public void sessionDidActivate(HttpSessionEvent se)  { 
    	System.out.println("我已经活化了>>"+this);
    }

    public void sessionWillPassivate(HttpSessionEvent se)  {
    	System.out.println("我将要钝化>>"+this);
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
}
