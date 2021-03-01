package es.alba.sweet.base.communication.command;

import java.io.IOException;

public class CommandStreamNullException extends IOException {

	private static final long serialVersionUID = -1808272683998623181L;

	public CommandStreamNullException(String message) {
		super(message);
	}
}
