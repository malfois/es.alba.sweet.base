package es.alba.sweet.base.constant;

import java.io.File;
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
				Output.DEBUG.error("es.alba.sweet.base.constant.UserHome.set", "Error creating directory " + this.path.toString());
				e.printStackTrace();
			}
		}
	}

	private Directory() {
		this.path = getSharedDirectory();
	}

	private Path getSharedDirectory() {
		File file = new File(".");
		String filename = file.getAbsolutePath();
		while (filename.contains(MainPackage.NAME.get())) {
			Path p = Paths.get(filename).getParent();
			filename = p.toString();
		}
		return Paths.get("Z:\\github");
	}

	public Path get() {
		return this.path;
	}

}
