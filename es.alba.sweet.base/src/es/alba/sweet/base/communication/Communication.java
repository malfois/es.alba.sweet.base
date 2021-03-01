package es.alba.sweet.base.communication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.alba.sweet.base.configuration.AFileConfiguration;
import es.alba.sweet.base.constant.Directory;

public class Communication extends AFileConfiguration {

	private int		port		= -1;
	private String	hostName	= "N/A";

	public Communication() {
		super(Directory.SHARED.get().toString(), "communication");
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void invalidate() {
		port = -1;
		hostName = "N/A";
	}

	@JsonIgnore
	public boolean isValid() {
		return !hostName.equals("N/A") && port > 0;
	}
}
