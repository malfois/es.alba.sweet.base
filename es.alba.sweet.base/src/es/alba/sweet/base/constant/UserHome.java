package es.alba.sweet.base.constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import es.alba.sweet.base.output.Output;

public enum UserHome {

	CLIENT("client"), SERVER("server");

	private Path path;

	private UserHome(String subDirectory) {
		path = Paths.get(System.getProperty("user.home"), subDirectory);

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
