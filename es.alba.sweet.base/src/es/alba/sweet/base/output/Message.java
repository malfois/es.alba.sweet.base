package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;

public class Message extends AMessage {

	public Message(MessageType type, String method, String message) {
		super(type, method, message);
	}

	@Override
	public String toString() {
		return getDateTime() + " - " + getType() + " - " + getMessage() + "\n";
	}
}
