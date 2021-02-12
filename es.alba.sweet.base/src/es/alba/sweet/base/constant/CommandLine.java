package es.alba.sweet.base.constant;

import java.util.ArrayList;
import java.util.List;

public enum CommandLine {

	SERVER("server");

	private List<String> commandLine = new ArrayList<>();

	private CommandLine(String type) {
		if (type.equals("server"))
			initiliaseServer();
	}

	public List<String> get() {
		return this.commandLine;
	}

	private void initiliaseServer() {
		String os = System.getProperty("os.name");
		System.out.println(os);
		if (os.equals("Linux")) {
			commandLine.add("/beamlines/bl11/controls/Marc/jdk-11.0.2/bin/java");
			commandLine.add("-jar");
			commandLine.add("/beamlines/bl11/controls/Marc/javaRCP/jar/SweetServer.jar");
		}
		if (os.startsWith("Windows")) {
			commandLine.add("C:\\jdk\\jdk-11.0.2\\bin\\javaw.exe");
			commandLine.add("-jar");
			commandLine.add("Z:\\github\\sweetServer.jar");
		}
	}

}
