package es.alba.sweet.base.configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.alba.sweet.base.constant.Extension;
import es.alba.sweet.base.output.Output;

public class Json<T extends AFileConfiguration> {

	private Path file;
	private T configuration;

	// create ObjectMapper instance
	private ObjectMapper objectMapper = new ObjectMapper();

	public Json(T configuration) {
		this.configuration = configuration;
		this.file = Paths.get(configuration.getDirectory(), configuration.getFilePrefix() + "." + Extension.JSON);
	}

	public T getConfiguration() {
		return configuration;
	}

	public void setConfiguration(T configuration) {
		this.configuration = configuration;
	}

	public File getFile() {
		return file.toFile();
	}

	public void setFile(File fileConfiguration) {
		this.file = fileConfiguration.toPath();
	}

	@SuppressWarnings("unchecked")
	public void read() {
		try {
			byte[] jsonData = Files.readAllBytes(this.file);
			// convert json string to object
			this.configuration = (T) objectMapper.readValue(jsonData, configuration.getClass());
			Output.MESSAGE.info("es.alba.sweet.base.configuration.Json.read",
					"File " + this.file.toString() + " loaded");
		} catch (IOException e) {
			// e.printStackTrace();
			Output.MESSAGE.warning("es.alba.sweet.base.configuration.Json.read",
					"Error reading file " + this.file.toString() + ". Default settings loaded");
		}
	}

	public void write() {
		try {
			if (!Files.exists(this.file)) {
				Files.createDirectories(this.file.getParent());
				Files.createFile(this.file);
			}

			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			// writing to file
			FileOutputStream outputFile = new FileOutputStream(this.file.toFile());
			objectMapper.writeValue(outputFile, this.configuration);
			Output.DEBUG.info("es.alba.sweet.configuration.Json.write", "File " + this.file.toString() + " saved");
		} catch (IOException e) {
			Output.DEBUG.error("es.alba.sweet.configuration.Json.write", "Error writing file " + this.file.toString());
		}
	}

	public void changeFilePermission() {
		Set<PosixFilePermission> perms = new HashSet<>();
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);

		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);

		perms.add(PosixFilePermission.GROUP_READ);

		try {
			Files.setPosixFilePermissions(file, perms);
		} catch (IOException e) {
			Output.DEBUG.error("es.alba.sweet.configuration.Json.changeFilePermission",
					"Error changing file permission");
		}
	}

	public void print() {
		System.out.println(toString());
	}

	@Override
	public String toString() {
		StringWriter text = new StringWriter();
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(text, this.configuration);
			return text.toString();
		} catch (IOException e) {
			Output.DEBUG.error("es.alba.sweet.configuration.Json.toString", "Error reading configuration ");
			return e.getMessage();
		}
	}

}
