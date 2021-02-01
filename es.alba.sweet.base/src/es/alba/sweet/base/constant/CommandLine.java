package es.alba.sweet.base.constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public enum CommandLine {

	SERVER("serverCommandLine");

	private String commandLine;

	private CommandLine(String filename) {
		Path p = Paths.get(SharedDirectory.NAME.get().toString(), filename + ".txt");
		try {
			this.commandLine = Files.readString(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String get() {
		return this.commandLine;
	}
}
