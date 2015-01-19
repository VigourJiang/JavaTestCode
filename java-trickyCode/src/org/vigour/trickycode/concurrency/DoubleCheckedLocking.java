package org.vigour.trickycode.concurrency;

/**
 * Illegal Double Checked Locking。
 * 
 * @author vigour
 * 
 */
public class DoubleCheckedLocking {
	/**
	 * 如果把baz设置为volatile，就是正确的代码
	 */
	Object baz;

	public Object bar() {
		if (baz == null) { // baz may be non-null yet not fully created
			synchronized (this) {
				if (baz == null) {
					baz = new Object();
				}
			}
		}
		return baz;
	}

	public synchronized void testSync() {
		baz = null;
	}
}
