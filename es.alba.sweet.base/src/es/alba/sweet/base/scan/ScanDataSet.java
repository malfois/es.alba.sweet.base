package es.alba.sweet.base.scan;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.alba.sweet.base.communication.command.CommandArgument;
import es.alba.sweet.base.communication.command.JsonException;

public class ScanDataSet extends CommandArgument {

	private Map<String, ScanData>	scanDataset;

	private String					text;

	public ScanDataSet() {
	}

	public ScanDataSet(Map<String, ScanData> scanDataset) {
		this.scanDataset = scanDataset;
	}

	public ScanDataSet(String json) throws JsonException {
		ScanDataSet scanDataset = super.toObject(json, this.getClass());
		this.scanDataset = scanDataset.scanDataset;
		this.text = scanDataset.text;
	}

	public ScanDataSet(List<String> diagnostics) {
		scanDataset = new LinkedHashMap<>(diagnostics.size());
		for (String diagnostic : diagnostics) {
			scanDataset.put(diagnostic, new ScanData());
		}
	}

	public void addPoint(Map<String, DataPoint> dataPoints) {
		for (String dataPoint : dataPoints.keySet()) {
			DataPoint point = dataPoints.get(dataPoint);
			scanDataset.get(dataPoint).addPoint(point.getX(), point.getY());
		}

	}

	@JsonIgnore
	public Map<String, XyData> getScanXyData() {
		Map<String, XyData> map = new HashMap<>();
		for (String data : scanDataset.keySet()) {
			FitData d = scanDataset.get(data).getRaw();
			map.put(data, d.getData());
		}
		return map;
	}

	@JsonIgnore
	public Map<String, XyData> getScanFitXyData() {
		Map<String, XyData> map = new HashMap<>();
		for (String data : scanDataset.keySet()) {
			FitData d = scanDataset.get(data).getRaw();
			String name = data + " Fit";
			map.put(name, d.getFit());
		}
		return map;
	}

	@JsonIgnore
	public Map<String, XyData> getDerivativeData() {
		Map<String, XyData> map = new HashMap<>();
		for (String data : scanDataset.keySet()) {
			FitData d = scanDataset.get(data).getDerivative();
			map.put(data, d.getData());
		}
		return map;
	}

	@JsonIgnore
	public Map<String, XyData> getFitDerivativeData() {
		Map<String, XyData> map = new HashMap<>();
		for (String data : scanDataset.keySet()) {
			FitData d = scanDataset.get(data).getDerivative();
			String name = data + " Fit";
			map.put(name, d.getFit());
		}
		return map;
	}

	public ScanDataSet sublist(List<String> yAxis, int from, int to) {
		Map<String, ScanData> map = new HashMap<>();
		for (String dataKey : scanDataset.keySet()) {
			if (yAxis.contains(dataKey)) map.put(dataKey, scanDataset.get(dataKey).sublist(from, to));
		}
		return new ScanDataSet(map);
	}

	public void derivate() {
		for (String dataKey : scanDataset.keySet()) {
			scanDataset.get(dataKey).derivate();
		}
	}

	public void fit() {
		for (String dataKey : scanDataset.keySet()) {
			scanDataset.get(dataKey).fit();
		}
	}

	@JsonIgnore
	public List<ScanData> getScanData(List<String> yAxis) {
		return scanDataset.keySet().stream().filter(p -> yAxis.contains(p)).map(m -> scanDataset.get(m)).collect(Collectors.toList());
	}

	@Override
	public String toJson() {
		return super.jsonConverter(this);
	}

	public Map<String, ScanData> getScanDataset() {
		return scanDataset;
	}

	public void setScanDataset(Map<String, ScanData> scanDataset) {
		this.scanDataset = scanDataset;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
