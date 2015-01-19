package org.vigour.trickycode;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NoReturnInFinal {
	private void foo(boolean check) throws Exception {
		try {
			throw new Exception("My Exception");
		} catch (Exception e) {
			throw e;
		} finally {
			if (check)
				return; // return not recommended here
		}
	}

	@Test
	public void testException() {
		try {
			foo(true);
		} catch (Exception e) {
			assertTrue(false);
		}
		
		try {
			foo(false);
			assertTrue(false);
		} catch (Exception e) {
		}
	}

}
