package org.vigour.trickycode;

public class Initialization {
	int hashCode;

	Object getValue() {
		return 32;
	}

	Initialization() {
		hashCode = getValue().hashCode();
	}

	public static void main(String[] args) {

	}

	/**
	 * aaaa
	 */
	static {
		Initialization a = new Initialization();
		a.getValue();
		System.out.println(Initialization.nonFinalStr);
		System.out.println(Initialization.finalStr);

		System.out.println(Initialization.nonFinalLong);
		System.out.println(Initialization.finalLong);

		System.out.println(Initialization.nonFinalObject);
		System.out.println(Initialization.finalObject);
	}

	static String nonFinalStr = "not final str";
	final static String finalStr = "final str";

	static long nonFinalLong = 100L;
	final static long finalLong = 100L;

	static Object nonFinalObject = new Object();
	final static Object finalObject = new Object();
}
