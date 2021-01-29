package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public enum Output {

	DEBUG(OutputName.DEBUG), MESSAGE(OutputName.MESSAGE, DEBUG);

	private List<AMessage>		messages		= new ArrayList<>();

	private List<Output>		outputs			= new ArrayList<>();

	private AMessage			currentMessage;

	private int					nLength			= 0;

	private String				name;

	public final static int		MAX_CHARACTERS	= 80000;

	private static Logger		LOG;
	private final static int	SIZE_LIMIT		= 1024 * 1024 * 5;

	Output(String name) {
		this.name = name;
	}

	public void setLogger(Logger log) {
		LOG = log;

		LOG.setLevel(Level.INFO);

		String userHome = System.getProperty("user.home") + File.separator;

		if (name.equals(MESSAGE.name)) info("es.alba.sweet.base.output.Output.setLogger", "Logging file will be in " + userHome + "Logging_n.txt (n being a number from 0 to 4)");

		if (!name.equals(DEBUG.name)) return;

		FileHandler fileTxt;
		try {

			fileTxt = new FileHandler(userHome + "Logging_%g.txt", SIZE_LIMIT, 5);
			// create a TXT formatter
			MessageFormatter formatterTxt = new MessageFormatter();
			fileTxt.setFormatter(formatterTxt);
			LOG.addHandler(fileTxt);

		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	Output(String name, Output... outputs) {
		this.outputs.addAll(List.of(outputs));
		this.name = name;
	}

	public List<AMessage> getMessages() {
		return this.messages;
	}

	public void info(String method, String message) {
		AMessage info = Factory(name, MessageType.INFO, method, message);
		message(info);
	}

	public void warning(String method, String message) {
		AMessage warning = Factory(name, MessageType.WARNING, method, message);
		message(warning);
	}

	public void error(String method, String message) {
		AMessage error = Factory(name, MessageType.ERROR, method, message);
		message(error);
	}

	public AMessage getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(AMessage currentMessage) {
		firePropertyChange("currentMessage", this.currentMessage, this.currentMessage = currentMessage);
	}

	private void message(AMessage message) {
		while (nLength + message.getMessage().length() > MAX_CHARACTERS) {
			nLength = nLength - messages.get(0).getMessage().length();
			messages.remove(0);
		}
		messages.add(message);

		setCurrentMessage(message);

		outputs.forEach(a -> a.message(Factory(a.name, message.getType(), message.getMethod(), message.getMessage())));

		if (name.equalsIgnoreCase(OutputName.DEBUG)) {
			LOG.info(message.toString());
		}
		System.out.print(message);
	}

	private static AMessage Factory(String name, MessageType type, String method, String message) {
		switch (name) {
		case OutputName.MESSAGE:
			return new Message(type, method, message);
		case OutputName.DEBUG:
			return new DebugMessage(type, method, message);
		default:
			break;
		}

		return null;
	}

	private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		changeSupport.removePropertyChangeListener(listener);
	}

	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
		changeSupport.firePropertyChange(propertyName, oldValue, newValue);
	}

	private class MessageFormatter extends Formatter {

		@Override
		public String format(LogRecord record) {
			return record.getMessage();
		}

	}
}
