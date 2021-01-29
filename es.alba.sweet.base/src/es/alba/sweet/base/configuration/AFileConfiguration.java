package es.alba.sweet.base.configuration;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AFileConfiguration extends AModelObject {

	@JsonIgnore
	public String	filePrefix;

	@JsonIgnore
	public String	directory;

	protected AFileConfiguration(String directory, String filePrefix) {
		this.directory = directory;
		this.filePrefix = filePrefix;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public void setFilePrefix(String filePrefix) {
		this.filePrefix = filePrefix;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
