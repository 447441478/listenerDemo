package patternDemo.test;

import patternDemo.Person.IPersonActionListener;
import patternDemo.Person.Person;
import patternDemo.Person.PersonActionEvent;

public class Test {
	public static void main(String[] args) {
		Person p = new Person("Tom"); //事件源
		
		//注册一个监听器
		p.addIPersonActionListener( new IPersonActionListener() {
			@Override
			public void runAction(PersonActionEvent e) {
				System.out.println("我看到"+e.getSource()+"要开始跑步了");
			}
			@Override
			public void eatAction(PersonActionEvent e) {
				System.out.println("我看到"+e.getSource()+"要开吃饭了");
			}
		});
		
		//当事件源做出一些动作时
		p.run();
		p.program(); //Person做这个动作时，不给外部提供监听
		p.eat();
		
	}
}
