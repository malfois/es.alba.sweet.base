package es.alba.sweet.base.constant;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import es.alba.sweet.base.output.Output;

public enum Directory {

	CLIENT(Application.SWEET.name()), SERVER(Application.SERVER.name()), SHARED();

	private Path path;

	private Directory(String subDirectory) {
		path = getSharedDirectory().resolve(subDirectory);
		if (!Files.exists(this.path)) {
			try {
				Files.createDirectory(this.path);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				Output.DEBUG.error("es.alba.sweet.base.constant.UserHome.set",
						"Error creating directory " + this.path.toString());
				// e.printStackTrace();
			}
		}
	}

	private Directory() {
		this.path = getSharedDirectory();
	}

	private Path getSharedDirectory() {
		String os = System.getProperty("os.name");
		if (os.equals("Linux")) {
			return Paths.get("/beamlines/bl11/controls/Marc/javaRCP");
		}
		if (os.startsWith("Windows")) {
			return Paths.get("Z:\\github");
		}
		return path;
	}

	public Path get() {
		return this.path;
	}

}
