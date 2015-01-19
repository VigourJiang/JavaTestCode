package org.vigour.trickycode.concurrency;


public class ResourceFactory {
	/**
	 * 这里单独创建了一个类，用来保证以下两点:
	 * <li> resource的访问是线程安全的
	 * <li> 只有第一次调用getResource()的时候，resouce才会被创建起来。
	 * @author vigour
	 *
	 */
	private static class ResourceHolder {
		static {
			System.out.println("In ResourceHolder static initializer block");
		}
		public static Object resource = new Object();
	}

	public static Object getResource() {
		return ResourceHolder.resource;
	}

	public static void main(String[] args) {
		System.out.println("Start");
		getResource();
	}
}
