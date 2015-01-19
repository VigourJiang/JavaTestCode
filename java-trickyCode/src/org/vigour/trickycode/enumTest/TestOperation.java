package org.vigour.trickycode.enumTest;

public class TestOperation {

	private static <T extends Enum<T> & Operation> void test(

		Class<T> opSet, double x, double y

		) {
		for (Operation op : opSet.getEnumConstants()) {

			System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
		}

	}

	static class A {

		static A a;
		static Object lock;
		static {
			a = new A();
			System.out.println(lock);
		}

		static {
			lock = new Object();
		}
	}

	/**
	 * d
	 * @param args
	 */
	public static void main(String[] args) {
		A a = new A();
	}

}
