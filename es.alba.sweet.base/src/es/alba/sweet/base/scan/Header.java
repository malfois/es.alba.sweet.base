package es.alba.sweet.base.scan;

import java.util.ArrayList;
import java.util.List;

import es.alba.sweet.base.communication.command.CommandArgument;
import es.alba.sweet.base.communication.command.JsonException;

public class Header extends CommandArgument {

	private String			filename;
	private String			command;
	private String			motor;
	private int				scanID;
	private String			selectedDiagnostic;
	private List<String>	diagnostics		= new ArrayList<>();
	private List<String>	plotDiagnostics	= new ArrayList<>();

	private int				numberOfPoints;

	private List<String>	headerFile		= new ArrayList<>();

	public Header() {
	}

	public Header(String json) throws JsonException {
		Header header = super.toObject(json, this.getClass());
		this.filename = header.filename;
		this.command = header.command;
		this.motor = header.motor;
		this.scanID = header.scanID;
		this.selectedDiagnostic = header.selectedDiagnostic;
		this.diagnostics = header.diagnostics;
		this.numberOfPoints = header.numberOfPoints;
		this.headerFile = header.headerFile;
		this.plotDiagnostics = header.plotDiagnostics;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public int getScanID() {
		return scanID;
	}

	public void setScanID(int scanID) {
		this.scanID = scanID;
	}

	public String getSelectedDiagnostic() {
		return selectedDiagnostic;
	}

	public void setSelectedDiagnostic(String selectedDiagnostic) {
		this.selectedDiagnostic = selectedDiagnostic;
	}

	public List<String> getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(List<String> diagnostics) {
		this.diagnostics = diagnostics;
	}

	public int getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(int numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}

	public List<String> getHeaderFile() {
		return headerFile;
	}

	public void setHeaderFile(List<String> headerFile) {
		this.headerFile = headerFile;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<String> getPlotDiagnostics() {
		return plotDiagnostics;
	}

	public void setPlotDiagnostics(List<String> plotDiagnostics) {
		this.plotDiagnostics = plotDiagnostics;
	}

	@Override
	public String toJson() {
		return super.jsonConverter(this);
	}

}
