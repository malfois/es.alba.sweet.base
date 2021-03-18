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
		if (os.startsWith("Windows")) {
			this.createWindowsCommandLine();
			return;
		}
		this.createLinuxCommandLine();
	}

	private void createWindowsCommandLine() {
		commandLine.add("C:\\jdk\\jdk-11.0.2\\bin\\javaw.exe");
		commandLine.add("-Dfile.encoding=Cp1252");
		commandLine.add("-classpath");
		commandLine.add(Windows.CLASSPATH.get());
		commandLine.add("es.alba.sweet.server.SweetServer");
	}

	private void createLinuxCommandLine() {
		commandLine.add("/beamlines/bl11/controls/Marc/jdk-11.0.2/bin/java");
		commandLine.add("-Dfile.encoding=UTF-8");
		commandLine.add("-classpath");
		commandLine.add(Linux.CLASSPATH.get());
		commandLine.add("es.alba.sweet.server.SweetServer");
	}

	private enum Linux {

		CLASSPATH();

		private String classPath;

		private Linux() {
			String pluginsDirectory = "/homelocal/opbl11/.p2/pool/plugins/";
			String baseDirectory = "/beamlines/bl11/controls/Marc/git/es.alba.sweet.base/es.alba.sweet.base/bin:";
			String serverDirectory = "/beamlines/bl11/controls/Marc/git/es.alba.sweet.server/es.alba.sweet.server/bin";

			classPath = baseDirectory;
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-annotations_2.10.3.v20200512-1600.jar:";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-core_2.10.3.v20200512-1600.jar:";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-databind_2.10.3.v20200512-1600.jar:";
			classPath = classPath + pluginsDirectory + "org.apache.commons.math3_3.5.0.v20160301-1110.jar:";
			classPath = classPath + serverDirectory;
		}

		public String get() {
			return this.classPath;
		}
	}

	private enum Windows {

		CLASSPATH();

		private String classPath;

		private Windows() {
			String pluginsDirectory = "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\";
			String baseDirectory = "Z:\\github\\es.alba.sweet.base\\es.alba.sweet.base\\bin;";
			String serverDirectory = "C:\\JavaProgram\\RCP\\es.alba.sweet.server\\bin";

			classPath = baseDirectory;
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-annotations_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-core_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-databind_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-databind_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.january_3.0.0.v202001161133.jar;";
			classPath = classPath + pluginsDirectory + "org.apache.commons.math3_3.5.0.v20160301-1110.jar;";
			classPath = classPath + pluginsDirectory + "javax.measure.unit-api_1.0.0.v20170818-1538.jar;";
			classPath = classPath + serverDirectory;
		}

		public String get() {
			return this.classPath;
		}
	}
}
