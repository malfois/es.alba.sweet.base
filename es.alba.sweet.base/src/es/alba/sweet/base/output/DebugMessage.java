package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;

import es.alba.sweet.base.communication.command.JsonException;
import es.alba.sweet.base.constant.Application;

public class DebugMessage extends AMessage {

	public DebugMessage(String json) throws JsonException {
		super(json);
	}

	public DebugMessage(MessageType type, Application application, String method, String message) {
		super(type, application, method, message);
	}

	@Override
	public String toString() {
		return getFormattedDateTime() + SEPARATOR + getTypeToString() + SEPARATOR + getApplicationToString() + SEPARATOR + getMethod() + SEPARATOR + getMessage() + "\n";
	}

}
