package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;

public class DebugMessage extends AMessage {

	public DebugMessage(MessageType type, String method, String message) {
		super(type, method, message);
	}

	@Override
	public String toString() {
		return getDateTime() + " - " + getType() + " - " + getMethod() + " - " + getMessage() + "\n";
	}

}
