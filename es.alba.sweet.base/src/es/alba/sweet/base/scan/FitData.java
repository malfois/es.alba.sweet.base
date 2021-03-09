package es.alba.sweet.base.scan;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FitData {

	private XyData	data	= new XyData();

	private XyData	fit		= new XyData();

	public FitData() {
	}

	public FitData(XyData data, XyData fit) {
		this.data = data;
		this.fit = fit;
	}

	public void addPoint(Double x, Double y) {
		this.data.addPoint(x, y);
	}

	public XyData getData() {
		return data;
	}

	public void setData(XyData data) {
		this.data = data;
	}

	public XyData getFit() {
		return fit;
	}

	public void setFit(XyData fit) {
		this.fit = fit;
	}

	@JsonIgnore
	public DataPoint getLastDataPoint() {
		if (data.getX().isEmpty()) return null;

		int lastIndex = data.getX().size() - 1;
		return new DataPoint(data.getX().get(lastIndex), data.getY().get(lastIndex));
	}

	public FitData sublist(int from, int to) {
		return new FitData(this.data.subList(from, to), this.fit.subList(from, to));
	}

	public XyData derivate() {
		return this.data.derivate();
	}

	public void fit() {
		int nPoints = this.data.getX().size();
		if (nPoints < 5) return;
		this.fit = this.data.fit();
	}

	@Override
	public String toString() {
		return "FitData [data=" + data.getX().size() + ", fit=" + fit.getX().size() + "]";
	}

}
