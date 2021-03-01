package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import es.alba.sweet.base.communication.command.JsonException;
import es.alba.sweet.base.constant.Application;

@JsonDeserialize(using = MessageDeserializer.class)
public class Message extends AMessage {

	public Message() {
	}

	public Message(LocalDateTime dateTime, MessageType type, Application application, String method, String message) {
		super(dateTime, type, application, method, message);
	}

	public Message(String json) throws JsonException {
		super(json);
	}

	public Message(MessageType type, Application application, String method, String message) {
		super(type, application, method, message);
	}

	@Override
	public String toString() {
		return getFormattedDateTime() + SEPARATOR + getTypeToString() + SEPARATOR + getApplicationToString() + SEPARATOR + getMessage() + "\n";
	}

}
