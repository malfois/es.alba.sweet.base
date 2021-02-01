package es.alba.sweet.base.constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import es.alba.sweet.base.output.Output;

public enum SharedDirectory {

	NAME;

	private Path path;

	private SharedDirectory() {
		// Properties properties = System.getProperties();
		// Set<Object> set = properties.keySet();
		// set.forEach(a -> System.out.println("Key: " + a.toString() + " - Value: " + properties.getProperty(a.toString())));
		//
		// String dir = System.getProperty("osgi.instance.area").substring(6);
		// path = Paths.get(dir).getParent();
		path = Paths.get("C:\\JavaProgram\\config");
		System.out.println("Shared directory is " + path.toString());
		if (!Files.exists(this.path)) {
			try {
				Files.createDirectory(this.path);
			} catch (IOException e) {
				Output.DEBUG.error("es.alba.sweet.base.constant.UserHome.set", "Error creating directory " + this.path.toString());
				e.printStackTrace();
			}
		}
	}

	public Path get() {
		return this.path;
	}
}
