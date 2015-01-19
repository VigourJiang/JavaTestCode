package org.vigour.trickycode.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestGeneric {
	@Test
	public void test1() {
		List<?> l = new ArrayList<String>();

		// compilation error
		// l.add("DS");
		// l.add(new Object());
		l.add(null);

		((List) l).add("DS");
	}

}
