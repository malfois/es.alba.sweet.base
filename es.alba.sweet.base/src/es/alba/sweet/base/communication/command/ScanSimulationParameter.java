package es.alba.sweet.base.communication.command;

public class ScanSimulationParameter extends CommandArgument {

	private String	filename;
	private String	diagnostics;

	public ScanSimulationParameter() {
	}

	public ScanSimulationParameter(String json) throws JsonException {
		ScanSimulationParameter parameter = super.toObject(json, this.getClass());
		this.filename = parameter.getFilename();
		this.diagnostics = parameter.getDiagnostics();
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDiagnostics() {
		return diagnostics;
	}

	public void setDiagnostics(String diagnostics) {
		this.diagnostics = diagnostics;
	}

	@Override
	public String toJson() {
		return super.jsonConverter(this);
	}

}
