package es.alba.sweet.base.scan;

public class ScanData {

	private FitData	raw			= new FitData();

	private FitData	derivative	= new FitData();

	public ScanData() {
	}

	public ScanData(FitData raw, FitData derivative) {
		this.raw = raw;
		this.derivative = derivative;
	}

	public void addPoint(Double x, Double y) {
		this.raw.addPoint(x, y);
	}

	public FitData getRaw() {
		return raw;
	}

	public void setRaw(FitData raw) {
		this.raw = raw;
	}

	public FitData getDerivative() {
		return derivative;
	}

	public void setDerivative(FitData derivative) {
		this.derivative = derivative;
	}

	public ScanData sublist(int from, int to) {
		return new ScanData(this.raw.sublist(from, to), this.derivative.sublist(from, to));
	}

	public void derivate() {
		this.derivative.setData(this.raw.derivate());
	}

	public void fit() {
		this.raw.fit();
		this.derivative.fit();
	}

}
