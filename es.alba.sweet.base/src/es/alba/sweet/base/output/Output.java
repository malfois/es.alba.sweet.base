package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import es.alba.sweet.base.constant.Application;
import es.alba.sweet.base.logger.LogFile;

public enum Output {

	DEBUG(OutputName.DEBUG), MESSAGE(OutputName.MESSAGE, DEBUG);

	private List<AMessage>	messages		= new ArrayList<>();

	private List<Output>	outputs			= new ArrayList<>();

	private AMessage		currentMessage;

	private int				nLength			= 0;

	private String			name;

	private Application		application;

	public final static int	MAX_CHARACTERS	= 80000;

	Output(String name) {
		this.name = name;
	}

	Output(String name, Output... outputs) {
		this.outputs.addAll(List.of(outputs));
		this.name = name;
	}

	public void setApplication(Application application) {
		this.application = application;
		outputs.forEach(a -> a.setApplication(application));
	}

	public List<AMessage> getMessages() {
		return this.messages;
	}

	public void print(AMessage message) {
		message(message);
	}

	public void info(String method, String message) {
		AMessage info = Factory(name, MessageType.INFO, application, method, message);
		message(info);
	}

	public void warning(String method, String message) {
		AMessage warning = Factory(name, MessageType.WARNING, application, method, message);
		message(warning);
	}

	public void error(String method, String message) {
		AMessage error = Factory(name, MessageType.ERROR, application, method, message);
		message(error);
	}

	public AMessage getCurrentMessage() {
		return currentMessage;
	}

	public void setCurrentMessage(AMessage currentMessage) {
		firePropertyChange("currentMessage", this.currentMessage, this.currentMessage = currentMessage);
	}

	private void message(AMessage message) {
		// if (message.getMessage() == null) return;

		while (nLength + message.getMessage().length() > MAX_CHARACTERS) {
			nLength = nLength - messages.get(0).getMessage().length();
			messages.remove(0);
		}
		messages.add(message);

		setCurrentMessage(message);

		outputs.forEach(a -> a.message(Factory(a.name, message.getType(), message.getApplication(), message.getMethod(), message.getMessage())));

		if (name.equalsIgnoreCase(OutputName.DEBUG)) {
			LogFile.LOG.info(message.toString());
			System.out.print(message);
		}

	}

	private static AMessage Factory(String name, MessageType type, Application application, String method, String message) {
		switch (name) {
		case OutputName.MESSAGE:
			return new Message(type, application, method, message);
		case OutputName.DEBUG:
			return new DebugMessage(type, application, method, message);
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

}
