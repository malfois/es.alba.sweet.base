package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.alba.sweet.base.constant.Application;

public abstract class AMessage {

	private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private LocalDateTime dateTime;
	private String method;
	private String message;
	private MessageType type = MessageType.INFO;
	private Application application;

	protected AMessage(MessageType type, Application application, String method, String message) {
		this.dateTime = LocalDateTime.now();
		this.method = method;
		this.application = application;
		this.message = message;
		this.type = type;
	}

	public String getDateTime() {
		return dateTime.format(FORMATTER);
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public Application getApplication() {
		return this.application;
	}

	public String getTypeToString() {
		switch (this.type) {
		case WARNING:
			return type.name().toUpperCase();
		case INFO:
			return " " + type.name().toUpperCase() + "  ";
		case ERROR:
			return " " + type.name().toUpperCase() + " ";
		default:
			break;
		}
		return message;
	}

	public String toString() {
		return getDateTime() + " - " + getTypeToString() + " - " + getApplication() + " - " + getMessage() + "\n";
	}

}