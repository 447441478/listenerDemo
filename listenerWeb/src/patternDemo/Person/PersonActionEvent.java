package patternDemo.Person;

/*
 * 事件源---封装事件源与其相关信息
 */
public class PersonActionEvent {
	private Person p;

	public PersonActionEvent(Person p) {
		this.p = p;
	}
	
	public Object getSource() {
		return p;
	}
	
}
