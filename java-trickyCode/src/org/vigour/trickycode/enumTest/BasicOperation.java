package org.vigour.trickycode.enumTest;

/**
 * 
 * @author vigour
 * 
 */
public enum BasicOperation implements Operation {
	PLUS("+") {
		public double apply(double x, double y) {
			return x + y;
		}
	},
	MINUS("-") {
		public double apply(double x, double y) {
			return x - y;
		}
	},
	TIMES("*") {
		public double apply(double x, double y) {
			return x * y;
		}
	},
	DIVIDE("/") {
		public double apply(double x, double y) {
			return x / y;
		}
	};

	private final String symbol;

	BasicOperation(String sym) {
		symbol = sym;
	}

	@Override
	public String toString() {
		return symbol;
	}
}