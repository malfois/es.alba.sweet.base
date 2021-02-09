package es.alba.sweet.base.constant;

import java.util.ArrayList;
import java.util.List;

public enum CommandLine {

	SERVER("server");

	private List<String> commandLine = new ArrayList<>();

	private CommandLine(String type) {
		if (type.equals("server")) initiliaseServer();
	}

	public List<String> get() {
		return this.commandLine;
	}

	private void initiliaseServer() {
		commandLine.add("C:\\jdk\\jdk-11.0.2\\bin\\javaw.exe");
		commandLine.add("-jar");
		commandLine.add("Z:\\github\\sweetServer.jar");
		// commandLine.add("Z:\github\es.alba.sweet.base\es.alba.sweet.base\bin;C:\Users\mmalfois\.p2\pool\plugins\com.fasterxml.jackson.core.jackson-annotations_2.10.3.v20200512-1600.jar;C:\Users\mmalfois\.p2\pool\plugins\com.fasterxml.jackson.core.jackson-core_2.10.3.v20200512-1600.jar;C:\Users\mmalfois\.p2\pool\plugins\com.fasterxml.jackson.core.jackson-databind_2.10.3.v20200512-1600.jar;Z:\github\es.alba.sweet.communication\es.alba.sweet.communication\bin;C:\Users\mmalfois\.p2\pool\plugins\org.eclipse.core.databinding.observable_1.10.0.v20200730-0848.jar;C:\Users\mmalfois\.p2\pool\plugins\org.eclipse.equinox.common_3.14.0.v20201102-2053.jar;Z:\github\es.alba.sweet.server\es.alba.sweet.server\bin"
		// es.alba.sweet.server.SweetServer);
		// //commandLine.add("es.alba.sweet.server.SweetServer");
	}

	private String serverClassPath() {
		return "Z:\\github\\es.alba.sweet.base\\es.alba.sweet.base\\bin;"
				+ "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\com.fasterxml.jackson.core.jackson-annotations_2.10.3.v20200512-1600.jar;"
				+ "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\com.fasterxml.jackson.core.jackson-core_2.10.3.v20200512-1600.jar;"
				+ "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\com.fasterxml.jackson.core.jackson-databind_2.10.3.v20200512-1600.jar;"
				+ "Z:\\github\\es.alba.sweet.communication\\es.alba.sweet.communication\bin;"
				+ "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\org.eclipse.core.databinding.observable_1.10.0.v20200730-0848.jar;"
				+ "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\org.eclipse.equinox.common_3.14.0.v20201102-2053.jar;" + "Z:\\github\\es.alba.sweet.server\\es.alba.sweet.server\\bin";
	}
}
