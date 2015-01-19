package org.vigour.trickycode.enumTest;

/**
 * 
 * @author vigour
 * 
 */
public enum ExtendedOperation implements Operation {
	EXP("^") {
		public double apply(double x, double y) {
			return Math.pow(x, y);
		}
	},
	REMAINDER("%") {
		public double apply(double x, double y) {
			return x % y;
		}
	};

	private final String symbol;

	ExtendedOperation(String sym) {
		symbol = sym;
	}

	@Override
	public String toString() {
		return symbol;
	}
}
