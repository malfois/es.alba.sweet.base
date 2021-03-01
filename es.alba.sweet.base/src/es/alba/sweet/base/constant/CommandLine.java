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
		String os = System.getProperty("os.name");
		if (os.equals("Linux")) {
			commandLine.add("/beamlines/bl11/controls/Marc/jdk-11.0.2/bin/java");
			commandLine.add("-jar");
			commandLine.add("/beamlines/bl11/controls/Marc/javaRCP/jar/SweetServer.jar");
		}
		if (os.startsWith("Windows")) {
			this.createWindowsCommandLine();
		}
	}

	private void createWindowsCommandLine() {
		commandLine.add("C:\\jdk\\jdk-11.0.2\\bin\\java.exe");
		commandLine.add("-Dfile.encoding=Cp1252");
		commandLine.add("-classpath");
		commandLine.add(Windows.CLASSPATH.get());
		commandLine.add("es.alba.sweet.server.SweetServer");
	}

	private enum Windows {

		CLASSPATH();

		private String classPath;

		private Windows() {
			String pluginsDirectory = "C:\\Users\\mmalfois\\.p2\\pool\\plugins\\";
			String baseDirectory = "Z:\\github\\es.alba.sweet.base\\es.alba.sweet.base\\bin;";
			String serverDirectory = "C:\\JavaProgram\\RCP\\es.alba.sweet.server.plugin\\bin";

			classPath = baseDirectory;
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-annotations_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-core_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "com.fasterxml.jackson.core.jackson-databind_2.10.3.v20200512-1600.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.core.runtime_3.20.0.v20201027-1526.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.osgi_3.16.100.v20201030-1916.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.fx.osgi_3.7.0.202102030601.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.osgi.compatibility.state_1.2.200.v20200915-2015.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.equinox.common_3.14.0.v20201102-2053.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.core.jobs_3.10.1000.v20200909-1312.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.equinox.registry_3.10.0.v20201107-1818.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.equinox.preferences_3.8.100.v20201102-2042.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.core.contenttype_3.7.800.v20200724-0804.jar;";
			classPath = classPath + pluginsDirectory + "org.eclipse.equinox.app_1.5.0.v20200717-0620.jar;";
			classPath = classPath + serverDirectory;
		}

		public String get() {
			return this.classPath;
		}
	}
}
