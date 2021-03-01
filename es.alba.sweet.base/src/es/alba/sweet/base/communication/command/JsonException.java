package es.alba.sweet.base.communication.command;

import java.io.IOException;

public class JsonException extends IOException {

	private static final long serialVersionUID = 4686777956826605779L;

	public JsonException(String message) {
		super(message);
	}
}
