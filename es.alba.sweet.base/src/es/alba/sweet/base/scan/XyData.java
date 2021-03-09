package es.alba.sweet.base.scan;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.alba.sweet.base.maths.Fitter;
import es.alba.sweet.base.maths.Gaussian;

public class XyData {

	private List<Double>	x	= new ArrayList<>();

	private List<Double>	y	= new ArrayList<>();

	public XyData() {
	}

	public XyData(List<Double> x, List<Double> y) {
		this.x = x;
		this.y = y;
	}

	public void addPoint(Double x, Double y) {
		this.x.add(x);
		this.y.add(y);
	}

	public List<Double> getX() {
		return x;
	}

	public void setX(List<Double> x) {
		this.x = x;
	}

	public List<Double> getY() {
		return y;
	}

	public void setY(List<Double> y) {
		this.y = y;
	}

	@JsonIgnore
	public DataPoint getLastDataPoint() {
		if (this.x.isEmpty()) return null;

		int lastIndex = this.x.size() - 1;
		return new DataPoint(this.x.get(lastIndex), this.y.get(lastIndex));
	}

	public XyData subList(int from, int to) {
		List<Double> subX = x.isEmpty() ? new ArrayList<>() : x.subList(from, to);
		List<Double> subY = y.isEmpty() ? new ArrayList<>() : y.subList(from, to);
		return new XyData(subX, subY);
	}

	public XyData fit() {
		Gaussian function = (Gaussian) Fitter.GaussianFit(this);
		return function.calculateValues(this.x);
	}

	public XyData derivate() {
		List<Double> result = new ArrayList<>();
		int n = 1;
		for (int i = 0, imax = x.size(); i < imax; i++) {
			double LeftValue = selectedMean(y, i - n, i - 1);
			double RightValue = selectedMean(y, i + 1, i + n);
			double LeftPosition = selectedMean(x, i - n, i - 1);
			double RightPosition = selectedMean(x, i + 1, i + n);

			// now the values and positions are calculated, the derivative
			// can be
			// calculated.
			result.add(((RightValue - LeftValue) / (RightPosition - LeftPosition)));
		}
		return new XyData(this.x, result);
	}

	private double selectedMean(List<Double> data, int min, int max) {

		double result = 0.0;
		for (int i = min, imax = data.size(); i <= max; i++) {
			// clip i appropriately, imagine that effectively the two ends
			// continue
			// straight out.
			int pos = i;
			if (pos < 0) {
				pos = 0;
			} else if (pos >= imax) {
				pos = imax - 1;
			}
			result += data.get(pos);
		}

		// now the sum is complete, average the values.
		result /= (max - min) + 1;
		return result;
	}

	public Double residual(XyData data) {
		double sum = 0;
		double comp = 0;

		int nPoints = data.getX().size() < this.x.size() ? data.getX().size() : this.x.size();
		List<Double> yData = data.getY();
		for (int i = 0; i < nPoints; i++) {
			final double diff = yData.get(i) - this.y.get(i);
			if (Double.isNaN(diff)) continue;
			final double err = diff * diff - comp;
			final double temp = sum + err;
			comp = (temp - sum) - err;
			sum = temp;

		}
		return sum;
	}
}
