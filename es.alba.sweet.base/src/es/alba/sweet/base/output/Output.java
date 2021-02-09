package es.alba.sweet.base.output;

import java.awt.TrayIcon.MessageType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import es.alba.sweet.base.logger.LogFile;

public enum Output {

	DEBUG(OutputName.DEBUG), MESSAGE(OutputName.MESSAGE, DEBUG);

	private List<AMessage>	messages		= new ArrayList<>();

	private List<Output>	outputs			= new ArrayList<>();

	private AMessage		currentMessage;

	private int				nLength			= 0;

	private String			name;

	public final static int	MAX_CHARACTERS	= 80000;

	Output(String name) {
		this.name = name;
	}

	// public void setLogger(Logger log) {
	//
	// String userHome = UserHome.CLIENT.get().toString() + File.separator;
	//
	// if (name.equals(MESSAGE.name)) info("es.alba.sweet.base.output.Output.setLogger", "Logging file will be in " + userHome + "Logging_n.txt (n being a number from 0 to 4)");
	//
	// if (!name.equals(DEBUG.name)) return;
	//
	// }

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
			LogFile.LOG.info(message.toString());
			System.out.print(message);
		}

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

}
