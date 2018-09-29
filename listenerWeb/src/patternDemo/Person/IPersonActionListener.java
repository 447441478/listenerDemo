package patternDemo.Person;

/* 观察(监听)者的父类
 */
public interface IPersonActionListener {

	public abstract void runAction( PersonActionEvent e );
	public abstract void eatAction( PersonActionEvent e );
}
