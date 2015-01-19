package org.vigour.trickycode.concurrency.publish;

public class PublishWrapper {
	public PublishWrapper() {
		value = new PublishedObject();
	}

	private final PublishedObject value;

	public PublishedObject getValue() {
		return value;
	}
}
