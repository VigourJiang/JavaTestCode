package org.vigour.trickycode.concurrency;

import java.awt.Event;
import java.util.EventListener;

public class NotAtomicNumber {
	public void writeAllValues() {

		// 对于long和double的写入不是原子的
		valueLong = 0xffff_3248_2343_3E23L;
		valueDouble = 2.0D;

		// 对于volatile long和volatile double的写入是原子的
		valueLongVolatile = 0xffff_3248_2343_3E23L;
		valueDoubleVolatile = 2.0D;

		// 对于其他类型的写入，是原子的。
		valueInt = 0xfffe_efef;
		valueFloat = 3.0F;
		ref = null;
	}

	public void readAllValues() {

	}

	private long valueLong;
	private double valueDouble;
	private volatile long valueLongVolatile;
	private volatile double valueDoubleVolatile;
	private int valueInt;
	private float valueFloat;
	private Object ref = new Object();

}
