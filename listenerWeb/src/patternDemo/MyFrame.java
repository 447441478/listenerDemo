package patternDemo;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* 观察者(监听者)模式要素：
 * 1) 被监听者(事件源) ---btn(Button类)  由它注册(添加)监听者
 * 2) 监听者(监听器) ----obj(实现监听器接口的类对象)
 * 3) 事件对象 ----e(封装了事件源的对象,根据需求还可包含事件相关的其它信息)
 */
public class MyFrame extends Frame {
	private static final long serialVersionUID = 1L;
	
	public MyFrame() {
		setBounds(200, 100, 400, 400);
		setLayout( new FlowLayout() );
		
		Button btn = new Button("ok");
		ListenerA obj = new ListenerA();
		btn.addActionListener( obj );
		
		add(btn);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MyFrame();
	}

}
class ListenerA implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("我看到"+e.getSource().toString()+"被点击了");
	}
	
}
