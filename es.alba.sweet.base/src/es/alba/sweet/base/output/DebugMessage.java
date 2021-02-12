package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;

import es.alba.sweet.base.constant.Application;

public class DebugMessage extends AMessage {

	public DebugMessage(MessageType type, Application application, String method, String message) {
		super(type, application, method, message);
	}

}
