package es.alba.sweet.base.communication;

import java.io.IOException;

public class ReadingLineException extends IOException {

	private static final long serialVersionUID = -1193852273623541589L;

	public ReadingLineException(String message) {
		super(message);
	}
}
