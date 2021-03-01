package es.alba.sweet.base.communication.command;

public class CommandStream {

	public final static String	SEPARATOR	= " - ";

	private CommandName			commandName;

	private String				json;

	public CommandStream(CommandName commandName, String json) {
		this.commandName = commandName;
		this.json = json;
	}

	public CommandStream(String command) throws CommandStreamNullException {
		if (command == null) throw new CommandStreamNullException("null recieved. Socket is closed");

		String[] texts = command.split(SEPARATOR);
		this.commandName = CommandName.valueOf(texts[0]);

		if (texts.length > 1) json = texts[1];
	}

	public CommandName getCommandName() {
		return commandName;
	}

	public String getCommandArgument() {
		return this.json;
	}

	public String toString() {
		return this.commandName.name() + SEPARATOR + json;
	}
}
