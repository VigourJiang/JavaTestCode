package org.vigour.trickycode.concurrency;

import java.awt.Event;
import java.util.EventListener;

/**
 * ThisEscape illustrates an important special case of escape—when the this ref-
 * erences escapes during construction. When the inner EventListener instance is
 * published, so is the enclosing ThisEscape instance. But an object is in a
 * pre-dictable, consistent state only after its constructor returns, so
 * publishing an object from within its constructor can publish an incompletely
 * constructed object.
 * This is true even if the publication is the last statement in the
 * constructor.
 * If the this reference escapes during construction, the object is considered
 * not properly constructed.
 * @author vigour
 * 
 */
public class ThisEscape {
	public ThisEscape(EventSource source) {
		source.registerListener(
			new EventListener() {
				public void onEvent(Event e) {
					// do something
					// 如果构造函数还没有退出，另外的线程已经调用到了这个回调函数
					// 那么，根据Java线程模型，member字段可能为null。
					member.foo();
				}
			});
	}

	final SharedObj member = new SharedObj();
}

class EventSource {
	public void registerListener(EventListener listener) {

	}
}

class SharedObj {
	public void foo() {

	}
}
