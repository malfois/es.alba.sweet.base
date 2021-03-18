package es.alba.sweet.base.communication.command;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.special.Erf;

import es.alba.sweet.base.scan.DataPoint;
import es.alba.sweet.base.scan.XyData;

public class FunctionSimulationArgument extends CommandArgument {

	private String	function;
	private Double	xMin;
	private Double	xMax;
	private Double	position;
	private Double	fwhm;
	private Double	height;
	private Double	offset;

	private XyData	data	= new XyData();

	public FunctionSimulationArgument() {
	}

	public FunctionSimulationArgument(String json) throws JsonException {
		FunctionSimulationArgument parameter = super.toObject(json, this.getClass());
		this.function = parameter.function;
		this.xMin = parameter.xMin;
		this.xMax = parameter.xMax;
		this.position = parameter.position;
		this.fwhm = parameter.fwhm;
		this.height = parameter.height;
		this.offset = parameter.offset;
	}

	public DataPoint getValue(int i) {
		return new DataPoint(data.getX().get(i), data.getY().get(i));
	}

	public void calculate(int numberOfPoints) {
		data.setX(this.calculateX(numberOfPoints));
		data.setY(this.calculateY(data.getX()));
	}

	private List<Double> calculateX(int numberOfPoints) {
		List<Double> x = new ArrayList<>();
		double step = (xMax - xMin) / (numberOfPoints - 1);
		for (int i = 0; i < numberOfPoints; i++) {
			x.add(xMin + (i * step));
		}
		return x;
	}

	public List<Double> calculateY(List<Double> x) {
		if (this.function.equals("Gaussian")) {
			return calculateGaussian(x);
		}
		return calculateErrorFunction(x);
	}

	private List<Double> calculateGaussian(List<Double> x) {
		double CONST = Math.sqrt(4. * Math.log(2.));

		double sigma = this.fwhm / CONST;

		Double[] xArray = x.toArray(new Double[0]);
		int n = xArray.length;
		Double[] buffer = new Double[n];
		for (int i = 0; i < n; i++) {
			double arg = this.offset;
			if (sigma != 0) arg = (xArray[i] - this.position) / sigma;

			buffer[i] = offset + this.height * Math.exp(-arg * arg);
		}
		return List.of(buffer);

	}

	private List<Double> calculateErrorFunction(List<Double> x) {
		double CONST = Math.sqrt(4. * Math.log(2.));
		double sigma = this.fwhm / CONST;

		List<Double> y = new ArrayList<>();
		for (int i = 0; i < x.size(); i++) {
			Double xx = (x.get(i) - this.position) / sigma;
			y.add(this.height * Erf.erf(xx));
		}
		return y;

	}

	@Override
	public String toJson() {
		return super.jsonConverter(this);
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public Double getxMin() {
		return xMin;
	}

	public void setxMin(Double xMin) {
		this.xMin = xMin;
	}

	public Double getxMax() {
		return xMax;
	}

	public void setxMax(Double xMax) {
		this.xMax = xMax;
	}

	public Double getPosition() {
		return position;
	}

	public void setPosition(Double position) {
		this.position = position;
	}

	public Double getFwhm() {
		return fwhm;
	}

	public void setFwhm(Double fwhm) {
		this.fwhm = fwhm;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getOffset() {
		return offset;
	}

	public void setOffset(Double offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "function=" + function + ", xMin=" + xMin + ", xMax=" + xMax + ", position=" + position + ", fwhm=" + fwhm + ", height=" + height + ", offset=" + offset;
	}

}
