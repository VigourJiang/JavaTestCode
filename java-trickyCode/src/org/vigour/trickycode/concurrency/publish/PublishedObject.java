package org.vigour.trickycode.concurrency.publish;

public class PublishedObject {
	public PublishedObject() {
		value = 0x11223344;
	}

	private int value;

	public int getValue() {
		return value;
	}
}
