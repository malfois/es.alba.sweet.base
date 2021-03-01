package es.alba.sweet.base.communication.command;

import java.util.List;

public enum CommandName {

	NAME, STOP_CLIENT, EXIT_SERVER, INFO, MESSAGE, DEBUG, SCAN_SIMULATION, SCAN_HEADER, UNKNOWN;

	public static CommandName Factory(String name) {
		List<CommandName> commands = List.of(CommandName.values());

		return commands.stream().filter(p -> p.name().toUpperCase().equals(name.toUpperCase())).findFirst().orElse(CommandName.UNKNOWN);
	}
}
