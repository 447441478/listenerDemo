package patternDemo.Person;

import java.util.ArrayList;
import java.util.List;

/* 被观察(监听)者
 */
public class Person {
	private String name;
	
	public Person() {
	}
	public Person(String name) {
		this.name = name;
	}
	
	List<IPersonActionListener> listeners = new ArrayList<IPersonActionListener>();
	public void addIPersonActionListener(IPersonActionListener l) {
		listeners.add(l);
	}
	public void run() {
		for (IPersonActionListener listener : listeners) {
			PersonActionEvent e = new PersonActionEvent( this );
			listener.runAction(e);
		}
		System.out.println( name +">>在跑步");
	}
	public void eat() {
		for (IPersonActionListener listener : listeners) {
			PersonActionEvent e = new PersonActionEvent( this );
			listener.eatAction(e);
		}
		System.out.println( name +">>在吃饭");
	}
	
	public void program() {
		System.out.println( name +">>在编程");
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}
	
	
}
