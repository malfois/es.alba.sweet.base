package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.alba.sweet.base.communication.command.CommandArgument;
import es.alba.sweet.base.communication.command.JsonException;
import es.alba.sweet.base.constant.Application;

public abstract class AMessage extends CommandArgument {

	protected final static String				SEPARATOR	= " - ";
	protected final static DateTimeFormatter	FORMATTER	= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private LocalDateTime						dateTime;
	private String								method;
	private String								message;
	private MessageType							type		= MessageType.INFO;
	private Application							application;

	protected AMessage() {
	}

	protected AMessage(String json) throws JsonException {
		AMessage message = super.toObject(json, this.getClass());
		setDateTime(message.getDateTime());
		setType(message.getType());
		setApplication(message.getApplication());
		setMethod(message.getMethod());
		setMessage(message.getMessage());
	}

	protected AMessage(MessageType type, Application application, String method, String message) {
		this.dateTime = LocalDateTime.now();
		this.method = method;
		this.application = application;
		this.message = message;
		this.type = type;
	}

	protected AMessage(LocalDateTime dateTime, MessageType type, Application application, String method, String message) {
		this.dateTime = dateTime;
		this.method = method;
		this.application = application;
		this.message = message;
		this.type = type;
	}

	public String getFormattedDateTime() {
		return dateTime.format(FORMATTER);
	}

	@JsonIgnore
	public LocalDateTime getDateTime() {
		return dateTime;
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

	public void setType(MessageType type) {
		this.type = type;
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	@JsonIgnore
	public String getApplicationToString() {
		switch (this.application) {
		case SWEET:
			return application.name().toUpperCase() + " ";
		case SERVER:
			return application.name().toUpperCase();
		default:
			return Application.UNKNOWN.name().toUpperCase();
		}
	}

	@JsonIgnore
	public String getTypeToString() {
		switch (this.type) {
		case WARNING:
			return type.name().toUpperCase();
		case INFO:
			return " " + type.name().toUpperCase() + "  ";
		case ERROR:
			return " " + type.name().toUpperCase() + " ";
		default:
			return " " + type.name().toUpperCase() + " ";
		}
	}

	public String toJson() {
		String t = super.jsonConverter(this);
		return t;
	}

	public static Message Factory(String line) {
		String[] texts = line.split(AMessage.SEPARATOR);

		LocalDateTime dateTime = LocalDateTime.parse(texts[0], AMessage.FORMATTER);
		MessageType messageType = MessageType.valueOf(texts[1].trim());
		Application application = Application.valueOf(texts[2].trim());
		String method = texts[3];
		String message = texts[4];

		return new Message(dateTime, messageType, application, method, message);

	}

}